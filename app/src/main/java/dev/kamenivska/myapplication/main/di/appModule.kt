package dev.kamenivska.myapplication.main.di

import androidx.navigation.NavHostController
import androidx.room.Room
import dev.kamenivska.myapplication.data.firebase.service.FirebaseService
import dev.kamenivska.myapplication.data.firebase.service.impl.FirebaseServiceImpl
import dev.kamenivska.myapplication.data.repository.trainings.TrainingsRepository
import dev.kamenivska.myapplication.data.repository.trainings.impl.TrainingsRepositoryImpl
import dev.kamenivska.myapplication.data.repository.user.UserRepository
import dev.kamenivska.myapplication.data.repository.user.impl.UserRepositoryImpl
import dev.kamenivska.myapplication.data.room.GidraDatabase
import dev.kamenivska.myapplication.data.room.dao.TrainingsDao
import dev.kamenivska.myapplication.data.room.service.TrainingsService
import dev.kamenivska.myapplication.data.room.service.impl.TrainingsServiceImpl
import dev.kamenivska.myapplication.domain.trainings.DeleteTrainingUseCase
import dev.kamenivska.myapplication.domain.trainings.GetClosestTrainingUseCase
import dev.kamenivska.myapplication.domain.trainings.InsertTrainingUseCase
import dev.kamenivska.myapplication.domain.trainings.TrainingsByDateFlowUseCase
import dev.kamenivska.myapplication.domain.trainings.TrainingsFlowUseCase
import dev.kamenivska.myapplication.domain.trainings.impl.DeleteTrainingUseCaseImpl
import dev.kamenivska.myapplication.domain.trainings.impl.GetClosestTrainingUseCaseImpl
import dev.kamenivska.myapplication.domain.trainings.impl.InsertTrainingUseCaseImpl
import dev.kamenivska.myapplication.domain.trainings.impl.TrainingsByDateFlowUseCaseImpl
import dev.kamenivska.myapplication.domain.trainings.impl.TrainingsFlowUseCaseImpl
import dev.kamenivska.myapplication.domain.user.CreditCardFlowUseCase
import dev.kamenivska.myapplication.domain.user.LogoutUseCase
import dev.kamenivska.myapplication.domain.user.PhoneFlowUseCase
import dev.kamenivska.myapplication.domain.user.RefreshCreditCardUseCase
import dev.kamenivska.myapplication.domain.user.RefreshPhoneUseCase
import dev.kamenivska.myapplication.domain.user.RefreshUserNameUseCase
import dev.kamenivska.myapplication.domain.user.SetCreditCardUseCase
import dev.kamenivska.myapplication.domain.user.SetPhoneUseCase
import dev.kamenivska.myapplication.domain.user.SetUserBreathHoldUseCase
import dev.kamenivska.myapplication.domain.user.SetUserNameUseCase
import dev.kamenivska.myapplication.domain.user.SignInSuccessfulFlowUseCase
import dev.kamenivska.myapplication.domain.user.SignInWithEmailAndPasswordUseCase
import dev.kamenivska.myapplication.domain.user.SignUpSuccessfulFlowUseCase
import dev.kamenivska.myapplication.domain.user.SignUpWithEmailAndPasswordUseCase
import dev.kamenivska.myapplication.domain.user.UserErrorFlowUseCase
import dev.kamenivska.myapplication.domain.user.UserNameFlowUseCase
import dev.kamenivska.myapplication.domain.user.impl.CreditCardFlowUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.LogoutUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.PhoneFlowUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.RefreshCreditCardUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.RefreshPhoneUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.RefreshUserNameUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.SetCreditCardUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.SetPhoneUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.SetUserBreathHoldUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.SetUserNameUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.SignInSuccessfulFlowUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.SignInWithEmailAndPasswordUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.SignUpSuccessfulFlowUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.SignUpWithEmailAndPasswordUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.UserErrorFlowUseCaseImpl
import dev.kamenivska.myapplication.domain.user.impl.UserNameFlowUseCaseImpl
import dev.kamenivska.myapplication.feature.account.AccountViewModel
import dev.kamenivska.myapplication.feature.addtraining.AddTrainingViewModel
import dev.kamenivska.myapplication.feature.auth.signin.SignInViewModel
import dev.kamenivska.myapplication.feature.auth.signup.SignUpViewModel
import dev.kamenivska.myapplication.feature.calendar.CalendarViewModel
import dev.kamenivska.myapplication.feature.coaching.CoachingViewModel
import dev.kamenivska.myapplication.feature.home.HomeViewModel
import dev.kamenivska.myapplication.feature.logbook.LogbookViewModel
import dev.kamenivska.myapplication.feature.profile.ProfileViewModel
import dev.kamenivska.myapplication.feature.splash.SplashViewModel
import dev.kamenivska.myapplication.feature.subscriptions.SubscriptionsViewModel
import dev.kamenivska.myapplication.feature.testbreath.TestBreathViewModel
import dev.kamenivska.myapplication.feature.train.breathe.pranayama.PranayamaViewModel
import dev.kamenivska.myapplication.feature.train.breathe.tables.TablesViewModel
import dev.kamenivska.myapplication.feature.training.TrainingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { TestBreathViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { PranayamaViewModel(get()) }
    viewModel { TablesViewModel(get()) }
    viewModel { SignUpViewModel(get(), get(), get(), get()) }
    viewModel { SignInViewModel(get(), get(), get()) }
    viewModel { CoachingViewModel(get()) }
    viewModel { CalendarViewModel(get()) }
    viewModel { ProfileViewModel(get(), get(), get(), get()) }
    viewModel { AccountViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get()) }
    viewModel { SubscriptionsViewModel(get()) }
    viewModel { LogbookViewModel(get()) }
    viewModel { TrainingViewModel(get(), get()) }
    viewModel { AddTrainingViewModel(get()) }


    single { getKoin().getProperty<NavHostController>("mainNavController") }


    single<FirebaseService> { FirebaseServiceImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }

    single<SignInSuccessfulFlowUseCase> { SignInSuccessfulFlowUseCaseImpl(get()) }
    single<SignInWithEmailAndPasswordUseCase> { SignInWithEmailAndPasswordUseCaseImpl(get()) }
    single<SignUpSuccessfulFlowUseCase> { SignUpSuccessfulFlowUseCaseImpl(get()) }
    single<SignUpWithEmailAndPasswordUseCase> { SignUpWithEmailAndPasswordUseCaseImpl(get()) }
    single<LogoutUseCase> { LogoutUseCaseImpl(get()) }
    single<UserErrorFlowUseCase> { UserErrorFlowUseCaseImpl(get()) }

    single<SetUserNameUseCase> { SetUserNameUseCaseImpl(get()) }
    single<SetPhoneUseCase> { SetPhoneUseCaseImpl(get()) }
    single<SetCreditCardUseCase> { SetCreditCardUseCaseImpl(get()) }
    single<SetUserBreathHoldUseCase> { SetUserBreathHoldUseCaseImpl(get()) }

    single<RefreshUserNameUseCase> { RefreshUserNameUseCaseImpl(get()) }
    single<RefreshPhoneUseCase> { RefreshPhoneUseCaseImpl(get()) }
    single<RefreshCreditCardUseCase> { RefreshCreditCardUseCaseImpl(get()) }

    single<UserNameFlowUseCase> { UserNameFlowUseCaseImpl(get()) }
    single<PhoneFlowUseCase> { PhoneFlowUseCaseImpl(get()) }
    single<CreditCardFlowUseCase> { CreditCardFlowUseCaseImpl(get()) }


    single {
        Room.databaseBuilder(
            androidContext(),
            GidraDatabase::class.java,
            "GidraDB"
        ).build()
    }

    single<TrainingsDao> {
        val database = get<GidraDatabase>()
        database.trainingsDao()
    }

    single<TrainingsService> { TrainingsServiceImpl(get()) }
    single<TrainingsRepository> { TrainingsRepositoryImpl(get()) }

    single<InsertTrainingUseCase> { InsertTrainingUseCaseImpl(get()) }
    single<DeleteTrainingUseCase> { DeleteTrainingUseCaseImpl(get()) }
    single<GetClosestTrainingUseCase> { GetClosestTrainingUseCaseImpl(get()) }
    single<TrainingsByDateFlowUseCase> { TrainingsByDateFlowUseCaseImpl(get()) }
    single<TrainingsFlowUseCase> { TrainingsFlowUseCaseImpl(get()) }


    factory { CoroutineScope(Dispatchers.IO + SupervisorJob()) }
}