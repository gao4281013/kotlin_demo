package com.example.administrator.mykotlin_project.mvp.contract

import com.example.administrator.mykotlin_project.base.BasePresenter
import com.example.administrator.mykotlin_project.base.BaseView
import com.example.administrator.mykotlin_project.mvp.model.bean.HomeBean

/**
 * Created by Administrator on 2017/7/29 0029.
 */
interface HomeContract {

    interface View: BaseView<Presenter> {
        fun setData(bean: HomeBean)
    }

    interface Presenter: BasePresenter {
        fun requestData()

    }
}