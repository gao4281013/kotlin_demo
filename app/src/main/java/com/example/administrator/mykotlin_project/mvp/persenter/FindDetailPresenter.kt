package com.example.administrator.mykotlin_project.mvp.persenter

import android.content.Context
import com.example.administrator.mykotlin_project.mvp.contract.FindDetailContract
import com.example.administrator.mykotlin_project.mvp.model.FindDetailModel
import com.example.administrator.mykotlin_project.mvp.model.bean.HotBean
import com.example.administrator.mykotlin_project.utils.applyScheduler
import io.reactivex.Observable

/**
 * Created by Administrator on 2017/8/17 0017.
 */

class FindDetailPresenter(context: Context,view:FindDetailContract.View):FindDetailContract.Presenter{
    var mContext:Context?=null
    var mView:FindDetailContract.View?=null
    val mModel:FindDetailModel by lazy {
        FindDetailModel()
    }

    init {
        mView = view
        mContext = context
    }



    override fun start() {

    }

    override fun requestData(categoryName: String, strategy: String) {
        val observable:Observable<HotBean>?=mContext?.let { mModel.loadData(mContext!!,categoryName,strategy) }
        observable?.applyScheduler()?.subscribe {
            bean:HotBean ->
            mView?.setData(bean)
        }
    }

    fun requestMoreData(start:Int,categoryName: String,strategy: String){
        val observable:Observable<HotBean>?= mContext?.let {
            mModel.loadMoreData(mContext!!,start,categoryName,strategy)
        }
        observable?.applyScheduler()?.subscribe {
            bean:HotBean ->
            mView?.setData(bean)
        }
    }
}
