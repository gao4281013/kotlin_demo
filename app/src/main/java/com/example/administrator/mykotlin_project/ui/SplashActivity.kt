package com.example.administrator.mykotlin_project.ui

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.example.administrator.mykotlin_project.MainActivity
import com.example.administrator.mykotlin_project.R
import com.example.administrator.mykotlin_project.util.newIntent
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置全屏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        initView();
        setAnimation();

    }

    private fun setAnimation() {

        val valueAnimator = ObjectAnimator.ofFloat(iv_icon_splash,"alpha",0f,1f);
        valueAnimator.setDuration(2000);
        valueAnimator.start()
        valueAnimator.addListener(object:Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                newIntent<MainActivity>()
                finish()
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }
        })

    }

    private fun initView() {
        val font: Typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        tv_name_english.typeface = font;
        tv_english_intro.typeface = font;
    }
}
