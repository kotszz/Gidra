package dev.kamenivska.myapplication.feature.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.kamenivska.myapplication.GidraSnackbar
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.SnackbarCustomVisuals
import dev.kamenivska.myapplication.feature.calendar.composable.AddTrainingDialog
import dev.kamenivska.myapplication.feature.calendar.composable.CalendarMonthAndYearView
import dev.kamenivska.myapplication.feature.calendar.composable.CalendarView
import dev.kamenivska.myapplication.feature.calendar.model.TrainingCalendarDay
import dev.kamenivska.myapplication.ui.elements.FullScreenLoader
import dev.kamenivska.myapplication.ui.elements.GidraButton
import dev.kamenivska.myapplication.ui.theme.DialogBackground
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    val viewModel: CalendarViewModel = koinViewModel()

    val calendarDaysWithTraining by viewModel.calendarDaysWithTraining.collectAsStateWithLifecycle()
    var calendarDaysWithTrainingUi: List<List<TrainingCalendarDay>> by remember {
        mutableStateOf(emptyList())
    }
    val selectedMonthDays by viewModel.calendarDays.collectAsStateWithLifecycle()
    val monthAndYear by viewModel.currentMonthAndYear.collectAsStateWithLifecycle()
    val trainingType by viewModel.currentTrainingType.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { calendarDaysWithTrainingUi.size }
    )
    val pagerUserScrollEnabled = remember {
        mutableStateOf(true)
    }

    val pagerOffsetFractionChanged by remember {
        derivedStateOf {
            pagerState.currentPageOffsetFraction == 0f
        }
    }

    LaunchedEffect(calendarDaysWithTraining) {
        if (calendarDaysWithTrainingUi.isEmpty() && calendarDaysWithTraining.isNotEmpty()) {
            calendarDaysWithTrainingUi = calendarDaysWithTraining
        } else {
            delay(200)
            calendarDaysWithTrainingUi = calendarDaysWithTraining
            pagerState.scrollToPage(1)
            pagerUserScrollEnabled.value = true
        }
    }

    LaunchedEffect(pagerOffsetFractionChanged) {
        if (pagerOffsetFractionChanged) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                if (page != 1) {
                    pagerUserScrollEnabled.value = false
                    if (page > 1) {
                        viewModel.onNextClick()
                    }
                    if (page < 1) {
                        viewModel.onPreviousClick()
                    }
                }
            }
        }
    }

    val addTrainingDialogState = remember {
        mutableStateOf(false)
    }

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.snackbarMessage.collectLatest {
            snackbarHostState.showSnackbar(it.visuals)
        }
    }

    Box(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Box(modifier = Modifier
            .heightIn(min = screenHeight)
            .fillMaxWidth()
            .background(
                color = DialogBackground,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 24.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    CalendarMonthAndYearView(
                        monthAndYear = monthAndYear,
                        onNextClick = viewModel::onNextClick,
                        onPreviousClick = viewModel::onPreviousClick,
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        modifier = Modifier.clickable(onClick = navigateBack),
                        painter = painterResource(R.drawable.ic_cross),
                        contentDescription = null,
                    )
                }

                HorizontalPager(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    state = pagerState,
                    verticalAlignment = Alignment.Top,
                    userScrollEnabled = pagerUserScrollEnabled.value,
                    pageSpacing = 48.dp,
                    beyondViewportPageCount = 0
                ) { page ->
                    if (calendarDaysWithTrainingUi.isNotEmpty()) {
                        CalendarView(
                            modifier = Modifier
                                .padding(top = 24.dp)
                                .heightIn(max = screenHeight),
                            calendarDays = calendarDaysWithTrainingUi[page],
                            openAddTrainingDialog = { addTrainingDialogState.value = true }
                        )
                    }
                }

                GidraButton(
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    text = "Add training"
                ) {
                    addTrainingDialogState.value = true
                }
            }

            if (loading) FullScreenLoader(modifier = Modifier.fillMaxSize())
        }
    }

    AddTrainingDialog(
        openDialogState = addTrainingDialogState,
        onAddClick = {
            viewModel.addTraining(
                inserted = {
                    addTrainingDialogState.value = false
                }
            )
        },
        calendarDays = selectedMonthDays.getOrNull(2) ?: emptyList(),
        selectedTrainingType = trainingType,
        onDateClick = viewModel::updateCurrentMonthDaysList,
        onTrainingClick = viewModel::updateCurrentTrainingType,
        onRepeatEveryWeekClick = viewModel::updateIsRepeatEveryWeek
    )

    SnackbarHost(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 12.dp),
        hostState = snackbarHostState
    ) { snackbarData: SnackbarData ->
        val visuals = snackbarData.visuals as SnackbarCustomVisuals
        GidraSnackbar(
            visuals = visuals,
            contentAlignment = Alignment.CenterVertically,
        )
    }
}