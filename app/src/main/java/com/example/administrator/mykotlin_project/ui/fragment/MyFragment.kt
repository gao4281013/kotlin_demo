package com.example.administrator.mykotlin_project.ui.fragment

import android.graphics.Typeface
import android.view.View
import com.example.administrator.mykotlin_project.R
import kotlinx.android.synthetic.main.fragment_my.*

class MyFragment : BaseFragment(),View.OnClickListener {

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_watch ->{
//                var intent = Intent(activity)
            }
            R.id.tv_advise ->{

            }
            R.id.tv_save ->{

            }


        }
    }


    override fun getLayoutResources(): Int {
      return  R.layout.fragment_my
    }

    override fun initView() {
        tv_advise.setOnClickListener(this)
        tv_watch.setOnClickListener(this)
        tv_save.setOnClickListener(this)
        tv_advise.typeface = Typeface.createFromAsset(context?.assets,"fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        tv_save.typeface = Typeface.createFromAsset(context?.assets,"fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        tv_watch.typeface = Typeface.createFromAsset(context?.assets,"fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }
}
