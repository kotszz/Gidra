package dev.kamenivska.myapplication.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kamenivska.myapplication.ui.theme.DefaultBackground
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.PrimaryColor

@Composable
fun GidraButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(40.dp))
        .background(PrimaryColor)
        .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = text,
            fontFamily = Outfit,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            lineHeight = 22.sp,
            color = DefaultBackground,
        )
    }
}