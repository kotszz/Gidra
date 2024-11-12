package dev.kamenivska.myapplication.main.navigation.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.kamenivska.myapplication.feature.calendar.CalendarScreen
import dev.kamenivska.myapplication.feature.coaching.CoachingScreen
import dev.kamenivska.myapplication.feature.home.HomeScreen
import dev.kamenivska.myapplication.feature.logbook.LogbookScreen
import dev.kamenivska.myapplication.feature.profile.ProfileScreen
import dev.kamenivska.myapplication.main.navigation.Screen
import dev.kamenivska.myapplication.main.navigation.train.TrainScreen

fun NavGraphBuilder.mainGraph(
    navController: NavHostController
) {
    homeScreen(
        navigateToCalendar = {
            navController.navigate(Screen.CalendarScreen.route)
        }
    )

    trainScreen()

    logbookScreen()

    coachingScreen()

    profileScreen()

    calendarScreen(
        navigateBack = navController::popBackStack
    )
}

fun NavGraphBuilder.homeScreen(
    navigateToCalendar: () -> Unit,
) {
    composable(
        route = Screen.HomeScreen.route,
    ) {
        HomeScreen(
            navigateToCalendar = navigateToCalendar
        )
    }
}

fun NavGraphBuilder.trainScreen() {
    composable(
        route = Screen.TrainScreen.route,
    ) {
        TrainScreen()
    }
}

fun NavGraphBuilder.logbookScreen() {
    composable(
        route = Screen.LogbookScreen.route,
    ) {
        LogbookScreen()
    }
}

fun NavGraphBuilder.coachingScreen() {
    composable(
        route = Screen.CoachingScreen.route,
    ) {
        CoachingScreen()
    }
}

fun NavGraphBuilder.profileScreen() {
    composable(
        route = Screen.ProfileScreen.route,
    ) {
        ProfileScreen()
    }
}

fun NavGraphBuilder.calendarScreen(
    navigateBack: () -> Unit,
) {
    composable(
        route = Screen.CalendarScreen.route,
    ) {
        CalendarScreen(navigateBack = navigateBack)
    }
}
