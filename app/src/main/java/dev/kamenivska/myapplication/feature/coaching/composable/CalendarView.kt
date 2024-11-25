package dev.kamenivska.myapplication.feature.coaching.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kamenivska.myapplication.main.utils.model.CalendarDay
import dev.kamenivska.myapplication.main.utils.model.CalendarDayUi
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.TextGrey

@Composable
fun CoachingCalendarView(
    modifier: Modifier = Modifier,
    calendarDays: List<CalendarDayUi>,
    onDaySelected: (Int) -> Unit,
) {

    val offset = remember {
       mutableIntStateOf(0)
    }

    LaunchedEffect(calendarDays.first().dayOfTheWeek) {
        when(calendarDays.first().dayOfTheWeek) {
            "Tue" -> offset.intValue = 1
            "Wed" -> offset.intValue = 2
            "Thu" -> offset.intValue = 3
            "Fri" -> offset.intValue = 4
            "Sat" -> offset.intValue = 5
            "Sun" -> offset.intValue = 6

            else -> offset.intValue = 0
        }
    }

    LazyVerticalGrid(
        modifier = modifier.heightIn(max = 500.dp),
        columns = GridCells.Fixed(7),
        verticalArrangement = Arrangement.spacedBy(20.dp),
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

        items(offset.intValue) { }

        items(
            items = calendarDays,
            key = { calendarDay ->
                calendarDay.id
            }
        ) { day ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            onDaySelected(day.date?.toInt() ?: 1)
                        }
                        .padding(8.dp),
                    text = day.date.toString(),
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    color = TextGrey,
                )
            }
        }
    }
}