package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.RefreshCreditCardUseCase

class RefreshCreditCardUseCaseImpl(
    private val userRepository: UserRepository
): RefreshCreditCardUseCase {
    override fun invoke() = userRepository.refreshCreditCard()
}