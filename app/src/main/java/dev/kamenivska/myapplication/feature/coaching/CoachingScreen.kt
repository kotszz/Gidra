package dev.kamenivska.myapplication.feature.coaching

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.kamenivska.myapplication.GidraSnackbar
import dev.kamenivska.myapplication.SnackbarCustomVisuals
import dev.kamenivska.myapplication.feature.coaching.composable.CoachListView
import dev.kamenivska.myapplication.feature.coaching.composable.CoachingCalendarView
import dev.kamenivska.myapplication.feature.coaching.composable.MonthAndYearView
import dev.kamenivska.myapplication.feature.coaching.composable.SelectCoachDialog
import dev.kamenivska.myapplication.feature.coaching.composable.SelectDateDialog
import dev.kamenivska.myapplication.ui.elements.CalendarView
import dev.kamenivska.myapplication.ui.theme.DarkGrey
import dev.kamenivska.myapplication.ui.theme.DialogBackground
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextGrey
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoachingScreen(
    modifier: Modifier = Modifier
) {

    val viewModel: CoachingViewModel = koinViewModel()
    val calendarDays by viewModel.calendarDaysList.collectAsStateWithLifecycle()
    val monthAndYear by viewModel.currentMonthAndYear.collectAsStateWithLifecycle()
    val currentMonthDays by viewModel.currentMonthDays.collectAsStateWithLifecycle()

    val byCoachSelected = remember {
        mutableStateOf(true)
    }

    val byDateSelected = remember {
        mutableStateOf(false)
    }

    val selectDateDialogState = remember {
        mutableStateOf(false)
    }

    val selectCoachDialogState = remember {
        mutableStateOf(false)
    }

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.snackbarMessage.collectLatest {
            snackbarHostState.showSnackbar(it.visuals)
        }
    }

    Column (modifier = modifier
        .fillMaxSize()
        .padding(top = 32.dp, start = 32.dp, end = 32.dp)
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = DialogBackground,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "Scheduled",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = TextGrey,
                )

                Spacer(modifier = Modifier.weight(1F))

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "View all",
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = PrimaryColor,
                )
            }

            CalendarView(
                modifier = Modifier.padding(bottom = 24.dp, start = 16.dp, end = 16.dp),
                calendarDays = currentMonthDays
            )
        }

        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = "Book a session",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            lineHeight = 40.sp,
            color = PrimaryColor,
        )

        Row(
            modifier = Modifier.padding(top = 16.dp),
        ) {
            Box(modifier = modifier
                .clip(RoundedCornerShape(40.dp))
                .background(color = if(byCoachSelected.value) PrimaryColor else DarkGrey)
                .clickable {
                    if (!byCoachSelected.value) {
                        byCoachSelected.value = true
                        byDateSelected.value = false
                    }
                },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    text = "by Coach",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                    color = TextGrey,
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Box(modifier = modifier
                .clip(RoundedCornerShape(40.dp))
                .background(color = if(byDateSelected.value) PrimaryColor else DarkGrey)
                .clickable{
                    if (!byDateSelected.value) {
                        byDateSelected.value = true
                        byCoachSelected.value = false
                    }
                },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    text = "by Date",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                    color = TextGrey,
                )
            }
        }

        if (byCoachSelected.value) {
            CoachListView(
                bookClicked = {
                    selectDateDialogState.value = true
                }
            )
        } else {
            Column(
                modifier = Modifier
                    .padding(top = 28.dp, bottom = 112.dp)
                    .background(
                        color = DialogBackground,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                MonthAndYearView(
                    modifier = Modifier.padding(top = 32.dp, bottom = 12.dp, start = 20.dp),
                    monthAndYear = monthAndYear,
                    onNextClick = viewModel::onNextClick,
                    onPreviousClick = viewModel::onPreviousClick
                )
                CoachingCalendarView(
                    modifier = Modifier.padding(bottom = 28.dp, start = 20.dp, end = 20.dp),
                    calendarDays = calendarDays,
                    onDaySelected = {
                        viewModel.setDate(it)
                        selectCoachDialogState.value = true
                    }
                )
            }
        }
    }

    SelectDateDialog (
        openDialogState = selectDateDialogState,
        calendarDays = calendarDays,
        onDaySelected = {
            selectDateDialogState.value = false
            viewModel.setDate(it)
            viewModel.addTraining()
        },
        monthAndYear = monthAndYear,
        onNextClick = viewModel::onNextClick,
        onPreviousClick = viewModel::onPreviousClick
    )

    SelectCoachDialog (
        openDialogState = selectCoachDialogState,
        bookClicked = {
            selectCoachDialogState.value = false
            viewModel.addTraining()
        }

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


@Preview(name = "CoachingScreen")
@Composable
private fun PreviewCoachingScreen() {
    CoachingScreen()
}