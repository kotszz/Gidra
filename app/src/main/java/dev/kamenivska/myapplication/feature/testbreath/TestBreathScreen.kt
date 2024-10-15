package dev.kamenivska.myapplication.feature.testbreath

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.main.utils.formatTime
import dev.kamenivska.myapplication.ui.elements.GidraButton
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextGrey
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun TestBreathScreen(
    navigateBack: () -> Unit,
    onStartExperienceClick: () -> Unit,
) {

    val viewModel: TestBreathViewModel = koinViewModel()

    val timerValue by viewModel.timer.collectAsState()

    val scrollState = rememberScrollState()

    val buttonText = remember {
        mutableStateOf("Start")
    }

    val doLaterVisible = remember {
        mutableStateOf(true)
    }

    val resultsSavedDialogState = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.showSuccessDialog.collectLatest {
            resultsSavedDialogState.value = true
        }
    }

    Image(
        modifier = Modifier.padding(16.dp).clickable(onClick = navigateBack),
        painter = painterResource(R.drawable.ic_chevron_left),
        contentDescription = null,
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(scrollState).imePadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Test Your Breath",
            fontFamily = Outfit,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            lineHeight = 32.76.sp,
            color = PrimaryColor,
        )

        Text(
            modifier = Modifier.padding(24.dp),
            text = "Let us test your breathhold to create the best training flow for you",
            fontFamily = Outfit,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            color = TextGrey,
            textAlign = Center
        )

        Box(Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .border(1.dp, PrimaryColor, RoundedCornerShape(1000.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = timerValue.formatTime(),
                fontFamily = Outfit,
                fontWeight = FontWeight.Normal,
                fontSize = 60.sp,
                lineHeight = 76.sp,
                color = TextGrey,
            )
        }

        GidraButton(Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp), buttonText.value) {
            viewModel.updateTimerState()
            buttonText.value = "Stop"
            doLaterVisible.value = false
        }

        if (doLaterVisible.value) {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "I’ll do it later",
                fontFamily = Outfit,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                color = TextGrey,
            )
        }
    }

    ResultsSavedDialog(resultsSavedDialogState) {
        onStartExperienceClick()
    }
}