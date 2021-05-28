package com.wolppi.ktivo.service.repository.remote.service

import com.wolppi.ktivo.service.model.PlaceModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface PlaceService {

    @Headers("Content-Type: application/json")
    @POST("Place/loadPlace.php")
    fun loadPlace(@Body areaModel: PlaceModel): Call<List<PlaceModel>>

    @Headers("Content-Type: application/json")
    @POST("Place/loadPlaceFirstTime.php")
    fun loadPlaceFirstTime(@Body string: String): Call<List<PlaceModel>>
}