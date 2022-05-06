package cz.stepanzalis.spacexlifts.ui.feature.launches.rocketdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import cz.stepanzalis.spacexlifts.R
import androidx.compose.ui.res.stringResource
import cz.stepanzalis.spacexlifts.io.common.RocketDetailBodyTestTag
import cz.stepanzalis.spacexlifts.ui.base.StatusScreen
import cz.stepanzalis.spacexlifts.ui.feature.company.components.TitleDescription
import cz.stepanzalis.spacexlifts.ui.theme.spacing

@Composable
fun RocketDetailBody(
    rocketVM: RocketDetailVM,
    modifier: Modifier,
) {

    val viewState = rocketVM.viewState.collectAsState()

    StatusScreen(
        vm = rocketVM,
        showFullscreenLoading = false
    ) {
        with(viewState.value.rocket) {
            Column(
                modifier = modifier.padding(
                    start = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ).testTag(RocketDetailBodyTestTag)
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                ) {
                    TitleDescription(title = stringResource(R.string.rocket_name)) {
                        Text(text = name)
                    }
                    Spacer(modifier = modifier.padding(horizontal = MaterialTheme.spacing.small))
                    TitleDescription(title = stringResource(R.string.rocket_stages)) {
                        Text(text = stages)
                    }
                }
                Spacer(modifier = modifier.padding(vertical = MaterialTheme.spacing.small))
                TitleDescription(title = stringResource(R.string.rocket_first_flight)) {
                    Text(text = firstFlight)
                }
                Spacer(modifier = modifier.padding(vertical = MaterialTheme.spacing.small))
                TitleDescription(title = stringResource(R.string.rocket_description)) {
                    Text(text = description)
                }
            }
        }
    }
}