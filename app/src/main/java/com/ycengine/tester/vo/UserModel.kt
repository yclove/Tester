package com.ycengine.tester.vo

data class UserModel(
    val RESPONSE: User
) : BaseModel()

data class User(val UAI: String)