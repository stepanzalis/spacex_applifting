package cz.stepanzalis.spacexlifts.ui.feature.launches.route

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import cz.stepanzalis.spacexlifts.ui.feature.launches.LaunchesScreen
import cz.stepanzalis.spacexlifts.ui.feature.launches.LaunchesVM

@Composable
fun LaunchesRoute(
    launchesVM: LaunchesVM,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    LaunchesScreen(
        isExpandedScreen = isExpandedScreen,
        openDrawer = openDrawer,
        scaffoldState = scaffoldState
    )
}