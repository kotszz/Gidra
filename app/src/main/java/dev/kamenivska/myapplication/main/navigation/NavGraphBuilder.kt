package dev.kamenivska.myapplication.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.kamenivska.myapplication.feature.splash.SplashScreen
import dev.kamenivska.myapplication.feature.testbreath.TestBreathScreen
import dev.kamenivska.myapplication.main.navigation.auth.AuthScreen
import dev.kamenivska.myapplication.main.navigation.main.MainScreen

fun NavGraphBuilder.splash(
    continueNavigation: (String) -> Unit,
) {
    composable(
        route = Screen.Splash.route,
    ) {
        SplashScreen(
            continueNavigation = continueNavigation,
        )
    }
}

fun NavGraphBuilder.authScreenFlow(
    navigateToMain: () -> Unit,
    navigateToTestBreath: () -> Unit,
) {
    composable(
        route = Screen.AuthScreen.route,
    ) {
        AuthScreen(
            navigateToMain = navigateToMain,
            navigateToTestBreath = navigateToTestBreath,
        )
    }
}

fun NavGraphBuilder.mainScreenFlow(
    navigateToAuth: () -> Unit,
) {
    composable(
        route = Screen.MainScreen.route,
    ) {
        MainScreen(navigateToAuth = navigateToAuth)
    }
}

fun NavGraphBuilder.testBreathScreen(
    navigateBack: () -> Unit,
    onStartExperienceClick: () -> Unit,
) {
    composable(
        route = Screen.TestBreathScreen.route,
    ) {
        TestBreathScreen(
            navigateBack = navigateBack,
            onStartExperienceClick = onStartExperienceClick,
        )
    }
}