package dev.kamenivska.myapplication.domain.user

import kotlinx.coroutines.flow.SharedFlow

interface SignUpSuccessfulFlowUseCase {
    operator fun invoke(): SharedFlow<Unit>
}