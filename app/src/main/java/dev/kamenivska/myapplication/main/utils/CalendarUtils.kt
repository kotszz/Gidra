package dev.kamenivska.myapplication.main.utils

import dev.kamenivska.myapplication.main.utils.model.CalendarDay
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

fun getMonthData(
    date: LocalDate = LocalDate.now()
): List<CalendarDay> {
    val currentDate = LocalDate.now()

    val firstDayOfMonth = date.withDayOfMonth(1)
    val totalDaysInMonth = date.lengthOfMonth()


    return(0 until totalDaysInMonth).map { dayOffset ->
            val dateWithOffset = firstDayOfMonth.plusDays(dayOffset.toLong())

            CalendarDay(
                id = dayOffset,
                date = dateWithOffset,
                dayOfTheWeek = dateWithOffset.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                isCurrent = dateWithOffset == currentDate
            )
        }

}

fun getMonthAndYear(
    date: LocalDate = LocalDate.now()
): String {
    val monthAndYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy").withLocale(Locale.ENGLISH)
    return date.format(monthAndYearFormatter)
}

val dayFormatter = DateTimeFormatter.ofPattern("dd")