package cz.stepanzalis.spacexlifts.ui.feature.launches.launch

import androidx.lifecycle.viewModelScope
import cz.stepanzalis.spacexlifts.io.base.BaseVM
import cz.stepanzalis.spacexlifts.io.base.Status
import cz.stepanzalis.spacexlifts.io.base.Success
import cz.stepanzalis.spacexlifts.io.repositories.SpaceXRepo
import cz.stepanzalis.spacexlifts.io.utils.ext.fromEntityToModel
import kotlinx.coroutines.flow.*

class LaunchesVM(
    private val spaceXRepo: SpaceXRepo,
) : BaseVM() {

    private val _viewState = MutableStateFlow(LaunchesState())

    val viewState = _viewState
        .map { it }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            _viewState.value
        )

    init {
        watchRocketLaunches()
        fetchRocketLaunches()
    }

    private fun fetchRocketLaunches() = launch { spaceXRepo.fetchRocketsWithLaunches() }

    private fun watchRocketLaunches(
        upcoming: Boolean? = null,
        thisYearInTimestamp: Long? = null
    ) {
        launch {
            spaceXRepo
                .getAllLaunches(upcoming, thisYearInTimestamp)
                .map { launches ->
                    _viewState.update {
                        it.copy(
                            status = Success(),
                            launches = launches.map { it.fromEntityToModel() },
                        )
                    }
                }.collect()
        }
    }

    override fun dismissErrorDialog() {
        launch { _viewState.update { it.copy(status = Status.Success()) } }
    }
}