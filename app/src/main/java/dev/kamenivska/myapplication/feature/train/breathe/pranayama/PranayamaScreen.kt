package dev.kamenivska.myapplication.feature.train.breathe.pranayama

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.feature.train.composable.Header
import dev.kamenivska.myapplication.ui.elements.GidraButton
import dev.kamenivska.myapplication.ui.theme.DialogBackground
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun PranayamaScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
) {

    val viewModel: PranayamaViewModel = koinViewModel()

    val isInProgress by viewModel.isInProgress.collectAsStateWithLifecycle()
    val progressState by viewModel.currentProgressState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            text = "pranayama",
            onNavigateBack = navigateBack
        )

        Spacer(Modifier.weight(1f))

        if (isInProgress) {
            PranayamaContent(progressState)
        } else {
            PranayamaDescription()
        }

        Spacer(modifier = Modifier.weight(1f))

        GidraButton(
            modifier = Modifier.padding(bottom = 152.dp),
            text = if(!isInProgress) "Start" else "Stop"
        ) {
            viewModel.onStartClick()
        }

    }
}

@Composable
fun PranayamaContent(progressState: String?) {
    Text(
        text = progressState ?: "",
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        color = PrimaryColor,
    )
}

@Composable
@Preview
fun PranayamaDescription() {
    ConstraintLayout {
        val (
            inhale,
            inhaleTopText,
            arrowRight,

            firstHold,
            holdTopText,
            arrowDown,

            exhale,
            exhaleBottomText,
            arrowLeft,

            secondHold,
            holdBottomText,
        ) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(inhale) {}
                .background(
                    color = DialogBackground,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.padding(40.dp),
                text = "4",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = PrimaryColor,
            )
        }
        Text(
            modifier =  Modifier.constrainAs(inhaleTopText) {
                bottom.linkTo(inhale.top, margin = 12.dp)
                start.linkTo(inhale.start)
                end.linkTo(inhale.end)
            },
            text = "INHALE",
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 30.sp,
            color = PrimaryColor,
        )
        Image(
            modifier =  Modifier.constrainAs(arrowRight) {
                start.linkTo(inhale.end)
                top.linkTo(inhale.top)
                bottom.linkTo(inhale.bottom)
            },
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = null,
        )


        Box(
            modifier = Modifier
                .constrainAs(firstHold) {
                    top.linkTo(parent.top)
                    start.linkTo(arrowRight.end)
                }
                .background(
                    color = DialogBackground,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.padding(40.dp),
                text = "8",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = PrimaryColor,
            )
        }
        Text(
            modifier =  Modifier.constrainAs(holdTopText) {
                bottom.linkTo(firstHold.top, margin = 12.dp)
                start.linkTo(firstHold.start)
                end.linkTo(firstHold.end)
            },
            text = "HOLD",
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 30.sp,
            color = PrimaryColor,
        )
        Image(
            modifier =  Modifier.constrainAs(arrowDown) {
                top.linkTo(firstHold.bottom)
                start.linkTo(firstHold.start)
                end.linkTo(firstHold.end)
            },
            painter = painterResource(R.drawable.ic_arrow_down),
            contentDescription = null,
        )


        Box(
            modifier = Modifier
                .constrainAs(exhale) {
                    top.linkTo(arrowDown.bottom)
                    start.linkTo(firstHold.start)
                    end.linkTo(firstHold.end)
                }
                .background(
                    color = DialogBackground,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.padding(40.dp),
                text = "8",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = PrimaryColor,
            )
        }
        Text(
            modifier =  Modifier.constrainAs(exhaleBottomText) {
                top.linkTo(exhale.bottom, margin = 12.dp)
                start.linkTo(exhale.start)
                end.linkTo(exhale.end)
            },
            text = "EXHALE",
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 30.sp,
            color = PrimaryColor,
        )
        Image(
            modifier =  Modifier.constrainAs(arrowLeft) {
                end.linkTo(exhale.start)
                top.linkTo(exhale.top)
                bottom.linkTo(exhale.bottom)
            },
            painter = painterResource(R.drawable.ic_arrow_left),
            contentDescription = null,
        )


        Box(
            modifier = Modifier
                .constrainAs(secondHold) {
                    end.linkTo(arrowLeft.start)
                    top.linkTo(exhale.top)
                    bottom.linkTo(exhale.bottom)
                }
                .background(
                    color = DialogBackground,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.padding(40.dp),
                text = "4",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = PrimaryColor,
            )
        }
        Text(
            modifier =  Modifier.constrainAs(holdBottomText) {
                top.linkTo(secondHold.bottom, margin = 12.dp)
                start.linkTo(secondHold.start)
                end.linkTo(secondHold.end)
            },
            text = "HOLD",
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 30.sp,
            color = PrimaryColor,
        )
    }

}

@Preview(name = "PranayamaScreen")
@Composable
private fun PreviewPranayamaScreen() {
    PranayamaScreen()
}