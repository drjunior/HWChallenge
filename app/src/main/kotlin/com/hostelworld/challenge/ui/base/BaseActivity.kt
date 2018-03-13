package com.hostelworld.challenge.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


/**
 * Activity that all activity classes must extend.
 */

abstract class BaseActivity : AppCompatActivity(), BaseView {
    open var presenter: BasePresenter<*>? = null

    protected abstract fun initDagger()

    protected abstract fun initPresenter()

    abstract var layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initDagger()
        initPresenter()

        presenter?.init()
    }
}