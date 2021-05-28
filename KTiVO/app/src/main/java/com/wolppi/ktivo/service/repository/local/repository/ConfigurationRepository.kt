package com.wolppi.ktivo.service.repository.local.repository

import android.content.Context
import com.wolppi.ktivo.service.model.ConfigurationModel
import com.wolppi.ktivo.service.repository.local.dao.KtivoDatabase

class ConfigurationRepository (context: Context){

    private val mDataBase = KtivoDatabase.getDatabase(context).configurationDAO()

    fun saveConfiguration(configuration: ConfigurationModel) : Boolean {
        return mDataBase.saveConfiguration(configuration) > 0
    }

    fun getConfiguration(): List<ConfigurationModel> {
        return mDataBase.getConfiguration()
    }
}