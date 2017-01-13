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
import com.bwie.caolei.myapp.ui.model.FragmentOneRecyclerBean;

import java.util.List;

/**
 * autour: 曹磊
 * date: 2017/1/13 14:01
 * update: 2017/1/13
 */

public class FragmentOneRecyclerAdapter extends RecyclerView.Adapter<FragmentOneRecyclerAdapter.MyViewHolder> {

    private List<FragmentOneRecyclerBean.StoriesBean> mStories;
    private Context context;

    public FragmentOneRecyclerAdapter(List<FragmentOneRecyclerBean.StoriesBean> stories, Context context) {
        this.mStories = stories;
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
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.fragmentone_recycler_item, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//        holder.mFragmentOne_item_img.setImageResource(mStories.get(position).getImages().get());

        Glide.with(context)
                .load(mStories.get(position).getImages().get(0))
                .into(holder.mFragmentOne_item_img);
        holder.mfragmentOne_item_title.setText(mStories.get(position).getTitle());
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
        return mStories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mFragmentOne_item_img;
        private final TextView mfragmentOne_item_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            mFragmentOne_item_img = (ImageView) itemView.findViewById(R.id.fragmentOne_item_img);
            mfragmentOne_item_title = (TextView) itemView.findViewById(R.id.fragmentOne_item_title);

        }
    }
}
