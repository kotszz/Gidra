package dev.kamenivska.myapplication.data.repository.user

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow


interface UserRepository {

    val nameStateFlow: MutableStateFlow<String?>
    val phoneStateFlow: MutableStateFlow<String?>
    val creditCardStateFlow: MutableStateFlow<String?>
    val breathHoldStateFlow: MutableStateFlow<String?>

    val onSignInSuccessfully: MutableSharedFlow<Unit>
    val onSignUpSuccessfully: MutableSharedFlow<Unit>

    val error: MutableSharedFlow<String> // maybe change to dynamic error in sealed hierarchy
    val loading: MutableStateFlow<Boolean>

    fun isUserSignedIn(): Boolean

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
    )

    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
    )

    fun logout()

    fun setName(name: String)

    fun setPhone(phone: String)

    fun setCreditCard(creditCard: String)

    fun setBreathHold(breathHold: Long)

    fun refreshName()

    fun refreshPhone()

    fun refreshCreditCard()

    fun refreshBreathHold()

    fun getBreathHold(
        onSuccess: suspend (String?) -> Unit,
        onFailure: suspend (String) -> Unit,
    )

}