package dev.kamenivska.myapplication.domain.user

import kotlinx.coroutines.flow.Flow

interface PhoneFlowUseCase {
    operator fun invoke(): Flow<String?>
}