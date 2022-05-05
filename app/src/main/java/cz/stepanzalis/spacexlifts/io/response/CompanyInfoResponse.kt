package cz.stepanzalis.spacexlifts.io.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompanyInfoResponse(
    val id: String,
    val headquarters: Headquarters,
    val links: Links,
    val name: String,
    val founder: String,
    val founded: Int,
    val employees: Int,
    val vehicles: Int,
    @Json(name = "launch_sites")
    val launchSites: Int,
    @Json(name = "test_sites")
    val testSites: Int,
    val ceo: String,
    val cto: String,
    val coo: String,
    @Json(name = "cto_propulsion")
    val ctoPropulsion: String,
    val valuation: Long, // when valuation is too big that int is not enough 😱
    val summary: String,
)

@JsonClass(generateAdapter = true)
data class Headquarters(
    val address: String,
    val city: String,
    val state: String,
)