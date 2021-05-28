package com.wolppi.ktivo.service.repository.remote.repository

import android.content.Context
import com.wolppi.ktivo.R
import com.wolppi.ktivo.listener.APIListener
import com.wolppi.ktivo.service.constants.KtivoConstants
import com.wolppi.ktivo.service.model.AreaModel
import com.wolppi.ktivo.service.repository.local.repository.SharedPreferenceRepository
import com.wolppi.ktivo.service.repository.remote.retrofit.RetrofitClient
import com.wolppi.ktivo.service.repository.remote.service.AreaService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AreaRemoteRepository (val context: Context){

    private val mSharedPreferences = SharedPreferenceRepository(context)

    fun loadArea(areaModel: AreaModel, listener: APIListener<List<AreaModel>>) {

        val url = mSharedPreferences.getShared(KtivoConstants.SHARED.URL_DATA)
        var mRemote = RetrofitClient.createService(AreaService::class.java, url)

        val call: Call<List<AreaModel>> = mRemote.loadArea(areaModel)

        call.enqueue(object : Callback<List<AreaModel>> {

            override fun onResponse(call: Call<List<AreaModel>>, response: Response<List<AreaModel>>) {
                if (response.code() != KtivoConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body().let {
                        if (it != null) {
                            listener.onSuccess(it)
                        }else{
                            listener.onFailure("")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<AreaModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    fun loadAreaFirstTime(listener: APIListener<List<AreaModel>>) {

        val url = mSharedPreferences.getShared(KtivoConstants.SHARED.URL_DATA)
        var mRemote = RetrofitClient.createService(AreaService::class.java, url)

        val call: Call<List<AreaModel>> = mRemote.loadAreaFirstTime("")

        call.enqueue(object : Callback<List<AreaModel>> {

            override fun onResponse(call: Call<List<AreaModel>>, response: Response<List<AreaModel>>) {
                if (response.code() != KtivoConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body().let {
                        if (it != null) {
                            listener.onSuccess(it)
                        }else{
                            listener.onFailure("")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<AreaModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

}