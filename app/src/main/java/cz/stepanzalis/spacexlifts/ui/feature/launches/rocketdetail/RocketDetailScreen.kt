package cz.stepanzalis.spacexlifts.ui.feature.launches.rocketdetail

import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import cz.stepanzalis.spacexlifts.ui.base.BaseScreen
import cz.stepanzalis.spacexlifts.ui.feature.launches.launch.LaunchesList
import org.koin.androidx.compose.getViewModel

@Composable
fun RocketDetailScreen(
    id: String,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    rocketVM: RocketDetailVM = getViewModel(),
) {
    Text("Not implemented")
}