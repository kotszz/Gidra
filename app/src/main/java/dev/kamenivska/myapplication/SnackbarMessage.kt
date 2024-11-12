package dev.kamenivska.myapplication

import androidx.annotation.DrawableRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.ui.graphics.Color
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextGrey

sealed class SnackbarMessage(val visuals: SnackbarCustomVisuals) {
    class Error(message: String) : SnackbarMessage(
        SnackbarCustomVisuals(
            uiMessage = message,
            backgroundColor = Color.Red,
            elementsColor = TextGrey,
        )
    )

    class Success(message: String) : SnackbarMessage(
        SnackbarCustomVisuals(
            uiMessage = message,
            backgroundColor = Color(0xFF00E85D),
            elementsColor = TextGrey,
        )
    )
}

data class SnackbarCustomVisuals(
    override val message: String = "",
    override val actionLabel: String? = null,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    override val withDismissAction: Boolean = true,
    //Own values
    val uiMessage: String,
    @DrawableRes
    val icon: Int? = null,
    val backgroundColor: Color = Color.Gray,
    val elementsColor: Color = PrimaryColor,
) : SnackbarVisuals