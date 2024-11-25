package dev.kamenivska.myapplication.feature.logbook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.data.room.model.TrainingType
import dev.kamenivska.myapplication.main.utils.getDayMonthYear
import dev.kamenivska.myapplication.main.utils.getLocalDate
import dev.kamenivska.myapplication.ui.theme.DialogBackground
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun LogbookScreen(
    modifier: Modifier = Modifier,
    navigateToTrainingScreen: (LocalDate) -> Unit,
) {

    val viewModel: LogbookViewModel = koinViewModel()

    val trainings by viewModel.trainingsFlow.collectAsStateWithLifecycle()
    val currentDate = LocalDate.now()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(36.dp),
            text = "LOGBOOK",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            lineHeight = 39.sp,
            color = PrimaryColor,
        )

        LazyColumn(
            modifier = Modifier.padding(horizontal = 28.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = trainings.size,
                key = { index ->
                    trainings[index].id
                },
                itemContent = { index ->
                    trainings[index].apply {
                        LogbookItem(
                            date = date.getDayMonthYear(),
                            dayOfWeek = date.dayOfWeek.name.take(3),
                            trainingType = type,
                            isCompleted = date <= currentDate,
                            navigateToTrainingScreen = navigateToTrainingScreen,
                        )
                    }
                    if (index == trainings.size - 1) {
                        Spacer(modifier = Modifier.size(112.dp))
                    }
                }
            )
        }
    }
}

@Composable
@Preview
fun LogbookItem(
    date: String = "01/06/23",
    dayOfWeek: String = "Mon",
    trainingType: TrainingType = TrainingType.DNF,
    isCompleted: Boolean = false,
    navigateToTrainingScreen: (LocalDate) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(DialogBackground)
            .clickable { navigateToTrainingScreen(date.getLocalDate(pattern = "dd/MM/yy")) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .size(20.dp),
                painter = when(trainingType) {
                    TrainingType.DYN -> painterResource(R.drawable.ic_crab)
                    TrainingType.DYNB -> painterResource(R.drawable.ic_flippers)
                    TrainingType.COACHING -> painterResource(R.drawable.ic_coaching)
                    TrainingType.STA -> painterResource(R.drawable.ic_lungs)
                    TrainingType.DNF -> painterResource(R.drawable.ic_mask)
                },
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = PrimaryColor),
            )

            Text(
                text = trainingType.name,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color = PrimaryColor,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row (
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$dayOfWeek $date",
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                lineHeight = 20.sp,
                color = PrimaryColor,
            )

            Image(
                modifier = Modifier.padding(vertical = 36.dp, horizontal = 24.dp),
                painter = if(isCompleted) painterResource(R.drawable.ic_completed)
                else painterResource(R.drawable.ic_uncompleted),
                contentDescription = null,
            )
        }
    }
}
