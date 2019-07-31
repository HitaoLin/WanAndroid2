package com.yhcxxt.wanandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.model.TodoListBean;
import com.yhcxxt.wanandroid.model.TodoListDataBean;
import com.yhcxxt.wanandroid.utils.DensityUtil;

import java.util.List;


/**
 * Created by gavin
 * Created date 17/6/5
 * Created log
 */

public class SimpleAdapter extends RecyclerView.Adapter {
    private List<TodoListBean> todoListDataBeanList;
    private Context mContext;

    public SimpleAdapter(Context context, List<TodoListBean> todoListDataBeanList) {
        todoListDataBeanList = todoListDataBeanList;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        if (todoListDataBeanList.size() > position) {
            TodoListBean city = todoListDataBeanList.get(position);
            if (city.isExpanded()) {
                ViewGroup.LayoutParams layoutParams = holder.mLlBg.getLayoutParams();
                layoutParams.height = DensityUtil.dip2px(mContext, 100);
                holder.mLlBg.setLayoutParams(layoutParams);
                //holder.itemView.setVisibility(View.VISIBLE);
                int i = position % 5 + 1;
                if (i == 1) {
                    holder.mIvCity.setImageResource(R.mipmap.item_record);
                    holder.mLlBg.setBackgroundColor(mContext.getResources().getColor(R.color.bg1));
                } else if (i == 2) {
                    holder.mIvCity.setImageResource(R.mipmap.item_record);
                    holder.mLlBg.setBackgroundColor(mContext.getResources().getColor(R.color.bg2));
                } else if (i == 3) {
                    holder.mIvCity.setImageResource(R.mipmap.item_record);
                    holder.mLlBg.setBackgroundColor(mContext.getResources().getColor(R.color.bg3));
                } else if (i == 4) {
                    holder.mIvCity.setImageResource(R.mipmap.item_record);
                    holder.mLlBg.setBackgroundColor(mContext.getResources().getColor(R.color.bg4));
                } else {
                    holder.mIvCity.setImageResource(R.mipmap.item_record);
                    holder.mLlBg.setBackgroundColor(mContext.getResources().getColor(R.color.bg5));
                }
                holder.mTvCity.setText(city.getTitle());
            } else {
                ViewGroup.LayoutParams layoutParams = holder.mLlBg.getLayoutParams();
                layoutParams.height = DensityUtil.dip2px(mContext, 0);
                holder.mLlBg.setLayoutParams(layoutParams);
                //holder.itemView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return todoListDataBeanList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvCity;
        TextView mTvCity;
        TextView mTvBrief;
        LinearLayout mLlBg;

        ViewHolder(View view) {
            super(view);
            mIvCity = view.findViewById(R.id.iv);
            mTvCity = view.findViewById(R.id.tv_title);
            mTvBrief = view.findViewById(R.id.tv_content);
            mLlBg = view.findViewById(R.id.ll_bg);
        }
    }
}
