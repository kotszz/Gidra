package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.SignInSuccessfulFlowUseCase
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SignInSuccessfulFlowUseCaseImpl(
    private val userRepository: UserRepository
): SignInSuccessfulFlowUseCase {
    override fun invoke(): SharedFlow<Unit> = userRepository.onSignInSuccessfully.asSharedFlow()
}