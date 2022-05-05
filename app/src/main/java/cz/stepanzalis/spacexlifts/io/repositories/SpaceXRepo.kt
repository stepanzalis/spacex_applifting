package cz.stepanzalis.spacexlifts.io.repositories

import cz.stepanzalis.spacexlifts.io.db.dao.RocketDao
import cz.stepanzalis.spacexlifts.io.db.dao.RocketLaunchDao
import cz.stepanzalis.spacexlifts.io.db.entities.RocketEntity
import cz.stepanzalis.spacexlifts.io.db.entities.RocketLaunchEntity
import cz.stepanzalis.spacexlifts.io.services.SpaceXApiService
import kotlinx.coroutines.flow.Flow

class SpaceXRepo(
    private val rocketLaunchDao: RocketLaunchDao,
    private val rocketDao: RocketDao,
    private val spaceXApiService: SpaceXApiService,
) {

    suspend fun fetchCompanyInfo() = spaceXApiService.fetchCompanyInfo()

    suspend fun fetchRocketsWithLaunches() {
        val rockets = saveRockets()
        saveLaunchesWithRocketName(rockets)
    }

    private suspend fun saveRockets(): List<RocketEntity> {
        val rockets = spaceXApiService.fetchRockets().map { it.toEntity() }
        rocketDao.insert(rockets)
        return rockets
    }

    /**
     * We need to save rocket's name to launch entity (to show it quickly in UI)
     * It's nicer to group it and find the rocket name for the whole group instead for all items separately
     * ! When rocket id isn't found, empty string is added
     */
    private suspend fun saveLaunchesWithRocketName(rockets: List<RocketEntity>) {
        val launchesWithRocketName = spaceXApiService.fetchAllRocketLaunches()
            .groupBy { it.rocketId }
            .map { entry ->
                val rocketName = rockets.firstOrNull { it.id == entry.key }?.name ?: ""
                entry.value.map { it.toEntity(rocketName = rocketName) }
            }
            .flatten()

        rocketLaunchDao.insert(launchesWithRocketName)
    }

    fun getAllLaunches(upcoming: Boolean? = null, thisYearInTimestamp: Long?): Flow<List<RocketLaunchEntity>> {
        return rocketLaunchDao.getLaunches(upcoming, thisYearInTimestamp)
    }

    fun getRocketDetail(id: String): Flow<RocketEntity> = rocketDao.getByRocketId(id)
}