package dev.kamenivska.myapplication.main.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.kamenivska.myapplication.main.navigation.Screen

@Composable
fun MainNavGraph(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        mainGraph(navController)
    }
}