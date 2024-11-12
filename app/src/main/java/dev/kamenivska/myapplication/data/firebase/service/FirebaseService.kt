package dev.kamenivska.myapplication.data.firebase.service

interface FirebaseService {

    fun isUserSignedIn(): Boolean

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: suspend () -> Unit,
        onFailure: suspend (String) -> Unit,
    )

    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: suspend () -> Unit,
        onFailure: suspend (String) -> Unit,
    )

    fun setName(name: String)

    fun setBreathHold(breathHold: Long)

    fun getName(
        onSuccess: suspend (String?) -> Unit,
        onFailure: suspend (String) -> Unit,
    )

    fun getBreathHold(
        onSuccess: suspend (String?) -> Unit,
        onFailure: suspend (String) -> Unit,
    )
}
