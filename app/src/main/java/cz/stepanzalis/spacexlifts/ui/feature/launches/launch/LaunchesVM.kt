package cz.stepanzalis.spacexlifts.ui.feature.launches.launch

import androidx.lifecycle.viewModelScope
import cz.stepanzalis.spacexlifts.io.base.BaseVM
import cz.stepanzalis.spacexlifts.io.base.Status
import cz.stepanzalis.spacexlifts.io.base.Success
import cz.stepanzalis.spacexlifts.io.models.launches.LaunchFilter
import cz.stepanzalis.spacexlifts.io.repositories.SpaceXRepo
import cz.stepanzalis.spacexlifts.io.utils.ext.fromEntityToModel
import cz.stepanzalis.spacexlifts.io.utils.ext.inSeconds
import kotlinx.coroutines.flow.*
import java.util.*

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

    fun toggleFilter(filter: LaunchFilter) = launch {
        val filters = _viewState.value.filters.apply { addOrRemove(filter)  }
        _viewState.update { it.copy(filters = it.filters) }
        filterLaunches(filters)
    }

    private fun filterLaunches(filters: MutableSet<LaunchFilter>) {
        val upcomingFilter = filters.contains(LaunchFilter.Upcoming)
        val yearFilter = if (filters.contains(LaunchFilter.OnlyThisYear)) thisYearInMillis else null
        watchRocketLaunches(upcomingFilter, yearFilter)
    }

    private val thisYearInMillis: Long
        get() {
            return Calendar.getInstance().apply {
                set(Calendar.YEAR, this.get(Calendar.YEAR))
                set(Calendar.MONTH, 0)
                set(Calendar.DATE, 1)
            }.timeInMillis.inSeconds
        }

    override fun dismissErrorDialog() {
        launch { _viewState.update { it.copy(status = Status.Success()) } }
    }

    private fun MutableSet<LaunchFilter>.addOrRemove(filter: LaunchFilter) {
        if (contains(filter)) remove(filter)
        else add(filter)
    }
}