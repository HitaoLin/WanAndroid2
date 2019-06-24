package com.yhcxxt.wanandroid.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.activity.SystemArtcileListActivity;
import com.yhcxxt.wanandroid.fragments.SystemFragment;
import com.yhcxxt.wanandroid.model.ArticleDatasBean;
import com.yhcxxt.wanandroid.model.SystemArticleListModel;
import com.yhcxxt.wanandroid.model.SystemTreeDatas;
import com.yhcxxt.wanandroid.model.SystemTreeDatasBean;
import com.yhcxxt.wanandroid.model.SystemTreeModel;
import com.yhcxxt.wanandroid.utils.ClickUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 *      author:LHT
 *      time:2019/5/7
 *      desc:知识体系 Adapter
 * </pre>
 */
public class RvSystemTreeAdapter extends RecyclerView.Adapter<RvSystemTreeAdapter.ViewHolder> {

    private List<SystemTreeDatas> systemTreeDatasList;
    private List<SystemTreeDatasBean> systemTreeDatasBeanList;
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

    public RvSystemTreeAdapter(List<SystemTreeDatas> systemTreeDatasList) {
        this.systemTreeDatasList = systemTreeDatasList;
    }


    //第一步：自定义一个回调接口来实现Click和LongClick事件
    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onItemLongClick(View v);
    }

    public RvSystemTreeAdapter.OnItemClickListener mOnItemClickListener;//第二步：声明自定义的接口

    //第三步：定义方法并暴露给外面的调用者
    public void setOnItemClickListener(RvSystemTreeAdapter.OnItemClickListener listener) {
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_system_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == TYPE_HEADER) return;

        pos = getRealPosition(viewHolder);
        SystemTreeDatas systemTreeDatas = systemTreeDatasList.get(pos);
        viewHolder.tv_sys_name.setText(systemTreeDatas.getName());

        systemTreeDatasBeanList = systemTreeDatas.getChildren();

        mVals.clear();
        id.clear();
        for (int y = 0; y < systemTreeDatasBeanList.size(); y++) {

            mVals.add(systemTreeDatasBeanList.get(y).getName());
            id.add(systemTreeDatasBeanList.get(y).getId());

            mValsList.addAll(mVals);
            idList.addAll(id);
//            Log.e("mvals", systemTreeDatasBeanList.get(y).getName() + "----" + systemTreeDatasBeanList.get(y).getId());

        }


        viewHolder.tfl.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                //字体颜色随机
                Random random = new Random();
                int r = random.nextInt(150);
                int g = random.nextInt(150);
                int b = random.nextInt(150);
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.tfl, parent, false);
                tv.setTextColor(Color.rgb(r, g, b));
                tv.setText(s);

                return tv;

            }

        });

        viewHolder.tfl.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                Intent intent = new Intent(parent.getContext(), SystemArtcileListActivity.class);
                intent.putExtra("id", systemTreeDatas.getChildren().get(position).getId());
                intent.putExtra("name", systemTreeDatas.getChildren().get(position).getName());
                parent.getContext().startActivity(intent);
//                Log.e("child",systemTreeDatas.getChildren().get(position).getId());
                return false;
            }
        });

    }

    public List<SystemTreeDatas> getDatas() {
        return systemTreeDatasList;
    }


    public void clearData() {
        systemTreeDatasList.clear();
        notifyItemRangeRemoved(0, systemTreeDatasList.size());

    }

    public void addData(List<SystemTreeDatas> datas) {
        addData(0, datas);

    }

    public void addData(int position, List<SystemTreeDatas> datas) {
        if (datas != null && datas.size() > 0) {
            systemTreeDatasList.addAll(datas);
            notifyItemRangeChanged(0, systemTreeDatasList.size());
        }
    }


    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    //返回正确的item个数
    @Override
    public int getItemCount() {
        return mHeaderView == null ? systemTreeDatasList.size() : systemTreeDatasList.size() + 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_sys_name;
        private TagFlowLayout tfl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_sys_name = itemView.findViewById(R.id.tv_sys_name);
            tfl = itemView.findViewById(R.id.tfl);

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
