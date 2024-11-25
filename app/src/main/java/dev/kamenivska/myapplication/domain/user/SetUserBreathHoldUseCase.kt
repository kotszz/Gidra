package dev.kamenivska.myapplication.domain.user

interface SetUserBreathHoldUseCase {
    operator fun invoke(breathHold: Long)
}