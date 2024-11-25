package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.LogoutUseCase

class LogoutUseCaseImpl(
    private val userRepository: UserRepository
): LogoutUseCase {
    override fun invoke() = userRepository.logout()
}