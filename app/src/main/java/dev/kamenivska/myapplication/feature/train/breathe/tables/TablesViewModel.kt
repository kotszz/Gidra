package dev.kamenivska.myapplication.feature.train.breathe.tables

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.data.room.model.TrainingType
import dev.kamenivska.myapplication.domain.trainings.InsertTrainingUseCase
import dev.kamenivska.myapplication.feature.train.breathe.tables.model.ItemState
import dev.kamenivska.myapplication.feature.train.breathe.tables.model.TableItem
import dev.kamenivska.myapplication.feature.train.breathe.tables.model.tableItemList
import dev.kamenivska.myapplication.main.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class TablesViewModel(
    private val insertTrainingUseCase: InsertTrainingUseCase
): BaseViewModel() {

    private val _isInProgress = MutableStateFlow(false)
    val isInProgress = _isInProgress.asStateFlow()

    private val _currentList = MutableStateFlow<List<TableItem>>(emptyList())
    val currentList = _currentList.asStateFlow()

    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()

    private val _onFinish = MutableSharedFlow<Unit>()
    val onFinish = _onFinish.asSharedFlow()

    private var tablesJob: Job? = null

    init {
        _currentList.value = tableItemList

        viewModelScope.launch {
            isInProgress.collectLatest {
                if (!it) {
                    tablesJob?.cancel()
                } else {
                    tablesJob = launch {
                        _currentList.value = tableItemList

                        tableItemList.forEach { tableItem ->
                            _currentList.value = _currentList.value.map { tableItemInStateFlow ->
                                tableItemInStateFlow.copy(
                                    state = if (tableItem.id == tableItemInStateFlow.id)
                                        ItemState.CURRENT
                                    else if (tableItemInStateFlow.id < tableItem.id)
                                        ItemState.FINISHED
                                    else
                                        ItemState.DEFAULT
                                )
                            }
                            updateTimer(tableItem.time)
                        }

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

    private suspend fun updateTimer(time: Int) = coroutineScope {
        var start = 0
        while (start <= time) {
            _timer.emit(
                (time - start).toLong()
            )
            if (time != start) {
                delay(1000)
            }
            start++
        }
    }

    fun onStartClick() {
        _isInProgress.value = !isInProgress.value
    }

}