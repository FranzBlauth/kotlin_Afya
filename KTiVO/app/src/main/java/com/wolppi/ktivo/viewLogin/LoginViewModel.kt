package com.wolppi.ktivo.viewLogin

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wolppi.ktivo.listener.APIListener
import com.wolppi.ktivo.listener.ValidationListener
import com.wolppi.ktivo.service.constants.KtivoConstants
import com.wolppi.ktivo.service.model.*
import com.wolppi.ktivo.service.repository.local.repository.*
import com.wolppi.ktivo.service.repository.remote.repository.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.wolppi.ktivo.service.repository.remote.retrofit.CheckInternet


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val mSharedPreferences = SharedPreferenceRepository(application)
    private val mLoginRepository = NumberInstallRemoteRepository(application)
    private val mConfigurationRepository = ConfigurationRepository(application)

    private val mAreaLocalRepository = AreaLocalRepository(application)
    private val mAreaRemoteRepository = AreaRemoteRepository(application)

    private val mContactLocalRepository = ContactLocalRepository(application)
    private val mContactRemoteRepository = ContactRemoteRepository(application)

    private val mPlaceLocalRepository = PlaceLocalRepository(application)
    private val mPlaceRemoteRepository = PlaceRemoteRepository(application)

    private val mProviderLocalRepository = ProviderLocalRepository(application)
    private val mProviderRemoteRepository = ProviderRemoteRepository(application)

    private val mSensorLocalRepository = SensorLocalRepository(application)
    private val mSensorRemoteRepository = SensorRemoteRepository(application)

    private val mLastTempRemoteRepository = LastTempRemoteRepository(application)
    private val mLastTempLocalRepository = LastTempLocalRepository(application)

    private val mCheckConfigurationOK = MutableLiveData<Boolean>()
    var checkConfigurationOK: LiveData<Boolean> = mCheckConfigurationOK

    private val mLogin = MutableLiveData<ValidationListener>()
    val login: LiveData<ValidationListener> = mLogin

    private val mInstallOk = MutableLiveData<Boolean>()
    var installOk: LiveData<Boolean> = mInstallOk

    private val mContactRemoteOK = MutableLiveData<Boolean>()
    var contactRemoteOK: LiveData<Boolean> = mContactRemoteOK

    private val mContactLocalOK = MutableLiveData<Boolean>()
    var contactLocalOK: LiveData<Boolean> = mContactLocalOK

    private val mSaveConfigurationOK = MutableLiveData<Boolean>()
    var saveConfigurationOK: LiveData<Boolean> = mSaveConfigurationOK

    private val mSaveLastTempRemoteRepository = MutableLiveData<List<LastTempModel>>()
    var saveLastTempRemoteRepository: LiveData<List<LastTempModel>> = mSaveLastTempRemoteRepository

    private val mCheckInternet = CheckInternet(application)

    var mNumberInstall = 0
    var mUrlData = ""
    var mUrlPhotos = ""
    var mUrlRecords = ""

    fun checkConfiguration() {


        val result = mConfigurationRepository.getConfiguration()
        if (result.count() > 0) {
            if (mCheckInternet.isConnectionAvailable(getApplication())) {
                saveSharedPreferences(result[0])
//                val tokenStr = FirebaseMessaging.getInstance().token.result.toString()
//                mSharedPreferences.saveShared(KtivoConstants.SHARED.TOKEN, tokenStr)
            }
            mCheckConfigurationOK.value = true
        } else {
            if (mCheckInternet.isConnectionAvailable(getApplication())) {
                FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                        return@OnCompleteListener
                    } else {
                        val token = task.result
                        val tokenStr = token.toString()
                        mSharedPreferences.saveShared(KtivoConstants.SHARED.TOKEN, tokenStr)
                    }
                })
            }
            mCheckConfigurationOK.value = false
        }
    }

    fun checkNumberInstall(numberInstall: Int) {
        mLoginRepository.callNumberInstall(
            numberInstall,
            object : APIListener<List<NumberInstallModelBack>> {

                override fun onFailure(str: String) {
                    mLogin.value = ValidationListener(str)
                }

                override fun onSuccess(model: List<NumberInstallModelBack>) {
                    mNumberInstall = numberInstall
                    mUrlData = model[0].urlData
                    mUrlPhotos = model[0].urlPhotos
                    mUrlRecords = model[0].urlRecords
                    mInstallOk.value = model[0].urlRecords != "Não existe registros de URL."
                }
            })
    }

    fun saveContactRemote(contactModel: ContactModel) {
        contactModel.token = mSharedPreferences.getShared(KtivoConstants.SHARED.TOKEN)
        mContactRemoteRepository.saveContactRemote(
            contactModel,
            object : APIListener<List<ContactModel>> {

                override fun onSuccess(model: List<ContactModel>) {
                    mContactRemoteOK.value = true
                }

                override fun onFailure(str: String) {
                }
            })
    }

    fun saveContactLocal(contactModel: ContactModel) {
        if (mContactLocalRepository.saveContactLocal(contactModel)) {
            mContactLocalOK.value = true
        }
    }

    fun saveConfiguration(uuid: String) {

        val mConfigurationModel = ConfigurationModel()
        mConfigurationModel.id = 1
        mConfigurationModel.contactUUID = uuid
        mConfigurationModel.numberInstall = mNumberInstall
        mConfigurationModel.urlData = mUrlData
        mConfigurationModel.urlPhotos = mUrlPhotos
        mConfigurationModel.urlRecords = mUrlRecords

        if (mConfigurationRepository.saveConfiguration(mConfigurationModel)) {
            saveSharedPreferences(mConfigurationModel)
            mSaveConfigurationOK.value = true
        }
    }

    private fun saveSharedPreferences(model: ConfigurationModel) {
        mSharedPreferences.allClear()
        mSharedPreferences.saveShared(KtivoConstants.SHARED.UUID_CONTACT, model.contactUUID)
        mSharedPreferences.saveShared(
            KtivoConstants.SHARED.NUMBER_INSTALL,
            model.numberInstall.toString()
        )
        mSharedPreferences.saveShared(KtivoConstants.SHARED.URL_DATA, (model.urlData + "/"))
        mSharedPreferences.saveShared(KtivoConstants.SHARED.URL_PHOTOS, (model.urlPhotos + "/"))
        mSharedPreferences.saveShared(KtivoConstants.SHARED.URL_RECORDS, (model.urlRecords + "/"))
    }

    fun loadAreaFirstTime() {
        mAreaRemoteRepository.loadAreaFirstTime(object : APIListener<List<AreaModel>> {
            override fun onSuccess(model: List<AreaModel>) {
                if (model.count() > 0) {
                    mAreaLocalRepository.saveAll(model)
                }
            }

            override fun onFailure(str: String) {
            }
        })
    }

    fun loadPlaceFirstTime() {
        mPlaceRemoteRepository.loadPlaceFirstTime(object : APIListener<List<PlaceModel>> {
            override fun onSuccess(model: List<PlaceModel>) {
                if (model.count() > 0) {
                    mPlaceLocalRepository.saveAll(model)
                }
            }

            override fun onFailure(str: String) {
            }
        })
    }

    fun loadContactFirstTime() {

        var mContactModel = ContactModel()
        mContactModel.idUser = mSharedPreferences.getShared(KtivoConstants.SHARED.UUID_CONTACT)

        mContactRemoteRepository.loadContactFirstTime(
            mContactModel,
            object : APIListener<List<ContactModel>> {
                override fun onSuccess(model: List<ContactModel>) {
                    if (model.count() > 0) {
                        mContactLocalRepository.saveAll(model)
                    }
                }

                override fun onFailure(str: String) {
                }
            })
    }

    fun loadProviderFirstTime() {
        mProviderRemoteRepository.loadProviderFirstTime(object : APIListener<List<ProviderModel>> {
            override fun onSuccess(model: List<ProviderModel>) {
                if (model.count() > 0) {
                    mProviderLocalRepository.saveAll(model)
                }
            }

            override fun onFailure(str: String) {
            }
        })
    }

    fun loadSensorFirstTime() {
        mSensorRemoteRepository.loadSensorFirstTime(object : APIListener<List<SensorModel>> {
            override fun onSuccess(model: List<SensorModel>) {
                if (model.count() > 0) {
                    mSensorLocalRepository.saveAll(model)
                }
            }

            override fun onFailure(str: String) {
            }
        })
    }

    fun loadLastTempRemote() {
        mLastTempRemoteRepository.loadLastTemp(object : APIListener<List<LastTempModel>> {
            override fun onSuccess(model: List<LastTempModel>) {
                if (model.count() > 0) {
                    mLastTempLocalRepository.deleteAll()
                    mSaveLastTempRemoteRepository.value = model
                }
            }

            override fun onFailure(str: String) {
            }
        })
    }

    fun saveLastTempLocal(model: List<LastTempModel>) {
        mLastTempLocalRepository.saveAll(model)
    }

    fun isConnectionAvailable(): Boolean{
        return mCheckInternet.isConnectionAvailable(getApplication())
    }
}
