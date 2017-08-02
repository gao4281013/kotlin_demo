package com.example.administrator.mykotlin_project.mvp.contract

import com.example.administrator.mykotlin_project.base.BasePresenter
import com.example.administrator.mykotlin_project.base.BaseView
import com.example.administrator.mykotlin_project.mvp.model.bean.HotBean

/**
 * Created by Administrator on 2017/8/1 0001.
 */
interface HotContract {

    interface View:BaseView<Presenter>{
        fun setData(beas: HotBean)
    }


    interface Presenter:BasePresenter{
        fun requestData(strategy:String)
    }
}