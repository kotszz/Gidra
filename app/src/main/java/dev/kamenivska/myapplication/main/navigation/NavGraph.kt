package dev.kamenivska.myapplication.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.kamenivska.myapplication.main.navigation.auth.authGraph

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
                navController.navigate(Screen.WelcomeScreen.route)
            },
        )

        authGraph(navController)

        mainScreenFlow()
    }
}