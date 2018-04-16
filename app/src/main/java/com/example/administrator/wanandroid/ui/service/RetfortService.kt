package com.example.administrator.wanandroid.ui.service

import com.example.administrator.wanandroid.bean.*
import com.example.administrator.wanandroid.url.Constants
import retrofit2.http.*
import rx.Observable

/**
 * Created by： wls
 * Created in： 2018/3/16
 * Describption：
 */
interface RetfortService {
    /** 首页 */
    @GET(Constants.URL.BANNER)
    fun getData(): Observable<HomeBannerBean>

    @GET("article/list/{page}/json")
    fun getArtical(@Path("page") page:Int): Observable<HomeArticalBean>

    /** 搜索页 */
    @GET(Constants.URL.SEARCH)
    fun getKeyboard():Observable<SearchBean>

    @FormUrlEncoded
    @POST("article/query/{page}/json")
    fun getSearchdata(@Path("page") page: Int,@Field("k") key:String):Observable<SearchDataBean>

    /** 分类页面 */
    @GET(Constants.URL.CLASSIFYTAB)
    fun getClassifyTab():Observable<ClassifyTabBean>

    @GET("article/list/{page}/json")
    fun getSelectTabArtical(@Path("page") page:Int,@Query("cid") cid:Int):Observable<HomeArticalBean>

    /** 登录，注册 */
    @FormUrlEncoded
    @POST(Constants.URL.LOGIN)
    fun getLogin(@Field("username") username:String ,@Field("password") password:String ):Observable<LoginBean>

    @FormUrlEncoded
    @POST(Constants.URL.REGISTER)
    fun getRegister(@Field("username") username:String, @Field("password") password:String,
                    @Field("repassword") repassword:String):Observable<LoginBean>

    /** 收藏文章列表 */
    @GET("lg/collect/list/{page}/json")
    fun getCollectList(@Path("page") page:Int):Observable<CollectBean>

    /** 收藏 */
    @POST("lg/collect/{id}/json")
    fun getArticals( @Path("id") id:Int):Observable<BaseBean>

    /** 取消收藏（主界面触发） */
    @POST("lg/uncollect_originId/{id}/json")
    fun unCollect_Artical(@Path("id") id:Int):Observable<BaseBean>

    /** 取消收藏（我的收藏界面触发） */
    @FormUrlEncoded
    @POST("lg/uncollect/{id}/json")
    fun unCollect_collect(@Path("id") id:Int,@Field("originId") originId:Int):Observable<BaseBean>

}