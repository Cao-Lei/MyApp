package com.bwie.caolei.myapp.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwie.caolei.myapp.R;

/**
 * 1.
 * 2.曹磊
 * 3.2016/11/23
 */
public class FragmentOneViewPagerAdapter extends PagerAdapter {
    private int[] imgId = {R.mipmap.img1, R.mipmap.img2, R.mipmap.img3, R.mipmap.img4, R.mipmap.img5};
    private Context context;


    public FragmentOneViewPagerAdapter( Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgId.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = View.inflate(context, R.layout.fragment_vp_item, null);
        ImageView fragment_vp_item_img = (ImageView) view.findViewById(R.id.fragment_vp_item_img);
        fragment_vp_item_img.setImageResource(imgId[position % imgId.length]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }
}
