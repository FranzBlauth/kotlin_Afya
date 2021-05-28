package com.wolppi.ktivo.service.repository.local.repository

import android.content.Context
import com.wolppi.ktivo.service.model.ProviderModel
import com.wolppi.ktivo.service.repository.local.dao.KtivoDatabase

class ProviderLocalRepository (context: Context) {


    private val mDataBase = KtivoDatabase.getDatabase(context).providerDAO()

    fun saveProvider(provider: ProviderModel): Boolean {
        return mDataBase.saveProvider(provider) > 0
    }

    fun updateProvider(provider: ProviderModel): Boolean {
        return mDataBase.updateProvider(provider) > 1
    }

    fun deleteProvider(provider: ProviderModel) {
        return mDataBase.deleteProvider(provider)
    }

    fun saveAll(model: List<ProviderModel>) {
        mDataBase.saveList(model)
    }

    fun picProvider(uuid: String): ProviderModel? {
        return mDataBase.picProvider(uuid)
    }

    fun getAll(): List<ProviderModel> {
        return mDataBase.getAll()
    }

    fun countRows(): Int {
        return  mDataBase.countRows()
    }

}