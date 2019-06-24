package com.yhcxxt.wanandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yhcxxt.wanandroid.R;

/**
 * Create by hsw
 * on 2018/12/3.
 */
public class GirdItemViewHolder extends RecyclerView.ViewHolder {
    public SimpleDraweeView ivAvatar;//图片
    public TextView tvCity;//分类名称
//    public TextView tvContent;//描述
    public TextView tv_author;//作者  + 时间
//    public ImageView item_home_love;//收藏

    public GirdItemViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.item_classify_detail, parent, false));
        ivAvatar = itemView.findViewById(R.id.ivAvatar);
        tvCity = itemView.findViewById(R.id.tvCity);
//        tvContent = itemView.findViewById(R.id.tvContent);
        tv_author = itemView.findViewById(R.id.tv_author);
//        item_home_love = itemView.findViewById(R.id.item_home_love);
    }
}
