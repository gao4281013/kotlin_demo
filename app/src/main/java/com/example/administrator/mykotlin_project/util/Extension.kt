package com.example.administrator.mykotlin_project.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Administrator on 2017/7/23 0023.
 */


inline fun <reified T: Activity> Activity.newIntent() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}


fun Context.showToast(message:String):Toast{
    var toast:Toast = Toast.makeText(this,message,Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER,0,0)
    toast.show()
    return toast

}

fun <T> Observable<T>.applyScheduler():Observable<T>{
    return subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io()).
            observeOn(AndroidSchedulers.mainThread())


}