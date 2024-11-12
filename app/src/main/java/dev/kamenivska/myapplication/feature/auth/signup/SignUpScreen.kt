package dev.kamenivska.myapplication.feature.auth.signup

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.kamenivska.myapplication.GidraSnackbar
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.SnackbarCustomVisuals
import dev.kamenivska.myapplication.ui.elements.GidraButton
import dev.kamenivska.myapplication.ui.elements.GidraCheckbox
import dev.kamenivska.myapplication.ui.theme.DefaultBackground
import dev.kamenivska.myapplication.ui.theme.Grey
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextFieldColor
import dev.kamenivska.myapplication.ui.theme.TextGrey
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit,
    navigateToTestBreath: () -> Unit,
) {

    val viewModel: SignUpViewModel = koinViewModel()

    val nameInputState = viewModel.nameInputState.collectAsStateWithLifecycle()
    val emailInputState = viewModel.emailInputState.collectAsStateWithLifecycle()
    val passwordInputState = viewModel.passwordInputState.collectAsStateWithLifecycle()
    val confirmPasswordInputState = viewModel.confirmPasswordInputState.collectAsStateWithLifecycle()

    val nameError = viewModel.nameErrorState.collectAsStateWithLifecycle()
    val emailError = viewModel.emailErrorState.collectAsStateWithLifecycle()
    val passwordError = viewModel.passwordErrorState.collectAsStateWithLifecycle()
    val confirmPasswordError = viewModel.confirmPasswordErrorState.collectAsStateWithLifecycle()


    var policyCheckboxChecked by remember {
        mutableStateOf(false)
    }
    val scrollState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.navigateToNext.collectLatest {
            navigateToTestBreath()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.snackbarMessage.collectLatest {
            snackbarHostState.showSnackbar(it.visuals)
        }
    }

    Box {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp).verticalScroll(scrollState)
                .imePadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "CREATE ACCOUNT",
                fontFamily = Outfit,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                lineHeight = 32.76.sp,
                color = PrimaryColor,
            )

            Column {
                Text(
                    modifier = Modifier.padding(top = 32.dp),
                    text = "Name",
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = if (nameError.value) Color.Red else Grey,
                )

                TextField(
                    isError = nameError.value,
                    value = nameInputState.value,
                    onValueChange = viewModel::updateNameInput,
                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    singleLine = true,
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
                    text = "Confirm Password",
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = if (confirmPasswordError.value) Color.Red else Grey,
                )

                TextField(
                    isError = confirmPasswordError.value,
                    value = confirmPasswordInputState.value,
                    onValueChange = viewModel::updateConfirmPasswordInput,
                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    singleLine = true,
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

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GidraCheckbox(
                    checked = policyCheckboxChecked,
                    onClick = { policyCheckboxChecked = !policyCheckboxChecked }
                )
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        text = "I’ve read and accepted ",
                        fontFamily = Outfit,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        lineHeight = 14.sp,
                        color = TextGrey,
                    )

                    Text(
                        text = "Terms of Service and Privacy Policy",
                        fontFamily = Outfit,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        lineHeight = 14.sp,
                        color = PrimaryColor,
                    )
                }
            }

            GidraButton(
                modifier = Modifier.padding(top = 32.dp),
                text = "Sign Up",
                onClick = viewModel::validateInputs
            )

            Row(
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Already have an account?",
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = TextGrey,
                )
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clickable(onClick = navigateToSignIn),
                    text = "Log In",
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