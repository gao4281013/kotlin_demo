package com.example.administrator.mykotlin_project.ui

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.example.administrator.mykotlin_project.R
import com.example.administrator.mykotlin_project.util.ImageLoadUtil
import com.example.administrator.mykotlin_project.utils.SPUtils
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.tt.lvruheng.eyepetizer.mvp.model.bean.VideoBean
import kotlinx.android.synthetic.main.activity_video_detail.*

/**
 * Created by Administrator on 2017/8/2.
 */
class VideoDetailActivity : AppCompatActivity(){
    companion object{
        var MSG_IMAGE_LOADED = 101
    }

    var mContext:Context = this
    lateinit var imageview:ImageView
    lateinit var bean:VideoBean
    var isPlay:Boolean = false
    var isPause:Boolean = false
    lateinit var orientionUtils:OrientationUtils //处理屏幕旋转
    var mHandler: Handler = object :Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when(msg?.what){
                MSG_IMAGE_LOADED -> {
                    gsy_player.setThumbImageView(imageview)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_detail)
        bean = intent.getParcelableExtra("data")
        initView()
        prepareVideo()
    }

    private fun prepareVideo() {

    }

    private fun initView() {
        var bgUrl = bean.blurred
        bgUrl?.let { ImageLoadUtil.displayHigh(this,iv_bottom_bg,bgUrl) }
        tv_video_desc.text =bean.description
        tv_video_desc.typeface = Typeface.createFromAsset(this.assets,"fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        tv_video_title.text =bean.title
        tv_video_title.typeface = Typeface.createFromAsset(this.assets,"fonts/FZLanTingHeiS-L-GB-Regular.TTF")
        var category = bean.category
        var duration = bean.duration
        var minute = duration?.div(60)
        var second = duration?.minus((minute?.times(60)) as Long)
        var realMinute:String
        var realSecond:String
        if (minute!!<10){
            realMinute = "0"+minute
        }else{
            realMinute = minute.toString()
        }

        if (second!!<10){
            realSecond = "0"+second
        }else{
            realSecond = second.toString()
        }

        tv_video_time.text = "$category/$realMinute‘$realSecond''"
        tv_video_favor.text = bean.collect.toString()
        tv_video_share.text = bean.share.toString()
        tv_video_reply.text = bean.share.toString()
        tv_video_download.setOnClickListener {
            //点击下载
            var url = bean.playUrl?.let {
                it1 ->SPUtils.getInstance(this,"downloads").getString(it1) }
              if (url.equals("")){
                  var count = SPUtils.getInstance(this,"downloads").getInt("count")
                  if (count!=-1){
                      count = count.inc()
                  }else{
                      count = 1
                  }

                  SPUtils.getInstance(this,"downloads").put("count",count)



              }


        }


    }


}