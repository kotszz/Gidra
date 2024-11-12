package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.SignInWithEmailAndPasswordUseCase

class SignInWithEmailAndPasswordUseCaseImpl(
    private val userRepository: UserRepository
): SignInWithEmailAndPasswordUseCase {
    override fun invoke(
        email: String,
        password: String,
    ) {
        userRepository.signInWithEmailAndPassword(email, password)
    }
}