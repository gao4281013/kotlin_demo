package com.example.administrator.mykotlin_project.mvp.model

import android.content.Context
import com.example.administrator.mykotlin_project.mvp.model.bean.HotBean
import com.example.administrator.mykotlin_project.network.ApiService
import com.example.administrator.mykotlin_project.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by Administrator on 2017/8/1 0001.
 */
class HotModel {
    fun loadData(context: Context,stregary:String):Observable<HotBean>?{
        var retrofitCLient = RetrofitClient.getInstance(context,ApiService.BASE_URL)
        var apiService = retrofitCLient.create(ApiService::class.java)
        return apiService?.getHotData(10,stregary,"26868b32e808498db32fd51fb422d00175e179df",83)
    }
}