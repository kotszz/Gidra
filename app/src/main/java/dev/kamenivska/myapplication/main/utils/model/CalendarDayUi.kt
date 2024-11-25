package dev.kamenivska.myapplication.main.utils.model

import dev.kamenivska.myapplication.main.utils.dayFormatter

data class CalendarDayUi(
    val id: Int,
    val date: String? = null,
    val dayOfTheWeek: String? = null,
    val isCurrent: Boolean = false
)

fun CalendarDay.toUiModel(): CalendarDayUi {
    return CalendarDayUi(
        id = id,
        date = date.format(dayFormatter),
        dayOfTheWeek = dayOfTheWeek,
        isCurrent = isCurrent
    )
}