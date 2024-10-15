package dev.kamenivska.myapplication.feature.testbreath

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.main.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TestBreathViewModel : BaseViewModel() {

    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()

    private val _showSuccessDialog = MutableSharedFlow<Unit>()
    val showSuccessDialog = _showSuccessDialog.asSharedFlow()

    private var timerJob: Job? = null

    fun updateTimerState() = viewModelScope.launch {
        if (timerJob != null) {
            timerJob?.cancel()
            _showSuccessDialog.emit(Unit)
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