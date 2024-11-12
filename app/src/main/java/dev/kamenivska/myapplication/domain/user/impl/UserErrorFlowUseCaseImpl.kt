package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.UserErrorFlowUseCase
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class UserErrorFlowUseCaseImpl(
    private val userRepository: UserRepository
): UserErrorFlowUseCase {
    override fun invoke(): SharedFlow<String> = userRepository.error.asSharedFlow()
}