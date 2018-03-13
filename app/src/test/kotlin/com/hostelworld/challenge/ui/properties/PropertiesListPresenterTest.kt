package com.hostelworld.challenge.ui.properties

import com.hostelworld.challenge.data.model.Property
import com.hostelworld.challenge.ui.base.BasePresenterTest

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.ArrayList

/**
 * PropertiesListPresenter unit tests
 */
class PropertiesListPresenterTest : BasePresenterTest() {

    @Mock
    lateinit var propertiesListPresenter: PropertiesListPresenter

    @Mock
    lateinit var view: PropertiesListView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testInit_initView() {

        propertiesListPresenter.view = view
        propertiesListPresenter.init()

        var anyList: ArrayList<Property> = ArrayList(1)
        verify(view, times(1)).initView(propertiesList = anyList)
    }

}
