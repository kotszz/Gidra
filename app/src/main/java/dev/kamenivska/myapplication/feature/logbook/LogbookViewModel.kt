package dev.kamenivska.myapplication.feature.logbook

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.domain.trainings.TrainingsFlowUseCase
import dev.kamenivska.myapplication.main.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LogbookViewModel(
    trainingsFlowUseCase: TrainingsFlowUseCase
): BaseViewModel() {

    private val _trainingsFlow = MutableStateFlow(emptyList<Training>())
    val trainingsFlow = _trainingsFlow.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            trainingsFlowUseCase().collectLatest {
                _trainingsFlow.value = it
            }
        }
    }
}