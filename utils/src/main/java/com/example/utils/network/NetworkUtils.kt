package com.example.utils.network

import com.example.commons.constants.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *   @author Jose Alejandro Ojalvo
 *   @since 03/02/2021
 *   @email jojalvo@topdoctors.es
 *
 *
 */
object NetworkUtils {

    /**
     * Init the RetrofitService that'll allow us to call endpoints
     */
    fun initServiceLog(): Retrofit {

        val interceptor = HttpLoggingInterceptor().apply {
            level = Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

}