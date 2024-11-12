package dev.kamenivska.myapplication.domain.user

import kotlinx.coroutines.flow.SharedFlow

interface SignInSuccessfulFlowUseCase {
    operator fun invoke(): SharedFlow<Unit>
}