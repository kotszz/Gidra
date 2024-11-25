package dev.kamenivska.myapplication.feature.testbreath

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.data.room.model.TrainingType
import dev.kamenivska.myapplication.domain.trainings.InsertTrainingUseCase
import dev.kamenivska.myapplication.domain.user.SetUserBreathHoldUseCase
import dev.kamenivska.myapplication.main.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class TestBreathViewModel(
    private val setUserBreathHoldUseCase: SetUserBreathHoldUseCase,
    private val insertTrainingUseCase: InsertTrainingUseCase,
): BaseViewModel() {

    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()

    private val _showSuccessDialog = MutableSharedFlow<Unit>()
    val showSuccessDialog = _showSuccessDialog.asSharedFlow()

    private var timerJob: Job? = null

    fun updateTimerState() = viewModelScope.launch {
        if (timerJob != null) {
            timerJob?.cancel()
            setUserBreathHoldUseCase(timer.value)
            _showSuccessDialog.emit(Unit)

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
        } else {
            timerJob = launch {
                while (true) {
                    delay(1000)
                    _timer.value++
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}