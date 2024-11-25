package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.RefreshUserNameUseCase

class RefreshUserNameUseCaseImpl(
    private val userRepository: UserRepository
): RefreshUserNameUseCase {
    override fun invoke() = userRepository.refreshName()
}