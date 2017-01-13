package com.bwie.caolei.myapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.caolei.myapp.R;
import com.bwie.caolei.myapp.api.ZhiHuApi;
import com.bwie.caolei.myapp.ui.adapter.FragmentThreeRecyclerAdapter;
import com.bwie.caolei.myapp.ui.model.FragmentThreeRecyclerBean;

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

public class FragmentThree extends Fragment {

    private View mView;
    private RecyclerView mFragmentThree_recycler;

    private List<FragmentThreeRecyclerBean.NewslistBean> mThreeRecyclerBeanList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_three, null);


        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        initView();

        initData();
        super.onActivityCreated(savedInstanceState);
    }

    private void initData() {


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.tianapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ZhiHuApi api = retrofit.create(ZhiHuApi.class);

        api.getWechat("78926886d340296c5125405447aed227", 20, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FragmentThreeRecyclerBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FragmentThreeRecyclerBean fragmentThreeRecyclerBean) {
                        mThreeRecyclerBeanList.addAll(fragmentThreeRecyclerBean.getNewslist());

                        FragmentThreeRecyclerAdapter adapter = new FragmentThreeRecyclerAdapter(mThreeRecyclerBeanList, getActivity());
                        mFragmentThree_recycler.setAdapter(adapter);

                        adapter.setOnItemClickListener(new FragmentThreeRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Toast.makeText(getActivity(), "哈哈哈哈哈哈哈", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

/*
        //判断RecyclerView的长度为0的时候添加数据
        if (mThreeRecyclerBeanList.size() == 0) {
            //添加数据
            for (int i = 0; i < 10; i++) {
                mThreeRecyclerBeanList.add(new FragmentThreeRecyclerBean(R.mipmap.duo1, "标题", "内容", "时间"));
            }
        }*/
    }

    private void initView() {

        mFragmentThree_recycler = (RecyclerView) mView.findViewById(R.id.fragmentThree_recycler);
//        设置ReecyclerView的布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        mFragmentThree_recycler.setLayoutManager(layoutManager);
        mFragmentThree_recycler.setHasFixedSize(true);
        mFragmentThree_recycler.setNestedScrollingEnabled(false);


        //RecyclerView的适配器
        FragmentThreeRecyclerAdapter adapter = new FragmentThreeRecyclerAdapter(mThreeRecyclerBeanList, getActivity());
        mFragmentThree_recycler.setAdapter(adapter);


    }
}
