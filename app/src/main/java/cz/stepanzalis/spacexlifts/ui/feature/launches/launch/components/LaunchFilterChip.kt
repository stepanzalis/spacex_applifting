package cz.stepanzalis.spacexlifts.ui.feature.launches.launch.components

import androidx.compose.material.Surface
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.stepanzalis.spacexlifts.io.common.CardElevation
import cz.stepanzalis.spacexlifts.io.common.SpacingS
import cz.stepanzalis.spacexlifts.io.models.launches.LaunchFilter
import cz.stepanzalis.spacexlifts.ui.theme.SpaceXLiftsTheme

@Composable
fun LaunchFilterChip(
    filter: LaunchFilter,
    isSelected: Boolean = false,
    onSelectedFilterChanged: (LaunchFilter) -> Unit,
) {
    Surface(
        modifier = Modifier.padding(end = SpacingS),
        elevation = CardElevation,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) MaterialTheme.colors.primary else Color.LightGray,
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectedFilterChanged(filter)
                }
            )
        ) {
            Text(
                text = stringResource(id = filter.stringRes),
                style = MaterialTheme.typography.body2,
                color = if (isSelected) Color.White else MaterialTheme.colors.primary,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview("Filter item contents - selected")
@Composable
fun PreviewLaunchesFilterItemEnabled() {
    PreviewLaunchesFilterItem(true)
}

@Preview("Filter item contents - not selected")
@Composable
fun PreviewLaunchesFilterItemDisabled() {
    PreviewLaunchesFilterItem(false)
}

@Composable
fun PreviewLaunchesFilterItem(selected: Boolean) {
    SpaceXLiftsTheme {
        Surface {
            LaunchFilterChip(
                isSelected = selected,
                filter = LaunchFilter.OnlyThisYear,
                onSelectedFilterChanged = {}
            )
        }
    }
}