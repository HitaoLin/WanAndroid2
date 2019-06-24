package com.yhcxxt.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.adapter.LinerItemViewHolder;
import com.yhcxxt.wanandroid.model.ProjectClassificationDatas;
import com.yhcxxt.wanandroid.model.ProjectListDatasBean;
import com.yhcxxt.wanandroid.utils.ClickUtil;

import java.util.List;

/**
 * <pre>
 *     author:LHT
 *     date:20190624
 *     desc:LinerRecycleAdapter2
 * </pre>
 */
public class LinerRecycleAdapter2 extends RecyclerView.Adapter<LinerRecycleAdapter2.ViewHolder> {
    Context mContext;
    List<ProjectListDatasBean> mList;
    int recycleHeight;

    int pos;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private View mHeaderView;

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public LinerRecycleAdapter2(List<ProjectListDatasBean> mList) {
        this.mList = mList;
    }


    //第一步：自定义一个回调接口来实现Click和LongClick事件
    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onItemLongClick(View v);
    }

    public LinerRecycleAdapter2.OnItemClickListener mOnItemClickListener;//第二步：声明自定义的接口

    //第三步：定义方法并暴露给外面的调用者
    public void setOnItemClickListener(LinerRecycleAdapter2.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    //根据pos返回不同的ItemViewType
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }


    public LinerRecycleAdapter2(Context context, List<ProjectListDatasBean> list, int recycleHeight) {
        this.mContext = context;
        this.mList = list;
        this.recycleHeight = recycleHeight;
    }

    public LinerRecycleAdapter2(Context mContext) {
        this.mContext = mContext;
    }

    public LinerRecycleAdapter2(Context mContext, List<ProjectListDatasBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
////        return new LinerItemViewHolder(mContext,viewGroup);

//
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mHeaderView != null && i == TYPE_HEADER)
            return new LinerRecycleAdapter2.ViewHolder(mHeaderView);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_classify_details, viewGroup, false);
        return new LinerRecycleAdapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == TYPE_HEADER) return;

        pos = getRealPosition(viewHolder);

//        LinerItemViewHolder vh = (LinerItemViewHolder) viewHolder;
//
//        ViewGroup.LayoutParams layoutParams = vh.llMain.getLayoutParams();

        viewHolder.ivAvatar.setImageURI(mList.get(i).getEnvelopePic());
        viewHolder.tvCity.setText(mList.get(i).getTitle());
        viewHolder.tvContent.setText(mList.get(i).getDesc());
        viewHolder.tv_author.setText(mList.get(i).getAuthor()+"     "+mList.get(i).getNiceDate());
        viewHolder.item_home_love.setBackgroundResource(R.drawable.selector_home_love);


    }

    public List<ProjectListDatasBean> getDatas() {
        return mList;
    }


    public void clearData() {
        mList.clear();
        notifyItemRangeRemoved(0, mList.size());

    }

    public void addData(List<ProjectListDatasBean> datas) {
        addData(0, datas);

    }

    public void addData(int position, List<ProjectListDatasBean> datas) {
        if (datas != null && datas.size() > 0) {
            mList.addAll(datas);
            notifyItemRangeChanged(0, mList.size());
        }
    }


    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    //返回正确的item个数
    @Override
    public int getItemCount() {
        return mHeaderView == null ? mList.size() : mList.size() + 1;
    }


//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//        LinerItemViewHolder vh = (LinerItemViewHolder) viewHolder;
//
//        ViewGroup.LayoutParams layoutParams = vh.llMain.getLayoutParams();
//
//        vh.ivAvatar.setImageURI(mList.get(i).getEnvelopePic());
//        vh.tvCity.setText(mList.get(i).getTitle());
//        vh.tvContent.setText(mList.get(i).getDesc());
//        vh.tv_author.setText(mList.get(i).getAuthor()+"     "+mList.get(i).getNiceDate());
//        vh.item_home_love.setBackgroundResource(R.drawable.selector_home_love);
//
//
////        GridLayoutManager glm = new GridLayoutManager(mContext,3);
////        vh.recyclerView.setLayoutManager(glm);
////        GridRecycleAdapter adapter = new GridRecycleAdapter(mContext,mList);
////        vh.recyclerView.setAdapter(adapter);
//
//    }


//    @Override
//    public int getItemCount() {
//        return mList.size();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public SimpleDraweeView ivAvatar;//图片
        public TextView tvCity;//分类名称
        public TextView tvContent;//描述
        public TextView tv_author;//作者  + 时间
        public ImageView item_home_love;//收藏
        public LinearLayout llMain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvContent = itemView.findViewById(R.id.tvContent);
            tv_author = itemView.findViewById(R.id.tv_author);
            item_home_love = itemView.findViewById(R.id.item_home_love);
            llMain = itemView.findViewById(R.id.ll_main);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {

                if (ClickUtil.isFastClick() == false){
                    mOnItemClickListener.onItemClick(v, getAdapterPosition());

                }
            }
        }
    }
}
