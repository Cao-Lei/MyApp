package com.bwie.caolei.myapp.api;

import com.bwie.caolei.myapp.ui.model.FragmentOneRecyclerBean;
import com.bwie.caolei.myapp.ui.model.FragmentThreeRecyclerBean;
import com.bwie.caolei.myapp.ui.model.FragmentTwoRecyclerBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hpw on 16/11/2.
 */

public interface ZhiHuApi {
    //private String columnUrl = "http://news-at.zhihu.com/api/4/sections";
    //private String url = "http://news-at.zhihu.com/api/4/news/latest";
    //http://api.tianapi.com/wxnew/?key=78926886d340296c5125405447aed227&amp;num=20&amp;page=1

    //日报
    @GET("news/latest")
    Observable<FragmentOneRecyclerBean> getDayPaper();

    //专栏
    @GET("sections")
    Observable<FragmentTwoRecyclerBean> getSection();

    //微信
    @GET("wxnew")
    Observable<FragmentThreeRecyclerBean> getWechat(@Query("key") String key, @Query("num") int num, @Query("page") int page);

}
