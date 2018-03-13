package com.hostelworld.challenge.data.service

import com.hostelworld.challenge.data.model.PropertiesApiResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The interface which provides methods to get properties and track stats
 */
interface PropertiesService {

    /**
     * Get the list of Properties from the API
     */
    @GET("/dovdtel87/ef6dd1422a86554d22172e5975222f81/raw/ba5b81b567efebc1039a481b7e9712b7cd61ea6c/properties.json")
    fun getProperties(): Call<PropertiesApiResult>

    /**
     * Save remotely on server the time spent by network requests
     */
    @GET("/dovdtel87/ffa9a36b21af4d5d93c32979667bf92c/raw/6afae87190ad8af6c85a30d9460d51f17c9e886e/stats")
    fun trackNetworkStats(@Query("action") action: String, @Query("duration") duration: String): Call<PropertiesApiResult>

}