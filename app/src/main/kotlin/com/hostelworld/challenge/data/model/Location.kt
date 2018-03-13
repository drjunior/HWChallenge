package com.hostelworld.challenge.data.model

import com.google.gson.annotations.SerializedName

/**
 * Class which provides a model for PropertiesApiResult Location
 */
data class Location(

        @SerializedName("city") val city: City,
        @SerializedName("region") val region: String
)


/**
 * Class which provides a model for location City
 */
data class City(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("idCountry") val idCountry: Int,
        @SerializedName("country") val country: String,
        var urlLocationMap: String
)