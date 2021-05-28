package com.wolppi.ktivo.service.repository.local.dao

import androidx.room.*
import com.wolppi.ktivo.service.model.AreaModel

@Dao
interface AreaDAO {

    @Insert
    fun saveArea(area: AreaModel): Long

    @Insert
    @JvmSuppressWildcards
    fun saveList(objects: List<AreaModel>)

    @Update
    fun updateArea(area: AreaModel): Int

    @Delete
    fun deleteModel(area: AreaModel)

    @Query("SELECT * FROM Area WHERE idArea = :uuid LIMIT 1")
    fun picArea(uuid: String): AreaModel

    @Query("DELETE FROM Area WHERE idArea = :uuid")
    fun deleteArea(uuid: String)

    @Query("SELECT * FROM Area")
    fun getAll(): List<AreaModel>

    @Query("SELECT COUNT(idArea) FROM Area")
    fun countRows(): Int

}