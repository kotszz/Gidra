package dev.kamenivska.myapplication.main.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.kamenivska.myapplication.feature.auth.signin.ForgotPasswordScreen
import dev.kamenivska.myapplication.feature.auth.signin.SignInScreen
import dev.kamenivska.myapplication.feature.auth.signup.SignUpScreen
import dev.kamenivska.myapplication.feature.testbreath.TestBreathScreen
import dev.kamenivska.myapplication.feature.welcome.WelcomeScreen
import dev.kamenivska.myapplication.main.navigation.Screen

fun NavGraphBuilder.authGraph(
    navController: NavHostController
) {
    welcomeScreen(
        navigateToSignUp = {
            navController.navigate(Screen.SignUpScreen.route)
        }
    )

    signInScreen(
        navigateToSignUp = {
            navController.navigate(Screen.SignUpScreen.route)
        },
        navigateToForgotPassword = {
            navController.navigate(Screen.ForgotPasswordScreen.route)
        }
    )

    signUpScreen(
        navigateToSignIn = {
            navController.navigate(Screen.SignInScreen.route)
        },
        navigateToTestBreath = {
            navController.navigate(Screen.TestBreathScreen.route)
        },
    )

    forgotPasswordScreen(
        navigateBack = {
            navController.popBackStack()
        }
    )

    testBreathScreen(
        navigateBack = {
            navController.popBackStack()
        },
        onStartExperienceClick = {
            navController.navigate(Screen.MainScreen.route)
        }
    )
}

fun NavGraphBuilder.welcomeScreen(
    navigateToSignUp: () -> Unit
) {
    composable(
        route = Screen.WelcomeScreen.route,
    ) {
        WelcomeScreen(
            navigateToSignUp = navigateToSignUp
        )
    }
}

fun NavGraphBuilder.signInScreen(
    navigateToSignUp: () -> Unit,
    navigateToForgotPassword: () -> Unit,
) {
    composable(
        route = Screen.SignInScreen.route,
    ) {
        SignInScreen(
            navigateToSignUp = navigateToSignUp,
            navigateToForgotPassword = navigateToForgotPassword,
        )
    }
}

fun NavGraphBuilder.signUpScreen(
    navigateToSignIn: () -> Unit,
    navigateToTestBreath: () -> Unit,
) {
    composable(
        route = Screen.SignUpScreen.route,
    ) {
        SignUpScreen(
            navigateToSignIn = navigateToSignIn,
            navigateToTestBreath = navigateToTestBreath
        )
    }
}

fun NavGraphBuilder.forgotPasswordScreen(
    navigateBack: () -> Unit,
) {
    composable(
        route = Screen.ForgotPasswordScreen.route,
    ) {
        ForgotPasswordScreen(navigateBack = navigateBack)
    }
}

fun NavGraphBuilder.testBreathScreen(
    navigateBack: () -> Unit,
    onStartExperienceClick: () -> Unit,
) {
    composable(
        route = Screen.TestBreathScreen.route,
    ) {
        TestBreathScreen(
            navigateBack = navigateBack,
            onStartExperienceClick = onStartExperienceClick,
        )
    }
}