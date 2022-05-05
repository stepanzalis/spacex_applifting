package cz.stepanzalis.spacexlifts.ui.feature.launches.launch.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.stepanzalis.spacexlifts.R
import cz.stepanzalis.spacexlifts.io.common.CardElevation
import cz.stepanzalis.spacexlifts.io.common.SpacingM
import cz.stepanzalis.spacexlifts.io.models.launches.RocketLaunchesVo
import cz.stepanzalis.spacexlifts.ui.feature.company.components.TitleDescription
import cz.stepanzalis.spacexlifts.ui.theme.Shapes

@Composable
fun LaunchItem(
    item: RocketLaunchesVo,
    onItemClicked: () -> Unit,
) {

    Card(
        shape = Shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SpacingM)
            .clickable { onItemClicked.invoke() },
        elevation = CardElevation,
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(SpacingM)
        ) {
            Column {
                TitleDescription(
                    title = stringResource(R.string.company_address),
                ) {

                }
                Spacer(modifier = Modifier.padding(top = SpacingM))
                TitleDescription(
                    title = stringResource(R.string.company_head),
                ) {
                    Column {


                    }
                }
            }
        }
    }
}