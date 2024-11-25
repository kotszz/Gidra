package dev.kamenivska.myapplication.data.firebase

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

sealed class FirebaseReference(
    protected val path: String
) {
    private val database = Firebase.database.reference
    val reference = database.child(path)

    data class Name(private val uid: String): FirebaseReference("users/$uid/username")
    data class Phone(private val uid: String): FirebaseReference("users/$uid/phone")
    data class CreditCard(private val uid: String): FirebaseReference("users/$uid/creditCard")
    data class BreathHold(private val uid: String): FirebaseReference("users/$uid/breathHold")
}