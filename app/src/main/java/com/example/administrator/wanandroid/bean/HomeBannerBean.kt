package com.example.administrator.wanandroid.bean

/**
 * Created by： wls
 * Created in： 2018/3/15
 * Describption：
 */

data class HomeBannerBean(
		val data: List<Data>,
		val errorCode: Int,
		val errorMsg: String
)

data class Data(
		val desc: String,
		val id: Int,
		val imagePath: String,
		val isVisible: Int,
		val order: Int,
		val title: String,
		val type: Int,
		val url: String
)