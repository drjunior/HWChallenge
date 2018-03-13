package com.hostelworld.challenge.ui.properties

import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.hostelworld.challenge.PropertiesApplication
import com.hostelworld.challenge.R
import com.hostelworld.challenge.data.model.Property
import com.hostelworld.challenge.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_properties_list.*
import java.util.*
import javax.inject.Inject


/**
 * Activity that shows a list of properties
 */
class PropertiesListActivity : BaseActivity(), PropertiesListView {

    @Inject
    lateinit var propertiesListPresenter: PropertiesListPresenter

    private var propertyAdapter: PropertiesListAdapter? = null


    override fun initView(propertiesList: ArrayList<Property>) {

        setSupportActionBar(toolbar)

        propertyAdapter = PropertiesListAdapter(propertiesList)
        val linearLayoutManager = LinearLayoutManager(this)
        rv_properties_list.layoutManager = linearLayoutManager
        rv_properties_list.isNestedScrollingEnabled = false
        rv_properties_list.adapter = propertyAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_properties_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_refresh) {
            propertiesListPresenter.onPropertyListRefresh()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun loadPropertiesList() {
        clpb_properties_list.visibility = View.GONE
        rv_properties_list.visibility = View.VISIBLE
        propertyAdapter?.notifyDataSetChanged()
    }

    override fun initToolbar(title: String, urlLocationMap: String) {
        toolbar_layout.title = title
        Glide.with(this).load(urlLocationMap).into(iv_location)
    }


    override fun hidePropertiesList() {
        rv_properties_list.visibility = View.GONE
    }

    override fun showLoading() {
        clpb_properties_list.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        clpb_properties_list.visibility = View.GONE
    }


    override fun showErrorMessage() {
        Snackbar.make(nsv_root_container, getString(R.string.message_problem), Snackbar.LENGTH_LONG).show()
    }

    override fun initDagger() {
        val app = application as PropertiesApplication
        app.appComponent?.inject(this)
    }

    override fun initPresenter() {
        super.presenter = propertiesListPresenter
        propertiesListPresenter.view = this
    }

    override var layoutId: Int = R.layout.activity_properties_list

}