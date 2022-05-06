package cz.stepanzalis.spacexlifts.ui.feature.launches.launch.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import cz.stepanzalis.spacexlifts.R
import cz.stepanzalis.spacexlifts.io.common.CardElevation
import cz.stepanzalis.spacexlifts.io.models.launches.RocketLaunchesVo
import cz.stepanzalis.spacexlifts.ui.theme.Shapes
import cz.stepanzalis.spacexlifts.ui.theme.SpaceXLiftsTheme
import cz.stepanzalis.spacexlifts.ui.theme.spacing

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
            .padding(top = MaterialTheme.spacing.medium),
        elevation = CardElevation,
    ) {
        with(item) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium)
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
                    Spacer(modifier = Modifier.padding(top = MaterialTheme.spacing.medium))
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

@Preview("Launch item - filled")
@Composable
fun PreviewLaunchesItem() {
    SpaceXLiftsTheme {
        Surface {
            LaunchItem(
                onItemClicked = {},
                item = RocketLaunchesVo(
                    id = "lorem",
                    detail = "Detaol",
                    date = "22. 6. 2022",
                    flightNumber = "1",
                    rocketName = "Falcon",
                    success = false,
                    rocketId = "1",
                    upcoming = false
                ),
            )
        }
    }
}