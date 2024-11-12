package dev.kamenivska.myapplication.feature.calendar.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.PrimaryColor

@Composable
fun CalendarMonthAndYearView(
    modifier: Modifier = Modifier,
    monthAndYear: String,
    onPreviousClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(12.dp)
                .clickable {
                    onPreviousClick()
                },
            painter = painterResource(R.drawable.ic_chevron_left),
            contentDescription = null,
        )

        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = monthAndYear,
            fontFamily = Outfit,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 20.sp,
            color = PrimaryColor,
        )

        Image(
            modifier = Modifier
                .size(12.dp)
                .clickable {
                    onNextClick()
                },
            painter = painterResource(R.drawable.ic_chevron_right),
            contentDescription = null,
        )
    }
}

@Preview(name = "MonthAndYearView")
@Composable
private fun PreviewMonthAndYearView() {
    CalendarMonthAndYearView(
        monthAndYear = "March 2024"
    )
}