package com.ycengine.tester

enum class ErrorApi(val code: Int, val message: String = "") {
    NOT_FOUND(code = 404, message = "not found"),
    SERVER_ERROR(code = 503, message = "server error")
}