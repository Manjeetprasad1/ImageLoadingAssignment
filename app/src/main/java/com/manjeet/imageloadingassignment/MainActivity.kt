package com.manjeet.imageloadingassignment

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.manjeet.imageloadingassignment.adpter.ImageLoadAdapter
import com.manjeet.imageloadingassignment.databinding.ActivityMainBinding
import com.manjeet.imageloadingassignment.extension.ImageLoader
import com.manjeet.imageloadingassignment.model.MediaCoverageResponseItem
import com.manjeet.imageloadingassignment.viewModel.ImageLoadViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val mImageLoadViewModel by lazy {
        ViewModelProvider(this)[ImageLoadViewModel::class.java]
    }
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        runBlocking {
            installSplashScreen()
            delay(1000)
        }
        setContentView(binding.root)

        ImageLoader.initialize(this)
        if (isInternetAvailable()){
            val bitmapList = ImageLoader.loadBitmapsFromDisk()
            Log.d("AppCompatActivityaa", "onCreate: ${bitmapList.size}")
            binding.pgLoader.visibility = View.VISIBLE
            mImageLoadViewModel.callMediaCoverageApi()
            mImageLoadViewModel.getMediaCoverageData().observe(this){data->
                if (data!=null){
                    setGridImageListAdapter(data)
                }
            }
        }else{

        }

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun setGridImageListAdapter(data: ArrayList<MediaCoverageResponseItem>) {
        binding.rvImageList.apply {
            adapter = ImageLoadAdapter(this@MainActivity,data)
            layoutManager = GridLayoutManager(this@MainActivity,3)
            adapter?.notifyDataSetChanged()
        }
        binding.pgLoader.visibility = View.GONE
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val networkInfo = connectivityManager?.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}