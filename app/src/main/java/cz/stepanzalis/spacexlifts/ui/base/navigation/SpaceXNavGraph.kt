package cz.stepanzalis.spacexlifts.ui.base.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.stepanzalis.spacexlifts.ui.feature.company.route.CompanyRoute
import cz.stepanzalis.spacexlifts.ui.feature.launches.launch.LaunchesRoute
import cz.stepanzalis.spacexlifts.ui.feature.launches.rocketdetail.RocketDetailRoute

@Composable
fun SpaceXNavGraph(
    isExpandedScreen: Boolean,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    startDestination: String = SpaceXNavigation.Launches,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(SpaceXNavigation.Launches) {
            LaunchesRoute(
                navController = navController,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
        composable(SpaceXNavigation.Company) {
            CompanyRoute(
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
        composable(
            SpaceXNavigation.RocketDetail + NavArg.Id,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id")

            RocketDetailRoute(
                id = id ?: "",
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
    }
}


object NavArg {
    const val Id = "/{id}"
}