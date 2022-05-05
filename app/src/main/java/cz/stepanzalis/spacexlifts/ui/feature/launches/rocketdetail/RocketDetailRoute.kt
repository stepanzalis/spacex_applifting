package cz.stepanzalis.spacexlifts.ui.feature.launches.rocketdetail

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import cz.stepanzalis.spacexlifts.ui.feature.launches.rocketdetail.RocketDetailScreen
import cz.stepanzalis.spacexlifts.ui.feature.launches.rocketdetail.RocketDetailVM

@Composable
fun RocketDetailRoute(
    id: String,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    RocketDetailScreen(
        id = id,
        isExpandedScreen = isExpandedScreen,
        openDrawer = openDrawer,
        scaffoldState = scaffoldState
    )
}