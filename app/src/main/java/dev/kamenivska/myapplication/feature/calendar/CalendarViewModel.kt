package dev.kamenivska.myapplication.feature.calendar

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.SnackbarMessage
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.data.room.model.TrainingType
import dev.kamenivska.myapplication.domain.trainings.InsertTrainingUseCase
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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth


class CalendarViewModel(
    private val trainingsByDateFlowUseCase: TrainingsByDateFlowUseCase,
    private val insertTrainingUseCase: InsertTrainingUseCase,
): BaseViewModel() {

    private val _calendarDaysWithTraining: MutableStateFlow<List<List<TrainingCalendarDay>>> =
        MutableStateFlow(emptyList())
    val calendarDaysWithTraining = _calendarDaysWithTraining.asStateFlow()

    private val _currentMonthAndYear = MutableStateFlow("")
    val currentMonthAndYear = _currentMonthAndYear.asStateFlow()

    private val _calendarDays = MutableStateFlow<List<List<CalendarDay>>>(emptyList())
    val calendarDays = _calendarDays.asStateFlow()

    private val _currentTrainingType = MutableStateFlow<TrainingType?>(null)
    val currentTrainingType = _currentTrainingType.asStateFlow()

    private val isRepeatEveryWeek = MutableStateFlow(false)

    private val currentSelectedDate = MutableStateFlow<CalendarDay?>(null)

    private var date: LocalDate = LocalDate.now()

    private var collectTrainingsJob: Job? = null

    init {
        viewModelScope.launch {
            updateCalendarData()
        }
    }

    private fun updateCalendarData() {
        viewModelScope.launch {
            collectTrainingsJob?.cancelAndJoin()

            _calendarDays.value = listOf(
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
                        _calendarDays.value.map { list ->
                            list.map { day ->
                                TrainingCalendarDay(
                                    calendarDay = day.toUiModel(),
                                    trainings = trainings.filter { training ->
                                        training.date == day.date ||
                                                training.repeatEveryWeek && training.dayOfWeek == day.date.dayOfWeek.ordinal
                                    }
                                )
                            }
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


    fun updateCurrentMonthDaysList(
        selectedId: Int,
    ) {

        currentSelectedDate.value = calendarDays.value[2].find {
            it.id == selectedId
        }

        _calendarDays.update { list ->
            list.map { innerList ->
                innerList.map {
                    it.copy(isSelected = it.id == selectedId)
                }
            }
        }
    }

    fun updateCurrentTrainingType(
        selectedType: TrainingType,
    ) {
        _currentTrainingType.value = selectedType
    }

    fun updateIsRepeatEveryWeek(
        repeat: Boolean,
    ) {
        isRepeatEveryWeek.value = repeat
    }

    fun addTraining(inserted: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            currentSelectedDate.value?.date?.let { date ->
                currentTrainingType.value?.let { trainingType ->
                    insertTrainingUseCase(
                        Training(
                            date = date,
                            dayOfWeek = date.dayOfWeek.ordinal,
                            type = trainingType,
                            repeatEveryWeek = isRepeatEveryWeek.value,
                        )
                    ).let {
                        if (it != -1L) {
                            _snackbarMessage.emit(SnackbarMessage.Success("Training has been added"))
                        } else {
                            _snackbarMessage.emit(SnackbarMessage.Error("Training has not been added"))
                        }
                    }
                    inserted()
                } ?: run { _snackbarMessage.emit(SnackbarMessage.Error("No training type selected!")) }
            } ?: run { _snackbarMessage.emit(SnackbarMessage.Error("No date selected!")) }
        }
    }
}

val timeslots = listOf(
    "08:00",
    "09:00",
    "11:00",
    "11:00",
    "12:00",
    "13:00",
    "14:00",
    "15:00",
    "16:00",
    "17:00",
    "18:00",
    "19:00",
    "20:00",
)