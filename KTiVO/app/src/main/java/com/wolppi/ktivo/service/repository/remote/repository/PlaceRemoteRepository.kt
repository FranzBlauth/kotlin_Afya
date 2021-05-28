package com.wolppi.ktivo.service.repository.remote.repository

import android.content.Context
import com.wolppi.ktivo.R
import com.wolppi.ktivo.listener.APIListener
import com.wolppi.ktivo.service.constants.KtivoConstants
import com.wolppi.ktivo.service.model.PlaceModel
import com.wolppi.ktivo.service.repository.local.repository.SharedPreferenceRepository
import com.wolppi.ktivo.service.repository.remote.retrofit.RetrofitClient
import com.wolppi.ktivo.service.repository.remote.service.PlaceService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceRemoteRepository (val context: Context) {

    private val mSharedPreferences = SharedPreferenceRepository(context)

    fun loadPlace(placeModel: PlaceModel, listener: APIListener<List<PlaceModel>>) {

        val url = mSharedPreferences.getShared(KtivoConstants.SHARED.URL_DATA)
        val mRemote = RetrofitClient.createService(PlaceService::class.java, url)

        val call: Call<List<PlaceModel>> = mRemote.loadPlace(placeModel)

        call.enqueue(object : Callback<List<PlaceModel>> {

            override fun onResponse(call: Call<List<PlaceModel>>, response: Response<List<PlaceModel>>) {
                if (response.code() != KtivoConstants.HTTP.SUCCESS) {
                    val validation = Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<PlaceModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    fun loadPlaceFirstTime(listener: APIListener<List<PlaceModel>>) {

        val url = mSharedPreferences.getShared(KtivoConstants.SHARED.URL_DATA)
        val mRemote = RetrofitClient.createService(PlaceService::class.java, url)
        val call: Call<List<PlaceModel>> = mRemote.loadPlaceFirstTime("")

        call.enqueue(object : Callback<List<PlaceModel>> {

            override fun onResponse(call: Call<List<PlaceModel>>, response: Response<List<PlaceModel>>) {
                if (response.code() != KtivoConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<PlaceModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

}