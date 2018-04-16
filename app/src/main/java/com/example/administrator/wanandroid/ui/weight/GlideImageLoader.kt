package com.example.administrator.wanandroid.ui.weight

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.administrator.wanandroid.R
import com.youth.banner.loader.ImageLoader

/**
 * Created by： wls
 * Created in： 2018/3/15
 * Describption：
 */
class GlideImageLoader :ImageLoader(){
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        val options = RequestOptions()
                .placeholder(R.mipmap.banner_error)//预加载占位符
                .error(R.mipmap.banner_error)//加载失败占位符
                .centerCrop()

        Glide.with(context).load(path).apply(options).into(imageView)
    }
}