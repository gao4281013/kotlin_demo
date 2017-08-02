package com.example.administrator.mykotlin_project.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.administrator.mykotlin_project.R
import com.example.administrator.mykotlin_project.adapter.RankAdapter
import com.example.administrator.mykotlin_project.mvp.contract.HotContract
import com.example.administrator.mykotlin_project.mvp.model.bean.HotBean
import com.example.administrator.mykotlin_project.mvp.persenter.HotPresenter
import kotlinx.android.synthetic.main.rank_fragment_layout.*

/**
 * Created by Administrator on 2017/8/2.
 */
class RankFragment:BaseFragment(),HotContract.View{
    lateinit var mPresenter:HotPresenter
    lateinit var mStrategy:String
    lateinit var mAdapter:RankAdapter
    var mList:ArrayList<HotBean.ItemList.Data> = ArrayList()

    override fun setData(beas: HotBean) {
        Log.e("rank",beas.toString())
        if (mList.size>0){
            mList.clear()
        }

        beas.itemList?.forEach {
            it.data?.let {
                it1 ->
                mList.add(it1)
            }
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun getLayoutResources(): Int {
        return R.layout.rank_fragment_layout
    }

    override fun initView() {
        recycleview.layoutManager = LinearLayoutManager(context)
        mAdapter = RankAdapter(context,mList)
        recycleview.adapter = mAdapter
        if (arguments!=null){
            mStrategy = arguments.getString("strategy")
            mPresenter = HotPresenter(context,this)
            mPresenter.requestData(mStrategy)
        }
    }
}