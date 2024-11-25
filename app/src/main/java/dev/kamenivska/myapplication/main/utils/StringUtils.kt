package dev.kamenivska.myapplication.main.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.applyPhoneMask() = this.replace(
    "(\\d{2})(\\d{3})(\\d{3})(\\d{2})(\\d{2})".toRegex(),
    "($1) $2 $3 $4 $5"
)

fun String.applyCreditCardMask() = this.replace(
    "(.{4})".toRegex(),
    "$1 "
)

fun String.getLocalDate(
    pattern: String = "dd MMMM yyyy",
): LocalDate {
    val monthAndYearFormatter = DateTimeFormatter.ofPattern(pattern).withLocale(Locale.ENGLISH)
    return LocalDate.parse(this, monthAndYearFormatter)
}
