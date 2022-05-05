package cz.stepanzalis.spacexlifts.io.utils.ext

import cz.stepanzalis.spacexlifts.io.db.entities.RocketEntity
import cz.stepanzalis.spacexlifts.io.models.rocket.RocketVo

fun RocketEntity.fromEntity(): RocketVo {
    return RocketVo(
        name = name,
        stages = stages.toString(),
        costPerLaunch = costPerLaunch,
        description = description,
        firstFlight = firstFlight.parseToHumanReadableDateTime()
    )
}