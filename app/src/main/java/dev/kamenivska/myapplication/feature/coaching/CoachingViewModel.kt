package dev.kamenivska.myapplication.feature.coaching

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.SnackbarMessage
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.data.room.model.TrainingType
import dev.kamenivska.myapplication.domain.trainings.InsertTrainingUseCase
import dev.kamenivska.myapplication.main.BaseViewModel
import dev.kamenivska.myapplication.main.utils.getMonthAndYear
import dev.kamenivska.myapplication.main.utils.getMonthData
import dev.kamenivska.myapplication.main.utils.model.CalendarDayUi
import dev.kamenivska.myapplication.main.utils.model.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale


class CoachingViewModel(
    private val insertTrainingUseCase: InsertTrainingUseCase,
): BaseViewModel() {

    private val _calendarDaysList: MutableStateFlow<List<CalendarDayUi>> =
        MutableStateFlow(emptyList())
    val calendarDaysList = _calendarDaysList.asStateFlow()

    private val _currentMonthDays: MutableStateFlow<List<CalendarDayUi>> =
        MutableStateFlow(emptyList())
    val currentMonthDays = _currentMonthDays.asStateFlow()

    private val _currentMonthAndYear = MutableStateFlow("")
    val currentMonthAndYear = _currentMonthAndYear.asStateFlow()

    private var date: LocalDate = LocalDate.now()

    private lateinit var selectedDate: LocalDate

    init {
        _calendarDaysList.value = getMonthData(date = date).map { it.toUiModel() }
        _currentMonthDays.value = _calendarDaysList.value
        _currentMonthAndYear.value = getMonthAndYear(date = date)
    }

    fun onPreviousClick() {
        date = date.minusMonths(1L)
        _calendarDaysList.value = getMonthData(date = date).map { it.toUiModel() }
        _currentMonthAndYear.value = getMonthAndYear(date = date)
    }

    fun onNextClick() {
        date = date.plusMonths(1L)
        _calendarDaysList.value = getMonthData(date = date).map { it.toUiModel() }
        _currentMonthAndYear.value = getMonthAndYear(date = date)
    }

    fun addTraining() {
        viewModelScope.launch(Dispatchers.IO) {
            insertTrainingUseCase(
                Training(
                    date = selectedDate,
                    type = TrainingType.COACHING,
                    repeatEveryWeek = false
                )
            ).let {
                if (it != -1L) {
                    _snackbarMessage.emit(SnackbarMessage.Success("Training has been added"))
                } else {
                    _snackbarMessage.emit(SnackbarMessage.Error("Training has not been added"))
                }
            }
        }
    }

    fun setDate(selectedDay: Int) {
        val formatter = DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ofPattern("MMMM yyyy"))
            .toFormatter(Locale.ENGLISH)

        selectedDate = YearMonth.parse(currentMonthAndYear.value, formatter).atDay(selectedDay)
    }

}