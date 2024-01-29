package com.refridev.bankapp.data.network.service

import com.refridev.bankapp.data.model.response.RecipientListResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiServices {

    @GET("recipient")
    suspend fun getRecipientList() : List<RecipientListResponse>

    companion object {

        @JvmStatic
        operator fun invoke(): ApiServices {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://demo2968123.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiServices::class.java)
        }


    }
}