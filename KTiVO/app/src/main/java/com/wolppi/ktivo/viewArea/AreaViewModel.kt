package com.wolppi.ktivo.viewArea

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wolppi.ktivo.listener.APIListener
import com.wolppi.ktivo.service.constants.KtivoConstants
import com.wolppi.ktivo.service.model.*
import com.wolppi.ktivo.service.repository.local.repository.*
import com.wolppi.ktivo.service.repository.remote.repository.*
import com.wolppi.ktivo.service.repository.remote.retrofit.CheckInternet

class AreaViewModel(application: Application) : AndroidViewModel(application) {

    private val mSharedPreferences = SharedPreferenceRepository(application)

    private val mAreaLocalRepository = AreaLocalRepository(application)
    private val mAreaRemoteRepository = AreaRemoteRepository(application)

    private val mContactLocalRepository = ContactLocalRepository(application)
    private val mContactRemoteRepository = ContactRemoteRepository(application)

    private val mLoadAreaRemote = MutableLiveData<Boolean>()
    var loadAReaRemote: LiveData<Boolean> = mLoadAreaRemote

    private val mLoadContactRemote = MutableLiveData<Boolean>()
    var loadContactRemote: LiveData<Boolean> = mLoadContactRemote

    private val mLoadAreaLocal = MutableLiveData<List<AreaModel>>()
    var loadAreaLocal: LiveData<List<AreaModel>> = mLoadAreaLocal

    private val mLoadContactLocal = MutableLiveData<List<ContactModel>>()
    var loadContactLocal: LiveData<List<ContactModel>> = mLoadContactLocal

    private val mPickArea = MutableLiveData<AreaModel>()
    var pickArea: LiveData<AreaModel> = mPickArea

    private val mCheckInternet = CheckInternet(application)

    fun loadAreaRemote() {
        val mAreaModel = AreaModel()
        mAreaModel.idUser = mSharedPreferences.getShared(KtivoConstants.SHARED.UUID_CONTACT)
        mAreaRemoteRepository.loadArea(mAreaModel, object : APIListener<List<AreaModel>> {
            val b = ""
            override fun onSuccess(model: List<AreaModel>) {
                for (ind in 0 until model.count()) {
                    val mModel = mAreaLocalRepository.picArea(model[ind].idArea)
                    if (mModel == null) {
                        mAreaLocalRepository.saveArea(model[ind])
                    } else {
                        if (model[ind].operation == "delete") {
                            mAreaLocalRepository.deleteModel(mModel)
                        } else {
                            mAreaLocalRepository.updateArea(mModel)
                        }
                    }
                }
                mLoadAreaRemote.value = true
            }
            override fun onFailure(str: String) {
                mLoadAreaRemote.value = true
            }
        })
    }

    fun loadContactRemote() {
        val mContactModel = ContactModel()
        mContactModel.id = mSharedPreferences.getShared(KtivoConstants.SHARED.UUID_CONTACT)
        mContactRemoteRepository.loadContact(mContactModel, object : APIListener<List<ContactModel>> {
                override fun onSuccess(model: List<ContactModel>) {
                    for (ind in 0 until model.count()) {
                        val mModel = mContactLocalRepository.picContact(model[ind].id)
                        if (mModel == null) {
                            mContactLocalRepository.saveContactLocal(model[ind])
                        } else {
                            if (model[ind].operation == "delete") {
                                mContactLocalRepository.deleteContact(mModel)
                            } else {
                                mContactLocalRepository.updateContact(mModel)
                            }
                        }
                    }
                    mLoadContactRemote.value = true
                }
                override fun onFailure(str: String) {
                    mLoadContactRemote.value = true
                }
            })
    }


    fun loadAreaLocal() {
        val result = mAreaLocalRepository.getAll()
        if (result.count() > 0) {
            mLoadAreaLocal.value = result
        }
    }

    fun loadContactLocal() {
        val result = mContactLocalRepository.getAll()
        if (result.count() > 0) {
            mLoadContactLocal.value = result
        }
    }

    fun pickArea(uuid: String) {
        mPickArea.value = mAreaLocalRepository.picArea(uuid)
        return
    }

    fun pickContact(uuid: String): ContactModel {
        return mContactLocalRepository.picContact(uuid)
    }

    fun loadContact():List<ContactModel>{
        return mContactLocalRepository.getAll()
    }

    fun deleteArea(uuid: String){
        return mAreaLocalRepository.delete(uuid)
    }

    fun isConnectionAvailable(): Boolean{
        return mCheckInternet.isConnectionAvailable(getApplication())
    }
}

