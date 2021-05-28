package com.wolppi.ktivo.service.repository.remote.service

import com.wolppi.ktivo.service.model.ContactModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ContactService {

    @Headers("Content-Type: application/json")
    @POST("Contacts/insertContacts.php")
    fun saveContact(@Body contactModel: ContactModel): Call<List<ContactModel>>

    @Headers("Content-Type: application/json")
    @POST("Contacts/loadContacts.php")
    fun loadContact(@Body contactModel: ContactModel): Call<List<ContactModel>>

    @Headers("Content-Type: application/json")
    @POST("Contacts/loadContactsFirstTime.php")
    fun saveContactFirstTime(@Body contactModel: ContactModel): Call<List<ContactModel>>
}