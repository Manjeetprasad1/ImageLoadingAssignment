package com.root.hrms.networkrequest


import com.forthgreen.app.repository.models.PojoNetworkResponse
import com.manjeet.imageloadingassignment.R
import com.manjeet.imageloadingassignment.model.ErrorMessage
import okhttp3.ResponseBody
import java.io.IOException


object RetrofitRequest {

    fun checkForNetworkResponseCode(code: Int): PojoNetworkResponse {
        return when (code) {
            200 -> PojoNetworkResponse(true, false)
            201 -> PojoNetworkResponse(true, false)
            403, 401 -> PojoNetworkResponse(false, true)
            else -> PojoNetworkResponse(false, false)
        }
    }

//    fun checkForBackendResponseCode(code: Int): PojoBackendResponse {
//        return when (code) {
//            100 -> PojoBackendResponse(true, false, false, false)
//            401 -> PojoBackendResponse(false, true, false, false)
//            302 -> PojoBackendResponse(false, false, false, true)
//            else -> PojoBackendResponse(false, false, true, false)
//        }
//    }

    fun getErrorMessage(responseBody: ResponseBody): String {
        val errorMessage = ""
        try {
            val errorConverter = RestClient.retrofitInstance!!
                    .responseBodyConverter<ErrorMessage>(ErrorMessage::class.java, arrayOfNulls<Annotation>(0))
            return errorConverter.convert(responseBody)!!.message
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return errorMessage
    }

//    fun getRetrofitError(t: Throwable): Int {
//        return if (t is IOException) {
//            R.string.no_internet
//        } else {
//            R.string.retrofit_failure
//        }
//    }
}