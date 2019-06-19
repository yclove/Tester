package com.ycengine.tester.api

import com.ycengine.tester.vo.UserModel

class RemoteRepository {

    private var service = TbRetrofit.service

    fun getUserData(): UserModel? {
        val response = service.getUserData().execute()
//        response.error()?.let { throw it }
        return response.body()
    }

//    private inline fun <T> Response<BaseModel<T>>.error(): PostException? = when {
//        this.errorBody() != null -> PostException(PostException.ERROR_BODY, this.errorBody()?.string() ?: "")
//        !this.isSuccessful -> PostException(PostException.IS_NOT_SUCCESSFUL, "IS_NOT_SUCCESSFUL")
//        this.body() == null -> PostException(PostException.NULL_BODY, "NULL_BODY")
//        !PostException.SUCCESS.equals(this.body()!!.CODE, ignoreCase = false) -> PostException(this.body()!!.CODE, this.body()!!.MESSAGE)
//        else -> null
//    }
}