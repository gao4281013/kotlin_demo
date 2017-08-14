package com.example.administrator.mykotlin_project.utils

/**
 * Created by Administrator on 2017/7/23 0023.
 */


inline fun <reified T: android.app.Activity> android.app.Activity.newIntent() {
    val intent = android.content.Intent(this, T::class.java)
    startActivity(intent)
}


fun android.content.Context.showToast(message:String): android.widget.Toast {
    var toast: android.widget.Toast = android.widget.Toast.makeText(this,message, android.widget.Toast.LENGTH_SHORT)
    toast.setGravity(android.view.Gravity.CENTER,0,0)
    toast.show()
    return toast

}

fun <T> io.reactivex.Observable<T>.applyScheduler(): io.reactivex.Observable<T> {
    return subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .unsubscribeOn(io.reactivex.schedulers.Schedulers.io()).
            observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())


}