package com.mvp.sample.io.retrofit

import com.mvp.sample.BuildConfig
import com.mvp.sample.ui.login.model.LoginAPIParams
import com.mvp.sample.ui.login.model.LoginModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Deepak
 */
class APIHandler private constructor() {
    private var api: APIs? = null

    /**
     * declare static variables
     */
    companion object {
        private var handler: APIHandler? = null
        fun getInstance(): APIHandler {
            if (handler == null)
                handler = APIHandler()

            return handler as APIHandler
        }
    }

    /**
     * get the @Retrofit instance
     */
    private fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
                .client(client)
                .baseUrl(APIs.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    /**
     * get the @APIs instance
     */
    private fun getAPIs(): APIs {
        if (api == null)
            api = getRetrofit().create(APIs::class.java)

        return api as APIs
    }

    fun userLogin(params: LoginAPIParams, callback: APICallback?): Call<LoginModel> {
        val api: Call<LoginModel> = getAPIs().userLogin(params)
        api.enqueue(object : Callback<LoginModel> {
            override fun onResponse(call: Call<LoginModel>?, response: Response<LoginModel>?) {
                callback?.onSuccess(call, response, APIs.TAG_USER_LOGIN)
            }

            override fun onFailure(call: Call<LoginModel>?, t: Throwable?) {
                callback?.onFailure(call, t, APIs.TAG_USER_LOGIN)
            }
        })
        return api
    }
}