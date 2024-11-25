package dev.kamenivska.myapplication.feature.training

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.SnackbarMessage
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.domain.trainings.DeleteTrainingUseCase
import dev.kamenivska.myapplication.domain.trainings.TrainingsByDateFlowUseCase
import dev.kamenivska.myapplication.main.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate

class TrainingViewModel(
    private val getTrainingsByDateFlowUseCase: TrainingsByDateFlowUseCase,
    private val deleteTrainingUseCase: DeleteTrainingUseCase,
): BaseViewModel() {

    private val _trainings = MutableStateFlow(emptyList<Training>())
    val trainings = _trainings.asStateFlow()

    private val _trainingDeleted = MutableSharedFlow<SnackbarMessage>()
    val trainingDeleted = _trainingDeleted.asSharedFlow()

    fun collectCurrentDateTrainings(date: LocalDate?) {
        viewModelScope.launch {
            date?.let {
                getTrainingsByDateFlowUseCase(date, date).collectLatest { trainings ->
                    _trainings.value = trainings.filter {
                        it.dayOfWeek == date.dayOfWeek.ordinal
                    }
                }
            }
        }
    }

    fun cancelTraining(training: Training) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTrainingUseCase(training).let {
                if (it == 0) _snackbarMessage.emit(SnackbarMessage.Error("Cannot cancel training"))
                else _trainingDeleted.emit(SnackbarMessage.Success("Training successfully canceled"))
            }
        }
    }

}