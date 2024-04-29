package com.root.hrms.networkrequest


import com.manjeet.imageloadingassignment.networkrequest.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Manjeet prasad on 1-04-2024.
 */
object RestClient {

    private var REST_CLIENT: ApiService? = null
    var retrofitInstance: Retrofit? = null

    init {
        setUpRestClient()
    }

    fun get(): ApiService {
        return REST_CLIENT!!
    }

    private fun setUpRestClient() {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY


        /*
              .addHeader("api-version", AppUtils.getApiVersion())
                    .addHeader("app-version", AppUtils.getAppVersion(GrizzlyApplication.getApplication().context))
                    .addHeader("device-id", AppUtils.getDeviceId())
                    .addHeader("device-model", "AppUtils.getDeviceModelName()")
                    .addHeader("device-name", AppUtils.getDeviceName())
                    .addHeader("device-os", AppUtils.getDeviceOs())
                    .addHeader("device-type", AppUtils.getDeviceType().toString())
                    .addHeader("timezone", AppUtils.getLocalTImeZone())
                    .addHeader("language","en")
                    .addHeader("latitude","37.7558")
                    .addHeader("longitude","-122.4449")
         */

        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()
//        val client: OkHttpClient.Builder = OkHttpClient.Builder()
//            .addNetworkInterceptor(StethoInterceptor())
//
//        client.addInterceptor(Interceptor { chain ->
//            val request: Request = chain.request()
//                .newBuilder()
//                .addHeader("Content-Type", "application/json")
//                .addHeader("X-Frappe-Site-Name","hrms.root.io")
//                .addHeader("Authorization", "token bb8da0c1cf821632f72f1b1e095ec1")
//                // .addHeader("timezone", AppUtils.getLocalTImeZone())
//                .build()
//            chain.proceed(request)
//        })
//        client.connectTimeout(60, TimeUnit.SECONDS)
//        client.writeTimeout(60, TimeUnit.SECONDS)
//        client.readTimeout(60, TimeUnit.SECONDS)
//        if (BuildConfig.DEBUG) {
//            client.addInterceptor(interceptor)
//        }

      // val okHttpClient: OkHttpClient = client.build()

        retrofitInstance = Retrofit.Builder()
                .baseUrl(WebConstants.ACTION_BASE_URL_FOR_APIS)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create()
                ).build()
        REST_CLIENT = retrofitInstance!!.create(ApiService::class.java)
    }

}