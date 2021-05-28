package com.wolppi.ktivo.service.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wolppi.ktivo.service.model.ConfigurationModel

@Dao
interface ConfigurationDAO {

    @Insert
    fun saveConfiguration(configuration: ConfigurationModel) : Long

    @Query("SELECT * FROM Configuration WHERE id = 1")
    fun getConfiguration(): List<ConfigurationModel>

}
