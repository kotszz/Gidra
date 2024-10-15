package dev.kamenivska.myapplication.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.kamenivska.myapplication.feature.splash.SplashScreen
import dev.kamenivska.myapplication.main.navigation.main.MainScreen

fun NavGraphBuilder.splash(
    continueNavigation: () -> Unit,
) {
    composable(
        route = Screen.Splash.route,
    ) {
        SplashScreen(
            continueNavigation = continueNavigation,
        )
    }
}

fun NavGraphBuilder.mainScreenFlow() {
    composable(
        route = Screen.MainScreen.route,
    ) {
        MainScreen()
    }
}