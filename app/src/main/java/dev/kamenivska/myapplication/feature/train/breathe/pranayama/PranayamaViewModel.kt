package dev.kamenivska.myapplication.feature.train.breathe.pranayama

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.main.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PranayamaViewModel: BaseViewModel() {

    private val _isInProgress = MutableStateFlow(false)
    val isInProgress = _isInProgress.asStateFlow()

    private val _currentProgressState = MutableStateFlow<String?>(null)
    val currentProgressState = _currentProgressState.asStateFlow()

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