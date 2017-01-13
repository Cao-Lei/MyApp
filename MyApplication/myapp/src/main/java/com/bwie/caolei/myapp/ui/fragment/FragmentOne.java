package com.bwie.caolei.myapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.CBPageAdapter;
import com.bigkoo.convenientbanner.CBViewHolderCreator;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bwie.caolei.myapp.R;
import com.bwie.caolei.myapp.api.ZhiHuApi;
import com.bwie.caolei.myapp.ui.adapter.FragmentOneRecyclerAdapter;
import com.bwie.caolei.myapp.ui.model.FragmentOneRecyclerBean;

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

public class FragmentOne extends Fragment implements OnItemClickListener {
    public static final String TAG = "FragmentOne";
    private String url = "http://news-at.zhihu.com/api/4/news/latest";
    private View mView;
    private ViewPager mFragment_vp;
    private RecyclerView mFragment_recycler;
    private ConvenientBanner mBanner;
    private FragmentOneRecyclerAdapter mAdapter;
    private List<FragmentOneRecyclerBean.StoriesBean> mOneRecyclerBeanList = new ArrayList<>();
    private List<FragmentOneRecyclerBean.TopStoriesBean> mTop_stories = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_one, null);

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //初始化控件
        initView();
        //准备数据源
        initData();

        super.onActivityCreated(savedInstanceState);
    }

    private void initView() {
        //轮播图
        mBanner = (ConvenientBanner) mView.findViewById(R.id.banner);
        //RecyclerView
        mFragment_recycler = (RecyclerView) mView.findViewById(R.id.fragmentone_recycler);
//        设置ReecyclerView的布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        mFragment_recycler.setLayoutManager(layoutManager);
        mFragment_recycler.setHasFixedSize(true);
        mFragment_recycler.setNestedScrollingEnabled(false);
    }

    private void initData() {

        Retrofit mRetrofit = new Retrofit.Builder().baseUrl("http://news-at.zhihu.com/api/4/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ZhiHuApi api = mRetrofit.create(ZhiHuApi.class);

        api.getDayPaper()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FragmentOneRecyclerBean>() {
                    @Override
                    public void onCompleted() {
                        initImage();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(FragmentOneRecyclerBean fragmentOneRecyclerBean) {
                        mTop_stories.addAll(fragmentOneRecyclerBean.getTop_stories());
                        mOneRecyclerBeanList.addAll(fragmentOneRecyclerBean.getStories());

                        mAdapter =  new FragmentOneRecyclerAdapter(mOneRecyclerBeanList, getActivity());
                        mFragment_recycler.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new FragmentOneRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Toast.makeText(getActivity(), "被点击了................", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });




        /*OkHttp.getAsync(url, new OkHttp.DataCallBack() {

            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {

                Gson gson = new Gson();
                FragmentOneRecyclerBean fragmentOneRecyclerBean = gson.fromJson(result, FragmentOneRecyclerBean.class);
                System.out.println("aaaaaaaaaaa2=" + fragmentOneRecyclerBean.getTop_stories().toString());
                mTop_stories.addAll(fragmentOneRecyclerBean.getTop_stories());
                mStories = fragmentOneRecyclerBean.getStories();

                mBanner.setPages(new CBViewHolderCreator() {
                    @Override
                    public Object createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, mTop_stories).setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer)//设置动画效果
                        .startTurning(3000)//设置播放间隔时间
                        .setPointViewVisible(true)//设置指示器是否可见
                        //设置翻页指示器的小圆点
                        .setPageIndicator(new int[]{R.drawable.shar, R.drawable.shar2})
                        //设置指示器的位置    左  中  右
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
//                        .setOnItemClickListener(this);


            }
        });*/
  /*      //给轮播图添加数据
        imgList.add(R.mipmap.duo1);
        imgList.add(R.mipmap.duo5);
        imgList.add(R.mipmap.duo8);
        imgList.add(R.mipmap.duo9);
        imgList.add(R.mipmap.duo10);
        imgList.add(R.mipmap.duo12);
*/

    }

    private void initImage() {
        mBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new NetworkImageHolderView();
            }
        }, mTop_stories).setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer)//设置动画效果
                .startTurning(3000)//设置播放间隔时间
                .setPointViewVisible(true)//设置指示器是否可见
                //设置翻页指示器的小圆点
                .setPageIndicator(new int[]{R.drawable.shar, R.drawable.shar2})
                //设置指示器的位置    左  中  右
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                Toast.makeText(getActivity(), "您点击了第1张图片", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getActivity(), "您点击了第2张图片", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getActivity(), "您点击了第3张图片", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getActivity(), "您点击了第4张图片", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getActivity(), "您点击了第5张图片", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(getActivity(), "您点击了第6张图片", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }


    public class NetworkImageHolderView implements CBPageAdapter.Holder<FragmentOneRecyclerBean.TopStoriesBean> {
        private ImageView mBanner_img;
        private TextView mBanner_tv;

        @Override
        public View createView(Context context) {
            View view = View.inflate(context, R.layout.banner_item, null);
            mBanner_img = (ImageView) view.findViewById(R.id.banner_img);
            mBanner_tv = (TextView) view.findViewById(R.id.banner_tv);
            mBanner_img.setScaleType(ImageView.ScaleType.FIT_XY);
            return view;
        }
        @Override
        public void UpdateUI(Context context, int position, FragmentOneRecyclerBean.TopStoriesBean data) {
            Glide.with(context)
                    .load(data.getImage())
                    .into(mBanner_img);
            mBanner_tv.setText(data.getTitle());
        }
    }


    @Override
    public void onPause() {
        mTop_stories.clear();
        super.onPause();
    }
}
