package cz.stepanzalis.spacexlifts.io.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import cz.stepanzalis.spacexlifts.io.db.entities.RocketEntity
import cz.stepanzalis.spacexlifts.io.db.entities.RocketLaunchEntity
import java.util.*

@JsonClass(generateAdapter = true)
data class RocketLaunchResponse(
    val id: String,
    @Json(name = "flight_number")
    val flightNumber: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "date_unix")
    val launchDateTimestamp: Long,
    @Json(name = "rocket")
    val rocketId: String,
    @Json(name = "success")
    val launchSuccess: Boolean?, // success param is null when it's upcoming launch
    val links: Links,
    @Json(name = "upcoming")
    val upcomingLaunch: Boolean,
    @Json(name = "details")
    val detail: String?,
) {
    fun toEntity() = RocketLaunchEntity(
        id = id,
        rocketName = "", // to prevent side effect, name is put later
        rocketId = rocketId,
        flightNumber = flightNumber,
        successLaunch = launchSuccess,
        launchDate = launchDateTimestamp,
        upcomingLaunch = upcomingLaunch,
        detail = detail
    )
}

@JsonClass(generateAdapter = true)
data class Rocket(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "stages")
    val stages: Int,
    @Json(name = "cost_per_launch")
    val costPerLaunch: Double,
    @Json(name = "description")
    val description: String,
    @Json(name = "flickr_images")
    val images: List<String>,
) {

    fun toEntity() = RocketEntity(
        id = id,
        name = name,
        stages = stages,
        costPerLaunch = costPerLaunch,
        firstFlight = Date().time,
        description = description
    )
}

