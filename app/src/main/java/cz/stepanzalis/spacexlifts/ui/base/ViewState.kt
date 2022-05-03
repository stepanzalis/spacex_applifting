package cz.stepanzalis.spacexlifts.ui.base

import cz.stepanzalis.spacexlifts.io.base.Status

sealed interface ViewState {
    open class State(open val status: Status) : ViewState
}