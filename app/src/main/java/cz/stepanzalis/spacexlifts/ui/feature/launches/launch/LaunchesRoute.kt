package cz.stepanzalis.spacexlifts.ui.feature.launches.launch

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import cz.stepanzalis.spacexlifts.ui.feature.launches.launch.LaunchesScreen
import cz.stepanzalis.spacexlifts.ui.feature.launches.launch.LaunchesVM

@Composable
fun LaunchesRoute(
    isExpandedScreen: Boolean,
    navController: NavController,
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    LaunchesScreen(
        navController = navController,
        isExpandedScreen = isExpandedScreen,
        openDrawer = openDrawer,
        scaffoldState = scaffoldState
    )
}