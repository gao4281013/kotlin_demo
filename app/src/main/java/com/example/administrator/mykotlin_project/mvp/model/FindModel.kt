package com.example.administrator.mykotlin_project.mvp.model

import android.content.Context
import com.example.administrator.mykotlin_project.network.ApiService
import com.example.administrator.mykotlin_project.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by Administrator on 2017/8/1 0001.
 */
class FindModel {
    fun loadData(context: Context): Observable<MutableList<FindBean>>?{
        val retrofitClient = RetrofitClient.getInstance(context,ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getFindData();

    }
}