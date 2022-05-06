package cz.stepanzalis.spacexlifts.ui.feature.launches.launch

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cz.stepanzalis.spacexlifts.R
import cz.stepanzalis.spacexlifts.io.base.Loading
import cz.stepanzalis.spacexlifts.io.base.Status
import cz.stepanzalis.spacexlifts.io.models.launches.LaunchFilter
import cz.stepanzalis.spacexlifts.io.models.launches.RocketLaunchesVo
import cz.stepanzalis.spacexlifts.ui.base.StatusScreen
import cz.stepanzalis.spacexlifts.ui.base.navigation.SpaceXNavigation
import cz.stepanzalis.spacexlifts.ui.feature.launches.launch.components.LaunchFilterChip
import cz.stepanzalis.spacexlifts.ui.feature.launches.launch.components.LaunchItem
import cz.stepanzalis.spacexlifts.ui.theme.spacing
import org.koin.androidx.compose.getViewModel

@Composable
fun LaunchesScreenBody(
    modifier: Modifier,
    navController: NavController,
    launchesVM: LaunchesVM = getViewModel(),
) {

    val viewState = launchesVM.viewState.collectAsState()

    StatusScreen(vm = launchesVM) {
        Row(modifier = modifier.padding(horizontal = MaterialTheme.spacing.medium)) {
            LaunchFilter.values().forEach {
                LaunchFilterChip(
                    filter = it,
                    isSelected = viewState.value.filters.contains(it),
                    onSelectedFilterChanged = launchesVM::toggleFilter
                )
            }
        }
        LaunchesList(
            navController = navController,
            launches = viewState.value.launches,
            status = viewState.value.status,
            modifier = modifier,
        )
    }
}

@Composable
fun LaunchesList(
    navController: NavController,
    launches: List<RocketLaunchesVo>,
    status: Status,
    modifier: Modifier,
) {
    val canShowEmptyView = launches.isEmpty() && status != Loading

    LazyColumn(
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium),
        modifier = modifier.padding(top = 40.dp),
    ) {
        when {
            canShowEmptyView -> item { EmptyLaunchView() }
            else -> {
                items(launches) { item ->
                    LaunchItem(
                        item = item,
                        onItemClicked = {
                            navController.navigate(SpaceXNavigation.RocketDetail + "/${item.rocketId}")
                        },
                    )
                }
            }
        }
    }
}


@Composable
fun EmptyLaunchView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
    ) {
        Text(stringResource(R.string.launches_empty_launches), fontWeight = FontWeight.Bold)
    }

}



