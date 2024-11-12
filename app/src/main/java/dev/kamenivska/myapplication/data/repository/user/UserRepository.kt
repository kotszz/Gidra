package dev.kamenivska.myapplication.data.repository.user

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow


interface UserRepository {

    val nameStateFlow: MutableStateFlow<String>
    val breathHoldStateFlow: MutableStateFlow<String>

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

    fun setName(name: String)

    fun setBreathHold(breathHold: Long)

    fun refreshName()

    fun refreshBreathHold()

    fun getBreathHold(
        onSuccess: suspend (String?) -> Unit,
        onFailure: suspend (String) -> Unit,
    )

}