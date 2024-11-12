package dev.kamenivska.myapplication.main.navigation.auth

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    navigateToMain: () -> Unit,
    navigateToTestBreath: () -> Unit,
) {
    Scaffold(modifier = modifier) { innerPadding ->
        AuthNavGraph(
            modifier = Modifier.padding(innerPadding),
            navController = rememberNavController(),
            navigateToMain = navigateToMain,
            navigateToTestBreath = navigateToTestBreath,
            navigationBarPadding = innerPadding.calculateBottomPadding()
        )
    }
}