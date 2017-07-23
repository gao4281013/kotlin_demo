package com.example.administrator.mykotlin_project.util

import android.app.Activity
import android.content.Intent

/**
 * Created by Administrator on 2017/7/23 0023.
 */


inline fun <reified T: Activity> Activity.newIntent() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}