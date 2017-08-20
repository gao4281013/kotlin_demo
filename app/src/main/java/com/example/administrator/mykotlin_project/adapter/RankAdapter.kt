package com.example.administrator.mykotlin_project.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.administrator.mykotlin_project.R
import com.example.administrator.mykotlin_project.mvp.model.bean.HotBean
import com.example.administrator.mykotlin_project.ui.VideoDetailActivity
import com.example.administrator.mykotlin_project.utils.ImageLoadUtil
import com.example.administrator.mykotlin_project.utils.ObjectSaveUtil
import com.example.administrator.mykotlin_project.utils.SPUtils
import com.tt.lvruheng.eyepetizer.mvp.model.bean.VideoBean

/**
 * Created by Administrator on 2017/8/2.
 */
class RankAdapter(context: Context,list:ArrayList<HotBean.ItemList.Data>):RecyclerView.Adapter<RankAdapter.RankViewHolder>() {
    var context:Context?=null
    var list:ArrayList<HotBean.ItemList.Data>?=null
    var inflater:LayoutInflater?=null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RankViewHolder {
       return RankViewHolder(inflater?.inflate(R.layout.item_rank,parent,false),context!!)
    }

    override fun onBindViewHolder(holder: RankViewHolder?, position: Int) {
        var photoUrl:String?=list?.get(position)?.cover?.feed
        photoUrl?.let {
            ImageLoadUtil.display(context!!,holder?.iv_photo,it)
        }
        var title:String?=list?.get(position)?.title
        holder?.tv_title?.text = title
        var category = list?.get(position)?.category
        var duration = list?.get(position)?.duration
        var minute = duration?.div(60)
        var second = duration?.minus((minute?.times(60)) as Long)
        var realMinute:String
        var realSecond:String
        if (minute!!<10){
            realMinute="0"+minute
        }else{
            realMinute = minute.toString()
        }

        if (second!!<10){
            realSecond = "0" +second
        }else{
            realSecond = second.toString()
        }

        holder?.tv_time?.text="$category/$realMinute'$realSecond''"
        holder?.itemView?.setOnClickListener {
            var intent:Intent = Intent(context, VideoDetailActivity::class.java)
            var desc = list?.get(position)?.description
            var playUrl = list?.get(position)?.playUrl
            var blurred = list?.get(position)?.cover?.blurred
            var collect = list?.get(position)?.consumption?.collectionCount
            var share = list?.get(position)?.consumption?.shareCount
            var reply = list?.get(position)?.consumption?.replyCount
            var time = System.currentTimeMillis()
            var videoBean = VideoBean(photoUrl,title,desc,duration,playUrl,category,blurred,collect,share,reply,time)
            var url = SPUtils.getInstance(context!!,"beans").getString(playUrl!!)
            if (url.equals("")){
                var count = SPUtils.getInstance(context!!,"beans").getInt("count")
                if (count!=-1){
                    count = count.inc()
                }else{
                    count =1
                }
                SPUtils.getInstance(context!!,"beans").put("count",count)
                SPUtils.getInstance(context!!,"beans").put(playUrl!!,playUrl)
                ObjectSaveUtil.saveObject(context!!,"bean$count",videoBean)
            }
            intent.putExtra("data",videoBean as Parcelable)
            context?.let { context -> context.startActivity(intent) }
        }
    }

    override fun getItemCount(): Int {
        return list?.size?:0
    }

    class RankViewHolder(itemView:View?,context: Context):RecyclerView.ViewHolder(itemView) {
        var iv_photo:ImageView = itemView?.findViewById(R.id.iv_photo) as ImageView
        var tv_title:TextView = itemView?.findViewById(R.id.tv_title) as TextView
        var tv_time:TextView = itemView?.findViewById(R.id.tv_time) as TextView
        init {
            tv_title?.typeface = Typeface.createFromAsset(context?.assets,"fonts/FZLanTingHeiS-L-GB-Regular.TTF")
        }
    }
}