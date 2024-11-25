package dev.kamenivska.myapplication.feature.auth.signin

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.SnackbarMessage
import dev.kamenivska.myapplication.domain.user.SignInSuccessfulFlowUseCase
import dev.kamenivska.myapplication.domain.user.SignInWithEmailAndPasswordUseCase
import dev.kamenivska.myapplication.domain.user.UserErrorFlowUseCase
import dev.kamenivska.myapplication.main.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    signInSuccessfulFlowUseCase: SignInSuccessfulFlowUseCase,
    userErrorFlowUseCase: UserErrorFlowUseCase,
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            userErrorFlowUseCase().collectLatest {
                _snackbarMessage.emit(SnackbarMessage.Error(it))
            }
        }

        viewModelScope.launch {
            signInSuccessfulFlowUseCase().collectLatest {
                _navigateToNext.emit(Unit)
            }
        }
    }

    // Email input
    private val _emailInputState = MutableStateFlow("")
    val emailInputState = _emailInputState.asStateFlow()

    private val _emailErrorState = MutableStateFlow(false)
    val emailErrorState = _emailErrorState.asStateFlow()

    // Password input
    private val _passwordInputState = MutableStateFlow("")
    val passwordInputState = _passwordInputState.asStateFlow()

    private val _passwordErrorState = MutableStateFlow(false)
    val passwordErrorState = _passwordErrorState.asStateFlow()

    private val _navigateToNext = MutableSharedFlow<Unit>()
    val navigateToNext = _navigateToNext.asSharedFlow()


    fun updateEmailInput(newValue: String) {
        _emailInputState.value = newValue
        _emailErrorState.value = false
    }

    fun updatePasswordInput(newValue: String) {
        _passwordInputState.value = newValue
        _passwordErrorState.value = false
    }


    fun validateInputs() {
        viewModelScope.launch {
            validateEmailInput()
            validatePasswordInput()

            if (!_emailErrorState.value && !_passwordErrorState.value) {
                signInWithEmailAndPasswordUseCase(
                    email = emailInputState.value,
                    password = passwordInputState.value,
                )
            }
        }
    }

    private fun validateEmailInput() {
        if (_emailInputState.value.isEmpty() || !_emailInputState.value.matches(android.util.Patterns.EMAIL_ADDRESS.toRegex())) {
            _emailErrorState.value = true
        }
    }

    private fun validatePasswordInput() {
        if (_passwordInputState.value.length < 8) {
            _passwordErrorState.value = true
        }
    }

}