package dev.kamenivska.myapplication.feature.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import dev.kamenivska.myapplication.ui.elements.GidraButton
import dev.kamenivska.myapplication.ui.theme.DefaultBackground
import dev.kamenivska.myapplication.ui.theme.DialogBackground
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextFieldColor
import dev.kamenivska.myapplication.ui.theme.TextGrey

@Composable
fun UpdateNameDialog(
    openDialogState: MutableState<Boolean>,
    nameInputState: State<String>,
    nameError: State<Boolean>,
    updateNameInput: (String) -> Unit,
    onSaveClick: () -> Boolean,
) {
    if (openDialogState.value) {
        Dialog(
            onDismissRequest = {
                openDialogState.value = false
            },
        ) {
            Column(
                modifier = Modifier
                    .background(DialogBackground, RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .padding(top = 100.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Update your",
                    fontFamily = Outfit,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    lineHeight = 26.sp,
                    color = TextGrey,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "NAME",
                    fontFamily = Outfit,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 36.sp,
                    color = PrimaryColor,
                )

                TextField(
                    isError = nameError.value,
                    value = nameInputState.value,
                    onValueChange = updateNameInput,
                    modifier = Modifier.padding(20.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.colors().copy(
                        focusedTextColor = DefaultBackground,
                        unfocusedTextColor = DefaultBackground.copy(alpha = 0.8F),
                        focusedContainerColor = TextFieldColor,
                        unfocusedContainerColor = TextFieldColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                    )
                )

                GidraButton(
                    modifier = Modifier
                        .padding(
                            top = 64.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                    text = "Save",
                    onClick = {
                        if (onSaveClick()) {
                            openDialogState.value = false
                        }
                    }
                )
            }
        }
    }
}

@Composable
@Preview
private fun UpdateNameDialog_Preview() {
    UpdateNameDialog(
        openDialogState = remember { mutableStateOf(true) },
        nameInputState = rememberUpdatedState("Name"),
        nameError = rememberUpdatedState(false),
        updateNameInput = { },
        onSaveClick = { false }
    )
}