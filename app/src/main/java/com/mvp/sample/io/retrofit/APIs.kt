package com.mvp.sample.io.retrofit

import com.mvp.sample.ui.login.model.LoginAPIParams
import com.mvp.sample.ui.login.model.LoginModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by Deepak
 */
interface APIs {
    companion object {
        const val BASE_URL: String = "https://example.com/"
        const val TAG_USER_LOGIN: String = "user/login"
    }

    @Headers("Content-Type: application/json")
    @POST(TAG_USER_LOGIN)
    fun userLogin(@Body params: LoginAPIParams): Call<LoginModel>
}