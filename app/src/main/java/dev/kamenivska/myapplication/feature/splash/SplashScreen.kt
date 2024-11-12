package dev.kamenivska.myapplication.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.kamenivska.myapplication.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun SplashScreen(
    continueNavigation: (String) -> Unit,
) {
    val viewModel: SplashViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        viewModel.startSplashDisplaying()
    }

    LaunchedEffect(Unit) {
        viewModel.splashNavigation.collect {
            continueNavigation(it)
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        SplashView()
    }
}

@Composable
private fun SplashView() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.size(192.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
    }
}

@Preview
@Composable
private fun SplashViewPreview() {
    SplashView()
}