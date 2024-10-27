package dev.kamenivska.myapplication.main.navigation.train

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.kamenivska.myapplication.main.navigation.Screen

@Composable
fun TrainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.SelectTrainScreen.route
    ) {
        trainGraph(navController)
    }
}