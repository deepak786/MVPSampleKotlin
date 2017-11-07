package com.mvp.sample.ui.login.view

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import com.mvp.sample.R
import com.mvp.sample.base.BaseActivity
import com.mvp.sample.ui.login.presenter.LoginPresenterImpl
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {
    private lateinit var presenter: LoginPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // initialize the presenter
        presenter = LoginPresenterImpl(this)

        // set click listener on login button
        login.setOnClickListener { validateLoginForm() }
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showMessage(msg: String) {
        showToast(msg)
    }

    /**
     * login form validation
     */
    private fun validateLoginForm() {
        val email: String = email.text.toString().trim()
        val password: String = password.text.toString()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) { // required validation
            showMessage(getString(R.string.emptyFields))
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { // invalid email
            showMessage(getString(R.string.invalidEmail))
        } else {
            // form is valid
            // validate the login details
            presenter.validateLoginDetails(email = email, password = password)
        }
    }

    override fun onLoginSuccess(token: String) {
        showMessage(token)
    }

    override fun onLoginError(error: String) {
        password.text.clear()
        showMessage(error)
    }
}