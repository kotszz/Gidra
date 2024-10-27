package dev.kamenivska.myapplication.feature.home

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.feature.home.composable.calendarDays
import dev.kamenivska.myapplication.feature.home.model.CalendarDay
import dev.kamenivska.myapplication.main.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class HomeViewModel: BaseViewModel() {

    private val _calendarDaysList: MutableStateFlow<List<CalendarDay>> =
        MutableStateFlow(calendarDays)
    val calendarDaysList = _calendarDaysList.asStateFlow()

    private val _currentMonthAndYear = MutableStateFlow("")
    val currentMonthAndYear = _currentMonthAndYear.asStateFlow()

    init {
        viewModelScope.launch {
            val currentDate = LocalDate.now()
            val firstDayOfMonth = currentDate.withDayOfMonth(1)
            val totalDaysInMonth = currentDate.lengthOfMonth()

            val dayFormatter = DateTimeFormatter.ofPattern("dd")
            val monthAndYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy").withLocale(Locale.ENGLISH)
            _currentMonthAndYear.emit(currentDate.format(monthAndYearFormatter))

            _calendarDaysList.emit(
                (0 until totalDaysInMonth).map { dayOffset ->
                    val date = firstDayOfMonth.plusDays(dayOffset.toLong())

                    CalendarDay(
                        id = dayOffset,
                        date = date.format(dayFormatter),
                        dayOfTheWeek = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                        isCurrent = date == currentDate
                    )
                }
            )
        }
    }

}