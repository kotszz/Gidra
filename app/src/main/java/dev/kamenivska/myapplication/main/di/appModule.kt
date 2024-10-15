package dev.kamenivska.myapplication.main.di

import dev.kamenivska.myapplication.feature.splash.SplashViewModel
import dev.kamenivska.myapplication.feature.testbreath.TestBreathViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashViewModel() }
    viewModel { TestBreathViewModel() }
}