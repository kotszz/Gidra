package dev.kamenivska.myapplication.feature.train.swim.dynamic.subtype

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
fun DynamicSubtype(
    modifier: Modifier = Modifier,
    subtype: String,
    navigateBack: () -> Unit= {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            text = subtype,
            onNavigateBack = navigateBack
        )

        TrainButton(
            modifier = Modifier.padding(top = 72.dp),
            text = "my sessions"
        )
        TrainButton(
            modifier = Modifier.padding(top = 24.dp),
            text = "new session"
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