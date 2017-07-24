package com.example.administrator.mykotlin_project.ui.fragment

import android.support.v4.app.Fragment
import com.example.administrator.mykotlin_project.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment() {


    override fun getLayoutResources(): Int {
      return  R.layout.fragment_home
    }


}
