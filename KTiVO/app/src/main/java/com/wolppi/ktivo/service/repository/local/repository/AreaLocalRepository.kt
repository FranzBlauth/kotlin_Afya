package com.wolppi.ktivo.service.repository.local.repository

import android.content.Context
import com.wolppi.ktivo.service.model.AreaModel
import com.wolppi.ktivo.service.repository.local.dao.KtivoDatabase

class AreaLocalRepository(context: Context){

    private val mDataBase = KtivoDatabase.getDatabase(context).areaDAO()

    fun saveArea(area: AreaModel): Boolean {
        return mDataBase.saveArea(area) > 0
    }

    fun updateArea(area: AreaModel): Boolean {
        return mDataBase.updateArea(area) > 1
    }

    fun deleteModel(area: AreaModel) {
        return mDataBase.deleteModel(area)
    }

    fun delete(uuid: String) {
        return mDataBase.deleteArea(uuid)
    }

    fun saveAll(model: List<AreaModel>) {
        mDataBase.saveList(model)
    }

    fun picArea(uuid: String): AreaModel {
        return mDataBase.picArea(uuid)
    }

    fun getAll(): List<AreaModel> {
        return mDataBase.getAll()
    }

    fun countRows(): Int {
        return  mDataBase.countRows()
    }

}