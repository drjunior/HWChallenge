package com.hostelworld.challenge.data.model

import com.google.gson.annotations.SerializedName

/**
 * Class which provides a model for Property
 */
data class Property(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("overview") val overview: String,
        @SerializedName("isFeatured") val isFeatured: Boolean,
        @SerializedName("lowestPricePerNight") val lowestPricePerNight: Price,
        @SerializedName("images") val images: ArrayList<Image>,
        @SerializedName("overallRating") val overallRating: Rating


)

/**
 * Class which provides a model for Property Price
 */
data class Price(
        @SerializedName("value") val value: String,
        @SerializedName("currency") val currency: String
)

/**
 * Class which provides a model for Property Rating
 */
data class Rating(
        @SerializedName("overall") val overall: Long,
        @SerializedName("numberOfRatings") val numberOfRatings: Long
)

/**
 * Class which provides a model for Property Image
 */
data class Image(
        @SerializedName("prefix") val prefix: String,
        @SerializedName("suffix") val suffix: String
)
