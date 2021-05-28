package com.wolppi.ktivo.service.repository.remote.repository

import android.content.Context
import com.wolppi.ktivo.R
import com.wolppi.ktivo.listener.APIListener
import com.wolppi.ktivo.service.constants.KtivoConstants
import com.wolppi.ktivo.service.model.LastTempModel
import com.wolppi.ktivo.service.repository.local.repository.SharedPreferenceRepository
import com.wolppi.ktivo.service.repository.remote.retrofit.RetrofitClient
import com.wolppi.ktivo.service.repository.remote.service.LastTempService
import com.google.gson.Gson
import com.wolppi.ktivo.service.repository.remote.retrofit.CheckInternet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LastTempRemoteRepository (val context: Context) {

    private val mSharedPreferences = SharedPreferenceRepository(context)
    private val mCheckInternet = CheckInternet(context)

    fun loadLastTemp(listener: APIListener<List<LastTempModel>>) {

        val url = mSharedPreferences.getShared(KtivoConstants.SHARED.URL_DATA)
        val mRemote = RetrofitClient.createService(LastTempService::class.java, url)
        val call: Call<List<LastTempModel>> = mRemote.loadLastTemp("")

        call.enqueue(object : Callback<List<LastTempModel>> {

            override fun onResponse(call: Call<List<LastTempModel>>, response: Response<List<LastTempModel>>) {
                if (response.code() != KtivoConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<LastTempModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }
}