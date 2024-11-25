package dev.kamenivska.myapplication.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.kamenivska.myapplication.ui.theme.DefaultBackground

@Composable
fun FullScreenLoader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = DefaultBackground.copy(alpha = 0.5f)
            ),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(name = "FullScreenLoader")
@Composable
private fun PreviewFullScreenLoader() {
    FullScreenLoader()
}