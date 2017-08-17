package com.example.administrator.mykotlin_project.mvp.contract

import com.example.administrator.mykotlin_project.base.BasePresenter
import com.example.administrator.mykotlin_project.base.BaseView
import com.example.administrator.mykotlin_project.mvp.model.bean.HotBean

/**
 * Created by Administrator on 2017/8/17 0017.
 */
interface FindDetailContract {
    interface View:BaseView<Presenter>{
        fun setData(bean:HotBean)
    }


    interface Presenter:BasePresenter{
        fun requestData(categoryName:String,strategy:String)
    }
}