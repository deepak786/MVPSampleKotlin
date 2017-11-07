package com.mvp.sample.ui.login.presenter

/**
 * Created by Deepak on 26/10/17
 */
interface LoginPresenter {
    fun validateLoginDetails(email: String, password: String)
}