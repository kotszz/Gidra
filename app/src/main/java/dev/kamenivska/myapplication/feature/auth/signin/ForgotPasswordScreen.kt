package dev.kamenivska.myapplication.feature.auth.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.ui.elements.GidraButton
import dev.kamenivska.myapplication.ui.theme.Grey
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextFieldColor
import dev.kamenivska.myapplication.ui.theme.TextGrey

@Composable
fun ForgotPasswordScreen(
    navigateBack: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Image(
        modifier = Modifier.padding(16.dp).clickable(onClick = navigateBack),
        painter = painterResource(R.drawable.ic_chevron_left),
        contentDescription = null,
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp).verticalScroll(scrollState).imePadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "FORGOT PASSWORD",
            fontFamily = Outfit,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            lineHeight = 32.76.sp,
            color = PrimaryColor,
        )

        Text(
            modifier = Modifier.padding(24.dp),
            text = "We will send you an email with a link to reset your password, please enter the email associated with your account below.",
            fontFamily = Outfit,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            color = TextGrey,
            textAlign = Center
        )

        Column {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Email",
                fontFamily = Outfit,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                color = Grey,
            )

            TextField(
                "",
                {},
                modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.colors().copy(
                    unfocusedContainerColor = TextFieldColor,
                    focusedContainerColor = TextFieldColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
        }

        GidraButton(Modifier.padding(top = 32.dp), "Send link", {})
    }
}