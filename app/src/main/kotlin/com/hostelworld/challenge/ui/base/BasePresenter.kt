package com.hostelworld.challenge.ui.base

/**
 * Base presenter that any presenter of the application must extend.
 */

abstract class BasePresenter<T : BaseView> {

    var view: T? = null

    open fun init() {}
}