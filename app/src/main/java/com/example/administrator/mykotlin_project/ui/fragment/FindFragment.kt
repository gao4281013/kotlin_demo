package com.example.administrator.mykotlin_project.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.administrator.mykotlin_project.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FindFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FindFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FindFragment : BaseFragment() {


    override fun getLayoutResources(): Int {
        return R.layout.fragment_find
    }

    override fun initView() {
    }
}
