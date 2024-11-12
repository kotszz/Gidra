package dev.kamenivska.myapplication.feature.auth.signup

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.SnackbarMessage
import dev.kamenivska.myapplication.domain.user.SetUserNameUseCase
import dev.kamenivska.myapplication.domain.user.SignUpSuccessfulFlowUseCase
import dev.kamenivska.myapplication.domain.user.SignUpWithEmailAndPasswordUseCase
import dev.kamenivska.myapplication.domain.user.UserErrorFlowUseCase
import dev.kamenivska.myapplication.main.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpWithEmailAndPasswordUseCase: SignUpWithEmailAndPasswordUseCase,
    signUpSuccessfulFlowUseCase: SignUpSuccessfulFlowUseCase,
    setUserNameUseCase: SetUserNameUseCase,
    userErrorFlowUseCase: UserErrorFlowUseCase,
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            userErrorFlowUseCase().collectLatest {
                _snackbarMessage.emit(SnackbarMessage.Error(it))
            }
        }

        viewModelScope.launch {
            signUpSuccessfulFlowUseCase().collectLatest {
                setUserNameUseCase(nameInputState.value)
                _navigateToNext.emit(Unit)
            }
        }
    }

    // Name input
    private val _nameInputState = MutableStateFlow("")
    val nameInputState = _nameInputState.asStateFlow()

    private val _nameErrorState = MutableStateFlow(false)
    val nameErrorState = _nameErrorState.asStateFlow()

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

    // Confirm Password input
    private val _confirmPasswordInputState = MutableStateFlow("")
    val confirmPasswordInputState = _confirmPasswordInputState.asStateFlow()

    private val _confirmPasswordErrorState = MutableStateFlow(false)
    val confirmPasswordErrorState = _confirmPasswordErrorState.asStateFlow()


    private val _navigateToNext = MutableSharedFlow<Unit>()
    val navigateToNext = _navigateToNext.asSharedFlow()


    fun updateNameInput(newValue: String) {
        _nameInputState.value = newValue
        _nameErrorState.value = false
    }

    fun updateEmailInput(newValue: String) {
        _emailInputState.value = newValue
        _emailErrorState.value = false
    }

    fun updatePasswordInput(newValue: String) {
        _passwordInputState.value = newValue
        _passwordErrorState.value = false
        _confirmPasswordErrorState.value = false
    }

    fun updateConfirmPasswordInput(newValue: String) {
        _confirmPasswordInputState.value = newValue
        _confirmPasswordErrorState.value = false
    }

    fun validateInputs() {
        viewModelScope.launch {
            validateNameInput()
            validateEmailInput()
            validatePasswordInput()
            validateConfirmPasswordInput()

            if (!_nameErrorState.value &&
                !_emailErrorState.value &&
                !_passwordErrorState.value &&
                !_confirmPasswordErrorState.value) {
                signUpWithEmailAndPasswordUseCase(
                    email = emailInputState.value,
                    password = passwordInputState.value,
                )
            }
        }
    }

    private fun validateNameInput() {
        if (_nameInputState.value.length < 2 || _nameInputState.value.length > 35) {
            _nameErrorState.value = true
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

    private fun validateConfirmPasswordInput() {
        if (_passwordInputState.value != _confirmPasswordInputState.value) {
            _confirmPasswordErrorState.value = true
        }
    }

}