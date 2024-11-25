package dev.kamenivska.myapplication.domain.user

import kotlinx.coroutines.flow.Flow

interface CreditCardFlowUseCase {
    operator fun invoke(): Flow<String?>
}