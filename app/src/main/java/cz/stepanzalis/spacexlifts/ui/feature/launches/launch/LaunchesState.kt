package cz.stepanzalis.spacexlifts.ui.feature.launches.launch

import cz.stepanzalis.spacexlifts.io.base.Status
import cz.stepanzalis.spacexlifts.io.models.launches.LaunchFilter
import cz.stepanzalis.spacexlifts.io.models.launches.RocketLaunchesVo
import cz.stepanzalis.spacexlifts.ui.base.ViewState

data class LaunchesState(
    val launches: List<RocketLaunchesVo> = emptyList(),
    val filters: MutableSet<LaunchFilter> = mutableSetOf(),
    override val status: Status = Status.Loading,
) : ViewState.State(status) {
    companion object
}