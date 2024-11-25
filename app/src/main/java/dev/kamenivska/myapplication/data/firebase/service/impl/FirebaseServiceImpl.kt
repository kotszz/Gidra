package dev.kamenivska.myapplication.data.firebase.service.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.kamenivska.myapplication.data.firebase.FirebaseReference
import dev.kamenivska.myapplication.data.firebase.service.FirebaseService
import dev.kamenivska.myapplication.main.utils.toStringOrNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FirebaseServiceImpl(
    private val scope: CoroutineScope,
): FirebaseService {

    private val auth: FirebaseAuth = Firebase.auth

    override fun isUserSignedIn() = auth.currentUser != null

    override fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: suspend () -> Unit,
        onFailure: suspend (String) -> Unit,
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                scope.launch {
                    onSuccess()
                }
            }
            .addOnFailureListener { error ->
                scope.launch {
                    onFailure(error.localizedMessage ?: error.message ?: "Unknown exception")
                }
            }
    }

    override fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: suspend () -> Unit,
        onFailure: suspend (String) -> Unit,
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                scope.launch {
                    onSuccess()
                }
            }
            .addOnFailureListener { error ->
                scope.launch {
                    onFailure(error.localizedMessage ?: error.message ?: "Unknown exception")
                }
            }
    }

    override fun logout() = auth.signOut()

    override fun setName(name: String) {
        auth.currentUser?.let { user ->
            FirebaseReference.Name(user.uid).reference.setValue(name)
        }
    }

    override fun setPhone(phone: String) {
        auth.currentUser?.let { user ->
            FirebaseReference.Phone(user.uid).reference.setValue(phone)
        }
    }

    override fun setCreditCard(creditCard: String) {
        auth.currentUser?.let { user ->
            FirebaseReference.CreditCard(user.uid).reference.setValue(creditCard)
        }
    }

    override fun setBreathHold(breathHold: Long) {
        auth.currentUser?.let { user ->
            FirebaseReference.BreathHold(user.uid).reference.setValue(breathHold)
        }
    }

    override fun getName(
        onSuccess: suspend (String?) -> Unit,
        onFailure: suspend (String) -> Unit,
    ) {
        auth.currentUser?.let { user ->
            FirebaseReference.Name(user.uid).reference.get()
                .addOnSuccessListener { result ->
                    scope.launch {
                        onSuccess(result.value as? String)
                    }
                }
                .addOnFailureListener { error ->
                    scope.launch {
                        onFailure(error.localizedMessage ?: error.message ?: "Unknown exception")
                    }
                }
        }
    }

    override fun getPhone(
        onSuccess: suspend (String?) -> Unit,
        onFailure: suspend (String) -> Unit
    ) {
        auth.currentUser?.let { user ->
            FirebaseReference.Phone(user.uid).reference.get()
                .addOnSuccessListener { result ->
                    scope.launch {
                        onSuccess(result.value as? String)
                    }
                }
                .addOnFailureListener { error ->
                    scope.launch {
                        onFailure(error.localizedMessage ?: error.message ?: "Unknown exception")
                    }
                }
        }
    }

    override fun getCreditCard(
        onSuccess: suspend (String?) -> Unit,
        onFailure: suspend (String) -> Unit
    ) {
        auth.currentUser?.let { user ->
            FirebaseReference.CreditCard(user.uid).reference.get()
                .addOnSuccessListener { result ->
                    scope.launch {
                        onSuccess(result.value as? String)
                    }
                }
                .addOnFailureListener { error ->
                    scope.launch {
                        onFailure(error.localizedMessage ?: error.message ?: "Unknown exception")
                    }
                }
        }
    }

    override fun getBreathHold(
        onSuccess: suspend (String?) -> Unit,
        onFailure: suspend (String) -> Unit,
    ) {
        auth.currentUser?.let { user ->
            FirebaseReference.BreathHold(user.uid).reference.get()
                .addOnSuccessListener { result ->
                    scope.launch {
                        onSuccess(
                            (result.value as? Long).toStringOrNull()
                        )
                    }
                }
                .addOnFailureListener { error ->
                    scope.launch {
                        onFailure(error.localizedMessage ?: error.message ?: "Unknown exception")
                    }
                }
        }
    }
}
