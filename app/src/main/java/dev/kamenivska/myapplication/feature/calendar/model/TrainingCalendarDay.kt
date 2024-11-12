package dev.kamenivska.myapplication.feature.calendar.model

import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.main.utils.model.CalendarDayUi

data class TrainingCalendarDay(
    val calendarDay: CalendarDayUi,
    val trainings: List<Training?> = emptyList()
)
