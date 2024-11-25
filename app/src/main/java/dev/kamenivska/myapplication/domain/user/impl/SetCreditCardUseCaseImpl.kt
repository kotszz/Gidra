package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.SetCreditCardUseCase

class SetCreditCardUseCaseImpl(
    private val userRepository: UserRepository,
): SetCreditCardUseCase {
    override fun invoke(creditCard: String) = userRepository.setCreditCard(creditCard)
}