package dev.kamenivska.myapplication.feature.testbreath

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.ui.elements.GidraButton
import dev.kamenivska.myapplication.ui.theme.DialogBackground
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextGrey

@Composable
fun ResultsSavedDialog(
    openDialogState: MutableState<Boolean>,
    onStartExperienceClick: () -> Unit,
) {
    if (openDialogState.value) {
        Dialog(
            onDismissRequest = {
                openDialogState.value = false
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Column(
                modifier = Modifier
                    .background(DialogBackground, RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .padding(top = 100.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your result is\nadded to your",
                    fontFamily = Outfit,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    lineHeight = 26.sp,
                    color = TextGrey,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "LOGBOOK",
                    fontFamily = Outfit,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 36.sp,
                    color = PrimaryColor,
                )

                Box(
                    modifier = Modifier
                        .padding(32.dp)
                        .size(72.dp)
                        .background(PrimaryColor, RoundedCornerShape(72.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }

                GidraButton(
                    modifier = Modifier
                        .padding(
                            top = 64.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                    text = "Start My Experience",
                    onClick = {
                        openDialogState.value = false
                        onStartExperienceClick()
                    }
                )
            }
        }
    }
}

@Composable
@Preview
private fun ResultsSavedDialog_Preview() {
    ResultsSavedDialog(
        openDialogState = remember { mutableStateOf(true) },
        onStartExperienceClick = {}
    )
}