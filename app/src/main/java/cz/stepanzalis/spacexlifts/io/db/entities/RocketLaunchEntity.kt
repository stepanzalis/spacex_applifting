package cz.stepanzalis.spacexlifts.io.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.stepanzalis.spacexlifts.io.db.entities.RocketLaunchEntity.Companion.TableName

@Entity(tableName = TableName)
data class RocketLaunchEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "rocket_id")
    val rocketId: String,

    @ColumnInfo(name = "rocketName")
    val rocketName: String,

    @ColumnInfo(name = "success_launch")
    val successLaunch: String,

    @ColumnInfo(name = "flight_number")
    val flightNumber: Int,

    @ColumnInfo(name = "launch_data")
    val launchDate: Long,
) {

    companion object {
        const val TableName = "rocket_launch"
    }
}