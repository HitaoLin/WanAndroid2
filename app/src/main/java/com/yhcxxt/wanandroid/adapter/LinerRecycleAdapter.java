package com.yhcxxt.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.model.ProjectListDatasBean;

import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190624
 * </pre>
 */
public class LinerRecycleAdapter extends RecyclerView.Adapter {
    Context mContext;
    List<ProjectListDatasBean> mList;
    int recycleHeight;
    public LinerRecycleAdapter(Context context, List<ProjectListDatasBean> list, int recycleHeight) {
        this.mContext = context;
        this.mList = list;
        this.recycleHeight= recycleHeight;
    }

    public LinerRecycleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public LinerRecycleAdapter(Context mContext, List<ProjectListDatasBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinerItemViewHolder(mContext,viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        LinerItemViewHolder vh = (LinerItemViewHolder) viewHolder;

        ViewGroup.LayoutParams layoutParams = vh.llMain.getLayoutParams();

        /**
         * 点击tablayout最后一项时 如果类目不足一页，
         * 调整此item为recyclerView的高度
         */
//        if (i == mList.size() - 1){
//            layoutParams.height = recycleHeight;
//        }else {
//            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        }
//        vh.llMain.setLayoutParams(layoutParams);

        /**
         * 类目的数量过少，高度不足recyclerView高度一半时会和tablayout造成bug
         * 可根据自己的实际情况进行调整
         * 这个方法有点low ，如有更好的办法，请替换
         */
//        if (mList.get(i).getItemName() != null && mList.get(i).getItemName().size() > 6){
//            vh.blockView.setVisibility(View.GONE);
//        }else {
//            vh.blockView.setVisibility(View.VISIBLE);
//        }


//        vh.tvName.setText(mList.get(i).getName());

//        vh.ivAvatar.setBackgroundResource(R.mipmap.iv_classify);
        vh.ivAvatar.setImageURI(mList.get(i).getEnvelopePic());
        vh.tvCity.setText(mList.get(i).getTitle());
        vh.tvContent.setText(mList.get(i).getDesc());
        vh.tv_author.setText(mList.get(i).getAuthor()+"     "+mList.get(i).getNiceDate());
        vh.item_home_love.setBackgroundResource(R.drawable.selector_home_love);


//        GridLayoutManager glm = new GridLayoutManager(mContext,3);
//        vh.recyclerView.setLayoutManager(glm);
//        GridRecycleAdapter adapter = new GridRecycleAdapter(mContext,mList);
//        vh.recyclerView.setAdapter(adapter);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
