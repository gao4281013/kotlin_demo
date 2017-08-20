package com.example.administrator.mykotlin_project.search

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.DecelerateInterpolator

/**
 * Created by Administrator on 2017/8/20 0020.
 */
class CircularRevealAnim {
    constructor()

    companion object {
        val DURATION: Long = 200
    }

    private var mListener: AnimListener? = null

    interface AnimListener {
        fun onHideAnimationEnd()

        fun onShowAnimationEnd()
    }

    @SuppressLint("NewApi")
    private fun actionOtherVisible(isShow: Boolean, triggerView: View, animView: View) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            if (isShow) {
                animView.visibility = View.VISIBLE
                if (mListener != null) mListener!!.onShowAnimationEnd()
            } else {
                animView.visibility = View.GONE
                if (mListener != null) mListener!!.onHideAnimationEnd()
            }
            return
        }

        /**
         * 计算triggerView的中心位置
         * */

        val tvLocation = IntArray(2)
        triggerView.getLocationInWindow(tvLocation)
        val tvX = tvLocation[0] + triggerView.width / 2
        val tvY = tvLocation[1] + triggerView.height / 2

        /**
         * 计算animView的中心位置
         * */
        val avLocaion = IntArray(2)
        animView.getLocationInWindow(avLocaion)
        val avX = avLocaion[0] + animView.width / 2
        val avY = avLocaion[1] + animView.height / 2

        val rippleW = if (tvX < avX) animView.width - tvX else tvX - avLocaion[0]
        val rippleH = if (tvY < avY) animView.height - tvY else tvY - avLocaion[1]

        val maxRadius = Math.sqrt((rippleW*rippleW+rippleH*rippleH).toDouble()).toFloat()
        val startRedius:Float
        val endRadius:Float

        if (isShow){
            startRedius = 0f
            endRadius = maxRadius
        }else{
            startRedius = maxRadius
            endRadius = 0f
        }

        val anim = ViewAnimationUtils.createCircularReveal(animView,tvX,tvY,startRedius,endRadius)
        animView.visibility = View.VISIBLE
        anim.duration = DURATION
        anim.interpolator = DecelerateInterpolator()

        anim.addListener(object :AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                if (isShow){
                    animView.visibility =View.VISIBLE
                    if (mListener!=null) mListener!!.onShowAnimationEnd()
                }else{
                    if (mListener!=null) mListener!!.onHideAnimationEnd()
                }
            }
        })

        anim.start()

    }

    fun show(triggerView: View,showView:View){
        actionOtherVisible(true,triggerView,showView)
    }

    fun hide(triggerView: View,showView:View){
        actionOtherVisible(false,triggerView,showView)
    }

    fun setAnimListener(listener:AnimListener){
        mListener = listener
    }

}