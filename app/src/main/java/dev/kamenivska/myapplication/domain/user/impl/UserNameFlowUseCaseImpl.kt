package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.UserNameFlowUseCase

class UserNameFlowUseCaseImpl(
    private val userRepository: UserRepository,
): UserNameFlowUseCase {
    override fun invoke() = userRepository.nameStateFlow
}