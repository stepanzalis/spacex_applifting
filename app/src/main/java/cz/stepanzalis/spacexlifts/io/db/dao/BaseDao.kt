package cz.stepanzalis.spacexlifts.io.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<Entity> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIgnoringExisting(item: Entity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIgnoringExisting(items: List<Entity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Entity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Entity>): List<Long>

    @Delete
    suspend fun delete(item: Entity)

    @Delete
    suspend fun deleteAll(items: List<Entity>)

    @Update
    suspend fun update(item: Entity)

    @Update
    suspend fun update(items: List<Entity>)

    companion object {
        const val ConflictingId = -1L
    }
}
