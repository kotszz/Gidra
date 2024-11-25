package dev.kamenivska.myapplication.main.utils.model

import java.time.LocalDate

data class CalendarDay(
    val id: Int,
    val date: LocalDate,
    val dayOfTheWeek: String? = null,
    val isCurrent: Boolean = false,
    val isSelected: Boolean = false,
)
