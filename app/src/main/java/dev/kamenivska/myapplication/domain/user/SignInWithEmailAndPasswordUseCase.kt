package dev.kamenivska.myapplication.domain.user

interface SignInWithEmailAndPasswordUseCase {
    operator fun invoke(
        email: String,
        password: String,
    )
}