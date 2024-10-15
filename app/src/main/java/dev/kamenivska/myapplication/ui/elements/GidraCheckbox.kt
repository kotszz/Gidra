package dev.kamenivska.myapplication.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.ui.theme.PrimaryColor

@Composable
fun GidraCheckbox(
    checked: Boolean,
    onClick: () -> Unit,
) {
    val checkboxModifier = Modifier
        .size(24.dp)
        .clickable(onClick = onClick)
    if (checked) {
        Box(
            modifier = checkboxModifier
                .clip(RoundedCornerShape(10.dp))
                .background(PrimaryColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(R.drawable.ic_check),
                null
            )
        }
    } else {
        Box(
            modifier = checkboxModifier
                .border(1.dp, PrimaryColor, RoundedCornerShape(10.dp))
        )
    }
}