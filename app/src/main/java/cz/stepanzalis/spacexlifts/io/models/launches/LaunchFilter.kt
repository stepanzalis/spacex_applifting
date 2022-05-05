package cz.stepanzalis.spacexlifts.io.models.launches

import androidx.annotation.StringRes
import cz.stepanzalis.spacexlifts.R

enum class LaunchFilter(@StringRes val stringRes: Int) {
    Upcoming(R.string.launches_filter_upcoming),
    OnlyThisYear(R.string.launches_filter_only_this_year)
}