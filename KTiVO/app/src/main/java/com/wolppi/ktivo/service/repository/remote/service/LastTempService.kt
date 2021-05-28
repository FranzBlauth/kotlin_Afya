package com.wolppi.ktivo.service.repository.remote.service

import com.wolppi.ktivo.service.model.AreaModel
import com.wolppi.ktivo.service.model.ContactModel
import com.wolppi.ktivo.service.model.LastTempModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface LastTempService {

    @Headers("Content-Type: application/json")
    @POST("Appointments/lastTempDevice.php")
    fun loadLastTemp(@Body string: String): Call<List<LastTempModel>>
}