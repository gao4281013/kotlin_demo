package com.example.administrator.mykotlin_project.ui

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.example.administrator.mykotlin_project.R
import com.example.administrator.mykotlin_project.ui.fragment.*
import com.example.administrator.mykotlin_project.utils.showToast
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_main_rea.*
import java.util.*

class MainReaActivity : AppCompatActivity(), View.OnClickListener {
    var homeFragment: HomeFragment? = null
    var findFragment: FindFragment? = null
    var hotFragment: HotFragment? = null
    var myFragment: MyFragment? = null
    var mExitTime: Long = 0
    var toast: Toast? = null
    lateinit var searchFragment: SearchFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_rea)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init();
        setRedioButton()
        initToolBar()
        initFragment(savedInstanceState)
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val mFragments: List<Fragment> = supportFragmentManager.fragments
            for (mFragment in mFragments) {
                if (mFragment is HomeFragment) {
                    homeFragment = mFragment
                }

                if (mFragment is FindFragment) {
                    findFragment = mFragment
                }

                if (mFragment is HotFragment) {
                    hotFragment = mFragment
                }

                if (mFragment is MyFragment) {
                    myFragment = mFragment
                }
            }
        } else {
            homeFragment = HomeFragment()
            findFragment = FindFragment()
            hotFragment = HotFragment()
            myFragment = MyFragment()
            val fragemntTrans = supportFragmentManager.beginTransaction()
            fragemntTrans.add(R.id.fl_content, homeFragment)
            fragemntTrans.add(R.id.fl_content, findFragment)
            fragemntTrans.add(R.id.fl_content, hotFragment)
            fragemntTrans.add(R.id.fl_content, myFragment)
            fragemntTrans.commit()

        }
        supportFragmentManager.beginTransaction().show(homeFragment)
                .hide(findFragment)
                .hide(hotFragment)
                .hide(myFragment)
                .commit()

    }

    private fun initToolBar() {
        var today = getToday()
        tv_bar_title.text = today
        tv_bar_title.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        iv_search.setOnClickListener {
            if (rb_my.isChecked) {

            } else {
                searchFragment = SearchFragment()
                searchFragment.show(fragmentManager, SEARCH_TAG)
            }
        }
    }

    private fun getToday(): String {
        var list = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        var data: Date = Date()
        var calender: Calendar = Calendar.getInstance()
        calender.time = data;
        var index: Int = calender.get(Calendar.DAY_OF_WEEK) - 1
        if (index < 0) {
            index = 0
        }
        return list[index]
    }

    private fun setRedioButton() {
        rb_home.isChecked = true
        rb_home.setTextColor(resources.getColor(R.color.black))
        rb_home.setOnClickListener(this)
        rb_find.setOnClickListener(this)
        rb_hot.setOnClickListener(this)
        rb_my.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        clearState()
        when (v?.id) {
            R.id.rb_find -> {
                rb_find.isChecked = true;
                rb_find.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(findFragment)
                        .hide(homeFragment)
                        .hide(hotFragment)
                        .hide(myFragment)
                        .commit()
                tv_bar_title.setText(getString(R.string.discover))
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.mipmap.icon_search)
            }

            R.id.rb_hot -> {
                rb_hot.isChecked = true;
                rb_hot.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(hotFragment)
                        .hide(homeFragment)
                        .hide(findFragment)
                        .hide(myFragment)
                        .commit()
                tv_bar_title.setText(getString(R.string.hot))
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.mipmap.icon_search)
            }/**/

            R.id.rb_home -> {
                rb_home.isChecked = true;
                rb_home.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(homeFragment)
                        .hide(findFragment)
                        .hide(hotFragment)
                        .hide(myFragment)
                        .commit()
                tv_bar_title.text = getToday()
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.mipmap.icon_search)
            }

            R.id.rb_my -> {
                rb_my.isChecked = true;
                rb_my.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(myFragment)
                        .hide(findFragment)
                        .hide(hotFragment)
                        .hide(myFragment)
                        .commit()
                tv_bar_title.setText(getString(R.string.my_fragment))
                tv_bar_title.visibility = View.GONE
                iv_search.setImageResource(R.mipmap.icon_search)
            }


        }
    }

    private fun clearState() {
        rg_root.clearCheck()
        rb_home.setTextColor(resources.getColor(R.color.gray))
        rb_find.setTextColor(resources.getColor(R.color.gray))
        rb_hot.setTextColor(resources.getColor(R.color.gray))
        rb_my.setTextColor(resources.getColor(R.color.gray))
    }


    override fun onPause() {
        super.onPause()
        toast?.let { toast!!.cancel() }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis().minus(mExitTime) <= 3000){
                finish()
                toast!!.cancel()
            }else{
                mExitTime = System.currentTimeMillis()
                toast = showToast(getString(R.string.exit_one_more_time))
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}

