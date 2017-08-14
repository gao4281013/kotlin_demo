package com.example.administrator.mykotlin_project.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by Administrator on 2017/7/28.
 */
object NetworkUtils {

    fun isNetConnected(context: android.content.Context):Boolean{
        val connectManager = context.getSystemService(android.content.Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val networdInfo: android.net.NetworkInfo?=connectManager.activeNetworkInfo
        if (networdInfo==null){
            return false
        }else{
            return networdInfo.isAvailable&&networdInfo.isConnected
        }
    }

    fun isNetWorkConnected(context: android.content.Context, typeMobile:Int):Boolean{
        if (com.example.administrator.mykotlin_project.utils.NetworkUtils.isNetConnected(context)){
            return false
        }
        val connectManager = context.getSystemService(android.content.Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val networkInfo: android.net.NetworkInfo = connectManager.activeNetworkInfo
        if (networkInfo==null){
            return false
        }else{
            return networkInfo.isConnected && networkInfo.isAvailable
        }
    }

    fun isPhoneNetConnect(context: android.content.Context):Boolean{
        val typeMobile = android.net.ConnectivityManager.TYPE_MOBILE
        return com.example.administrator.mykotlin_project.utils.NetworkUtils.isNetWorkConnected(context, typeMobile)
    }

    fun isWifiNetConnected(context: android.content.Context):Boolean{
        val typeWifi = android.net.ConnectivityManager.TYPE_WIFI
        return com.example.administrator.mykotlin_project.utils.NetworkUtils.isNetWorkConnected(context, typeWifi)
    }
}