package cz.stepanzalis.spacexlifts.io.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "mission_patch")
    val missionPatchUrl: String?,
    @Json(name = "article_link")
    val articleUrl: String?,
)