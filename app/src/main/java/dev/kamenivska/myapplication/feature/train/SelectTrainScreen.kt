package dev.kamenivska.myapplication.feature.train

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kamenivska.myapplication.feature.train.composable.ConfusedView
import dev.kamenivska.myapplication.feature.train.composable.TrainButton
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.PrimaryColor

@Composable
fun SelectTrainScreen(
    modifier: Modifier = Modifier,
    navigateToBreath: () -> Unit = {},
    navigateToSwim: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = 72.dp),
            text = "TRAIN",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            lineHeight = 39.sp,
            color = PrimaryColor,
        )

        TrainButton(
            text = "breathe",
            onClick = navigateToBreath
        )
        TrainButton(
            modifier = Modifier.padding(top = 24.dp),
            text = "swim",
            onClick = navigateToSwim
        )
        TrainButton(
            modifier = Modifier.padding(top = 24.dp),
            text = "equalize"
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

@Preview(name = "SelectTrainScreen")
@Composable
private fun PreviewTrainScreen() {
    SelectTrainScreen()
}