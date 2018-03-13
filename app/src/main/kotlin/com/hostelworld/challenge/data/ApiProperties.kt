package com.hostelworld.challenge.data

import com.google.gson.Gson
import com.hostelworld.challenge.BuildConfig
import com.hostelworld.challenge.data.model.PropertiesApiResult
import com.hostelworld.challenge.data.service.PropertiesService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ApiProperties @Inject
constructor(gson: Gson) {

    private val service: Retrofit

    private val BASE_URL = "https://gist.githubusercontent.com/"

    private val CONNECT_TIMEOUT: Long = 30
    private val WRITE_TIMEOUT: Long = 30
    private val READ_TIMEOUT: Long = 30

    init {

        /**
         * Http logger
         */
        val logger = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logger.level = HttpLoggingInterceptor.Level.BASIC
        }

        /**
         * Interceptor that track API request times
         */
        val interceptorStatistics = Interceptor { chain ->

            val request = chain.request()
            val time = System.currentTimeMillis()
            val response = chain.proceed(request)

            val durationMs = System.currentTimeMillis() - time

            /** Avoid loops and don't track the API Track Request **/
            if (!request.url().toString().contains("stats")) {

                val callResponse = trackNetworkStats(duration = durationMs.toString())
                callResponse.enqueue(object : Callback<PropertiesApiResult> {
                    override fun onResponse(call: Call<PropertiesApiResult>, response: retrofit2.Response<PropertiesApiResult>) {

                        if (!response.isSuccessful) {
                            /** tracking operations are not critical, but in case they are not working we
                             * should know that something is not working **/
                            // TODO
                        }
                    }

                    override fun onFailure(call: Call<PropertiesApiResult>, t: Throwable) {
                        /** tracking operations are not critical, but in case they are not working we
                         * should know that something is not working **/
                        // TODO
                    }
                })
            }

            response
        }

        /**
         * Okhttp builder
         */
        val client = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptorStatistics)
                .addInterceptor(logger)
                .build()

        /**
         * Retrofit Service
         */
        this.service = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
    }

    fun getProperties(): Call<PropertiesApiResult> {
        val propertiesService = service.create(PropertiesService::class.java)
        return propertiesService.getProperties()
    }

    fun trackNetworkStats(action: String = "load", duration: String): Call<PropertiesApiResult> {
        val propertiesService = service.create(PropertiesService::class.java)
        return propertiesService.trackNetworkStats(action, duration)
    }


}
