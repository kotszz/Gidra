package dev.kamenivska.myapplication.feature.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.ui.theme.DefaultBackground
import dev.kamenivska.myapplication.ui.theme.Outfit
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    navigateToSignUp: () -> Unit
) {

    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scrollState.scrollToItem(0, scrollOffset = 850)
        while (scrollState.firstVisibleItemScrollOffset < 9100) {
            coroutineScope.launch {
                scrollState.animateScrollToItem(0, scrollOffset = scrollState.firstVisibleItemScrollOffset + 20)
            }
            delay(100L)
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        LazyRow(
            state = scrollState,
            userScrollEnabled = false,
            modifier = Modifier
                .align(Alignment.BottomEnd),
        ) {
            item {
                Image(
                    painter = painterResource(R.drawable.bg_welcome_screen),
                    contentDescription = null,
                    contentScale = ContentScale.Companion.Crop
                )
            }
        }

        Column(modifier = Modifier
            .align(Alignment.Center)
            .padding(top = 100.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "THE FREEDOM OF",
                fontFamily = Outfit,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                lineHeight = 28.2.sp,
                color = DefaultBackground,
            )
            Text(
                text = "FREEDIVING",
                fontFamily = Outfit,
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
                lineHeight = 60.48.sp,
                color = DefaultBackground,
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(40.dp)),
                colors = ButtonDefaults.buttonColors().copy(containerColor = Color.Transparent),
                onClick = navigateToSignUp
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = "Let’s get started!",
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    lineHeight = 18.9.sp,
                    color = Color.White,
                )
            }
        }


    }
}