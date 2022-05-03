package cz.stepanzalis.spacexlifts.ui.main.base.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.stepanzalis.spacexlifts.ui.main.feature.company.route.CompanyRoute
import cz.stepanzalis.spacexlifts.ui.main.feature.launches.route.LaunchesRoute
import org.koin.androidx.compose.getViewModel

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
                launchesVM =  getViewModel(),
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
        composable(SpaceXNavigation.Company) {
            CompanyRoute(
                companyVM = getViewModel(),
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
    }
}
