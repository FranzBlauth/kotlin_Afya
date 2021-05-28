package com.wolppi.ktivo.ui.information

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wolppi.ktivo.listener.APIListener
import com.wolppi.ktivo.listener.ValidationListener
import com.wolppi.ktivo.service.constants.KtivoConstants
import com.wolppi.ktivo.service.model.ConfigurationModel
import com.wolppi.ktivo.service.model.NumberInstallModelBack
import com.wolppi.ktivo.service.repository.local.repository.SharedPreferenceRepository
import com.wolppi.ktivo.service.repository.remote.repository.NumberInstallRemoteRepository

class InformationViewModel(application: Application) : AndroidViewModel(application) {

    private var mNumberInstallRemoteRepository = NumberInstallRemoteRepository(application)
    private var mSharedPreferenceRepository = SharedPreferenceRepository(application)

    private val mReturnConfiguration = MutableLiveData<List<NumberInstallModelBack>>()
    val returnConfiguration: LiveData<List<NumberInstallModelBack>> = mReturnConfiguration

    fun loadConfiguration() {
        val mNumberInstall = mSharedPreferenceRepository.getShared(KtivoConstants.SHARED.NUMBER_INSTALL).toInt()
        mNumberInstallRemoteRepository.callNumberInstall(mNumberInstall,
            object : APIListener<List<NumberInstallModelBack>> {

                override fun onSuccess(model: List<NumberInstallModelBack>) {
                    if (model.count() > 0){
                        mReturnConfiguration.value = model
                    }
                }

                override fun onFailure(str: String) {
                }

            })
    }

    fun loadShared(): SharedPreferenceRepository{
        return mSharedPreferenceRepository
    }
}