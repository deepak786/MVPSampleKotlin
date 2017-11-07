package com.mvp.sample.base

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by Deepak on 26/10/17
 */
open class BaseActivity : AppCompatActivity() {

    protected fun showToast(msg: String?) {
        Toast.makeText(this, msg ?: "", Toast.LENGTH_SHORT).show()
    }
}