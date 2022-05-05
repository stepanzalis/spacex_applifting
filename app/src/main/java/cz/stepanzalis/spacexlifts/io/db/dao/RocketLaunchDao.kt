package cz.stepanzalis.spacexlifts.io.db.dao

import androidx.room.Dao
import androidx.room.Query
import cz.stepanzalis.spacexlifts.io.db.entities.RocketEntity
import cz.stepanzalis.spacexlifts.io.db.entities.RocketLaunchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketLaunchDao : BaseDao<RocketLaunchEntity> {

    @Query(
        """SELECT * FROM rocket_launch 
        WHERE (:upcoming IS NULL OR upcoming LIKE :upcoming) 
        AND (:thisYearInTimestamp IS NULL OR launch_date >= :thisYearInTimestamp) """
    )
    fun getLaunches(upcoming: Boolean?, thisYearInTimestamp: Long?): Flow<List<RocketLaunchEntity>>

}