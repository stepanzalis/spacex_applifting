package cz.stepanzalis.spacexlifts.io.services

import cz.stepanzalis.spacexlifts.io.response.CompanyInfoResponse
import cz.stepanzalis.spacexlifts.io.response.Rocket
import cz.stepanzalis.spacexlifts.io.response.RocketLaunchResponse
import retrofit2.http.GET

interface SpaceXApiService {

    @GET("/company")
    suspend fun fetchCompanyInfo(): CompanyInfoResponse

    @GET("/launches/latest")
    suspend fun fetchLatestRocketLaunches(): List<RocketLaunchResponse>

    @GET("/rockets")
    suspend fun fetchRockets(): List<Rocket>
}