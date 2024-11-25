package dev.kamenivska.myapplication.feature.training

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.kamenivska.myapplication.GidraSnackbar
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.SnackbarCustomVisuals
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.feature.addtraining.AddTrainingDialog
import dev.kamenivska.myapplication.main.utils.getDayMonthYear
import dev.kamenivska.myapplication.ui.elements.GidraOutlineResizableButton
import dev.kamenivska.myapplication.ui.elements.GidraResizableButton
import dev.kamenivska.myapplication.ui.theme.DarkGrey
import dev.kamenivska.myapplication.ui.theme.DialogBackground
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextGrey
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun TrainingScreen(
    modifier: Modifier = Modifier,
    date: LocalDate?,
    navigateBack: () -> Unit,
) {

    val viewModel: TrainingViewModel = koinViewModel()

    val trainings by viewModel.trainings.collectAsStateWithLifecycle()

    val trainingToEdit = remember<MutableState<Training?>> {
        mutableStateOf(null)
    }

    val pagerState = rememberPagerState {
        trainings.count()
    }

    LaunchedEffect(Unit) {
        viewModel.collectCurrentDateTrainings(date)
    }

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.snackbarMessage.collectLatest {
            snackbarHostState.showSnackbar(it.visuals)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.trainingDeleted.collectLatest {
            snackbarHostState.showSnackbar(it.visuals)
        }
    }

    val addTrainingDialogState = remember {
        mutableStateOf(false)
    }

    val now = LocalDate.now()

    if (trainings.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = navigateBack),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Looks like there is no trainings anymore",
                textAlign = TextAlign.Center,
                fontFamily = Outfit,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                lineHeight = 38.sp,
                color = TextGrey.copy(alpha = 0.8f),
            )
        }
    } else {
        Box(
            modifier = modifier
                .padding(
                    vertical = 32.dp,
                    horizontal = 20.dp,
                )
                .clip(RoundedCornerShape(16.dp))
        ) {
            VerticalPager(
                state = pagerState,
                pageSize = object : PageSize {
                    override fun Density.calculateMainAxisPageSize(
                        availableSpace: Int,
                        pageSpacing: Int
                    ): Int {
                        return ((availableSpace - 2 * pageSpacing) * 0.92f).toInt()
                    }
                },
            ) { page ->
                TrainingsPagerItem(
                    training = trainings[page],
                    isFirstPage = page == 0,
                    now = now,
                    onEditClick = {
                        trainingToEdit.value = it
                        addTrainingDialogState.value = true
                    },
                    navigateBack = navigateBack,
                    onCancelClick = viewModel::cancelTraining
                )
            }
        }
    }

    // Review
    date?.let {
        if (addTrainingDialogState.value) {
            AddTrainingDialog(
                openDialogState = addTrainingDialogState,
                date = date,
                preselectedDate = date,
                preselectedTrainingType = trainingToEdit.value?.type,
                preselectedRepeatEveryWeek = trainingToEdit.value?.repeatEveryWeek,
                preselectedId = trainingToEdit.value?.id
            )
        }
    }

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

@Composable
fun TrainingsPagerItem(
    training: Training,
    isFirstPage: Boolean,
    now: LocalDate,
    onEditClick: (Training) -> Unit,
    onCancelClick: (Training) -> Unit,
    navigateBack: () -> Unit,
) {
    Column(
        modifier = Modifier.then(
            if (isFirstPage) {
                Modifier
            } else Modifier.background(color = DialogBackground)
        )
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                modifier = Modifier.fillMaxWidth().padding(top = 1.dp),
                painter = painterResource(R.drawable.img_training_pager),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 36.dp,
                        start = 24.dp
                    ),
            ) {
                Text(
                    text = training.type.name,
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    lineHeight = 38.sp,
                    color = DialogBackground.copy(alpha = 0.8f),
                )

                Text(
                    text = training.date.getDayMonthYear(),
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    color = DialogBackground.copy(alpha = 0.8f),
                )
            }

            if(isFirstPage) {
                Image(
                    modifier = Modifier
                        .padding(bottom = 124.dp, end = 16.dp)
                        .clickable(onClick = navigateBack),
                    painter = painterResource(R.drawable.ic_cross),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(DarkGrey),
                )
            }
        }


        Column(modifier = Modifier
            .then(
                if (isFirstPage) {
                    Modifier.background(color = DialogBackground)
                } else Modifier
            )
            .padding(start = 40.dp, end = 24.dp)
            .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.weight(1.1f))

            Text(
                text = "Warm-up:",
                fontFamily = Outfit,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                color = PrimaryColor
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = "2x Holds until 1st contraction",
                fontFamily = Outfit,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                color = TextGrey
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Exercise 1:",
                fontFamily = Outfit,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                color = PrimaryColor
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = "Mini-CO2 table 2:00 + 8x \n" +
                        "Interval Holds: 1:30 (5x) \n" +
                        "Intervals: (1:15, 1:00, 00:45, 00:30)",
                fontFamily = Outfit,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                color = TextGrey
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Exercise 2:",
                fontFamily = Outfit,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                color = PrimaryColor
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = "5x max to 1st Strong Urge to Breathe \n" +
                        "5:00 + 6x HV ",
                fontFamily = Outfit,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                color = TextGrey
            )

            Spacer(modifier = Modifier.weight(1.3f))

            Row {
                GidraResizableButton(
                    modifier = Modifier.weight(1f),
                    text = "Edit",
                    textPadding = PaddingValues(
                        vertical = 12.dp,
                    )
                ) {
                    if (training.date > now) {
                        onEditClick(training)
                    }
                }

                GidraOutlineResizableButton(
                    modifier = Modifier.weight(2f).padding(start = 12.dp),
                    text = "Cancel Training",
                    textPadding = PaddingValues(
                        vertical = 12.dp,
                    )
                ) {
                    onCancelClick(training)
                }
            }

            Spacer(modifier = Modifier.weight(0.8f))
        }
    }
}

