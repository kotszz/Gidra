package dev.kamenivska.myapplication.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.PrimaryColor

@Composable
fun HeaderProfile(
    modifier: Modifier = Modifier,
    text: String,
    onNavigateBack: () -> Unit = {},
) {

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            modifier = Modifier.clickable(
                onClick = onNavigateBack,
                interactionSource = interactionSource,
                indication = null,
            ),
            painter = painterResource(R.drawable.ic_chevron_left),
            contentDescription = null,
        )

        Row {
            Spacer(Modifier.weight(1f))
            Text(
                text = text,
                fontFamily = Outfit,
                fontWeight = FontWeight.Normal,
                fontSize = 25.sp,
                lineHeight = 28.sp,
                color = PrimaryColor,
            )
            Spacer(Modifier.weight(1f))
        }
    }
}

@Preview(name = "Header")
@Composable
private fun PreviewHeader() {
    HeaderProfile(text = "swim")
}