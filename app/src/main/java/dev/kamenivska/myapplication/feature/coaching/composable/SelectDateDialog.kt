package dev.kamenivska.myapplication.feature.coaching.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dev.kamenivska.myapplication.main.utils.model.CalendarDayUi
import dev.kamenivska.myapplication.ui.theme.DialogBackground

@Composable
fun SelectDateDialog(
    openDialogState: MutableState<Boolean>,
    calendarDays: List<CalendarDayUi>,
    onDaySelected: (Int) -> Unit,

    monthAndYear: String,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
) {
    if (openDialogState.value) {
        Dialog(
            onDismissRequest = {
                openDialogState.value = false
            },
        ) {
            Column(
                modifier = Modifier.background(
                    color = DialogBackground,
                    shape = RoundedCornerShape(16.dp)
                )
            ) {
                MonthAndYearView(
                    modifier = Modifier.padding(top = 32.dp, bottom = 12.dp, start = 20.dp),
                    monthAndYear = monthAndYear,
                    onNextClick = onNextClick,
                    onPreviousClick = onPreviousClick
                )

                CoachingCalendarView(
                    modifier = Modifier.padding(bottom = 28.dp, start = 20.dp, end = 20.dp),
                    calendarDays = calendarDays,
                    onDaySelected = onDaySelected,
                )
            }
        }
    }
}