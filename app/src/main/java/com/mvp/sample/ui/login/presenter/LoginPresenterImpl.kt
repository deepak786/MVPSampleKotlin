package com.mvp.sample.ui.login.presenter

import com.google.gson.Gson
import com.mvp.sample.io.retrofit.APICallback
import com.mvp.sample.io.retrofit.APIHandler
import com.mvp.sample.ui.login.model.Error
import com.mvp.sample.ui.login.model.LoginAPIParams
import com.mvp.sample.ui.login.model.LoginModel
import com.mvp.sample.ui.login.view.LoginView
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Deepak on 26/10/17
 */
class LoginPresenterImpl() : LoginPresenter, APICallback {
    private var view: LoginView? = null

    constructor(view: LoginView) : this() {
        this.view = view
    }


    override fun validateLoginDetails(email: String, password: String) {
        view?.showProgress()
        val params = LoginAPIParams()
        params.email = email
        params.password = password

        APIHandler.getInstance().userLogin(params, this);
    }

    override fun onSuccess(call: Call<*>?, response: Response<*>?, tag: String) {
        if (response?.isSuccessful == true) {
            val loginResponse: LoginModel? = response.body() as LoginModel
            view?.onLoginSuccess(loginResponse?.token ?: "")
        } else {
            val gson = Gson()
            val error: Error = gson.fromJson(response?.errorBody()?.string() ?: "", com.mvp.sample.ui.login.model.Error::class.java)
            view?.onLoginError(error = error.message)
        }
        view?.hideProgress()
    }

    override fun onFailure(call: Call<*>?, t: Throwable?, tag: String) {
        view?.hideProgress()
        view?.onLoginError(t?.localizedMessage ?: "")
    }
}