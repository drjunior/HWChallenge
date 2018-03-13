package com.hostelworld.challenge.network

import com.hostelworld.challenge.data.ApiProperties
import com.hostelworld.challenge.data.model.PropertiesApiResult
import retrofit2.Callback
import javax.inject.Inject

/**
 * PropertiesService responsible for returning properties list
 */
class PropertiesService @Inject
constructor(private val properties: ApiProperties) {

    fun getProperties(propertiesListCallback: Callback<PropertiesApiResult>) {
        val callResponse = properties.getProperties()
        callResponse.enqueue(propertiesListCallback)
    }

}
