package dev.kamenivska.myapplication.domain.user

interface SignUpWithEmailAndPasswordUseCase {
    operator fun invoke(
        email: String,
        password: String,
    )
}