package dev.kamenivska.myapplication.feature.auth.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.material.MaterialTheme.colors
import dev.kamenivska.myapplication.GidraSnackbar
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.SnackbarCustomVisuals
import dev.kamenivska.myapplication.ui.elements.GidraButton
import dev.kamenivska.myapplication.ui.theme.DefaultBackground
import dev.kamenivska.myapplication.ui.theme.Grey
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextFieldColor
import dev.kamenivska.myapplication.ui.theme.TextGrey
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    navigateToSignUp: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    navigateToMain: () -> Unit,
) {

    val viewModel: SignInViewModel = koinViewModel()

    val emailInputState = viewModel.emailInputState.collectAsStateWithLifecycle()
    val passwordInputState = viewModel.passwordInputState.collectAsStateWithLifecycle()

    val emailError = viewModel.emailErrorState.collectAsStateWithLifecycle()
    val passwordError = viewModel.passwordErrorState.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.navigateToNext.collectLatest {
            navigateToMain()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.snackbarMessage.collectLatest {
            snackbarHostState.showSnackbar(it.visuals)
        }
    }

    Box {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp).verticalScroll(scrollState).imePadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "LOG IN",
                fontFamily = Outfit,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                lineHeight = 32.76.sp,
                color = PrimaryColor,
            )

            Column {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Email",
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = if (emailError.value) Color.Red else Grey,
                )

                TextField(
                    isError = emailError.value,
                    value = emailInputState.value,
                    onValueChange = viewModel::updateEmailInput,
                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    colors = TextFieldDefaults.colors().copy(
                        focusedTextColor = DefaultBackground,
                        unfocusedTextColor = DefaultBackground.copy(alpha = 0.8F),
                        focusedContainerColor = TextFieldColor,
                        unfocusedContainerColor = TextFieldColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                    )
                )
            }

            Column {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Password",
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = if (passwordError.value) Color.Red else Grey,
                )

                TextField(
                    isError = passwordError.value,
                    value = passwordInputState.value,
                    onValueChange = viewModel::updatePasswordInput,
                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    colors = TextFieldDefaults.colors().copy(
                        focusedTextColor = DefaultBackground,
                        unfocusedTextColor = DefaultBackground.copy(alpha = 0.8F),
                        focusedContainerColor = TextFieldColor,
                        unfocusedContainerColor = TextFieldColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                    )
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .clickable(onClick = navigateToForgotPassword),
                text = "Forgot password?",
                fontFamily = Outfit,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                color = PrimaryColor,
            )

            GidraButton(
                modifier = Modifier.padding(top = 32.dp),
                text = "Log In",
                onClick = viewModel::validateInputs
            )

            Row(
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Don’t have an account?",
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = TextGrey,
                )
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clickable(onClick = navigateToSignUp),
                    text = "Sign Up",
                    fontFamily = Outfit,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = PrimaryColor,
                )
            }

            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = "Or continue with",
                fontFamily = Outfit,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                color = TextGrey,
            )

            Row(
                modifier = Modifier.padding(12.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.ib_google),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.weight(1F))

                Image(
                    painter = painterResource(R.drawable.ib_facebook),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.weight(1F))

                Image(
                    painter = painterResource(R.drawable.ib_apple),
                    contentDescription = null,
                )
            }
        }

        SnackbarHost(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 12.dp),
            hostState = snackbarHostState
        ) { snackbarData: SnackbarData ->
            val visuals = snackbarData.visuals as SnackbarCustomVisuals
            GidraSnackbar(
                visuals = visuals,
                contentAlignment = Alignment.CenterVertically,
            )
        }
    }
}