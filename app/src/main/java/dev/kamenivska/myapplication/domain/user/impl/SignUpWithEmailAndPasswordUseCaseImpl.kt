package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.SignUpWithEmailAndPasswordUseCase

class SignUpWithEmailAndPasswordUseCaseImpl(
    private val userRepository: UserRepository
): SignUpWithEmailAndPasswordUseCase {
    override fun invoke(
        email: String,
        password: String,
    ) {
        userRepository.signUpWithEmailAndPassword(email, password)
    }
}