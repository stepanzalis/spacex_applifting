package cz.stepanzalis.spacexlifts.io.repositories

import cz.stepanzalis.spacexlifts.io.db.dao.RocketDao
import cz.stepanzalis.spacexlifts.io.db.dao.RocketLaunchDao
import cz.stepanzalis.spacexlifts.io.services.SpaceXApiService

class SpaceXRepo(
    private val rocketLaunchDao: RocketLaunchDao,
    private val rocketDao: RocketDao,
    private val spaceXApiService: SpaceXApiService,
) {

    suspend fun fetchCompanyInfo() = spaceXApiService.fetchCompanyInfo()

    suspend fun fetchRocketsWithLaunches() {
        val rockets = spaceXApiService.fetchRockets()
        val launches = spaceXApiService.fetchLatestRocketLaunches()
    }
}