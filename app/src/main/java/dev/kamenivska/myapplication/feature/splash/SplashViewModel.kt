package dev.kamenivska.myapplication.feature.splash

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.main.BaseViewModel
import dev.kamenivska.myapplication.main.navigation.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {
    private val _splashNavigation = MutableSharedFlow<String>()
    val splashNavigation = _splashNavigation.asSharedFlow()

    fun startSplashDisplaying() = viewModelScope.launch(Dispatchers.IO) {
        val waitJob = launch {
            delay(2000)
        }
        val route = getNextScreenRoute()

        waitJob.join()
        _splashNavigation.emit(route)
    }

    private suspend fun getNextScreenRoute(): String = coroutineScope {
        delay(1000) // check if app firstly launched / yes - welcome; no - check if authorized / yes - main; no - registration;
        Screen.WelcomeScreen.route
    }
}