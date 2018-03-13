package com.hostelworld.challenge.data.model

import com.google.gson.annotations.SerializedName


/**
 * Class which provides a model for PropertiesApiResult Pagination
 */
data class Pagination(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String
)