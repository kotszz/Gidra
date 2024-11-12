package dev.kamenivska.myapplication.data.repository.user.impl

import dev.kamenivska.myapplication.data.firebase.service.FirebaseService
import dev.kamenivska.myapplication.data.repository.user.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class UserRepositoryImpl(
    private val firebaseService: FirebaseService,
) : UserRepository {

    override val nameStateFlow = MutableStateFlow("")
    override val breathHoldStateFlow = MutableStateFlow("")
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

    override fun setName(name: String) {
        firebaseService.setName(name)
    }

    override fun setBreathHold(breathHold: Long) {
        firebaseService.setBreathHold(breathHold)
    }

    override fun refreshName() {
        firebaseService.getName(
            onSuccess = { name ->
                nameStateFlow.emit(name ?: "No Data")
            },
            onFailure = { message ->
                error.emit(message)
            }
        )
    }

    override fun refreshBreathHold() {
        firebaseService.getBreathHold(
            onSuccess = { breathHold ->
                breathHoldStateFlow.emit(breathHold ?: "No Data") //review
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