package com.wolppi.ktivo.service.repository.local.repository

import android.content.Context
import com.wolppi.ktivo.service.model.SensorModel
import com.wolppi.ktivo.service.repository.local.dao.KtivoDatabase

class SensorLocalRepository (context: Context){

    private val mDataBase = KtivoDatabase.getDatabase(context).sensorDAO()

    fun saveSensor(sensor: SensorModel): Boolean {
        return mDataBase.saveSensor(sensor) > 0
    }

    fun updateSensor(sensor: SensorModel): Boolean {
        return mDataBase.updateSensor(sensor) > 1
    }

    fun deleteSensor(sensor: SensorModel) {
        return mDataBase.deleteSensor(sensor)
    }

    fun saveAll(model: List<SensorModel>) {
        mDataBase.saveList(model)
    }

    fun picSensor(uuid: String): SensorModel? {
        return mDataBase.picSensor(uuid)
    }

    fun getAll(): List<SensorModel> {
        return mDataBase.getAll()
    }

    fun countRows(): Int {
        return  mDataBase.countRows()
    }
}