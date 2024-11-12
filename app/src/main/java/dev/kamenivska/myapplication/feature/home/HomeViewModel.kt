package dev.kamenivska.myapplication.feature.home

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.domain.trainings.GetClosestTrainingUseCase
import dev.kamenivska.myapplication.ui.elements.calendarDays
import dev.kamenivska.myapplication.main.utils.model.CalendarDay
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

class HomeViewModel(
    getClosestTrainingUseCase: GetClosestTrainingUseCase,
): BaseViewModel() {

    private val _calendarDaysList: MutableStateFlow<List<CalendarDayUi>> =
        MutableStateFlow(calendarDays)
    val calendarDaysList = _calendarDaysList.asStateFlow()

    private val _currentMonthAndYear = MutableStateFlow("")
    val currentMonthAndYear = _currentMonthAndYear.asStateFlow()

    private val _closestTraining = MutableStateFlow<Training?>(null)
    val closestTraining = _closestTraining.asStateFlow()

    private val currentDate: LocalDate = LocalDate.now()
    private var workingDate: LocalDate = currentDate

    init {
        _calendarDaysList.value = getMonthData(date = workingDate).map { it.toUiModel() }
        _currentMonthAndYear.value = getMonthAndYear(date = workingDate)

        viewModelScope.launch(Dispatchers.IO) {
            _closestTraining.value = getClosestTrainingUseCase()
        }
    }


    fun onPreviousClick() {
        workingDate = workingDate.minusMonths(1L)
        _calendarDaysList.value = getMonthData(date = workingDate).map { it.toUiModel() }
        _currentMonthAndYear.value = getMonthAndYear(date = workingDate)
    }

    fun onNextClick() {
        workingDate = workingDate.plusMonths(1L)
        _calendarDaysList.value = getMonthData(date = workingDate).map { it.toUiModel() }
        _currentMonthAndYear.value = getMonthAndYear(date = workingDate)
    }

}