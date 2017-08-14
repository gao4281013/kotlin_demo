package com.example.administrator.mykotlin_project.mvp.persenter

import android.content.Context
import com.example.administrator.mykotlin_project.mvp.contract.FindContract
import com.example.administrator.mykotlin_project.mvp.model.bean.FindBean
import com.example.administrator.mykotlin_project.mvp.model.FindModel
import com.example.administrator.mykotlin_project.utils.applyScheduler
import io.reactivex.Observable

/**
 * Created by Administrator on 2017/8/1 0001.
 */
class FindPresenter(context: Context,view:FindContract.View):FindContract.Presenter {
    var mContext:Context?=null
    var mView:FindContract.View?=null
    val mModel:FindModel by lazy {
        FindModel()
    }

    init {
        mView = view
        mContext = context
    }



    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable:Observable<MutableList<FindBean>>?=mContext?.let { mModel.loadData(mContext!!)  }
        observable?.applyScheduler()?.subscribe(){
            beans:MutableList<FindBean> -> mView?.setData(beans)
        }
    }
}