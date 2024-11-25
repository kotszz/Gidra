package dev.kamenivska.myapplication.main.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash_screen")

    // Auth
    data object AuthScreen : Screen("auth_screen")
    data object WelcomeScreen : Screen("welcome_screen")
    data object SignInScreen : Screen("sign_in_screen")
    data object SignUpScreen : Screen("sign_up_screen")
    data object ForgotPasswordScreen : Screen("forgot_password_screen")
    data object TestBreathScreen : Screen("test_breath_screen")

    //Main
    data object MainScreen : Screen("main_screen")

    data object HomeScreen : Screen("home_screen")
    data object CalendarScreen : Screen("calendar_screen")
    data object GetProScreen : Screen("get_pro_screen")

    data object TrainingScreen : Screen("training_screen/{date}") {
        const val DATE = "date"
        fun paramsRoute(date: Long) = "training_screen/$date"
    }

    data object TrainScreen : Screen("train_screen")
    data object SelectTrainScreen : Screen("select_train_screen")
    data object BreatheScreen : Screen("breath_screen")
    data object PranayamaScreen : Screen("pranayama_screen")
    data object TablesScreen : Screen("tables_screen")
    data object SwimScreen : Screen("swim_screen")
    data object DynamicsScreen : Screen("dynamics_screen")
    data object DynamicSubtypeScreen : Screen("dynamic_subtype_screen/{type}") {
        const val TYPE = "type"
        fun paramsRoute(type: String) = "dynamic_subtype_screen/$type"
    }

    data object LogbookScreen : Screen("logbook_screen")

    data object CoachingScreen : Screen("coaching_screen")

    data object ProfileScreen : Screen("profile_screen")
    data object AccountScreen : Screen("account_screen")
    data object SubscriptionsScreen : Screen("subscriptions_screen")





}