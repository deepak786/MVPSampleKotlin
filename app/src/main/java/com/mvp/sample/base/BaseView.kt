package com.mvp.sample.base

/**
 * Created by Deepak on 26/10/17
 */
interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun showMessage(msg: String)
}