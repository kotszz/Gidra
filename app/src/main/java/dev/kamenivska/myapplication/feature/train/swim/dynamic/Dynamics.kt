package dev.kamenivska.myapplication.feature.train.swim.dynamic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.kamenivska.myapplication.feature.train.composable.ConfusedView
import dev.kamenivska.myapplication.feature.train.composable.Header
import dev.kamenivska.myapplication.feature.train.composable.TrainButton


@Composable
fun DynamicsScreen(
    modifier: Modifier = Modifier,
    navigateToSubtype: (String) -> Unit = {},
    navigateBack: () -> Unit= {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            text = "dynamics",
            onNavigateBack = navigateBack
        )

        TrainButton(
            modifier = Modifier.padding(top = 72.dp),
            text = "dyn",
            onClick = { navigateToSubtype("dyn") }
        )
        TrainButton(
            modifier = Modifier.padding(top = 24.dp),
            text = "dynb",
            onClick = { navigateToSubtype("dynb") }
        )
        TrainButton(
            modifier = Modifier.padding(top = 24.dp),
            text = "dnf",
            onClick = { navigateToSubtype("dnf") }
        )

        Box(
            modifier = Modifier
                .fillMaxHeight(),
            contentAlignment = Alignment.BottomCenter
        ) {
            ConfusedView(
                modifier = Modifier.padding(bottom = 152.dp)
            )
        }
    }
}

@Preview(name = "Dynamics")
@Composable
private fun PreviewDynamics() {
    DynamicsScreen()
}