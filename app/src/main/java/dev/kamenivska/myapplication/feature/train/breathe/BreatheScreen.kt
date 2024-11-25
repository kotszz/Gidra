package dev.kamenivska.myapplication.feature.train.breathe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.kamenivska.myapplication.feature.train.composable.ConfusedView
import dev.kamenivska.myapplication.feature.train.composable.Header
import dev.kamenivska.myapplication.feature.train.composable.TrainButton

@Composable
fun BreatheScreen(
    modifier: Modifier = Modifier,
    navigateToPranayama: () -> Unit= {},
    navigateToTables: () -> Unit= {},
    navigateBack: () -> Unit= {},
    navigateToCoaching: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            text = "breathe",
            onNavigateBack = navigateBack
        )

        TrainButton(
            modifier = Modifier.padding(top = 72.dp),
            text = "pranayama",
            onClick = navigateToPranayama
        )
        TrainButton(
            modifier = Modifier.padding(top = 24.dp),
            text = "tables",
            onClick = navigateToTables
        )
        TrainButton(
            modifier = Modifier.padding(top = 24.dp),
            text = "pb attempt"
        )

        Box(
            modifier = Modifier
                .padding(top = 72.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.BottomCenter
        ) {
            ConfusedView(
                modifier = Modifier.padding(bottom = 122.dp),
                navigateToCoaching = navigateToCoaching,
            )
        }
    }
}

@Preview(name = "BreatheScreen")
@Composable
private fun PreviewBreatheScreen() {
    BreatheScreen()
}