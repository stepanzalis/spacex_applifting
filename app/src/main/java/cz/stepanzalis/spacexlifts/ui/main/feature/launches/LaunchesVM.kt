package cz.stepanzalis.spacexlifts.ui.main.feature.launches

import androidx.lifecycle.ViewModel
import cz.stepanzalis.spacexlifts.io.repositories.SpaceXRepo

class LaunchesVM(
    private val spaceXRepo: SpaceXRepo,
): ViewModel() {
}