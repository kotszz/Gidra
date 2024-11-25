package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.PhoneFlowUseCase

class PhoneFlowUseCaseImpl(
    private val userRepository: UserRepository,
): PhoneFlowUseCase {
    override fun invoke() = userRepository.phoneStateFlow
}