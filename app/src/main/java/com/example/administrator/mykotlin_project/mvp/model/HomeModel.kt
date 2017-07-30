package com.example.administrator.mykotlin_project.mvp.model

import android.content.ClipData
import android.content.Context
import android.util.Log
import com.example.administrator.mykotlin_project.network.ApiService
import com.example.administrator.mykotlin_project.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by Administrator on 2017/7/29 0029.
 */
class HomeModel {
    fun loadData(context: Context,isFirst:Boolean,data: String?):Observable<HomeBean>?{
        val retrofitClient = RetrofitClient.getInstance(context,ApiService.BASE_URL)
        val  apiService = retrofitClient.create(ApiService::class.java)
        Log.i("homeModel",apiService.toString())
        when(isFirst){
            true -> return apiService?.getHomeData()

            false-> return apiService?.getHomeMoreData(data.toString(),"2")
        }
    }
}