package com.example.administrator.mykotlin_project.ui

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.administrator.mykotlin_project.R
import com.example.administrator.mykotlin_project.utils.*
import com.shuyu.gsyvideoplayer.GSYVideoPlayer
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.tt.lvruheng.eyepetizer.mvp.model.bean.VideoBean
import kotlinx.android.synthetic.main.activity_video_detail.*
import zlc.season.rxdownload2.RxDownload
import java.io.FileInputStream

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
                it1 ->
                SPUtils.getInstance(this,"downloads").getString(it1) }
              if (url.equals("")){
                  var count = SPUtils.getInstance(this,"downloads").getInt("count")
                  if (count!=-1){
                      count = count.inc()
                  }else{
                      count = 1
                  }
                  SPUtils.getInstance(this,"downloads").put("count",count)
                  ObjectSaveUtil.saveObject(this,"download$count",bean)
                  addMission(bean.playUrl,count)
              }
        }
    }

    private fun addMission(playUrl: String?, count: Int) {
            RxDownload.getInstance(this).serviceDownload(playUrl,"download$count").subscribe({
              showToast(getString(R.string.start_download))
              SPUtils.getInstance(this,"downloads").put(bean.playUrl.toString(),bean.playUrl.toString())
              SPUtils.getInstance(this,"download_state").put(playUrl.toString(),true)
            },{
                showToast(getString(R.string.add_mission_fail))
            })

    }

    private fun prepareVideo(){
        var uri = intent.getStringExtra("localFile")
        if (uri!=null){
            Log.e("uri",uri)
            gsy_player.setUp(uri,false,null,null)
        }else{
            gsy_player.setUp(bean.playUrl,false,null,null)
        }

        //增加封面
        imageview = ImageView(this)
        imageview.scaleType = ImageView.ScaleType.CENTER_CROP
        ImageViewAsyncTask(mHandler,this,imageview).execute(bean.feed)
        gsy_player.titleTextView.visibility = View.GONE
        gsy_player.backButton.visibility = View.VISIBLE
        orientionUtils = OrientationUtils(this,gsy_player)
        gsy_player.setIsTouchWiget(true)
        //关闭自动旋转
        gsy_player.isRotateViewAuto = false
        gsy_player.isLockLand = false
        gsy_player.isShowFullAnimation = false
        gsy_player.isNeedLockFull = true
        gsy_player.fullscreenButton.setOnClickListener {

            //直接横屏
            orientionUtils.resolveByClick()
            //第一个true是否需要隐藏actionBar,第二个true是否需要隐藏statusBar
            gsy_player.startWindowFullscreen(mContext,true,true)
        }

        gsy_player.setStandardVideoAllCallBack(object: VideoListener(){
            override fun onPrepared(url: String?, vararg objects: Any?) {
                super.onPrepared(url, *objects)
                //开始播放了才能旋转和全屏
                orientionUtils.isEnable = true
                isPlay = true

            }

            override fun onAutoComplete(url: String?, vararg objects: Any?) {
                super.onAutoComplete(url, *objects)
            }

            override fun onClickStartError(url: String?, vararg objects: Any?) {
                super.onClickStartError(url, *objects)
            }

            override fun onQuitFullscreen(url: String?, vararg objects: Any?) {
                super.onQuitFullscreen(url, *objects)
                orientionUtils?.let {
                    orientionUtils.backToProtVideo() }
            }
        })

        gsy_player.setLockClickListener { view, lock ->
            //配合下方的onConfigurationChanged
            orientionUtils.isEnable = !lock
        }

        gsy_player.backButton.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }


    override fun onBackPressed() {
        orientionUtils?.let {
            orientionUtils.backToProtVideo()
        }
        if (StandardGSYVideoPlayer.backFromWindowFull(this)){
            return
        }
        super.onBackPressed()
    }


    override fun onPause() {
        super.onPause()
        isPause = true
    }

    override fun onResume() {
        super.onResume()
        isPause = false
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoPlayer.releaseAllVideos()
        orientionUtils?.let {
            orientionUtils.releaseListener()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (isPlay && !isPause){
            if (newConfig?.orientation==ActivityInfo.SCREEN_ORIENTATION_USER){
                if(!gsy_player.isIfCurrentIsFullscreen){
                    gsy_player.startWindowFullscreen(mContext,true,true)
                }
            }else{
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (gsy_player.isIfCurrentIsFullscreen){
                    StandardGSYVideoPlayer.backFromWindowFull(this)
                }
                orientionUtils?.let {
                    orientionUtils.isEnable = true
                }
            }
        }
    }

    private class ImageViewAsyncTask(handler: Handler,activity: VideoDetailActivity,private val mImageView: ImageView):AsyncTask<String,Void,String>(){
        private var handler = handler;
        private var mPath:String? = null
        private var mIs:FileInputStream?=null
        private var mActivity:VideoDetailActivity = activity

        override fun doInBackground(vararg params: String?): String? {
            val future = Glide.with(mActivity)
                    .load(params[0])
                    .downloadOnly(100,100)

            try {
                val cacheFile = future.get()
                mPath = cacheFile.absolutePath
            } catch(e: Exception) {
                e.printStackTrace()
            }

            return mPath

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                mIs = FileInputStream(result)
            } catch(e: Exception) {
                e.printStackTrace()
            }
            val bitmap = BitmapFactory.decodeStream(mIs)
            mImageView.setImageBitmap(bitmap)
            var message = handler.obtainMessage()
            message.what = MSG_IMAGE_LOADED
            handler.sendMessage(message)

        }
    }

}