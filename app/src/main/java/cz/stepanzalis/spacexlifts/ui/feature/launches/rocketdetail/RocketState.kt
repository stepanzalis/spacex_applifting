package cz.stepanzalis.spacexlifts.ui.feature.launches.rocketdetail

import cz.stepanzalis.spacexlifts.io.base.Status
import cz.stepanzalis.spacexlifts.io.models.rocket.RocketVo
import cz.stepanzalis.spacexlifts.ui.base.ViewState

data class RocketState(
    val rocket: RocketVo = RocketVo.init(),
    override val status: Status = Status.Loading,
) : ViewState.State(status) {
    companion object
}