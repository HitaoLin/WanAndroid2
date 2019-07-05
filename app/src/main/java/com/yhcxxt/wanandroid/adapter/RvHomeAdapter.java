package com.yhcxxt.wanandroid.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.model.ArticleDatasBean;
import com.yhcxxt.wanandroid.utils.ClickUtil;

import java.util.List;
/**
 * <pre>
 *      author:LHT
 *      time:2019/4/30
 *      desc:首页文章 Adapter
 * </pre>
 */
public class RvHomeAdapter extends RecyclerView.Adapter<RvHomeAdapter.ViewHolder> {

    private List<ArticleDatasBean> articleDatasBeanList;
    int pos;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private View mHeaderView;

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public RvHomeAdapter(List<ArticleDatasBean> articleDatasBeanList) {
        this.articleDatasBeanList = articleDatasBeanList;
    }


    //第一步：自定义一个回调接口来实现Click和LongClick事件
    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onItemLongClick(View v);
    }

    public RvHomeAdapter.OnItemClickListener mOnItemClickListener;//第二步：声明自定义的接口

    //第三步：定义方法并暴露给外面的调用者
    public void setOnItemClickListener(RvHomeAdapter.OnItemClickListener listener) {
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

    //在此根据ItemViewType来决定返回何种ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mHeaderView != null && i == TYPE_HEADER)
            return new ViewHolder(mHeaderView);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == TYPE_HEADER) return;

        pos = getRealPosition(viewHolder);
        ArticleDatasBean articleDatasBean = articleDatasBeanList.get(pos);
        viewHolder.itemHomeAuthor.setText(articleDatasBean.getAuthor());
        viewHolder.itemHomeDate.setText(articleDatasBean.getNiceDate());
        viewHolder.itemHomeTitle.setText(articleDatasBean.getTitle());
        viewHolder.itemHomeType.setText(articleDatasBean.getSuperChapterName()+"/"+articleDatasBean.getChapterName());
        if (articleDatasBean.getCollect().equals("true")){
            viewHolder.itemHomeLove.setBackgroundResource(R.mipmap.ic_love);
        }else {
            viewHolder.itemHomeLove.setBackgroundResource(R.mipmap.ic_no_love);
        }

    }

    public List<ArticleDatasBean> getDatas() {
        return articleDatasBeanList;
    }


    public void clearData() {
        articleDatasBeanList.clear();
        notifyItemRangeRemoved(0, articleDatasBeanList.size());

    }

    public void addData(List<ArticleDatasBean> datas) {
        addData(0, datas);

    }

    public void addData(int position, List<ArticleDatasBean> datas) {
        if (datas != null && datas.size() > 0) {
            articleDatasBeanList.addAll(datas);
            notifyItemRangeChanged(0, articleDatasBeanList.size());
        }
    }


    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    //返回正确的item个数
    @Override
    public int getItemCount() {
        return mHeaderView == null ? articleDatasBeanList.size() : articleDatasBeanList.size() + 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView itemHomeAuthor;
        private TextView itemHomeDate;
        private TextView itemHomeTitle;
        private TextView itemHomeType;
        private ImageView itemHomeLove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemHomeAuthor = itemView.findViewById(R.id.item_home_author);
            itemHomeDate = itemView.findViewById(R.id.item_home_date);
            itemHomeTitle = itemView.findViewById(R.id.item_home_title);
            itemHomeType = itemView.findViewById(R.id.item_home_type);
            itemHomeLove = itemView.findViewById(R.id.item_home_love);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {

                if (getAdapterPosition() == 0){

                }else {
                    if (ClickUtil.isFastClick() == false)
                    mOnItemClickListener.onItemClick(v, getAdapterPosition());
                }

            }
        }

    }
}
