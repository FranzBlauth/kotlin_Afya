package com.wolppi.ktivo.service.repository.remote.repository

import android.content.Context
import com.wolppi.ktivo.R
import com.wolppi.ktivo.listener.APIListener
import com.wolppi.ktivo.service.constants.KtivoConstants
import com.wolppi.ktivo.service.model.ContactModel
import com.wolppi.ktivo.service.repository.local.repository.SharedPreferenceRepository
import com.wolppi.ktivo.service.repository.remote.retrofit.RetrofitClient
import com.wolppi.ktivo.service.repository.remote.service.ContactService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactRemoteRepository (val context: Context) {

    private val mSharedPreferences = SharedPreferenceRepository(context)

    fun saveContactRemote(contactModel: ContactModel, listener: APIListener<List<ContactModel>>) {

        val url = mSharedPreferences.getShared(KtivoConstants.SHARED.URL_DATA)
        val mRemote = RetrofitClient.createService(ContactService::class.java, url)
        val call: Call<List<ContactModel>> = mRemote.saveContact(contactModel)

        call.enqueue(object : Callback<List<ContactModel>> {

            override fun onResponse(call: Call<List<ContactModel>>, response: Response<List<ContactModel>>) {
                if (response.code() != KtivoConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<ContactModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    fun loadContact(contactModel: ContactModel, listener: APIListener<List<ContactModel>>) {

        val url = mSharedPreferences.getShared(KtivoConstants.SHARED.URL_DATA)
        val mRemote = RetrofitClient.createService(ContactService::class.java, url)

        val call: Call<List<ContactModel>> = mRemote.loadContact(contactModel)

        call.enqueue(object : Callback<List<ContactModel>> {

            override fun onResponse(call: Call<List<ContactModel>>, response: Response<List<ContactModel>>) {
                if (response.code() != KtivoConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body().let {
                        if (it != null) {
                            listener.onSuccess(it)
                        } else {
                            listener.onFailure("")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<ContactModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    fun loadContactFirstTime(contactModel: ContactModel, listener: APIListener<List<ContactModel>>) {

        val url = mSharedPreferences.getShared(KtivoConstants.SHARED.URL_DATA)
        val mRemote = RetrofitClient.createService(ContactService::class.java, url)

        val call: Call<List<ContactModel>> = mRemote.saveContactFirstTime(contactModel)

        call.enqueue(object : Callback<List<ContactModel>> {

            override fun onResponse(call: Call<List<ContactModel>>, response: Response<List<ContactModel>>) {
                if (response.code() != KtivoConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<ContactModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }
}


