package dev.kamenivska.myapplication.feature.subscriptions

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.domain.user.LogoutUseCase
import dev.kamenivska.myapplication.main.BaseViewModel
import kotlinx.coroutines.launch

class SubscriptionsViewModel(
    private val logoutUseCase: LogoutUseCase,
): BaseViewModel() {
    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            _onLogout.emit(Unit)
        }
    }
}