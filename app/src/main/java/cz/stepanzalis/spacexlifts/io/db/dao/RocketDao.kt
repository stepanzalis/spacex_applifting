package cz.stepanzalis.spacexlifts.io.db.dao

import androidx.room.Dao
import androidx.room.Query
import cz.stepanzalis.spacexlifts.io.db.entities.RocketEntity

@Dao
interface RocketDao: BaseDao<RocketEntity> {

    @Query("SELECT * FROM rocket WHERE id = :id")
    suspend fun getByRocketId(id: String): RocketEntity
}