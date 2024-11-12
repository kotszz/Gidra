package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.SignUpSuccessfulFlowUseCase
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SignUpSuccessfulFlowUseCaseImpl(
    private val userRepository: UserRepository
): SignUpSuccessfulFlowUseCase {
    override fun invoke(): SharedFlow<Unit> = userRepository.onSignUpSuccessfully.asSharedFlow()
}