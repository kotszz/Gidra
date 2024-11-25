package dev.kamenivska.myapplication.domain.user

interface SetCreditCardUseCase {
    operator fun invoke(creditCard: String)
}