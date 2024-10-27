package dev.kamenivska.myapplication.feature.logbook

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LogbookScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxSize()) {
        Text(text = "LogbookScreen")
    }
}

@Preview(name = "LogbookScreen")
@Composable
private fun PreviewLogbookScreen() {
    LogbookScreen()
}