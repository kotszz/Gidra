package dev.kamenivska.myapplication.feature.calendar

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.domain.trainings.TrainingsByDateFlowUseCase
import dev.kamenivska.myapplication.feature.calendar.model.TrainingCalendarDay
import dev.kamenivska.myapplication.main.BaseViewModel
import dev.kamenivska.myapplication.main.utils.getMonthAndYear
import dev.kamenivska.myapplication.main.utils.getMonthData
import dev.kamenivska.myapplication.main.utils.model.CalendarDay
import dev.kamenivska.myapplication.main.utils.model.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth


class CalendarViewModel(
    private val trainingsByDateFlowUseCase: TrainingsByDateFlowUseCase,
): BaseViewModel() {

    private val _calendarDaysWithTraining: MutableStateFlow<List<Pair<Int, List<TrainingCalendarDay>>>> =
        MutableStateFlow(emptyList())
    val calendarDaysWithTraining = _calendarDaysWithTraining.asStateFlow()

    private val _currentMonthAndYear = MutableStateFlow("")
    val currentMonthAndYear = _currentMonthAndYear.asStateFlow()

    private val calendarDays = MutableStateFlow<List<List<CalendarDay>>>(emptyList())
    private var collectTrainingsJob: Job? = null

    var date: LocalDate = LocalDate.now()

    val preselectedDate = MutableStateFlow<LocalDate?>(null)

    init {
        viewModelScope.launch {
            updateCalendarData()
        }
    }

    private fun updateCalendarData() {
        viewModelScope.launch {
            collectTrainingsJob?.cancelAndJoin()

            calendarDays.value = listOf(
                getMonthData(date = date.minusMonths(1)),
                getMonthData(date = date),
                getMonthData(date = date.plusMonths(1)),
            )

            _currentMonthAndYear.value = getMonthAndYear(date = date)

            collectTrainings()
        }
    }

    private fun collectTrainings() {
        viewModelScope.launch(Dispatchers.IO) {
            val start = YearMonth.from(date.minusMonths(1)).atDay(1)
            val end = YearMonth.from(date.plusMonths(1)).atEndOfMonth()

            collectTrainingsJob = launch {
                trainingsByDateFlowUseCase(start, end).collectLatest { trainings ->
                    _calendarDaysWithTraining.value =
                        calendarDays.value.map { list ->
                            Pair(
                                list.firstOrNull()?.date?.dayOfWeek?.ordinal ?: 0,
                                list.map { day ->
                                    TrainingCalendarDay(
                                        calendarDay = day.toUiModel(),
                                        trainings = trainings.filter { training ->
                                            training.date == day.date ||
                                                    training.repeatEveryWeek && training.dayOfWeek == day.date.dayOfWeek.ordinal
                                        }
                                    )
                                }
                            )
                        }
                }
            }
        }
    }

    fun onPreviousClick() {
        viewModelScope.launch {
            date = date.minusMonths(1L)
            updateCalendarData()
        }
    }


    fun onNextClick() {
        viewModelScope.launch {
            date = date.plusMonths(1L)
            updateCalendarData()
        }
    }
}