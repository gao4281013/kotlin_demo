package com.example.administrator.mykotlin_project.network

import android.content.Context
import android.util.Log
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.File

/**
 * Created by Administrator on 2017/7/24 0024.
 */
class RetrofitClient private constructor(context:Context,baseUrl:String){
    var httpCacheDirectory:File?=null
    val mConext:Context = context;
    var cache:Cache?=null
    var okHttpClient:OkHttpClient?=null
    var retrofit:Retrofit?=null
    var DEFAULT_TIMEOUT:Long =20
    val url = baseUrl

    init {
        //缓存地址
        if (httpCacheDirectory == null){
            httpCacheDirectory = File(mConext.cacheDir,"app_cache")
        }

        try {
            if (cache == null){
                cache = Cache(httpCacheDirectory,10*1024*1024)
            }
        }catch (e:Exception){
            Log.e("OKhttp","Could not create http cache",e)
        }
        //okHttp 创建

    }





}