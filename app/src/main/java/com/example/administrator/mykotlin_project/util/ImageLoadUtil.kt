package com.example.administrator.mykotlin_project.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.administrator.mykotlin_project.R

/**
 * Created by Administrator on 2017/7/29 0029.
 */
class ImageLoadUtil {
    companion object{
        fun display(context: Context,imageView: ImageView?,url:String){
            if (imageView==null){
                throw IllegalAccessException("arguement error")
            }
          Glide.with(context).load(url)
                  .diskCacheStrategy(DiskCacheStrategy.ALL)
                  .centerCrop()
                  .placeholder(R.drawable.ic_image_loading)
                  .error(R.drawable.ic_image_loading)
                  .crossFade().into(imageView)

        }

        fun displayHigh(context: Context,imageView: ImageView?,url:String){
            if (imageView==null){
                throw IllegalAccessException("arguement error")
            }
            Glide.with(context).load(url)
                    .asBitmap()
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_loading)
                    .error(R.drawable.ic_empty_picture)
                    .into(imageView)

        }
    }
}