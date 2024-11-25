package dev.kamenivska.myapplication.data.repository.user.impl

import dev.kamenivska.myapplication.data.firebase.service.FirebaseService
import dev.kamenivska.myapplication.data.repository.user.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class UserRepositoryImpl(
    private val firebaseService: FirebaseService,
) : UserRepository {

    override val nameStateFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    override val phoneStateFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    override val creditCardStateFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    override val breathHoldStateFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    override val onSignInSuccessfully = MutableSharedFlow<Unit>()
    override val onSignUpSuccessfully = MutableSharedFlow<Unit>()
    override val error = MutableSharedFlow<String>()
    override val loading = MutableStateFlow(false)

    override fun isUserSignedIn(): Boolean = firebaseService.isUserSignedIn()

    override fun signInWithEmailAndPassword(email: String, password: String) {
        firebaseService.signInWithEmailAndPassword(
            email = email,
            password = password,
            onSuccess = {
                onSignInSuccessfully.emit(Unit)
            },
            onFailure = { message ->
                error.emit(message)
            }
        )
    }

    override fun signUpWithEmailAndPassword(email: String, password: String) {
        firebaseService.signUpWithEmailAndPassword(
            email = email,
            password = password,
            onSuccess = {
                onSignUpSuccessfully.emit(Unit)
            },
            onFailure = { message ->
                error.emit(message)
            }
        )
    }

    override fun logout() = firebaseService.logout()

    override fun setName(name: String) {
        firebaseService.setName(name)
    }

    override fun setPhone(phone: String) {
        firebaseService.setPhone(phone)
    }

    override fun setCreditCard(creditCard: String) {
        firebaseService.setCreditCard(creditCard)
    }

    override fun setBreathHold(breathHold: Long) {
        firebaseService.setBreathHold(breathHold)
    }

    override fun refreshName() {
        firebaseService.getName(
            onSuccess = { name ->
                nameStateFlow.emit(name)
            },
            onFailure = { message ->
                error.emit(message)
            }
        )
    }

    override fun refreshPhone() {
        firebaseService.getPhone(
            onSuccess = { phone ->
                phoneStateFlow.emit(phone)
            },
            onFailure = { message ->
                error.emit(message)
            }
        )
    }

    override fun refreshCreditCard() {
        firebaseService.getCreditCard(
            onSuccess = { creditCard ->
                creditCardStateFlow.emit(creditCard)
            },
            onFailure = { message ->
                error.emit(message)
            }
        )
    }

    override fun refreshBreathHold() {
        firebaseService.getBreathHold(
            onSuccess = { breathHold ->
                breathHoldStateFlow.emit(breathHold) //review
            },
            onFailure = { message ->
                error.emit(message)
            }
        )
    }

    override fun getBreathHold(
        onSuccess: suspend (String?) -> Unit,
        onFailure: suspend (String) -> Unit,
    ) {
        firebaseService.getBreathHold(
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}