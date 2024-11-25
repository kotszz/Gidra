package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.RefreshPhoneUseCase

class RefreshPhoneUseCaseImpl(
    private val userRepository: UserRepository
): RefreshPhoneUseCase {
    override fun invoke() = userRepository.refreshPhone()
}