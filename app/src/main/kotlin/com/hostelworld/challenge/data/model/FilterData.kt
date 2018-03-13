package com.hostelworld.challenge.data.model

import com.google.gson.annotations.SerializedName


/**
 * Class which provides a model for PropertiesApiResult FilterData
 */

data class FilterData(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String
)