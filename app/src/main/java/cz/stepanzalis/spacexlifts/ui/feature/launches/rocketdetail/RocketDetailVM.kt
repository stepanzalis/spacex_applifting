package cz.stepanzalis.spacexlifts.ui.feature.launches.rocketdetail

import androidx.lifecycle.viewModelScope
import cz.stepanzalis.spacexlifts.io.base.BaseVM
import cz.stepanzalis.spacexlifts.io.base.Success
import cz.stepanzalis.spacexlifts.io.repositories.SpaceXRepo
import cz.stepanzalis.spacexlifts.io.utils.ext.fromEntity
import kotlinx.coroutines.flow.*

class RocketDetailVM(
    private val rocketId: String,
    private val spaceXRepo: SpaceXRepo,
) : BaseVM() {

    private val _viewState = MutableStateFlow(RocketState())

    val viewState = _viewState
        .map { it }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            _viewState.value
        )

    init {
        getRocketDetail()
    }

    private fun getRocketDetail() {
        launch {
            val rocket = spaceXRepo.getRocketDetail(rocketId).fromEntity()
            _viewState.update { it.copy(rocket = rocket, status = Success()) }
        }
    }

    override fun dismissErrorDialog() {
        launch { _viewState.update { it.copy(status = Success()) } }
    }
}