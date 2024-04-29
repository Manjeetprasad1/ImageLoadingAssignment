package com.root.hrms.networkrequest

import retrofit2.Response


interface NetworkRequestCallbacks {

    fun onSuccess(response: Response<Any>)

    fun onError(t: Throwable)

}