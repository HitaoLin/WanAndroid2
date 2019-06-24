package com.yhcxxt.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.model.ProjectListDatasBean;

import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190624
 *     desc:GridRecycleAdapter
 * </pre>
 */
public class GridRecycleAdapter extends RecyclerView.Adapter {
    Context context;
    List<ProjectListDatasBean> list;
    public GridRecycleAdapter(Context context, List<ProjectListDatasBean> list) {
        this.context = context;
        this.list = list;
    }

    public GridRecycleAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ProjectListDatasBean> list){
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinerItemViewHolder(context,viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        LinerItemViewHolder vh = (LinerItemViewHolder) viewHolder;

        vh.ivAvatar.setImageURI(list.get(i).getEnvelopePic());
        vh.ivAvatar.setBackgroundResource(R.mipmap.iv_classify);
        vh.tvCity.setText(list.get(i).getTitle());
//        vh.tvContent.setText(list.get(i).getDesc());
        vh.tv_author.setText(list.get(i).getAuthor()+"     "+list.get(i).getNiceDate());
//        vh.item_home_love.setBackgroundResource(R.drawable.selector_home_love);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
