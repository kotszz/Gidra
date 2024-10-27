package dev.kamenivska.myapplication.main.navigation.train

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun TrainScreen(
    modifier: Modifier = Modifier
) {
    TrainNavGraph(
        modifier = modifier,
        navController = rememberNavController()
    )
}
