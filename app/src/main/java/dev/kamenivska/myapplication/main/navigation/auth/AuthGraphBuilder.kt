package dev.kamenivska.myapplication.main.navigation.auth

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.kamenivska.myapplication.feature.auth.signin.ForgotPasswordScreen
import dev.kamenivska.myapplication.feature.auth.signin.SignInScreen
import dev.kamenivska.myapplication.feature.auth.signup.SignUpScreen
import dev.kamenivska.myapplication.feature.welcome.WelcomeScreen
import dev.kamenivska.myapplication.main.navigation.Screen

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    navigateToMain: () -> Unit,
    navigateToTestBreath: () -> Unit,
    navigationBarPadding: Dp,
) {
    welcomeScreen(
        navigateToSignUp = {
            navController.navigate(Screen.SignUpScreen.route)
        },
        navigationBarPadding = navigationBarPadding
    )

    signInScreen(
        navigateToSignUp = {
            navController.navigate(Screen.SignUpScreen.route)
        },
        navigateToForgotPassword = {
            navController.navigate(Screen.ForgotPasswordScreen.route)
        },
        navigateToMain = navigateToMain
    )

    signUpScreen(
        navigateToSignIn = {
            navController.navigate(Screen.SignInScreen.route)
        },
        navigateToTestBreath = navigateToTestBreath
    )

    forgotPasswordScreen(
        navigateBack = navController::popBackStack
    )
}

fun NavGraphBuilder.welcomeScreen(
    navigateToSignUp: () -> Unit,
    navigationBarPadding: Dp,
) {
    composable(
        route = Screen.WelcomeScreen.route,
    ) {
        WelcomeScreen(
            navigateToSignUp = navigateToSignUp,
            navigationBarPadding = navigationBarPadding,
        )
    }
}

fun NavGraphBuilder.signInScreen(
    navigateToSignUp: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    navigateToMain: () -> Unit,
) {
    composable(
        route = Screen.SignInScreen.route,
    ) {
        SignInScreen(
            navigateToSignUp = navigateToSignUp,
            navigateToForgotPassword = navigateToForgotPassword,
            navigateToMain = navigateToMain,
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