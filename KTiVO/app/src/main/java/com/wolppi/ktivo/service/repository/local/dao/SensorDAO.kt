package com.wolppi.ktivo.service.repository.local.dao

import androidx.room.*
import com.wolppi.ktivo.service.model.SensorModel

@Dao
interface SensorDAO {

    @Insert
    fun saveSensor(sensor: SensorModel): Long

    @Insert
    @JvmSuppressWildcards
    fun saveList(objects: List<SensorModel>)

    @Update
    fun updateSensor(sensor: SensorModel): Int

    @Delete
    fun deleteSensor(sensor: SensorModel)

    @Query("SELECT * FROM Sensor WHERE idSensor = :uuid LIMIT 1")
    fun picSensor(uuid: String): SensorModel?

    @Query("SELECT * FROM Sensor")
    fun getAll(): List<SensorModel>

    @Query("SELECT COUNT(idSensor) FROM Sensor")
    fun countRows(): Int
}