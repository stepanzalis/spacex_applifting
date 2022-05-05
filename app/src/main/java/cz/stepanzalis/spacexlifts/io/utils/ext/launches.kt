package cz.stepanzalis.spacexlifts.io.utils.ext

import cz.stepanzalis.spacexlifts.io.db.entities.RocketLaunchEntity
import cz.stepanzalis.spacexlifts.io.models.launches.RocketLaunchesVo

fun RocketLaunchEntity.fromEntityToModel(): RocketLaunchesVo {
    return RocketLaunchesVo(
        id = id,
        rocketId = rocketId,
        rocketName = rocketName,
        success = successLaunch ?: upcomingLaunch,
        upcoming = upcomingLaunch,
        detail = detail ?: "-",
        flightNumber = flightNumber.toString(),
        date = launchDate.inMillis.parseToHumanReadableDate()
    )
}