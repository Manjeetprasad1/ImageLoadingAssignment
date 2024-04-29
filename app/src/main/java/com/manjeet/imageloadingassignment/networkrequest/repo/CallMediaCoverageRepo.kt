package com.manjeet.imageloadingassignment.networkrequest.repo

import com.root.hrms.networkrequest.NetworkRequestCallbacks
import com.root.hrms.networkrequest.RestClient
import retrofit2.Call
import retrofit2.Response

class CallMediaCoverageRepo {
    fun callMediaCoverageRequest(
        networkRequestCallbacks: NetworkRequestCallbacks
    ){
        val call = RestClient.get().callMediaCoverageApi()
        call.enqueue(object : retrofit2.Callback<Any>{
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                val responseCode = response.code()
                if (responseCode == 200) {
                    return networkRequestCallbacks.onSuccess(response)
                } else if (responseCode == 401) {
                    return networkRequestCallbacks.onSuccess(response)
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                return networkRequestCallbacks.onError(t)
            }

        })
    }

}