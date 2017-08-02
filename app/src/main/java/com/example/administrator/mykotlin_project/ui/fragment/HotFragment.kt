package com.example.administrator.mykotlin_project.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.administrator.mykotlin_project.R
import com.example.administrator.mykotlin_project.adapter.HotAdapter
import kotlinx.android.synthetic.main.fragment_hot.*

class HotFragment : BaseFragment() {
    var mTabs = listOf<String>("周排行","月排行","总排行").toMutableList()
    lateinit var mFragment:ArrayList<Fragment>
    val STRATEGY = arrayOf("weekly","monthly","historical")


    override fun getLayoutResources(): Int {
       return R.layout.fragment_hot
    }

    override fun initView() {
        var weekFragment:RankFragment= RankFragment()
        var weekBundle = Bundle()
        weekBundle.putString("strategy",STRATEGY[0])
        weekFragment.arguments= weekBundle
        var monthFragment:RankFragment = RankFragment()
        var monthBundle = Bundle()
        monthBundle.putString("strategy",STRATEGY[1])
        monthFragment.arguments=monthBundle
        var allFragment:RankFragment = RankFragment()
        var allBundle = Bundle()
        allBundle.putString("strategy",STRATEGY[2])
        allFragment.arguments = allBundle
        mFragment = ArrayList()
        mFragment.add(weekFragment as Fragment)
        mFragment.add(monthFragment as Fragment)
        mFragment.add(allFragment as Fragment)
        view_pager.adapter = HotAdapter(fragmentManager,mFragment,mTabs)
        tabs.setupWithViewPager(view_pager)


    }
}
