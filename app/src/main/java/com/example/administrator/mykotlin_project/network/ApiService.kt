package com.example.administrator.mykotlin_project.network

import com.example.administrator.mykotlin_project.mvp.model.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Administrator on 2017/7/24 0024.
 */
interface ApiService {

    companion object{
        val BASE_URL:String
             get() = "http://baobab.kaiyanapp.com/api/"
    }

    //获取首页第一页数据
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getHomeData():Observable<HomeBean>


    @GET("v2/feed")
    fun getHomeMoreData(@Query("date") date:String,@Query("num")num:String):Observable<HomeBean>

}