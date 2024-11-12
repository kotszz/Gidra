package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.SetUserBreathHoldUseCase

class SetUserBreathHoldUseCaseImpl(
    private val userRepository: UserRepository,
): SetUserBreathHoldUseCase {
    override fun invoke(breathHold: Long) = userRepository.setBreathHold(breathHold)
}