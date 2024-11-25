package dev.kamenivska.myapplication.feature.train.breathe.pranayama

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.data.room.model.TrainingType
import dev.kamenivska.myapplication.domain.trainings.InsertTrainingUseCase
import dev.kamenivska.myapplication.main.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class PranayamaViewModel(
    private val insertTrainingUseCase: InsertTrainingUseCase
): BaseViewModel() {

    private val _isInProgress = MutableStateFlow(false)
    val isInProgress = _isInProgress.asStateFlow()

    private val _currentProgressState = MutableStateFlow<String?>(null)
    val currentProgressState = _currentProgressState.asStateFlow()

    private val _onFinish = MutableSharedFlow<Unit>()
    val onFinish = _onFinish.asSharedFlow()

    private var pranayamaJob: Job? = null

    init {
        viewModelScope.launch {
            isInProgress.collectLatest {
                if (!it) {
                    pranayamaJob?.cancel()
                } else {
                    pranayamaJob = launch {
                        while (isInProgress.value) {
                            _currentProgressState.emit("INHALE")
                            delay(4000)

                            _currentProgressState.emit("HOLD")
                            delay(8000)

                            _currentProgressState.emit("EXHALE")
                            delay(8000)

                            _currentProgressState.emit("HOLD")
                            delay(4000)

                            val date = LocalDate.now()
                            withContext(Dispatchers.IO) {
                                insertTrainingUseCase(
                                    Training(
                                        date = date,
                                        dayOfWeek = date.dayOfWeek.ordinal,
                                        type = TrainingType.STA,
                                        repeatEveryWeek = false
                                    )
                                )
                            }
                            _onFinish.emit(Unit)
                        }
                    }
                }
            }
        }
    }

    fun onStartClick() {
        _isInProgress.value = !isInProgress.value
    }

}