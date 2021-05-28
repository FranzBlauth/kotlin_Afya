package com.wolppi.ktivo.service.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wolppi.ktivo.service.model.AreaModel
import com.wolppi.ktivo.service.model.LastTempModel

@Dao
interface LastTempDAO {

    @Insert
    fun saveLastTemp(LastTemp : LastTempModel) : Long

    @Insert
    @JvmSuppressWildcards
    fun saveList(objects: List<LastTempModel>)

    @Query("SELECT * FROM Appointments")
    fun getAll(): List<LastTempModel>

    @Query("SELECT * FROM Appointments WHERE filter = :filter")
    fun getFilter(filter: String): List<LastTempModel>

    @Query("SELECT COUNT(id) FROM Appointments")
    fun countRows(): Int

     @Query("DELETE FROM Appointments")
     fun deleteAll(): Int

}