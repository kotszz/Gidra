package dev.kamenivska.myapplication.feature.account

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
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.kamenivska.myapplication.GidraSnackbar
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.SnackbarCustomVisuals
import dev.kamenivska.myapplication.feature.profile.HeaderProfile
import dev.kamenivska.myapplication.main.utils.applyCreditCardMask
import dev.kamenivska.myapplication.main.utils.applyPhoneMask
import dev.kamenivska.myapplication.ui.theme.DividerColor
import dev.kamenivska.myapplication.ui.theme.LightGrey
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.SecondaryBackground
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    navigateToAuth: () -> Unit = {},
) {

    val viewModel: AccountViewModel = koinViewModel()

    val name by viewModel.userNameFlowUseCase().collectAsStateWithLifecycle("")
    val phoneNumber by viewModel.phoneFlowUseCase().collectAsStateWithLifecycle("")
    val creditCardNumber by viewModel.creditCardFlowUseCase().collectAsStateWithLifecycle("")

    val nameInputState = viewModel.nameInputState.collectAsStateWithLifecycle()
    val nameErrorState = viewModel.nameErrorState.collectAsStateWithLifecycle()

    val phoneInputState = viewModel.phoneInputState.collectAsStateWithLifecycle()
    val phoneErrorState = viewModel.phoneErrorState.collectAsStateWithLifecycle()

    val creditCardInputState = viewModel.creditCardInputState.collectAsStateWithLifecycle()
    val creditCardErrorState = viewModel.creditCardErrorState.collectAsStateWithLifecycle()

    val updateNameDialogState = remember {
        mutableStateOf(false)
    }

    val updatePhoneDialogState = remember {
        mutableStateOf(false)
    }

    val updateCreditCardDialogState = remember {
        mutableStateOf(false)
    }

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.snackbarMessage.collectLatest {
            snackbarHostState.showSnackbar(it.visuals)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onLogout.collectLatest {
            navigateToAuth()
        }
    }

    Column {

        HeaderProfile(
            modifier = Modifier.padding(36.dp),
            text = "Account",
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
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Details",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    lineHeight = 22.sp,
                    color = Color.White,
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
                    .clickable { updateNameDialogState.value = true }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name ?: "No Name Data",
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
                    .clickable { updatePhoneDialogState.value = true }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = phoneNumber?.applyPhoneMask() ?: "No Phone Data",
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

        Column(
            modifier = modifier
                .padding(20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SecondaryBackground),
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Credit Card",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    lineHeight = 22.sp,
                    color = Color.White,
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
                    .clickable { updateCreditCardDialogState.value = true }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = creditCardNumber?.applyCreditCardMask() ?: "No Credit Card Data",
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
    }

    UpdateNameDialog(
        openDialogState = updateNameDialogState,
        nameInputState = nameInputState,
        nameError = nameErrorState,
        updateNameInput = viewModel::updateNameInput,
        onSaveClick = viewModel::validateNameInput
    )

    UpdatePhoneDialog(
        openDialogState = updatePhoneDialogState,
        phoneInputState = phoneInputState,
        phoneError = phoneErrorState,
        updatePhoneInput = viewModel::updatePhoneInput,
        onSaveClick = viewModel::validatePhoneInput
    )

    UpdateCreditCardDialog(
        openDialogState = updateCreditCardDialogState,
        creditCardInputState = creditCardInputState,
        creditCardError = creditCardErrorState,
        updateCreditCardInput = viewModel::updateCreditCardInput,
        onSaveClick = viewModel::validateCreditCardInput
    )

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

@Preview(name = "AccountScreen")
@Composable
private fun PreviewAccountScreen() {
    AccountScreen()
}