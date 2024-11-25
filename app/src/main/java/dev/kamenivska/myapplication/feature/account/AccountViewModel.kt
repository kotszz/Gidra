package dev.kamenivska.myapplication.feature.account

import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.SnackbarMessage
import dev.kamenivska.myapplication.domain.user.CreditCardFlowUseCase
import dev.kamenivska.myapplication.domain.user.LogoutUseCase
import dev.kamenivska.myapplication.domain.user.PhoneFlowUseCase
import dev.kamenivska.myapplication.domain.user.RefreshCreditCardUseCase
import dev.kamenivska.myapplication.domain.user.RefreshPhoneUseCase
import dev.kamenivska.myapplication.domain.user.RefreshUserNameUseCase
import dev.kamenivska.myapplication.domain.user.SetCreditCardUseCase
import dev.kamenivska.myapplication.domain.user.SetPhoneUseCase
import dev.kamenivska.myapplication.domain.user.SetUserNameUseCase
import dev.kamenivska.myapplication.domain.user.UserErrorFlowUseCase
import dev.kamenivska.myapplication.domain.user.UserNameFlowUseCase
import dev.kamenivska.myapplication.main.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class AccountViewModel(
    val userNameFlowUseCase: UserNameFlowUseCase,
    val phoneFlowUseCase: PhoneFlowUseCase,
    val creditCardFlowUseCase: CreditCardFlowUseCase,

    private val setUserNameUseCase: SetUserNameUseCase,
    private val setPhoneUseCase: SetPhoneUseCase,
    private val setCreditCardUseCase: SetCreditCardUseCase,

    private val refreshUserNameUseCase: RefreshUserNameUseCase,
    private val refreshPhoneUseCase: RefreshPhoneUseCase,
    private val refreshCreditCardUseCase: RefreshCreditCardUseCase,

    userErrorFlowUseCase: UserErrorFlowUseCase,

    private val logoutUseCase: LogoutUseCase,
): BaseViewModel() {

    private val digitInputPattern: Pattern = Pattern.compile("\\+\\d*")

    // Name input
    private val _nameInputState = MutableStateFlow("")
    val nameInputState = _nameInputState.asStateFlow()

    private val _nameErrorState = MutableStateFlow(false)
    val nameErrorState = _nameErrorState.asStateFlow()

    // Email input
    private val _phoneInputState = MutableStateFlow("+380")
    val phoneInputState = _phoneInputState.asStateFlow()

    private val _phoneErrorState = MutableStateFlow(false)
    val phoneErrorState = _phoneErrorState.asStateFlow()

    // Password input
    private val _creditCardInputState = MutableStateFlow("")
    val creditCardInputState = _creditCardInputState.asStateFlow()

    private val _creditCardErrorState = MutableStateFlow(false)
    val creditCardErrorState = _creditCardErrorState.asStateFlow()

    init {
        viewModelScope.launch {
            userErrorFlowUseCase().collectLatest {
                _snackbarMessage.emit(SnackbarMessage.Error(it))
            }
        }

        viewModelScope.launch {
            phoneFlowUseCase().collectLatest {
                if(!it.isNullOrEmpty()) {
                    _phoneInputState.value = it
                }
            }
        }

        viewModelScope.launch {
            userNameFlowUseCase().collectLatest {
                if(!it.isNullOrEmpty()) {
                    _nameInputState.value = it
                }
            }
        }

        viewModelScope.launch {
            creditCardFlowUseCase().collectLatest {
                if(!it.isNullOrEmpty()) {
                    _creditCardInputState.value = it
                }
            }
        }

        refreshUserNameUseCase()
        refreshPhoneUseCase()
        refreshCreditCardUseCase()
    }

    fun updateNameInput(newValue: String) {
        _nameInputState.value = newValue
        _nameErrorState.value = false
    }

    fun updatePhoneInput(newValue: String) {
        if(digitInputPattern.matcher(newValue).matches() && newValue.length <= 13 && newValue.length >= 4) {
            _phoneInputState.value = newValue
            _phoneErrorState.value = false
        }
    }

    fun updateCreditCardInput(newValue: String) {
        if(newValue.length <= 16) {
            _creditCardInputState.value = newValue
            _creditCardErrorState.value = false
        }
    }

    fun validateNameInput(): Boolean {
        return if (_nameInputState.value.length < 2 || _nameInputState.value.length > 35) {
            viewModelScope.launch {
                _snackbarMessage.emit(SnackbarMessage.Error("Name is not valid"))
            }
            _nameErrorState.value = true
            false
        } else {
            setUserNameUseCase(_nameInputState.value)
            refreshUserNameUseCase()
            true
        }
    }

    fun validatePhoneInput(): Boolean {
        return if (_phoneInputState.value.length != 13) {
            viewModelScope.launch {
                _snackbarMessage.emit(SnackbarMessage.Error("Phone is not valid"))
            }
            _phoneErrorState.value = true
            false
        } else {
            setPhoneUseCase(_phoneInputState.value)
            refreshPhoneUseCase()
            true
        }
    }

    fun validateCreditCardInput(): Boolean {
        return if (_creditCardInputState.value.length < 16) {
            viewModelScope.launch {
                _snackbarMessage.emit(SnackbarMessage.Error("Credit card is not valid"))
            }
            _creditCardErrorState.value = true
            false
        } else {
            setCreditCardUseCase(_creditCardInputState.value)
            refreshCreditCardUseCase()
            true
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            _onLogout.emit(Unit)
        }
    }
}