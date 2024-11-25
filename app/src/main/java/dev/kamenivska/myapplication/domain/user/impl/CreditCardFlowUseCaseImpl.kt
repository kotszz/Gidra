package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.CreditCardFlowUseCase

class CreditCardFlowUseCaseImpl(
    private val userRepository: UserRepository,
): CreditCardFlowUseCase {
    override fun invoke() = userRepository.creditCardStateFlow
}