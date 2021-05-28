package com.wolppi.ktivo.service.repository.remote.repository

import android.content.Context
import com.wolppi.ktivo.R
import com.wolppi.ktivo.listener.APIListener
import com.wolppi.ktivo.service.constants.KtivoConstants
import com.wolppi.ktivo.service.model.SensorModel
import com.wolppi.ktivo.service.repository.local.repository.SharedPreferenceRepository
import com.wolppi.ktivo.service.repository.remote.retrofit.RetrofitClient
import com.wolppi.ktivo.service.repository.remote.service.SensorService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SensorRemoteRepository (val context: Context) {

    private val mSharedPreferences = SharedPreferenceRepository(context)

    fun loadSensor(sensorModel: SensorModel, listener: APIListener<List<SensorModel>>) {

        val url = mSharedPreferences.getShared(KtivoConstants.SHARED.URL_DATA)
        val mRemote = RetrofitClient.createService(SensorService::class.java, url)

        val call: Call<List<SensorModel>> = mRemote.loadSensor(sensorModel)

        call.enqueue(object : Callback<List<SensorModel>> {

            override fun onResponse(call: Call<List<SensorModel>>, response: Response<List<SensorModel>>) {
                if (response.code() != KtivoConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<SensorModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    fun loadSensorFirstTime(listener: APIListener<List<SensorModel>>) {

        val url = mSharedPreferences.getShared(KtivoConstants.SHARED.URL_DATA)
        val mRemote = RetrofitClient.createService(SensorService::class.java, url)

        val call: Call<List<SensorModel>> = mRemote.loadSensorFirstTime("")

        call.enqueue(object : Callback<List<SensorModel>> {

            override fun onResponse(call: Call<List<SensorModel>>, response: Response<List<SensorModel>>) {
                if (response.code() != KtivoConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<SensorModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

}