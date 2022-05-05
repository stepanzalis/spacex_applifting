package cz.stepanzalis.spacexlifts.io.models.launches

data class RocketLaunchesVo(
    val id: String,
    val rocketId: String,
    val rocketName: String,
    val success: Boolean,
    val detail: String,
    val flightNumber: Int,
    val date: String,
    val upcoming: Boolean,
)