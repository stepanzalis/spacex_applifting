package cz.stepanzalis.spacexlifts.io.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.stepanzalis.spacexlifts.io.db.entities.RocketEntity.Companion.TableName

@Entity(tableName = TableName)
data class RocketEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "stages")
    val stages: Int,

    @ColumnInfo(name = "cost_per_launch")
    val costPerLaunch: Double,

    @ColumnInfo(name = "first_flight")
    val firstFlight: Long,

    @ColumnInfo(name = "country")
    val country: String,

    @ColumnInfo(name = "description")
    val description: String,
) {

    companion object {
        const val TableName = "rocket"
    }
}