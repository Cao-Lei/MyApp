package com.bwie.caolei.myapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.caolei.myapp.R;
import com.bwie.caolei.myapp.ui.model.FragmentThreeRecyclerBean;

import java.util.List;

/**
 * autour: 曹磊
 * date: 2017/1/10 8:47
 * update: 2017/1/10
 */


public class FragmentThreeRecyclerAdapter extends RecyclerView.Adapter<FragmentThreeRecyclerAdapter.MyViewHolder> {

    private List<FragmentThreeRecyclerBean.NewslistBean> mThreeRecyclerBeanList;
    private Context context;


    public FragmentThreeRecyclerAdapter(List<FragmentThreeRecyclerBean.NewslistBean> threeRecyclerBeanList, Context context) {
        mThreeRecyclerBeanList = threeRecyclerBeanList;
        this.context = context;
    }

    private static RecyclerView.Adapter Adapter;
    private static OnItemClickListener mOnItemClickListener;
    //设置点击和长按事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position); //设置点击事件

    }
    public static void setOnItemClickListener(OnItemClickListener listener)     {
        mOnItemClickListener = listener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.fragmentthree_recycler_item, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//        holder.mFragmentThree_item_img.setImageResource(mThreeRecyclerBeanList.get(position).getPicUrl());
        Glide.with(context)
                .load(mThreeRecyclerBeanList.get(position).getPicUrl())
                .into(holder.mFragmentThree_item_img);
        holder.mFragmentThree_item_title.setText(mThreeRecyclerBeanList.get(position).getTitle());
        holder.mFragmentThree_item_content.setText(mThreeRecyclerBeanList.get(position).getDescription());
        holder.mFragmentThree_item_time.setText(mThreeRecyclerBeanList.get(position).getCtime());



        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                //itemview是holder里的所有控件，可以单独设置比如ImageButton Button等
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView,position);

                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mThreeRecyclerBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mFragmentThree_item_img;
        private final TextView mFragmentThree_item_title;
        private final TextView mFragmentThree_item_content;
        private final TextView mFragmentThree_item_time;

        public MyViewHolder(View itemView) {
            super(itemView);

            mFragmentThree_item_img = (ImageView) itemView.findViewById(R.id.fragmentThree_item_img);
            mFragmentThree_item_title = (TextView) itemView.findViewById(R.id.fragmentThree_item_title);
            mFragmentThree_item_content = (TextView) itemView.findViewById(R.id.fragmentThree_item_content);
            mFragmentThree_item_time = (TextView) itemView.findViewById(R.id.fragmentThree_item_time);
        }
    }
}
