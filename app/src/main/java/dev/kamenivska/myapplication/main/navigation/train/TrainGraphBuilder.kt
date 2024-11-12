package dev.kamenivska.myapplication.main.navigation.train

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.kamenivska.myapplication.feature.train.SelectTrainScreen
import dev.kamenivska.myapplication.feature.train.breathe.BreatheScreen
import dev.kamenivska.myapplication.feature.train.breathe.pranayama.PranayamaScreen
import dev.kamenivska.myapplication.feature.train.breathe.tables.TablesScreen
import dev.kamenivska.myapplication.feature.train.swim.SwimScreen
import dev.kamenivska.myapplication.feature.train.swim.dynamic.DynamicsScreen
import dev.kamenivska.myapplication.feature.train.swim.dynamic.subtype.DynamicSubtype
import dev.kamenivska.myapplication.main.navigation.Screen

fun NavGraphBuilder.trainGraph(
    navController: NavHostController
) {
    selectTrainScreen(
        navigateToBreath = { navController.navigate(Screen.BreatheScreen.route) },
        navigateToSwim = { navController.navigate(Screen.SwimScreen.route) },
    )


    breathScreen(
        navigateToPranayama = { navController.navigate(Screen.PranayamaScreen.route) },
        navigateToTables = { navController.navigate(Screen.TablesScreen.route) },
        navigateBack = navController::popBackStack
    )

    pranayamaScreen(
        navigateBack = navController::popBackStack
    )

    tablesScreen(
        navigateBack = navController::popBackStack
    )


    swimScreen(
        navigateToDynamics = { navController.navigate(Screen.DynamicsScreen.route) },
        navigateBack = navController::popBackStack
    )

    dynamicsScreen(
        navigateToSubtype = { navController.navigate(Screen.DynamicSubtypeScreen.paramsRoute(it)) },
        navigateBack = navController::popBackStack
    )

    dynamicSubtypeScreen(
        navigateBack = navController::popBackStack
    )
}

fun NavGraphBuilder.selectTrainScreen(
    navigateToBreath: () -> Unit,
    navigateToSwim: () -> Unit,
) {
    composable(
        route = Screen.SelectTrainScreen.route,
    ) {
        SelectTrainScreen(
            navigateToBreath = navigateToBreath,
            navigateToSwim = navigateToSwim,
        )
    }
}


fun NavGraphBuilder.breathScreen(
    navigateToPranayama: () -> Unit,
    navigateToTables: () -> Unit,
    navigateBack: () -> Unit,
) {
    composable(
        route = Screen.BreatheScreen.route,
    ) {
        BreatheScreen(
            navigateToPranayama = navigateToPranayama,
            navigateToTables = navigateToTables,
            navigateBack = navigateBack,
        )
    }
}


fun NavGraphBuilder.swimScreen(
    navigateToDynamics: () -> Unit,
    navigateBack: () -> Unit,
) {
    composable(
        route = Screen.SwimScreen.route,
    ) {
        SwimScreen(
            navigateToDynamics = navigateToDynamics,
            navigateBack = navigateBack,
        )
    }
}

fun NavGraphBuilder.dynamicsScreen(
    navigateToSubtype: (String) -> Unit,
    navigateBack: () -> Unit,
) {
    composable(
        route = Screen.DynamicsScreen.route,
    ) {
        DynamicsScreen(
            navigateToSubtype = navigateToSubtype,
            navigateBack = navigateBack,
        )
    }
}

fun NavGraphBuilder.dynamicSubtypeScreen(
    navigateBack: () -> Unit,
) {
    composable(
        route = Screen.DynamicSubtypeScreen.route,
        arguments = listOf(
            navArgument(Screen.DynamicSubtypeScreen.TYPE) {
                type = NavType.StringType
            },
        )
    ) { backStackEntry ->
        DynamicSubtype(
            subtype = backStackEntry.arguments?.getString(Screen.DynamicSubtypeScreen.TYPE).orEmpty(),
            navigateBack = navigateBack,
        )
    }
}

fun NavGraphBuilder.pranayamaScreen(
    navigateBack: () -> Unit,
) {
    composable(
        route = Screen.PranayamaScreen.route,
    ) {
        PranayamaScreen(
            navigateBack = navigateBack,
        )
    }
}

fun NavGraphBuilder.tablesScreen(
    navigateBack: () -> Unit,
) {
    composable(
        route = Screen.TablesScreen.route,
    ) {
        TablesScreen(
            navigateBack = navigateBack,
        )
    }
}