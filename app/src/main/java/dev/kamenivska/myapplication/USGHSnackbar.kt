package dev.kamenivska.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GidraSnackbar(
    visuals: SnackbarCustomVisuals,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment.Vertical,
) {
    val cornerRadius = 6.dp
    Row(
        modifier
            .fillMaxWidth()
            .shadow(
                elevation = 24.dp,
                spotColor = Color(0x1A101828),
                shape = RoundedCornerShape(12.dp),
            )
            .clip(
                RoundedCornerShape(
                    topStart = cornerRadius,
                    topEnd = cornerRadius,
                    bottomEnd = cornerRadius,
                    bottomStart = cornerRadius,
                )
            )
            .background(visuals.backgroundColor)
            .padding(start = 20.dp, top = 16.dp, end = 9.dp, bottom = 16.dp),
        verticalAlignment = contentAlignment,
    ) {
        visuals.icon?.let {
            Icon(
                painterResource(id = it),
                null,
                tint = visuals.elementsColor,
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = visuals.uiMessage,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            color = visuals.elementsColor,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
        )

        Spacer(modifier = Modifier.width(4.dp))
    }
}