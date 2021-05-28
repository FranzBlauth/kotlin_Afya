package com.wolppi.ktivo.service.repository.remote.service

import com.wolppi.ktivo.service.model.SensorModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SensorService {
    @Headers("Content-Type: application/json")
    @POST("Sensor/loadSensor.php")
    fun loadSensor(@Body sensorModel: SensorModel): Call<List<SensorModel>>

    @Headers("Content-Type: application/json")
    @POST("Sensor/loadSensorFirstTime.php")
    fun loadSensorFirstTime(@Body string: String): Call<List<SensorModel>>
}