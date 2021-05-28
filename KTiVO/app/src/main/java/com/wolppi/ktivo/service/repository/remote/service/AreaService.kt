package com.wolppi.ktivo.service.repository.remote.service

import com.wolppi.ktivo.service.model.AreaModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AreaService {

    @Headers("Content-Type: application/json")
    @POST("Area/loadArea.php")
    fun loadArea(@Body areaModel: AreaModel): Call<List<AreaModel>>

    @Headers("Content-Type: application/json")
    @POST("Area/loadAreaFirstTime.php")
    fun loadAreaFirstTime(@Body str: String): Call<List<AreaModel>>
}