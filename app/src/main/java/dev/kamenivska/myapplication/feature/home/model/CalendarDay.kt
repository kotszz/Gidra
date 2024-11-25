package dev.kamenivska.myapplication.feature.home.model

data class CalendarDay(
    val id: Int,
    val date: String? = null,
    val dayOfTheWeek: String? = null,
    val isCurrent: Boolean = false
)
