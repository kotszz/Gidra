package dev.kamenivska.myapplication.main.navigation.main.bottomnav

import androidx.annotation.DrawableRes
import androidx.navigation.NavHostController
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.main.navigation.Screen
import org.koin.java.KoinJavaComponent.inject

sealed class NavigationItem(
    @DrawableRes open val icon: Int,
    open val text: String,
    open val route: String,
) {

    private val navController: NavHostController by inject(NavHostController::class.java)
    val onClick: () -> Unit = {
        navController.navigate(route)
    }

    data class Home(
        override val icon: Int = R.drawable.ic_home,
        override val text: String = "Home",
        override val route: String = Screen.HomeScreen.route,
    ): NavigationItem(
        icon,
        text,
        route,
    )

    data class Train(
        override val icon: Int = R.drawable.ic_lungs,
        override val text: String = "Train",
        override val route: String = Screen.TrainScreen.route,
    ): NavigationItem(
        icon,
        text,
        route,
    )

    data class Logbook(
        override val icon: Int = R.drawable.ic_logbook,
        override val text: String = "Logbook",
        override val route: String = Screen.LogbookScreen.route,
    ): NavigationItem(
        icon,
        text,
        route,
    )

    data class Coaching(
        override val icon: Int = R.drawable.ic_coaching,
        override val text: String = "Coaching",
        override val route: String = Screen.CoachingScreen.route,
    ): NavigationItem(
        icon,
        text,
        route,
    )

    data class Profile(
        override val icon: Int = R.drawable.ic_profile,
        override val text: String = "Profile",
        override val route: String = Screen.ProfileScreen.route,
    ): NavigationItem(
        icon,
        text,
        route,
    )
}