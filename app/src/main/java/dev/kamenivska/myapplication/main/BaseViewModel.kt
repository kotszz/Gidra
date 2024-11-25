package dev.kamenivska.myapplication.main

import androidx.lifecycle.ViewModel
import dev.kamenivska.myapplication.SnackbarMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {
    protected val _snackbarMessage = MutableSharedFlow<SnackbarMessage>()
    val snackbarMessage = _snackbarMessage.asSharedFlow()

    protected val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    protected val _onLogout = MutableSharedFlow<Unit>()
    val onLogout = _onLogout.asSharedFlow()
}