package cz.stepanzalis.spacexlifts.io.repositories

import cz.stepanzalis.spacexlifts.io.db.dao.RocketDao
import cz.stepanzalis.spacexlifts.io.db.dao.RocketLaunchDao
import cz.stepanzalis.spacexlifts.io.db.entities.RocketEntity
import cz.stepanzalis.spacexlifts.io.db.entities.RocketLaunchEntity
import cz.stepanzalis.spacexlifts.io.response.CompanyInfoResponse
import cz.stepanzalis.spacexlifts.io.services.SpaceXApiService
import kotlinx.coroutines.flow.Flow

interface ISpaceXRepo {
    suspend fun fetchCompanyInfo(): CompanyInfoResponse
    suspend fun fetchRocketsWithLaunches()
    fun getAllLaunches(
        upcoming: Boolean? = null,
        thisYearInTimestamp: Long? = null
    ): Flow<List<RocketLaunchEntity>>

    suspend fun getRocketDetail(id: String): RocketEntity
}

class SpaceXRepo(
    private val rocketLaunchDao: RocketLaunchDao,
    private val rocketDao: RocketDao,
    private val spaceXApiService: SpaceXApiService,
) : ISpaceXRepo {

    override suspend fun fetchCompanyInfo() = spaceXApiService.fetchCompanyInfo()

    override suspend fun fetchRocketsWithLaunches() {
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
     * It's nicer to group them and find the rocket name for the whole group instead for all items separately
     * ! When rocket id isn't found, empty string is added
     */
    private suspend fun saveLaunchesWithRocketName(rockets: List<RocketEntity>) {
        val launchesWithRocketName = spaceXApiService.fetchAllRocketLaunches()
            .groupBy { it.rocketId }
            .map { entry ->
                val rocketName = rockets.firstOrNull { it.id == entry.key }?.name ?: ""
                entry.value.map { it.toEntity().copy(rocketName = rocketName) }
            }
            .flatten()

        rocketLaunchDao.insert(launchesWithRocketName)
    }

    override fun getAllLaunches(
        upcoming: Boolean?,
        thisYearInTimestamp: Long?
    ): Flow<List<RocketLaunchEntity>> {
        return rocketLaunchDao.getLaunches(upcoming, thisYearInTimestamp)
    }

    override suspend fun getRocketDetail(id: String): RocketEntity = rocketDao.getByRocketId(id)
}