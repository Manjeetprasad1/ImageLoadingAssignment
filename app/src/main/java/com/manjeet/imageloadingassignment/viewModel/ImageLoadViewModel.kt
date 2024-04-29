package com.manjeet.imageloadingassignment.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.manjeet.imageloadingassignment.model.MediaCoverageResponse
import com.manjeet.imageloadingassignment.model.MediaCoverageResponseItem
import com.manjeet.imageloadingassignment.networkrequest.repo.CallMediaCoverageRepo
import com.root.hrms.networkrequest.NetworkRequestCallbacks
import retrofit2.Response

class ImageLoadViewModel : ViewModel() {


    private val mediaCoverageResponseLiveData : MutableLiveData<ArrayList<MediaCoverageResponseItem>> = MutableLiveData()

    companion object{
        const val TAG = "ImageLoadViewModel"
        private val mCallMediaCoverageRepo by lazy {
            CallMediaCoverageRepo()
        }
    }

    fun callMediaCoverageApi(){
        mCallMediaCoverageRepo.callMediaCoverageRequest(object :NetworkRequestCallbacks{
            override fun onSuccess(response: Response<Any>) {
                if (response.isSuccessful){
                    try {
                        val gson = Gson()
                        val jsonString = gson.toJson(response.body())
                        val itemType = object : TypeToken<List<MediaCoverageResponseItem>>() {}.type
                        val mediaCoverageList: List<MediaCoverageResponseItem> = gson.fromJson(jsonString, itemType)
                        Log.d("MainActivity1sssss", "setGridImageListAdapter: $mediaCoverageList")
                        mediaCoverageResponseLiveData.value = mediaCoverageList as ArrayList
                        Log.d("MainActivity1sssss", "setGridImageListAdapter: ${mediaCoverageResponseLiveData.value?.size}}")
                    }catch (e: Exception) {
                        Log.d(TAG, " ${e.message}")
                    }
                }
            }
            override fun onError(t: Throwable) {

            }

        })
    }

    fun getMediaCoverageData() : LiveData<ArrayList<MediaCoverageResponseItem>> = mediaCoverageResponseLiveData

}