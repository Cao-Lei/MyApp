package com.bwie.caolei.myapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.caolei.myapp.R;
import com.bwie.caolei.myapp.api.ZhiHuApi;
import com.bwie.caolei.myapp.ui.adapter.FragmentTwoRecyclerAdapter;
import com.bwie.caolei.myapp.ui.model.FragmentTwoRecyclerBean;
import com.bwie.caolei.myapp.ui.view.XQAcitivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * autour: 曹磊
 * date: 2017/1/5 8:32
 * update: 2017/1/5
 */

public class FragmentTwo extends Fragment {

    private View mView;
    private RecyclerView mFragmenttwo_recycler;
    private List<FragmentTwoRecyclerBean.DataBean> mTwoRecyclerBeanList = new ArrayList<>();
    private String columnUrl = "http://news-at.zhihu.com/api/4/sections";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_two, null);

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        intiView();
    }

    private void initData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ZhiHuApi api = retrofit.create(ZhiHuApi.class);

        api.getSection()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FragmentTwoRecyclerBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FragmentTwoRecyclerBean fragmentTwoRecyclerBean) {

                        mTwoRecyclerBeanList.addAll(fragmentTwoRecyclerBean.getData());
                        //RecyclerView的适配器
                        FragmentTwoRecyclerAdapter adapter = new FragmentTwoRecyclerAdapter(mTwoRecyclerBeanList, getActivity());
                        mFragmenttwo_recycler.setAdapter(adapter);
                        adapter.setOnItemClickListener(new FragmentTwoRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                startActivity(new Intent(getActivity(), XQAcitivity.class));
                                Toast.makeText(getActivity(), "被点击了!!!!!!!", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                });
/*
        OkHttp.getAsync(columnUrl, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                FragmentTwoRecyclerBean fragmentTwoRecyclerBean = gson.fromJson(result, FragmentTwoRecyclerBean.class);
                mData = fragmentTwoRecyclerBean.getData();


            }
        });*/
    }

    private void intiView() {
        mFragmenttwo_recycler = (RecyclerView) mView.findViewById(R.id.fragmenttwo_recycler);
//        设置ReecyclerView的布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        mFragmenttwo_recycler.setLayoutManager(layoutManager);
        mFragmenttwo_recycler.setHasFixedSize(true);
        mFragmenttwo_recycler.setNestedScrollingEnabled(false);


    }
}
