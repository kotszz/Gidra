package dev.kamenivska.myapplication.domain.user

interface SetPhoneUseCase {
    operator fun invoke(phone: String)
}