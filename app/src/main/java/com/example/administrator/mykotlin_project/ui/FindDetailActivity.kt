package com.example.administrator.mykotlin_project.ui

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.administrator.mykotlin_project.R
import com.example.administrator.mykotlin_project.adapter.RankAdapter
import com.example.administrator.mykotlin_project.mvp.contract.FindDetailContract
import com.example.administrator.mykotlin_project.mvp.model.bean.HotBean
import com.example.administrator.mykotlin_project.mvp.persenter.FindDetailPresenter
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_find_detail.*
import java.util.regex.Pattern

/**
 * Created by Administrator on 2017/8/17 0017.
 */
class FindDetailActivity:AppCompatActivity(),FindDetailContract.View,SwipeRefreshLayout.OnRefreshListener{

    lateinit var mPresenter:FindDetailPresenter
    lateinit var mAdapter:RankAdapter
    lateinit var data:String
    var mIsRefresh:Boolean = false
    var mList:ArrayList<HotBean.ItemList.Data> = ArrayList()
    var mstart:Int =10
    lateinit var name: String



    override fun setData(bean: HotBean) {
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(bean.nextPageUrl as CharSequence?)
        data = m.replaceAll("").subSequence(1,m.replaceAll("").length-1).toString()
        if (mIsRefresh){
            mIsRefresh = false
            refresh_Layout.isRefreshing = false
            if (mList.size>0){
                mList.clear()
            }
        }
        bean.itemList?.forEach {
            it.data?.let { it1 -> mList.add(it1) }
        }

        mAdapter.notifyDataSetChanged()

    }

    override fun onRefresh() {
       if (!mIsRefresh){
           mIsRefresh = true
           mPresenter.requestData(name,"date")
       }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_detail)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        setToolbar()
        recycle_view.layoutManager = LinearLayoutManager(this)
        mAdapter = RankAdapter(this,mList)
        recycle_view.adapter = mAdapter
        refresh_Layout.setOnRefreshListener(this)
        mPresenter = FindDetailPresenter(this,this)
        mPresenter.requestData(name,"date")
        recycle_view.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var layoutManager:LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                var lastPosition = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPosition == mList.size - 1){
                    if (data!= null){
                      mPresenter?.requestMoreData(mstart,name,"date")
                      mstart = mstart.plus(10)
                    }
                }
            }
        })

    }

    private fun setToolbar(){
        setSupportActionBar(toolbar)
        var bar = supportActionBar
        intent.getStringExtra("name")?.let {
            name = intent.getStringExtra("name")
            bar?.title = name
        }
        bar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener{
            onBackPressed()
        }
    }
}