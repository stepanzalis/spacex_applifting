package cz.stepanzalis.spacexlifts.io.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RocketLaunchResponse(
    @Json(name = "flight_number")
    val flightNumber: Int,
    @Json(name = "mission_name")
    val missionName: String,
    @Json(name = "launch_year")
    val launchYear: Int,
    @Json(name = "launch_date_utc")
    val launchDateUTC: String,
    @Json(name = "rocket")
    val rocket: Rocket,
    @Json(name = "details")
    val details: String,
    @Json(name = "launch_success")
    val launchSuccess: Boolean,
    @Json(name = "links")
    val links: Links,
)

@JsonClass(generateAdapter = true)
data class Rocket(
    @Json(name = "rocket_id")
    val id: String,
    @Json(name = "rocket_name")
    val name: String,
    @Json(name = "rocket_type")
    val type: String,
)

