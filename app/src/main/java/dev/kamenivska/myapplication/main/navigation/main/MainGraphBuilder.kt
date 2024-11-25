package dev.kamenivska.myapplication.main.navigation.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.kamenivska.myapplication.feature.account.AccountScreen
import dev.kamenivska.myapplication.feature.calendar.CalendarScreen
import dev.kamenivska.myapplication.feature.coaching.CoachingScreen
import dev.kamenivska.myapplication.feature.home.HomeScreen
import dev.kamenivska.myapplication.feature.logbook.LogbookScreen
import dev.kamenivska.myapplication.feature.premium.GetProScreen
import dev.kamenivska.myapplication.feature.profile.ProfileScreen
import dev.kamenivska.myapplication.feature.subscriptions.SubscriptionsScreen
import dev.kamenivska.myapplication.feature.training.TrainingScreen
import dev.kamenivska.myapplication.main.navigation.Screen
import dev.kamenivska.myapplication.main.navigation.train.TrainScreen
import java.time.LocalDate

fun NavGraphBuilder.mainGraph(
    navController: NavHostController,
    navigateToAuth: () -> Unit = {},
) {
    homeScreen(
        navigateToCalendar = {
            navController.navigate(Screen.CalendarScreen.route)
        },
        navigateToGetPro = {
            navController.navigate(Screen.GetProScreen.route)
        },
        navigateToTrainingScreen = {
            navController.navigate(Screen.TrainingScreen.paramsRoute(date = it.toEpochDay()))
        }
    )

    trainScreen(
        navigateToLogbook = {
            navController.navigate(Screen.LogbookScreen.route)
        },
        navigateToCoaching = {
            navController.navigate(Screen.CoachingScreen.route)
        }
    )

    logbookScreen(
        navigateToTrainingScreen = {
            navController.navigate(Screen.TrainingScreen.paramsRoute(date = it.toEpochDay()))
        }
    )

    coachingScreen()

    profileScreen(
        navigateToAccount = {
            navController.navigate(Screen.AccountScreen.route)
        },
        navigateToSubscriptions = {
            navController.navigate(Screen.SubscriptionsScreen.route)
        },
        navigateToAuth = navigateToAuth
    )

    calendarScreen(
        navigateBack = navController::popBackStack,
        navigateToTrainingScreen = {
            navController.navigate(Screen.TrainingScreen.paramsRoute(date = it.toEpochDay()))
        }
    )

    getProScreen(
        navigateBack = navController::popBackStack,
    )

    accountScreen(
        navigateBack = navController::popBackStack,
        navigateToAuth = navigateToAuth
    )

    subscriptionsScreen(
        navigateBack = navController::popBackStack,
        navigateToAuth = navigateToAuth
    )

    trainingScreen(
        navigateBack = navController::popBackStack
    )
}

fun NavGraphBuilder.homeScreen(
    navigateToCalendar: () -> Unit,
    navigateToGetPro: () -> Unit,
    navigateToTrainingScreen: (LocalDate) -> Unit,
) {
    composable(
        route = Screen.HomeScreen.route,
    ) {
        HomeScreen(
            navigateToCalendar = navigateToCalendar,
            navigateToGetPro = navigateToGetPro,
            navigateToTrainingScreen = navigateToTrainingScreen
        )
    }
}

fun NavGraphBuilder.trainScreen(
    navigateToLogbook: () -> Unit,
    navigateToCoaching: () -> Unit,
) {
    composable(
        route = Screen.TrainScreen.route,
    ) {
        TrainScreen(
            navigateToLogbook = navigateToLogbook,
            navigateToCoaching = navigateToCoaching,
        )
    }
}

fun NavGraphBuilder.logbookScreen(
    navigateToTrainingScreen: (LocalDate) -> Unit,
) {
    composable(
        route = Screen.LogbookScreen.route,
    ) {
        LogbookScreen(
            navigateToTrainingScreen = navigateToTrainingScreen
        )
    }
}

fun NavGraphBuilder.coachingScreen() {
    composable(
        route = Screen.CoachingScreen.route,
    ) {
        CoachingScreen()
    }
}

fun NavGraphBuilder.profileScreen(
    navigateToAccount: () -> Unit,
    navigateToSubscriptions: () -> Unit,
    navigateToAuth: () -> Unit = {},
) {
    composable(
        route = Screen.ProfileScreen.route,
    ) {
        ProfileScreen(
            navigateToAccount = navigateToAccount,
            navigateToSubscriptions = navigateToSubscriptions,
            navigateToAuth = navigateToAuth,
        )
    }
}

fun NavGraphBuilder.calendarScreen(
    navigateBack: () -> Unit,
    navigateToTrainingScreen: (LocalDate) -> Unit,
) {
    composable(
        route = Screen.CalendarScreen.route,
    ) {
        CalendarScreen(
            navigateBack = navigateBack,
            navigateToTrainingScreen = navigateToTrainingScreen,
        )
    }
}

fun NavGraphBuilder.getProScreen(
    navigateBack: () -> Unit,
) {
    composable(
        route = Screen.GetProScreen.route,
    ) {
        GetProScreen(
            navigateBack = navigateBack,
        )
    }
}

fun NavGraphBuilder.accountScreen(
    navigateBack: () -> Unit,
    navigateToAuth: () -> Unit = {},
) {
    composable(
        route = Screen.AccountScreen.route,
    ) {
        AccountScreen(
            navigateBack = navigateBack,
            navigateToAuth = navigateToAuth,
        )
    }
}

fun NavGraphBuilder.subscriptionsScreen(
    navigateBack: () -> Unit,
    navigateToAuth: () -> Unit = {},
) {
    composable(
        route = Screen.SubscriptionsScreen.route,
    ) {
        SubscriptionsScreen(
            navigateBack = navigateBack,
            navigateToAuth = navigateToAuth,
        )
    }
}

fun NavGraphBuilder.trainingScreen(
    navigateBack: () -> Unit,
) {
    composable(
        route = Screen.TrainingScreen.route,
        arguments = listOf(
            navArgument(Screen.TrainingScreen.DATE) {
                type = NavType.LongType
            },
        )
    ) { backStackEntry ->
        TrainingScreen(
            date = backStackEntry.arguments?.getLong(Screen.TrainingScreen.DATE)
                ?.let { LocalDate.ofEpochDay(it) },
            navigateBack = navigateBack,
        )
    }
}