package com.example.administrator.mykotlin_project.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by Administrator on 2017/7/28.
 */
object NetworkUtils {

    fun isNetConnected(context: Context):Boolean{
        val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networdInfo:NetworkInfo?=connectManager.activeNetworkInfo
        if (networdInfo==null){
            return false
        }else{
            return networdInfo.isAvailable&&networdInfo.isConnected
        }
    }

    fun isNetWorkConnected(context: Context,typeMobile:Int):Boolean{
        if (isNetConnected(context)){
            return false
        }
        val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo = connectManager.activeNetworkInfo
        if (networkInfo==null){
            return false
        }else{
            return networkInfo.isConnected && networkInfo.isAvailable
        }
    }

    fun isPhoneNetConnect(context: Context):Boolean{
        val typeMobile = ConnectivityManager.TYPE_MOBILE
        return isNetWorkConnected(context,typeMobile)
    }

    fun isWifiNetConnected(context: Context):Boolean{
        val typeWifi = ConnectivityManager.TYPE_WIFI
        return isNetWorkConnected(context,typeWifi)
    }
}