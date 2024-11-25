package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.SetUserNameUseCase

class SetUserNameUseCaseImpl(
    private val userRepository: UserRepository,
): SetUserNameUseCase {
    override fun invoke(name: String) = userRepository.setName(name)
}