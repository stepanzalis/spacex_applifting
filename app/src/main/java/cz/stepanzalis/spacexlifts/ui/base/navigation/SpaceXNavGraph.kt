package cz.stepanzalis.spacexlifts.ui.base.navigation

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.stepanzalis.spacexlifts.ui.feature.company.CompanyScreen
import cz.stepanzalis.spacexlifts.ui.feature.launches.launch.LaunchesScreen
import cz.stepanzalis.spacexlifts.ui.feature.launches.rocketdetail.RocketDetailScreen

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
            LaunchesScreen(
                navController = navController,
                isExpandedScreen = isExpandedScreen,
                scaffoldState = rememberScaffoldState(),
                openDrawer = openDrawer
            )
        }
        composable(SpaceXNavigation.Company) {
            CompanyScreen(
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer,
                scaffoldState = rememberScaffoldState(),
            )
        }
        composable(
            SpaceXNavigation.RocketDetail + NavArg.IdArg,
            arguments = listOf(navArgument(NavArg.Id) { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString(NavArg.Id)
            RocketDetailScreen(id = id ?: "", navController)
        }
    }
}


object NavArg {
    const val IdArg = "/{id}"
    const val Id = "id"
}