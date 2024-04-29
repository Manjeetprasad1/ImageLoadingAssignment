package com.manjeet.imageloadingassignment

import android.app.Application
import android.content.Context

class ImageLoaderApplication : Application() {
    companion object {
        private var sInstance: ImageLoaderApplication? = null


        fun newInstance(): ImageLoaderApplication {
            if (sInstance == null) return ImageLoaderApplication()
            else return sInstance!!
        }

        fun getApplication(): ImageLoaderApplication {
            return sInstance!!
        }
    }

    fun getContext(): Context {
        return sInstance!!.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        // force AsyncTask to be initialized in the main thread due to the bug:
        try {
            Class.forName("android.os.AsyncTask")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }

    }
}