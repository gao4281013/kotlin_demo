package com.example.administrator.mykotlin_project.ui.fragment

import android.support.v4.app.Fragment

import com.example.administrator.mykotlin_project.R
import com.example.administrator.mykotlin_project.adapter.FindAdapter
import com.example.administrator.mykotlin_project.mvp.contract.FindContract
import com.example.administrator.mykotlin_project.mvp.model.bean.FindBean
import com.example.administrator.mykotlin_project.mvp.persenter.FindPresenter
import kotlinx.android.synthetic.main.fragment_find.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FindFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FindFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FindFragment : BaseFragment(),FindContract.View {
    var mPresenter:FindPresenter?=null
    var mList:MutableList<FindBean>?=null
    var mAdapter:FindAdapter?=null


    override fun getLayoutResources(): Int {
        return R.layout.fragment_find
    }

    override fun initView() {
        mPresenter = FindPresenter(context,this)
        mPresenter?.start()
        mAdapter = FindAdapter(context,mList)
        gv_find.adapter = mAdapter
        gv_find.setOnItemClickListener{ parent, view, position, id ->
            var bean = mList?.get(position)
            var name = bean?.name

        }


    }

    override fun setData(beans: MutableList<FindBean>) {
         mAdapter?.mList = beans
         mList = beans
         mAdapter?.notifyDataSetChanged()
    }


}
