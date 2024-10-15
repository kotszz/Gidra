package dev.kamenivska.myapplication.main.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val mainNavController = rememberNavController()
    MainNavGraph(modifier = Modifier, navController = mainNavController)
}