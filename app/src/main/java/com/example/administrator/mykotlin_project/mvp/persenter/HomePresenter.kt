package com.example.administrator.mykotlin_project.mvp.persenter

import android.content.Context
import com.example.administrator.mykotlin_project.mvp.contract.HomeContract
import com.example.administrator.mykotlin_project.mvp.model.HomeBean
import com.example.administrator.mykotlin_project.mvp.model.HomeModel
import com.example.administrator.mykotlin_project.util.applyScheduler
import io.reactivex.Observable

/**
 * Created by Administrator on 2017/7/29 0029.
 */
class HomePresenter(context: Context,view: HomeContract.View):HomeContract.Presenter {

    var mContext:Context?=null
    var mView:HomeContract.View?=null
    val mModel:HomeModel by lazy {
        HomeModel()
    }

    init {
        mView = view
        mContext = context
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable:Observable<HomeBean>?=mContext?.let { mModel.loadData(it,true,"0") }
        observable?.applyScheduler()?.subscribe { homeBean:HomeBean ->
            mView?.setData(homeBean)
        }
    }

    fun moreData(data:String?){
        val observable:Observable<HomeBean>?=mContext?.let { mModel.loadData(it,false,data) }
        observable?.applyScheduler()?.subscribe { homeBean:HomeBean->
            mView?.setData(homeBean)
        }

    }
}