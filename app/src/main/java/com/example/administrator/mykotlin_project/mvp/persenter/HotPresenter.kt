package com.example.administrator.mykotlin_project.mvp.persenter

import android.content.Context
import com.example.administrator.mykotlin_project.mvp.contract.HotContract
import com.example.administrator.mykotlin_project.mvp.model.HotBean
import com.example.administrator.mykotlin_project.mvp.model.HotModel
import com.example.administrator.mykotlin_project.util.applyScheduler
import io.reactivex.Observable

/**
 * Created by Administrator on 2017/8/1 0001.
 */
class HotPresenter(context: Context,view:HotContract.View):HotContract.Presenter{
    var mContext:Context? = null
    var mView:HotContract.View?=null
    val mModel:HotModel by lazy {
        HotModel()
    }

    init {
        mContext = context
        mView = view
    }


    override fun start() {

    }

    override fun requestData(strategy: String) {
        val observable:Observable<HotBean>?=mContext?.let { mModel.loadData(it,strategy) }
        observable?.applyScheduler()?.subscribe { hotbean:HotBean ->
            mView?.setData(hotbean)
        }
    }
}