package com.wolppi.ktivo.service.repository.remote.repository

import android.content.Context
import com.wolppi.ktivo.R
import com.wolppi.ktivo.listener.APIListener
import com.wolppi.ktivo.service.constants.KtivoConstants
import com.wolppi.ktivo.service.model.NumberInstallModel
import com.wolppi.ktivo.service.model.NumberInstallModelBack
import com.wolppi.ktivo.service.repository.remote.service.NumberInstallService
import com.wolppi.ktivo.service.repository.remote.retrofit.RetrofitInstall
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NumberInstallRemoteRepository(val context: Context) {

    private val mRemote = RetrofitInstall.createService(NumberInstallService::class.java)

    fun callNumberInstall(numberInstall: Int, listener: APIListener<List<NumberInstallModelBack>>) {

        val mNumberInstallModel = NumberInstallModel()
        mNumberInstallModel.numberInstall = numberInstall
        val call: Call<List<NumberInstallModelBack>> = mRemote.getUrlInfo(mNumberInstallModel)

        call.enqueue(object : Callback<List<NumberInstallModelBack>> {

            override fun onResponse(call: Call<List<NumberInstallModelBack>>, response: Response<List<NumberInstallModelBack>>) {
                if (response.code() != KtivoConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<NumberInstallModelBack>>, t: Throwable) {
                    listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }
}


