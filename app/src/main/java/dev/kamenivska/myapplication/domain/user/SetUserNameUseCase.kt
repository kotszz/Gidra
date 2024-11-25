package dev.kamenivska.myapplication.domain.user

interface SetUserNameUseCase {
    operator fun invoke(name: String)
}