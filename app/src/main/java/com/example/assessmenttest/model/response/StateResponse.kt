package com.example.assessmenttest.model.response

sealed class StateResponse<T>(val data: T? = null, val msg: String = ""){
    class Loading<T> : StateResponse<T>()
    class SuccessGetData<T>(data: T, msg: String): StateResponse<T>(data, msg)
    class FailedGetData<T>(msg: String): StateResponse<T>(msg = msg)
}