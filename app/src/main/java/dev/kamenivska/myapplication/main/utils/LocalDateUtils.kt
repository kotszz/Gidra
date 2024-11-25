package dev.kamenivska.myapplication.main.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDate.getDayMonthYear(
    pattern: String = "dd/MM/yy",
): String {
    val monthAndYearFormatter = DateTimeFormatter.ofPattern(pattern).withLocale(Locale.ENGLISH)
    return format(monthAndYearFormatter)
}
