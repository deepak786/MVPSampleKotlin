package com.mvp.sample.io.retrofit

import retrofit2.Call
import retrofit2.Response

/**
 * Created by Deepak
 */
interface APICallback {
    fun onSuccess(call: Call<*>?, response: Response<*>?, tag: String)

    fun onFailure(call: Call<*>?, t: Throwable?, tag: String)
}