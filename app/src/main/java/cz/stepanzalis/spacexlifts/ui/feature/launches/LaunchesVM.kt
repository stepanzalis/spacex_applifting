package cz.stepanzalis.spacexlifts.ui.feature.launches

import cz.stepanzalis.spacexlifts.io.base.BaseVM
import cz.stepanzalis.spacexlifts.io.base.Status
import cz.stepanzalis.spacexlifts.io.repositories.SpaceXRepo
import kotlinx.coroutines.flow.MutableStateFlow

class LaunchesVM(
    private val spaceXRepo: SpaceXRepo,
) : BaseVM() {

    private val viewState = MutableStateFlow(LaunchesState())

    init {
        fetchRocketLaunches()
    }

    private fun fetchRocketLaunches() {
        launch(
            block = {
                val launches = spaceXRepo.fetchRocketsWithLaunches()
                // viewState.emit(viewState.value.copy(launches = launches.))
            },
        )
    }

    override fun dismissErrorDialog() {
        launch {
            viewState.emit(value = viewState.value.copy(status = Status.Success()))
        }
    }
}