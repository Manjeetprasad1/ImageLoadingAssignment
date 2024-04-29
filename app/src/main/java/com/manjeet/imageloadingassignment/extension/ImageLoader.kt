package com.manjeet.imageloadingassignment.extension

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.manjeet.imageloadingassignment.ImageLoaderApplication
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object ImageLoader {
    // Memory cache to store Bitmaps
    private val memoryCache = mutableMapOf<String, Bitmap?>()

    private lateinit var appContext: Context
    fun initialize(context: Context) {
        appContext = context.applicationContext // Initialize application context
    }
    private val cacheDir: File by lazy {
        appContext.cacheDir
    }

    @BindingAdapter("imageUrl")
    fun ImageView.bindImage(imgUrl: String?) {
        if (this.drawable == null) {
            imgUrl?.let { url ->
                val imgUri = url.toUri().buildUpon().scheme("https").build().toString()
                // Check memory cache first
                memoryCache[imgUri]?.let { bitmap ->
                    setImageBitmap(bitmap)
                    return
                }
                // Check disk cache
                val file = File(cacheDir, imgUri.hashCode().toString())
                if (file.exists()) {
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    setImageBitmap(bitmap)
                    memoryCache[imgUri] = bitmap // Cache in memory
                    return
                }
                val uiHandler = Handler(Looper.getMainLooper())
                thread(start = true) {
                    try {
                        val bitmap = downloadBitmap(imgUri)
                        uiHandler.post {
                            if (bitmap != null) {
                                setImageBitmap(bitmap)
                                // Cache image in memory
                                memoryCache[imgUri] = bitmap
                                // Cache image to disk
                                saveBitmapToDisk(bitmap, file)
                            } else {
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("ImageLoader", "Error loading image: $e")
                    }
                }
            }
        }
    }

    private fun downloadBitmap(imageUrl: String): Bitmap? {
        return try {
            val conn = URL(imageUrl).openConnection() as HttpURLConnection
            conn.connect()
            val inputStream = conn.inputStream
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            conn.disconnect() // Close the connection
            bitmap
        } catch (e: Exception) {
            null
        }
    }

    private fun saveBitmapToDisk(bitmap: Bitmap, file: File) {
        try {
            FileOutputStream(file).use { out ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            }
        } catch (e: Exception) {
            Log.e("ImageLoader", "Error saving bitmap to disk: $e")
        }
    }

}
