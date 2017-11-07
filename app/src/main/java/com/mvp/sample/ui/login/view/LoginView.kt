package com.mvp.sample.ui.login.view

import com.mvp.sample.base.BaseView

/**
 * Created by Deepak on 26/10/17
 */
interface LoginView : BaseView {
    fun onLoginSuccess(token: String)
    fun onLoginError(error: String)
}