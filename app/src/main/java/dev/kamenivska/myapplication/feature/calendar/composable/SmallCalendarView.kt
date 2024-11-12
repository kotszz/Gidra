package dev.kamenivska.myapplication.feature.calendar.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.main.utils.dayFormatter
import dev.kamenivska.myapplication.main.utils.model.CalendarDay
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextGrey
import kotlinx.coroutines.launch

@Composable
fun SmallCalendarView(
    modifier: Modifier = Modifier,
    calendarDays: List<CalendarDay>,
    onClick: (Int) -> Unit,
) {

    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        calendarDays.find { it.isCurrent }?.id?.apply {
            if (this > 2) scrollState.scrollToItem(minus(2))
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(24.dp)
                .weight(1F)
                .clickable {
                    scrollState.apply {
                        coroutineScope.launch {
                            if (firstVisibleItemIndex != 0) {
                                animateScrollToItem(firstVisibleItemIndex - 1, 0)
                            }
                        }
                    }
                },
            painter = painterResource(R.drawable.ic_chevron_left),
            contentDescription = null,
        )

        LazyRow(
            state = scrollState,
            userScrollEnabled = false,
            modifier = Modifier
                .weight(6.2F)
        ) {
            itemsIndexed(
                items = calendarDays,
                key = { _, day -> day.id}
            ) { index, item ->
                CalendarDayItemView(
                    modifier = Modifier.fillParentMaxWidth(0.16F),
                    calendarDay = item,
                    onClick = onClick
                )
                if(index != calendarDays.size - 1) {
                    Spacer(modifier = Modifier.fillParentMaxWidth(0.05F))
                }
            }

        }

        Image(
            modifier = Modifier
                .size(24.dp)
                .weight(1F)
                .clickable {
                    scrollState.apply {
                        coroutineScope.launch {
                            if (firstVisibleItemIndex != calendarDays.size - 1 ) {
                                animateScrollToItem(firstVisibleItemIndex + 1, 0)
                            }
                        }
                    }
                },
            painter = painterResource(R.drawable.ic_chevron_right),
            contentDescription = null,
        )
    }
}

@Composable
fun CalendarDayItemView(
    modifier: Modifier = Modifier,
    calendarDay: CalendarDay,
    onClick: (Int) -> Unit,
) {
    Column(
        modifier = modifier.clickable {
            onClick(calendarDay.id)
        }.run {
            background(
                if (calendarDay.isSelected) PrimaryColor
                else Color.Transparent,
                RoundedCornerShape(4.dp)
            )
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp),
            text = calendarDay.dayOfTheWeek ?: "Day",
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 20.sp,
            color = TextGrey,
        )

        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = calendarDay.date.format(dayFormatter),
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 20.sp,
            color = TextGrey,
        )
    }
}