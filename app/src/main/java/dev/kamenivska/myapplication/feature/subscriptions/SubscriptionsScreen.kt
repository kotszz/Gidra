package dev.kamenivska.myapplication.feature.subscriptions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.feature.profile.HeaderProfile
import dev.kamenivska.myapplication.ui.theme.DividerColor
import dev.kamenivska.myapplication.ui.theme.LightGrey
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.SecondaryBackground
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SubscriptionsScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    navigateToAuth: () -> Unit = {},
) {

    val viewModel: SubscriptionsViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        viewModel.onLogout.collectLatest {
            navigateToAuth()
        }
    }

    Column {
        HeaderProfile(
            modifier = Modifier.padding(36.dp),
            text = "Subscriptions",
            onNavigateBack = navigateBack,
        )

        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SecondaryBackground),
        ) {
            Row(
                modifier = Modifier
                    .clickable(onClick = {})
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "PRO",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    lineHeight = 22.sp,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.ic_chevron_right),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(LightGrey)
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(
                    start = 16.dp,
                ),
                color = DividerColor,
                thickness = 1.dp
            )

            Row(
                modifier = Modifier
                    .clickable(onClick = {})
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Coaching",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    lineHeight = 22.sp,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.ic_chevron_right),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(LightGrey)
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SecondaryBackground)
                .clickable(onClick = viewModel::logout)
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "Log out",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                lineHeight = 22.sp,
                color = PrimaryColor,
            )
        }
    }
}