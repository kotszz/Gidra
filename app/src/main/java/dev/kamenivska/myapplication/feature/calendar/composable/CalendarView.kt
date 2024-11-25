package dev.kamenivska.myapplication.feature.calendar.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.data.room.model.TrainingType
import dev.kamenivska.myapplication.feature.calendar.model.TrainingCalendarDay
import dev.kamenivska.myapplication.ui.elements.calendarDays
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextGrey
import java.time.LocalDate

@Composable
fun CalendarView(
    modifier: Modifier = Modifier,
    calendarDays: Pair<Int, List<TrainingCalendarDay>>,
    onDayClick: (LocalDate) -> Unit,
    onDayWithoutTrainingClick: (String) -> Unit,
) {

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(7),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),

    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
             Row {
                 Box(
                     modifier = Modifier.weight(1f),
                     contentAlignment = Alignment.Center,
                 ) {
                     Text(
                         text = "Mon",
                         fontFamily = Poppins,
                         fontWeight = FontWeight.Medium,
                         fontSize = 13.sp,
                         lineHeight = 20.sp,
                         color = TextGrey,
                     )
                 }

                 Box(
                     modifier = Modifier.weight(1f),
                     contentAlignment = Alignment.Center,
                 ) {
                     Text(
                         text = "Tue",
                         fontFamily = Poppins,
                         fontWeight = FontWeight.Medium,
                         fontSize = 13.sp,
                         lineHeight = 20.sp,
                         color = TextGrey,
                     )
                 }

                 Box(
                     modifier = Modifier.weight(1f),
                     contentAlignment = Alignment.Center,
                 ) {
                     Text(
                         text = "Wed",
                         fontFamily = Poppins,
                         fontWeight = FontWeight.Medium,
                         fontSize = 13.sp,
                         lineHeight = 20.sp,
                         color = TextGrey,
                     )
                 }

                 Box(
                     modifier = Modifier.weight(1f),
                     contentAlignment = Alignment.Center,
                 ) {
                     Text(
                         text = "Thu",
                         fontFamily = Poppins,
                         fontWeight = FontWeight.Medium,
                         fontSize = 13.sp,
                         lineHeight = 20.sp,
                         color = TextGrey,
                     )
                 }

                 Box(
                     modifier = Modifier.weight(1f),
                     contentAlignment = Alignment.Center,
                 ) {
                     Text(
                         text = "Fri",
                         fontFamily = Poppins,
                         fontWeight = FontWeight.Medium,
                         fontSize = 13.sp,
                         lineHeight = 20.sp,
                         color = TextGrey,
                     )
                 }

                 Box(
                     modifier = Modifier.weight(1f),
                     contentAlignment = Alignment.Center,
                 ) {
                     Text(
                         text = "Sat",
                         fontFamily = Poppins,
                         fontWeight = FontWeight.Medium,
                         fontSize = 13.sp,
                         lineHeight = 20.sp,
                         color = TextGrey,
                     )
                 }

                 Box(
                     modifier = Modifier.weight(1f),
                     contentAlignment = Alignment.Center,
                 ) {
                     Text(
                         text = "Sun",
                         fontFamily = Poppins,
                         fontWeight = FontWeight.Medium,
                         fontSize = 13.sp,
                         lineHeight = 20.sp,
                         color = TextGrey,
                     )
                 }
             }
        }

        items(calendarDays.first) { }

        items(
            items = calendarDays.second,
            key = { calendarDay ->
                calendarDay.calendarDay.id
            }
        ) { day ->
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .then(if (day.trainings.isNotEmpty()) {
                        Modifier.border(width = 1.dp, color = PrimaryColor, shape = RoundedCornerShape(8.dp))
                    } else {
                        Modifier
                    }
                ).clickable {
                    day.trainings.getOrNull(0)?.date?.apply {
                        onDayClick(this)
                    } ?: onDayWithoutTrainingClick(day.calendarDay.date ?: "1")
                },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = day.calendarDay.date ?: "00",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    color = TextGrey,
                )

                Spacer(modifier = Modifier.size(8.dp))

                if (day.trainings.getOrNull(1) != null) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = when(day.trainings[1]!!.type) {
                            TrainingType.DYN -> painterResource(R.drawable.ic_crab)
                            TrainingType.DYNB -> painterResource(R.drawable.ic_flippers)
                            TrainingType.COACHING -> painterResource(R.drawable.ic_coaching)
                            TrainingType.STA -> painterResource(R.drawable.ic_lungs)
                            TrainingType.DNF -> painterResource(R.drawable.ic_mask)
                        },
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = PrimaryColor),
                    )
                } else {
                    Spacer(modifier = Modifier.size(20.dp))
                }

                Spacer(modifier = Modifier.size(2.dp))

                if (day.trainings.getOrNull(0) != null) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = when(day.trainings[0]!!.type) {
                            TrainingType.DYN -> painterResource(R.drawable.ic_crab)
                            TrainingType.DYNB -> painterResource(R.drawable.ic_flippers)
                            TrainingType.COACHING -> painterResource(R.drawable.ic_coaching)
                            TrainingType.STA -> painterResource(R.drawable.ic_lungs)
                            TrainingType.DNF -> painterResource(R.drawable.ic_mask)
                        },
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = PrimaryColor),
                    )
                } else {
                    Spacer(modifier = Modifier.size(20.dp))
                }

                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}