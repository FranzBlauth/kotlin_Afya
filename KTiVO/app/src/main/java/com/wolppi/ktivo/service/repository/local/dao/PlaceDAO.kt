package com.wolppi.ktivo.service.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.wolppi.ktivo.service.model.AreaModel
import com.wolppi.ktivo.service.model.ContactModel
import com.wolppi.ktivo.service.model.PlaceModel

@Dao
interface PlaceDAO {

    @Insert
    fun savePlace(place: PlaceModel): Long

    @Insert
    @JvmSuppressWildcards
    fun saveList(objects: List<PlaceModel>)

    @Update
    fun updatePlace(place: PlaceModel): Int

    @Update
    fun deletePlace(place: PlaceModel)

    @Query("SELECT * FROM Place WHERE idPlace = :uuid LIMIT 1")
    fun picPlace(uuid: String): PlaceModel?

    @Query("SELECT * FROM Place")
    fun getAll(): List<PlaceModel>

    @Query("SELECT COUNT(idPlace) FROM Place")
    fun countRows(): Int

}