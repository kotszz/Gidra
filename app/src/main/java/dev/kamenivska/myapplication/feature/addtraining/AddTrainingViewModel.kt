package dev.kamenivska.myapplication.feature.addtraining

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.SnackbarMessage
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.data.room.model.TrainingType
import dev.kamenivska.myapplication.domain.trainings.InsertTrainingUseCase
import dev.kamenivska.myapplication.main.BaseViewModel
import dev.kamenivska.myapplication.main.utils.getMonthData
import dev.kamenivska.myapplication.main.utils.model.CalendarDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate


class AddTrainingViewModel(
    private val insertTrainingUseCase: InsertTrainingUseCase,
): BaseViewModel() {

    private val _calendarDays = MutableStateFlow<List<CalendarDay>>(emptyList())
    val calendarDays = _calendarDays.asStateFlow()

    private val _currentTrainingType = MutableStateFlow<TrainingType?>(null)
    val currentTrainingType = _currentTrainingType.asStateFlow()

    private val isRepeatEveryWeek = MutableStateFlow(false)

    private val currentSelectedDate = MutableStateFlow<CalendarDay?>(null)

    private var preselectedId: Long? = null

    fun updateCalendarData(date: LocalDate) {
        viewModelScope.launch {
            _calendarDays.value = getMonthData(date = date)
        }
    }

    fun setPreselectedValues(
        preselectedDate: LocalDate? = null,
        preselectedTrainingType: TrainingType? = null,
        preselectedRepeatEveryWeek: Boolean? = null,
        preselectedId: Long? = null,
    ) {
        viewModelScope.launch {
            preselectedTrainingType?.let(::updateCurrentTrainingType)
            preselectedRepeatEveryWeek?.let(::updateIsRepeatEveryWeek)
            preselectedDate?.let {
                updateCurrentMonthDaysList(
                    selectedId = it.dayOfMonth -1
                )
            }
            this@AddTrainingViewModel.preselectedId = preselectedId
        }
    }

    fun updateCurrentMonthDaysList(
        selectedId: Int,
    ) {

        currentSelectedDate.value = calendarDays.value.find {
            it.id == selectedId
        }

        _calendarDays.update { list ->
            list.map {
                it.copy(isSelected = it.id == selectedId)
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
                            id = preselectedId ?: 0,
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

    fun clear() {
        _calendarDays.value = emptyList()
        _currentTrainingType.value = null
        isRepeatEveryWeek.value = false
        currentSelectedDate.value = null
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