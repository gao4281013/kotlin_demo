package com.example.administrator.mykotlin_project.search

import android.app.DialogFragment
import android.content.Context
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.view.*

import com.example.administrator.mykotlin_project.R


const val SEARCH_TAG = "SearchFragment"
class SearchFragment : DialogFragment(),CircularRevealAnim.AnimListener,ViewTreeObserver.OnPreDrawListener,DialogInterface.OnKeyListener,View.OnClickListener {

    var data:MutableList<String> = arrayListOf("脱口秀","城会玩","666","笑cry","漫威",
            "清新","匠心","VR","心理学","舞蹈","品牌广告","粉丝自制","电影相关","萝莉","魔性"
            ,"第一视角","教程","毕业设计","奥斯卡","燃","冰与火之歌","温情","线下campaign","公益")

    lateinit var mRootView:View
    lateinit var mCriclarRevealAnim:CircularRevealAnim


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_blank, container, false)
    }


    override fun onHideAnimationEnd() {
    }

    override fun onShowAnimationEnd() {
    }

    override fun onPreDraw(): Boolean {
        return false
    }

    override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
        return false
    }

    override fun onClick(v: View?) {
    }
}
