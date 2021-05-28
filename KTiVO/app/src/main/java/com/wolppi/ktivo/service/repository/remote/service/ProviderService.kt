package com.wolppi.ktivo.service.repository.remote.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import com.wolppi.ktivo.service.model.ProviderModel

interface ProviderService {

    @Headers("Content-Type: application/json")
    @POST("Provider/loadProvider.php")
    fun loadProvider(@Body provider : ProviderModel): Call<List<ProviderModel>>

    @Headers("Content-Type: application/json")
    @POST("Provider/loadProviderFirstTime.php")
    fun loadProviderFirstTime(@Body string: String): Call<List<ProviderModel>>
}