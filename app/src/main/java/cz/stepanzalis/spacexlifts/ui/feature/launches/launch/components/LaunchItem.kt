package cz.stepanzalis.spacexlifts.ui.feature.launches.launch.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cz.stepanzalis.spacexlifts.R
import cz.stepanzalis.spacexlifts.io.common.CardElevation
import cz.stepanzalis.spacexlifts.io.common.SpacingM
import cz.stepanzalis.spacexlifts.io.models.launches.RocketLaunchesVo
import cz.stepanzalis.spacexlifts.ui.theme.Shapes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LaunchItem(
    item: RocketLaunchesVo,
    onItemClicked: () -> Unit,
) {

    Card(
        shape = Shapes.large,
        onClick = { onItemClicked.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SpacingM),
        elevation = CardElevation,
    ) {
        with(item) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpacingM)
            ) {
                Column {
                    Row {
                        Text(
                            stringResource(
                                id = R.string.launches_flight_number,
                                formatArgs = arrayOf(flightNumber)
                            ),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(date, fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.padding(top = SpacingM))
                    Text(
                        stringResource(
                            id = R.string.launches_rocket_name,
                            formatArgs = arrayOf(rocketName)
                        ),
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}