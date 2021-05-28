package com.wolppi.ktivo.service.repository.remote.service

import com.wolppi.ktivo.service.model.NumberInstallModel
import com.wolppi.ktivo.service.model.NumberInstallModelBack
import retrofit2.Call
import retrofit2.http.*

interface NumberInstallService {

    @Headers("Content-Type: application/json")
    @POST("Configuration/configuration.php")
    fun getUrlInfo(@Body numberInstall: NumberInstallModel): Call<List<NumberInstallModelBack>>

}