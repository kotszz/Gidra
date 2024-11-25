package dev.kamenivska.myapplication.domain.user

import kotlinx.coroutines.flow.SharedFlow

interface UserErrorFlowUseCase {
    operator fun invoke(): SharedFlow<String>
}