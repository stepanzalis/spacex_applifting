package cz.stepanzalis.spacexlifts.io.db

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.stepanzalis.spacexlifts.io.db.SpaceXDatabase.Companion.Version
import cz.stepanzalis.spacexlifts.io.db.dao.RocketDao
import cz.stepanzalis.spacexlifts.io.db.dao.RocketLaunchDao
import cz.stepanzalis.spacexlifts.io.db.entities.RocketEntity
import cz.stepanzalis.spacexlifts.io.db.entities.RocketLaunchEntity

@Database(
    version = Version,
    exportSchema = false,
    entities = [
        RocketLaunchEntity::class,
        RocketEntity::class
    ]
)
abstract class SpaceXDatabase : RoomDatabase() {

    abstract val rocketLaunchDao: RocketLaunchDao
    abstract val rocketDao: RocketDao

    companion object {
        const val Version: Int = 1
        const val Name = "SpaceXDatabase"
    }
}
