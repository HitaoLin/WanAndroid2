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

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.model.CollectDatasBean;
import com.yhcxxt.wanandroid.model.TodoListBean;
import com.yhcxxt.wanandroid.utils.ClickUtil;

import java.util.List;

/**
 * <pre>
 *      author:LHT
 *      time:20190718
 *      desc:Todo列表数据 Adapter
 * </pre>
 */
public class RvTodoListAdapter extends RecyclerView.Adapter<RvTodoListAdapter.ViewHolder> {

    private List<TodoListBean> todoListBeanList;
    int pos;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private Context mContext;

    private View mHeaderView;

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public RvTodoListAdapter(List<TodoListBean> todoListBeanList) {
        this.todoListBeanList = todoListBeanList;
    }

    public RvTodoListAdapter(List<TodoListBean> todoListBeanList, Context mContext) {
        this.todoListBeanList = todoListBeanList;
        this.mContext = mContext;
    }

    //第一步：自定义一个回调接口来实现Click和LongClick事件
    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onItemLongClick(View v);
    }

    public RvTodoListAdapter.OnItemClickListener mOnItemClickListener;//第二步：声明自定义的接口

    //第三步：定义方法并暴露给外面的调用者
    public void setOnItemClickListener(RvTodoListAdapter.OnItemClickListener listener) {
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_record, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == TYPE_HEADER) return;

        pos = getRealPosition(viewHolder);
        TodoListBean todoListBean = todoListBeanList.get(pos);
        viewHolder.tv_title.setText(todoListBean.getTitle());
        viewHolder.tv_content.setText(todoListBean.getContent());
        viewHolder.tv_time.setText(todoListBean.getDateStr());
        if (todoListBeanList.size() > 0) {

            int y = pos % 5 + 1;
            if (y == 1) {
                viewHolder.ll_bg.setBackgroundColor(mContext.getResources().getColor(R.color.bg1));
            } else if (y == 2) {
                viewHolder.ll_bg.setBackgroundColor(mContext.getResources().getColor(R.color.bg2));
            } else if (y == 3) {
                viewHolder.ll_bg.setBackgroundColor(mContext.getResources().getColor(R.color.bg3));
            } else if (y == 4) {
                viewHolder.ll_bg.setBackgroundColor(mContext.getResources().getColor(R.color.bg4));
            } else {
                viewHolder.ll_bg.setBackgroundColor(mContext.getResources().getColor(R.color.bg5));
            }

        }
    }

    public List<TodoListBean> getDatas() {
        return todoListBeanList;
    }


    public void clearData() {
        todoListBeanList.clear();
        notifyItemRangeRemoved(0, todoListBeanList.size());

    }

    public void addData(List<TodoListBean> datas) {
        addData(0, datas);

    }

    public void addData(int position, List<TodoListBean> datas) {
        if (datas != null && datas.size() > 0) {
            todoListBeanList.addAll(datas);
            notifyItemRangeChanged(0, todoListBeanList.size());
        }
    }


    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    //返回正确的item个数
    @Override
    public int getItemCount() {
        return mHeaderView == null ? todoListBeanList.size() : todoListBeanList.size() + 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout ll_bg;
        private TextView iv_record;
        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_bg = itemView.findViewById(R.id.ll_bg);
            iv_record = itemView.findViewById(R.id.iv);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_time = itemView.findViewById(R.id.tv_time);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {

//                if (getAdapterPosition() == 0){
//
//                }else {
                if (ClickUtil.isFastClick() == false)
                    mOnItemClickListener.onItemClick(v, getAdapterPosition());
//                }

            }
        }


    }
}
