package dev.kamenivska.myapplication.feature.profile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.ui.theme.DividerColor
import dev.kamenivska.myapplication.ui.theme.GreyButton
import dev.kamenivska.myapplication.ui.theme.LightGrey
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.SecondaryBackground
import dev.kamenivska.myapplication.ui.theme.TextDarkGrey
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigateToAccount: () -> Unit,
    navigateToSubscriptions: () -> Unit,
    navigateToAuth: () -> Unit = {},
) {

    val viewModel: ProfileViewModel = koinViewModel()

    val name by viewModel.userNameFlowUseCase()
        .collectAsStateWithLifecycle(initialValue = "")

    val imageBitmap by viewModel.imageBitmap.collectAsStateWithLifecycle()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = viewModel::setImageUri
    )

    LaunchedEffect(Unit) {
        viewModel.getProfilePicData()
    }

    LaunchedEffect(Unit) {
        viewModel.onLogout.collectLatest {
            navigateToAuth()
        }
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (imageBitmap == null) {
            Image(
                modifier = Modifier.size(128.dp).clip(CircleShape),
                painter = painterResource(R.drawable.ic_avatar_placeholder),
                contentDescription = null,
            )
        } else {
            imageBitmap?.let {
                Image(
                    bitmap = it,
                    contentDescription = null,
                    modifier = Modifier.size(128.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Text(
            modifier = Modifier.padding(12.dp),
            text = name ?: "No Name Data",
            fontFamily = Outfit,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            color = PrimaryColor,
        )

        Button(
            onClick = {
                launcher.launch("image/*")
            },
            colors = ButtonDefaults.buttonColors().copy(containerColor = GreyButton)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_edit),
                contentDescription = null,
            )

            Text(
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
                text = "Edit picture",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 27.sp,
                color = TextDarkGrey,
            )
        }


        Spacer(modifier = Modifier.size(8.dp))

        Column(
            modifier = Modifier
                .padding(20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SecondaryBackground),
        ) {
            Row(
                modifier = Modifier
                    .clickable(onClick = navigateToAccount)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Account",
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
                    .clickable(onClick = navigateToSubscriptions)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Subscriptions",
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
                    text = "Stats",
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
                    text = "Settings",
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
                    text = "Terms & Conditions",
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
                .padding(horizontal = 20.dp)
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

        Spacer(modifier = Modifier.size(112.dp))

    }
}

@Preview(name = "ProfileScreen")
@Composable
private fun PreviewProfileScreen() {
    ProfileScreen(
        Modifier, {},{}
    )
}