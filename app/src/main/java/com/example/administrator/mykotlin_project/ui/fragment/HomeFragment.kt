package com.example.administrator.mykotlin_project.ui.fragment

import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.administrator.mykotlin_project.R
import com.example.administrator.mykotlin_project.adapter.HomeAdapter
import com.example.administrator.mykotlin_project.mvp.contract.HomeContract
import com.example.administrator.mykotlin_project.mvp.model.HomeBean
import com.example.administrator.mykotlin_project.mvp.persenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.regex.Pattern

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment(),HomeContract.View,SwipeRefreshLayout.OnRefreshListener {
    var mIsRefresh:Boolean = false
    var mPresenter:HomePresenter?=null
    var mList = ArrayList<HomeBean.IssueList.ItemList>()
    var mAdapter: HomeAdapter?=null
    var data:String?=null

    override fun getLayoutResources(): Int {
      return  R.layout.fragment_home
    }


    override fun setData(bean: HomeBean) {
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(bean?.nextPageUrl)
        data= m.replaceAll("").substring(1,m.replaceAll("").length-1).toString()
        if (mIsRefresh){
            mIsRefresh = false
            refresh_layout.isRefreshing = false
            if (mList.size>0){
                mList.clear()
            }

        }

        bean.issueList!!
                .flatMap { it.itemList!! }
                .filter { it.type.equals("video") }
                .forEach { mList.add(it) }
        mAdapter?.notifyDataSetChanged()
    }



    override fun onRefresh() {
        if (!mIsRefresh){
            mIsRefresh = true
            mPresenter?.start()
        }
    }

    override fun initView() {

        mPresenter = HomePresenter(context,this)
        Log.i("HomeFragment",mPresenter.toString())
        mPresenter?.start()
        recycle_view.layoutManager = LinearLayoutManager(context)
        mAdapter = HomeAdapter(context,mList)
        recycle_view.adapter = mAdapter
        refresh_layout.setOnRefreshListener(this)
        recycle_view.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var  layoutManager:LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                var lastPosition =layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPosition ==mList.size-1)
                    if (data!=null){
                        mPresenter?.moreData(data)
                    }
            }
        })
    }
}
