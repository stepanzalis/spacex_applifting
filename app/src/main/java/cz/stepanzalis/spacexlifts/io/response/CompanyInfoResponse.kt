package cz.stepanzalis.spacexlifts.io.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompanyInfoResponse(
    @Json(name = "headquarters")
    val headquarters: Headquarters,
    @Json(name = "links")
    val links: Links,
    @Json(name = "name")
    val name: String,
    @Json(name = "founder")
    val founder: String,
    @Json(name = "founded")
    val founded: Int,
    @Json(name = "employees")
    val employees: Int,
    @Json(name = "vehicles")
    val vehicles: Int,
    @Json(name = "launch_sites")
    val launchSites: Int,
    @Json(name = "test_sites")
    val testSites: Int,
    @Json(name = "ceo")
    val ceo: String,
    @Json(name = "cto")
    val cto: String,
    @Json(name = "coo")
    val coo: String,
    @Json(name = "cto_propulsion")
    val ctoPropulsion: String,
    @Json(name = "valuation")
    val valuation: Long, // when valuation is too big that int is not enough 😱
    @Json(name = "summary")
    val summary: String,
    @Json(name = "id")
    val id: String
)

data class Headquarters(

    @Json(name = "address")
    val address: String,

    @Json(name = "city")
    val city: String,

    @Json(name = "state")
    val state: String,
)