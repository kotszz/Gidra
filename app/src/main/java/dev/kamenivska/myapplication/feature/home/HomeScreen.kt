package dev.kamenivska.myapplication.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import dev.kamenivska.myapplication.ui.theme.DefaultBackground
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.PrimaryColor

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(32.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryColor, RoundedCornerShape(16.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = "Up Next",
                fontFamily = Outfit,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                color = DefaultBackground,
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.ic_crab),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "THU 04/08/23",
                fontFamily = Outfit,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                lineHeight = 15.sp,
                color = DefaultBackground,
            )
            Image(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

@Preview(name = "HomeScreen")
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}