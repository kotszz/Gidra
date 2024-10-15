package dev.kamenivska.myapplication.feature.coaching

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CoachingScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "CoachingScreen")
    }
}

@Preview(name = "CoachingScreen")
@Composable
private fun PreviewCoachingScreen() {
    CoachingScreen()
}