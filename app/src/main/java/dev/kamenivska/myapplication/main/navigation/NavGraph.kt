package dev.kamenivska.myapplication.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavGraph(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        splash(
            continueNavigation = {
                navController.navigate(it)
            },
        )

        testBreathScreen(
            navigateBack = navController::popBackStack,
            onStartExperienceClick = {
                navController.navigate(Screen.MainScreen.route)
            }
        )

        authScreenFlow(
            navigateToMain = {
                navController.navigate(Screen.MainScreen.route)
            },
            navigateToTestBreath = {
                navController.navigate(Screen.TestBreathScreen.route)
            }
        )

        mainScreenFlow(
            navigateToAuth = {
                navController.navigate(Screen.AuthScreen.route)
            }
        )
    }
}