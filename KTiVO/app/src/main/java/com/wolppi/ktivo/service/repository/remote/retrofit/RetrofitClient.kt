package com.wolppi.ktivo.service.repository.remote.retrofit

import android.content.Context
import android.icu.util.TimeUnit
import com.wolppi.ktivo.service.repository.local.repository.SharedPreferenceRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.xml.datatype.DatatypeConstants.SECONDS

class RetrofitClient private constructor() {

    companion object {

        private lateinit var retrofit: Retrofit

        private fun getRetrofitInstance(url: String) : Retrofit {

            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request =
                        chain.request()
                            .newBuilder()
                            .build()
                    return chain.proceed(request)
                }
            })

            if (!Companion::retrofit.isInitialized){
                retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>, url: String) : S {
            return getRetrofitInstance(url).create(serviceClass)
        }
    }

}
