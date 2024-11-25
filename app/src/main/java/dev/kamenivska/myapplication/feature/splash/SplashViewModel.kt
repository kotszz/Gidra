package dev.kamenivska.myapplication.feature.splash

import android.util.Log
import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.main.BaseViewModel
import dev.kamenivska.myapplication.main.navigation.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

class SplashViewModel(
    private val userRepository: UserRepository
) : BaseViewModel() {
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
        // check if app firstly launched / yes - welcome; no - check if authorized / yes - main; no - registration;
        val route: String = if (userRepository.isUserSignedIn()) {
            suspendCoroutine { continuation ->
                userRepository.getBreathHold(
                    onSuccess = { breathHold ->
                        if (breathHold.isNullOrEmpty()) {
                            continuation.resumeWith(Result.success(Screen.TestBreathScreen.route))
                        }
                        else {
                            continuation.resumeWith(Result.success(Screen.MainScreen.route))
                        }
                    },
                    onFailure = {
                        continuation.resumeWith(Result.success(Screen.MainScreen.route))
                    }
                )
            }
        }
        else Screen.AuthScreen.route

        Log.d("Selected route:", route)
        route
    }
}
