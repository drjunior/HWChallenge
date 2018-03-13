package com.hostelworld.challenge.ui.properties

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hostelworld.challenge.R
import com.hostelworld.challenge.data.model.Property
import kotlinx.android.synthetic.main.item_property.view.*
import java.util.*

/**
 * Adapter that shows a list of properties
 */

class PropertiesListAdapter(private val propertyList: ArrayList<Property>) : RecyclerView.Adapter<PropertiesListAdapter.PropertyItemViewHolder>() {
    @JvmField
    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertiesListAdapter.PropertyItemViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        return PropertyItemViewHolder(inflater.inflate(R.layout.item_property, parent, false), context!!)
    }

    override fun onBindViewHolder(holder: PropertiesListAdapter.PropertyItemViewHolder, position: Int) {
        val propertyModel = propertyList[position]
        holder.bindData(propertyModel)
    }

    override fun getItemCount(): Int = propertyList.size

    class PropertyItemViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {

        val VEF_TO_EUR = 43743.55

        fun bindData(property: Property) {
            val propertyName = property.name
            val propertyDescription = property.overview

            /** set property name, description and featured star **/
            itemView.tv_property_name.text = propertyName
            itemView.tv_property_description.text = propertyDescription
            itemView.iv_featured.visibility = if (property.isFeatured) View.VISIBLE else View.GONE

            /** set rating bar stars and value **/
            if (property.overallRating != null) {
                var overAllRating = (property.overallRating.overall.toFloat() / 10.0).toFloat()
                itemView.rb_stars_property.rating = overAllRating
                itemView.tv_overal_rating.text = "%.1f".format(overAllRating)
            } else {
                itemView.rb_stars_property.rating = 0.0F
                itemView.tv_overal_rating.text = "0.0"
            }

            /** set property image using the first image in the list of images **/
            if (property.images.size > 0) {
                val image = property.images[0]
                val imageUrl = "http://" + image.prefix + image.suffix
                Glide.with(context).load(imageUrl)
                        .apply(RequestOptions().error(null))
                        .into(itemView.iv_property)
            }

            /** set price in euros **/
            val priceInEuros = (property.lowestPricePerNight.value.toFloat() / VEF_TO_EUR)
            itemView.tv_property_value.text = "%.4f".format(priceInEuros) + "€"
        }
    }
}
