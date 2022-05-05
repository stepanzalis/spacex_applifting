package cz.stepanzalis.spacexlifts.ui.feature.launches.rocketdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.stepanzalis.spacexlifts.R
import cz.stepanzalis.spacexlifts.io.common.SpacingM
import cz.stepanzalis.spacexlifts.io.common.SpacingS
import cz.stepanzalis.spacexlifts.ui.base.BaseScreen
import cz.stepanzalis.spacexlifts.ui.feature.company.components.TitleDescription

@Composable
fun RocketDetailBody(
    rocketVM: RocketDetailVM,
    modifier: Modifier,
) {

    val viewState = rocketVM.viewState.collectAsState()

    BaseScreen(
        status = viewState.value.status,
        vm = rocketVM,
        showFullscreenLoading = false
    ) {
        with(viewState.value.rocket) {
            Column(modifier = modifier.padding(start = SpacingM, end = SpacingM, top = SpacingM)) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                ) {
                    TitleDescription(title = stringResource(R.string.rocket_name)) {
                        Text(text = name)
                    }
                    Spacer(modifier = modifier.padding(horizontal = SpacingS))
                    TitleDescription(title = stringResource(R.string.rocket_stages)) {
                        Text(text = stages)
                    }
                }
                Spacer(modifier = modifier.padding(vertical = SpacingS))
                TitleDescription(title = stringResource(R.string.rocket_first_flight)) {
                    Text(text = firstFlight)
                }
                Spacer(modifier = modifier.padding(vertical = SpacingS))
                TitleDescription(title = stringResource(R.string.rocket_description)) {
                    Text(text = description)
                }
            }
        }
    }
}