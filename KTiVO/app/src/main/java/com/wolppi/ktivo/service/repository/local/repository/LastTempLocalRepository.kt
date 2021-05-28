package com.wolppi.ktivo.service.repository.local.repository

import android.content.Context
import com.wolppi.ktivo.service.constants.KtivoConstants
import com.wolppi.ktivo.service.model.LastTempModel
import com.wolppi.ktivo.service.repository.local.dao.KtivoDatabase

class LastTempLocalRepository(context: Context) {

    private val mDataBaseLastTemp = KtivoDatabase.getDatabase(context).lastTempDAO()
    private val mSensorLocalRepository = SensorLocalRepository(context)
    private var mFilter: String = ""

    fun saveAll(model: List<LastTempModel>) {

        var mNewList: MutableList<LastTempModel> = arrayListOf()

        for (ind in 0 until model.count()) {

            val modelSensor = mSensorLocalRepository.picSensor(model[ind].id_Sensor)

            if (modelSensor != null) {

                val mMinDef = (modelSensor.tempMin).toDouble()
                val mMaxDef = (modelSensor.tempMax).toDouble()
                val lastTemp = (model[ind].temperature).toDouble()

                if (lastTemp < mMinDef) {
                    mFilter = KtivoConstants.TEMPERATURE.COLD
                } else {
                    if (lastTemp > mMaxDef) {
                        mFilter = KtivoConstants.TEMPERATURE.HOT
                    } else {
                        mFilter = KtivoConstants.TEMPERATURE.NORMAL
                    }
                }

                val mLastTempModel = LastTempModel().apply {
                    this.id_Sensor = model[ind].id_Sensor
                    this.temperature = model[ind].temperature
                    this.dateTime = model[ind].dateTime
                    this.humidity = model[ind].humidity
                    this.battery = model[ind].battery
                    this.redCollor = model[ind].redCollor
                    this.filter = mFilter
                }
                mNewList.add(mLastTempModel)
            }
        }
        return mDataBaseLastTemp.saveList(mNewList)
    }

    fun getAll(): List<LastTempModel> {
        return mDataBaseLastTemp.getAll()
    }

    fun getFilter(filter: String) : List<LastTempModel> {
        return mDataBaseLastTemp.getFilter(filter)
    }

    fun countRows() : Int {
        return mDataBaseLastTemp.countRows()
    }

    fun deleteAll() : Int {
        return mDataBaseLastTemp.deleteAll()
    }
}