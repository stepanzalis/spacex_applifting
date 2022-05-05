package cz.stepanzalis.spacexlifts.io.db.dao

import androidx.room.Dao
import androidx.room.Query
import cz.stepanzalis.spacexlifts.io.db.entities.RocketEntity
import cz.stepanzalis.spacexlifts.io.response.Rocket
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao: BaseDao<RocketEntity> {

    @Query("SELECT * FROM rocket WHERE id = :id")
    fun getByRocketId(id: String): Flow<RocketEntity>
}