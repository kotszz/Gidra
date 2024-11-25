package dev.kamenivska.myapplication.domain.user

import kotlinx.coroutines.flow.Flow

interface UserNameFlowUseCase {
    operator fun invoke(): Flow<String?>
}