package com.ycengine.tester.api

import com.ycengine.tester.vo.UserModel
import retrofit2.Call
import retrofit2.http.POST

interface ApiService {

    @POST("assets/usr0201v1")
    fun getUserData() : Call<UserModel>
}