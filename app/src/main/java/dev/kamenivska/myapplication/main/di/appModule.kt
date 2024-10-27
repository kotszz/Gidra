package dev.kamenivska.myapplication.main.di

import androidx.navigation.NavHostController
import dev.kamenivska.myapplication.feature.home.HomeViewModel
import dev.kamenivska.myapplication.feature.splash.SplashViewModel
import dev.kamenivska.myapplication.feature.testbreath.TestBreathViewModel
import dev.kamenivska.myapplication.feature.train.breathe.pranayama.PranayamaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashViewModel() }
    viewModel { TestBreathViewModel() }
    viewModel { HomeViewModel() }
    viewModel { PranayamaViewModel() }
    single { getKoin().getProperty<NavHostController>("mainNavController") }
}