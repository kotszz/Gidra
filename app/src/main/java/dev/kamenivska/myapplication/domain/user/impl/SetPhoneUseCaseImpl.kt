package dev.kamenivska.myapplication.domain.user.impl

import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.domain.user.SetPhoneUseCase

class SetPhoneUseCaseImpl(
    private val userRepository: UserRepository,
): SetPhoneUseCase {
    override fun invoke(phone: String) = userRepository.setPhone(phone)
}