package com.wolppi.ktivo.service.repository.local.dao

import androidx.room.*
import com.wolppi.ktivo.service.model.ProviderModel

@Dao
interface ProviderDAO {

    @Insert
    fun saveProvider(provider: ProviderModel): Long

    @Insert
    @JvmSuppressWildcards
    fun saveList(objects: List<ProviderModel>)

    @Update
    fun updateProvider(provider: ProviderModel): Int

    @Delete
    fun deleteProvider(provider: ProviderModel)

    @Query("SELECT * FROM Provider WHERE id = :uuid LIMIT 1")
    fun picProvider(uuid: String): ProviderModel?

    @Query("SELECT * FROM Provider")
    fun getAll(): List<ProviderModel>

    @Query("SELECT COUNT(id) FROM Provider")
    fun countRows(): Int
}