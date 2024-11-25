package dev.kamenivska.myapplication.main

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dev.kamenivska.myapplication.main.navigation.NavGraph
import dev.kamenivska.myapplication.ui.theme.GidraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(
                Color.TRANSPARENT
            )
        )
        setContent {
            GidraTheme {

                val navController = rememberNavController()

                NavGraph(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController
                )
            }
        }
    }
}