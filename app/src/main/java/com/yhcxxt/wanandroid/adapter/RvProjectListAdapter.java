package com.yhcxxt.wanandroid.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.model.ProjectClassificationDatas;
import com.yhcxxt.wanandroid.utils.ClickUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      author:LHT
 *      time:20190522
 *      desc:项目分类 Adapter
 * </pre>
 */
public class RvProjectListAdapter extends RecyclerView.Adapter<RvProjectListAdapter.ViewHolder> {

    private List<ProjectClassificationDatas> projectClassificationDatasList;
//    private List<SystemTreeDatasBean> systemTreeDatasBeanList;
    ArrayList<String> mVals = new ArrayList<>();
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> mValsList = new ArrayList<>();
    ArrayList<String> idList = new ArrayList<>();

    int pos;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private View mHeaderView;

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public RvProjectListAdapter(List<ProjectClassificationDatas> projectClassificationDatasList) {
        this.projectClassificationDatasList = projectClassificationDatasList;
    }


    //第一步：自定义一个回调接口来实现Click和LongClick事件
    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onItemLongClick(View v);
    }

    public RvProjectListAdapter.OnItemClickListener mOnItemClickListener;//第二步：声明自定义的接口

    //第三步：定义方法并暴露给外面的调用者
    public void setOnItemClickListener(RvProjectListAdapter.OnItemClickListener listener) {
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_classify_detail, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == TYPE_HEADER) return;

        pos = getRealPosition(viewHolder);
//        SystemTreeDatas systemTreeDatas = projectClassificationDatasList.get(pos);
//        viewHolder.ivAvatar.setBackgroundResource(projectClassificationDatasList.get(pos));

        mVals.clear();
        id.clear();
        for (int y = 0; y < projectClassificationDatasList.size(); y++) {

            mVals.add(projectClassificationDatasList.get(y).getName());
            id.add(projectClassificationDatasList.get(y).getId());

            mValsList.addAll(mVals);
            idList.addAll(id);
//            Log.e("mvals", systemTreeDatasBeanList.get(y).getName() + "----" + systemTreeDatasBeanList.get(y).getId());

        }


    }

    public List<ProjectClassificationDatas> getDatas() {
        return projectClassificationDatasList;
    }


    public void clearData() {
        projectClassificationDatasList.clear();
        notifyItemRangeRemoved(0, projectClassificationDatasList.size());

    }

    public void addData(List<ProjectClassificationDatas> datas) {
        addData(0, datas);

    }

    public void addData(int position, List<ProjectClassificationDatas> datas) {
        if (datas != null && datas.size() > 0) {
            projectClassificationDatasList.addAll(datas);
            notifyItemRangeChanged(0, projectClassificationDatasList.size());
        }
    }


    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    //返回正确的item个数
    @Override
    public int getItemCount() {
        return mHeaderView == null ? projectClassificationDatasList.size() : projectClassificationDatasList.size() + 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivAvatar;//图片
        private TextView tvCity;//分类名称
        private TextView tvContent;//描述
        private TextView tv_author;//作者  +  时间
        private ImageView item_home_love; //收藏

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvContent = itemView.findViewById(R.id.tvContent);
            tv_author = itemView.findViewById(R.id.tv_author);
            item_home_love = itemView.findViewById(R.id.item_home_love);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {

                if (ClickUtil.isFastClick() == false)
                    mOnItemClickListener.onItemClick(v, getAdapterPosition());

            }
        }


    }
}
