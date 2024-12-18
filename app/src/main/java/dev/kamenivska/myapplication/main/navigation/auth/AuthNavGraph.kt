package dev.kamenivska.myapplication.main.navigation.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.kamenivska.myapplication.main.navigation.Screen

@Composable
fun AuthNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigateToMain: () -> Unit,
    navigateToTestBreath: () -> Unit,
    navigationBarPadding: Dp,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.WelcomeScreen.route
    ) {
        authGraph(
            navController = navController,
            navigateToMain = navigateToMain,
            navigateToTestBreath = navigateToTestBreath,
            navigationBarPadding = navigationBarPadding,
        )
    }
}