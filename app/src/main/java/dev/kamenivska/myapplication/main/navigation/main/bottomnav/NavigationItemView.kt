package dev.kamenivska.myapplication.main.navigation.main.bottomnav

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kamenivska.myapplication.ui.theme.DefaultBackground
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.TextGrey

@Composable
fun RowScope.NavigationItem(
    navigationItem: NavigationItem,
    isSelected: Boolean,
) {

    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .weight(1f)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                if (!isSelected) navigationItem.onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(navigationItem.icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = if (isSelected) TextGrey else DefaultBackground
            )
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = navigationItem.text,
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 15.sp,
            color = if (isSelected) TextGrey else DefaultBackground,
        )
    }
}