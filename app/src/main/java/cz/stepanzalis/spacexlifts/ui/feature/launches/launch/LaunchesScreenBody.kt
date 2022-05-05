package cz.stepanzalis.spacexlifts.ui.feature.launches.launch

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cz.stepanzalis.spacexlifts.R
import cz.stepanzalis.spacexlifts.io.common.SpacingM
import cz.stepanzalis.spacexlifts.io.models.launches.RocketLaunchesVo
import cz.stepanzalis.spacexlifts.ui.base.BaseScreen
import cz.stepanzalis.spacexlifts.ui.base.navigation.SpaceXNavigation
import cz.stepanzalis.spacexlifts.ui.feature.launches.launch.components.LaunchItem
import org.koin.androidx.compose.getViewModel

@Composable
fun LaunchesScreenBody(
    modifier: Modifier,
    navController: NavController,
    launchesVM: LaunchesVM = getViewModel(),
) {

    val viewState = launchesVM.viewState.collectAsState()

    BaseScreen(
        status = viewState.value.status,
        vm = launchesVM,
        showFullscreenLoading = true
    ) {
        LaunchesList(
            navController = navController,
            launches = viewState.value.launches,
            modifier = modifier,
        )
    }
}

@Composable
fun LaunchesList(
    navController: NavController,
    launches: List<RocketLaunchesVo>,
    modifier: Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = SpacingM),
        modifier = modifier.fillMaxHeight()
    ) {
        when (launches.isEmpty()) {
            true -> item { EmptyLaunchView(modifier) }
            false -> {
                items(launches) { item ->
                    LaunchItem(
                        item = item,
                        onItemClicked = {
                            navController.navigate(SpaceXNavigation.RocketDetail )
                        },
                    )
                }
            }
        }
    }
}


@Composable
fun EmptyLaunchView(modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(SpacingM)
    ) {
        Text(stringResource(R.string.launches_empty_launches), fontWeight = FontWeight.Bold)
    }

}



