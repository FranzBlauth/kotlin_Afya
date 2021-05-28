package com.wolppi.ktivo.ui.lastTemp

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

class LastTempViewModel(application: Application) : AndroidViewModel(application) {

    private val mSharedPreferences = SharedPreferenceRepository(application)

    private val mContactLocalRepository = ContactLocalRepository(application)
    private val mContactRemoteRepository = ContactRemoteRepository(application)

    private val mAreaLocalRepository = AreaLocalRepository(application)
    private val mAreaRemoteRepository = AreaRemoteRepository(application)

    private val mPlaceLocalRepository = PlaceLocalRepository(application)
    private val mPlaceRemoteRepository = PlaceRemoteRepository(application)

    private val mProviderLocalRepository = ProviderLocalRepository(application)
    private val mProviderRemoteRepository = ProviderRemoteRepository(application)

    private val mSensorLocalRepository = SensorLocalRepository(application)
    private val mSensorRemoteRepository = SensorRemoteRepository(application)

    private val mLastTempLocalRepository = LastTempLocalRepository(application)
    private val mLastTempRemoteRepository = LastTempRemoteRepository(application)

    private val mLoadLastTempRemote = MutableLiveData<Boolean>()
    var loadLastTempRemote: LiveData<Boolean> = mLoadLastTempRemote

    private val mLoadLastTempLocal = MutableLiveData<List<LastTempModel>>()
    var loadLastTempLocal: LiveData<List<LastTempModel>> = mLoadLastTempLocal

    private val mCheckInternet = CheckInternet(application)

    fun loadArea() {
        val mAreaModel = AreaModel()
        mAreaModel.idUser = mSharedPreferences.getShared(KtivoConstants.SHARED.UUID_CONTACT)
        mAreaRemoteRepository.loadArea(mAreaModel, object : APIListener<List<AreaModel>> {
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
            }
            override fun onFailure(str: String) {}
        })
    }

    fun loadPlace() {
        val mPlaceModel = PlaceModel()
        mPlaceModel.idUser = mSharedPreferences.getShared(KtivoConstants.SHARED.UUID_CONTACT)
        mPlaceRemoteRepository.loadPlace(mPlaceModel, object : APIListener<List<PlaceModel>> {
            override fun onSuccess(model: List<PlaceModel>) {
                for (ind in 0 until model.count()) {
                    val mModel = mPlaceLocalRepository.picPlace(model[ind].idPlace)
                    if (mModel == null) {
                        mPlaceLocalRepository.savePlace(model[ind])
                    } else {
                        if (model[ind].operation == "delete") {
                            mPlaceLocalRepository.deletePlace(mModel)
                        } else {
                            mPlaceLocalRepository.updatePlace(mModel)
                        }
                    }
                }
            }
            override fun onFailure(str: String) {}
        })
    }

    fun loadContact() {
        val mContactModel = ContactModel()
        mContactModel.id = mSharedPreferences.getShared(KtivoConstants.SHARED.UUID_CONTACT)
        mContactRemoteRepository.loadContact(
            mContactModel,
            object : APIListener<List<ContactModel>> {
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
                }
                override fun onFailure(str: String) {}
            })
    }

    fun loadProvider() {
        val mProviderModel = ProviderModel()
        mProviderModel.idUser = mSharedPreferences.getShared(KtivoConstants.SHARED.UUID_CONTACT)
        mProviderRemoteRepository.loadProvider(
            mProviderModel,
            object : APIListener<List<ProviderModel>> {
                override fun onSuccess(model: List<ProviderModel>) {
                    for (ind in 0 until model.count()) {
                        val mModel = mProviderLocalRepository.picProvider(model[ind].id)
                        if (mModel == null) {
                            mProviderLocalRepository.saveProvider(model[ind])
                        } else {
                            if (model[ind].operation == "delele") {
                                mProviderLocalRepository.deleteProvider(mModel)
                            } else {
                                mProviderLocalRepository.updateProvider(mModel)
                            }
                        }
                    }
                }
                override fun onFailure(str: String) {}
            })
    }

    fun loadSensor() {
        val mSensorModel = SensorModel()
        mSensorModel.idUser = mSharedPreferences.getShared(KtivoConstants.SHARED.UUID_CONTACT)
        mSensorRemoteRepository.loadSensor(mSensorModel, object : APIListener<List<SensorModel>> {
            override fun onSuccess(model: List<SensorModel>) {
                for (ind in 0 until model.count()) {
                    val mModel = mSensorLocalRepository.picSensor(model[ind].idSensor)
                    if (mModel == null) {
                        mSensorLocalRepository.saveSensor(model[ind])
                    } else {
                        if (model[ind].operation == "delete") {
                            mSensorLocalRepository.deleteSensor(mModel)
                        } else {
                            mSensorLocalRepository.updateSensor(mModel)
                        }
                    }
                }
            }
            override fun onFailure(str: String) {}
        })
    }

    fun loadLastTempRemote() {
        mLastTempRemoteRepository.loadLastTemp(object : APIListener<List<LastTempModel>> {
            override fun onSuccess(model: List<LastTempModel>) {
                if (model.count() > 0) {
                    mLastTempLocalRepository.deleteAll()
                    mLastTempLocalRepository.saveAll(model)
                    mLoadLastTempRemote.value = true
                }
            }
            override fun onFailure(str: String) {
                 mLoadLastTempRemote.value = false
            }
        })
    }

    fun loadLastTempLocal() {
        val result = mLastTempLocalRepository.getAll()
        if (result.count() > 0) {
            mLoadLastTempLocal.value = result
        }
    }

    fun loadLastTempLocalFilter(filter: String) {
        val result = mLastTempLocalRepository.getFilter(filter)
        if (result.count() > 0) {
            mLoadLastTempLocal.value = result
        }
    }

    fun isConnectionAvailable(): Boolean{
        return mCheckInternet.isConnectionAvailable(getApplication())
    }
}
