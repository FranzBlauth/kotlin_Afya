package com.wolppi.ktivo.service.repository.remote.repository

import android.content.Context
import com.wolppi.ktivo.R
import com.wolppi.ktivo.listener.APIListener
import com.wolppi.ktivo.service.constants.KtivoConstants
import com.wolppi.ktivo.service.model.ProviderModel
import com.wolppi.ktivo.service.repository.local.repository.SharedPreferenceRepository
import com.wolppi.ktivo.service.repository.remote.retrofit.RetrofitClient
import com.wolppi.ktivo.service.repository.remote.service.ProviderService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProviderRemoteRepository (val context: Context) {

    private val mSharedPreferences = SharedPreferenceRepository(context)

    fun loadProvider(providerModel: ProviderModel, listener: APIListener<List<ProviderModel>>) {

        val url = mSharedPreferences.getShared(KtivoConstants.SHARED.URL_DATA)
        val mRemote = RetrofitClient.createService(ProviderService::class.java, url)

        val call: Call<List<ProviderModel>> = mRemote.loadProvider(providerModel)

        call.enqueue(object : Callback<List<ProviderModel>> {

            override fun onResponse(call: Call<List<ProviderModel>>, response: Response<List<ProviderModel>>) {
                if (response.code() != KtivoConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<ProviderModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    fun loadProviderFirstTime(listener: APIListener<List<ProviderModel>>) {

        val url = mSharedPreferences.getShared(KtivoConstants.SHARED.URL_DATA)
        val mRemote = RetrofitClient.createService(ProviderService::class.java, url)

        val call: Call<List<ProviderModel>> = mRemote.loadProviderFirstTime("")

        call.enqueue(object : Callback<List<ProviderModel>> {

            override fun onResponse(call: Call<List<ProviderModel>>, response: Response<List<ProviderModel>>) {
                if (response.code() != KtivoConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<ProviderModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

}