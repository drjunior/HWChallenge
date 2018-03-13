package com.hostelworld.challenge.data.model

import com.google.gson.annotations.SerializedName


/**
 * Class which provides a model for PropertiesApiResult request
 */
data class PropertiesApiResult(

        @SerializedName("properties") var properties: ArrayList<Property>? = null,
        @SerializedName("location") var location: Location? = null,
        @SerializedName("filterData") var filterData: FilterData? = null,
        @SerializedName("pagination") var pagination: Pagination? = null

)