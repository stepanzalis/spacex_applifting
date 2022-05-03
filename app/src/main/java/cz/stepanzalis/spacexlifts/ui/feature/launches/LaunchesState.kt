package cz.stepanzalis.spacexlifts.ui.feature.launches

import cz.stepanzalis.spacexlifts.io.base.Status
import cz.stepanzalis.spacexlifts.ui.base.ViewState

data class LaunchesState(
    val launches: List<String> = emptyList(),
    override val status: Status = Status.Loading,
) : ViewState.State(status) {
    companion object
}