package com.hostelworld.challenge.ui.properties

import com.hostelworld.challenge.data.model.PropertiesApiResult
import com.hostelworld.challenge.data.model.Property
import com.hostelworld.challenge.network.PropertiesService
import com.hostelworld.challenge.ui.base.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import java.util.*
import javax.inject.Inject

/**
 * Presenter responsible for showing a list of properties
 */
class PropertiesListPresenter @Inject constructor(val propertiesService: PropertiesService) : BasePresenter<PropertiesListView>() {

    var propertiesList = ArrayList<Property>()
    val GMAP_KEY = "AIzaSyD48tFuWBQYHDqsIHhdOrgKFptF6pOMFjc"


    override fun init() {
        super.init()

        view?.initView(propertiesList)
        getProperties()
    }

    fun onPropertyListRefresh() {
        view?.showLoading()
        view?.hidePropertiesList()
        getProperties()
    }

    private fun getProperties() {
        propertiesService.getProperties(propertiesListCallback)
    }

    private val propertiesListCallback = object : Callback<PropertiesApiResult> {
        override fun onResponse(call: Call<PropertiesApiResult>, response: retrofit2.Response<PropertiesApiResult>) {

            view?.hideLoading()

            if (response.isSuccessful) {

                val responseResult = response.body()

                if (responseResult != null) {

                    /** set city ui information **/
                    val city = responseResult?.location?.city
                    val cityName = city?.name
                    if (cityName != null) {
                        val urlLocationMap = "https://maps.googleapis.com/maps/api/staticmap?center=$cityName&zoom=15&size=900x300&maptype=terrain&key=$GMAP_KEY"
                        view?.initToolbar(cityName, urlLocationMap)
                    }

                    /** set properties list **/
                    propertiesList.removeAll(propertiesList)
                    propertiesList.addAll(responseResult.properties!!)
                    view?.loadPropertiesList()

                } else {
                    view?.hidePropertiesList()
                    view?.showErrorMessage()
                }
            } else {
                view?.hidePropertiesList()
                view?.showErrorMessage()
            }
        }

        override fun onFailure(call: Call<PropertiesApiResult>, t: Throwable) {

            /** show error message and hide list and loading views **/
            view?.hidePropertiesList()
            view?.showErrorMessage()
            view?.hideLoading()
        }
    }
}