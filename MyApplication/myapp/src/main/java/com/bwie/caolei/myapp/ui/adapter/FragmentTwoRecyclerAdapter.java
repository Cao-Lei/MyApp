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
import com.bwie.caolei.myapp.ui.model.FragmentTwoRecyclerBean;

import java.util.List;

/**
 * autour: 曹磊
 * date: 2017/1/10 8:47
 * update: 2017/1/10
 */


public class FragmentTwoRecyclerAdapter extends RecyclerView.Adapter<FragmentTwoRecyclerAdapter.MyViewHolder> {

    private List<FragmentTwoRecyclerBean.DataBean> mTwoRecyclerBeanList;
    private Context context;

    private static RecyclerView.Adapter Adapter;
    private static OnItemClickListener mOnItemClickListener;
    //设置点击和长按事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position); //设置点击事件

    }
    public static void setOnItemClickListener(OnItemClickListener listener)     {
        mOnItemClickListener = listener;
    }
    public FragmentTwoRecyclerAdapter(List<FragmentTwoRecyclerBean.DataBean> twoRecyclerBeanList, Context context) {
        mTwoRecyclerBeanList = twoRecyclerBeanList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.fragmenttwo_recycler_item, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Glide.with(context)
                .load(mTwoRecyclerBeanList.get(position).getThumbnail())
                .into(holder.mFragmentTwo_item_img);
        holder.mFragmentTwo_item_content.setText(mTwoRecyclerBeanList.get(position).getDescription());
        holder.mFragmentTwo_item_title.setText(mTwoRecyclerBeanList.get(position).getName());


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
        return mTwoRecyclerBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final ImageView mFragmentTwo_item_img;
        private final TextView mFragmentTwo_item_content;
        private final TextView mFragmentTwo_item_title;

        public MyViewHolder(View itemView) {
            super(itemView);

            mFragmentTwo_item_img = (ImageView) itemView.findViewById(R.id.fragmentTwo_item_img);
            mFragmentTwo_item_content = (TextView) itemView.findViewById(R.id.fragmentTwo_item_content);
            mFragmentTwo_item_title = (TextView) itemView.findViewById(R.id.fragmentTwo_item_title);
        }
    }
}
