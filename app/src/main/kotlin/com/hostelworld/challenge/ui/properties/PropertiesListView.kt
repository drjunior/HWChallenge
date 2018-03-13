package com.hostelworld.challenge.ui.properties

import com.hostelworld.challenge.data.model.Property
import com.hostelworld.challenge.ui.base.BaseView
import java.util.*

interface PropertiesListView : BaseView {
    fun initView(propertiesList: ArrayList<Property>)

    fun loadPropertiesList()

    fun showLoading()

    fun hideLoading()

    fun initToolbar(title: String, urlLocationMap: String)

    fun hidePropertiesList()

    fun showErrorMessage()
}