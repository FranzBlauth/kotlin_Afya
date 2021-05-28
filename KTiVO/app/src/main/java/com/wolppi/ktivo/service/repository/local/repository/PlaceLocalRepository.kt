package com.wolppi.ktivo.service.repository.local.repository

import android.content.Context
import com.wolppi.ktivo.service.model.PlaceModel
import com.wolppi.ktivo.service.repository.local.dao.KtivoDatabase

class PlaceLocalRepository (context: Context){

    private val mDataBase = KtivoDatabase.getDatabase(context).placeDAO()

    fun savePlace(place: PlaceModel): Boolean {
        return mDataBase.savePlace(place) > 0
    }

    fun updatePlace(place: PlaceModel): Boolean {
        return mDataBase.updatePlace(place) > 1
    }

    fun deletePlace(place: PlaceModel) {
        return mDataBase.deletePlace(place)
    }

    fun saveAll(model: List<PlaceModel>) {
        mDataBase.saveList(model)
    }

    fun picPlace(uuid: String): PlaceModel? {
        return mDataBase.picPlace(uuid)
    }

    fun getAll(): List<PlaceModel> {
        return mDataBase.getAll()
    }

    fun countRows(): Int {
        return  mDataBase.countRows()
    }
}