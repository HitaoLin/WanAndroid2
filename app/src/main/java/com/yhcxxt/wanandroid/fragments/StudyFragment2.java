package com.yhcxxt.wanandroid.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.activity.BannerActivity;
import com.yhcxxt.wanandroid.adapter.RvCollectAdapter;
import com.yhcxxt.wanandroid.adapter.RvTodoListAdapter;
import com.yhcxxt.wanandroid.model.TodoListBean;
import com.yhcxxt.wanandroid.model.TodoListDataBean;
import com.yhcxxt.wanandroid.model.TodoListModel;
import com.yhcxxt.wanandroid.presenter.TodoListPresenter;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.utils.ViewFindUtils;
import com.yhcxxt.wanandroid.view.TodoListView;
import com.yhcxxt.wanandroid.views.IosLoadDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     auhtor:LHT
 *     date:20190722
 *     desc:学习清单  Fragment
 * </pre>
 */
@SuppressLint("ValidFragment")
public class StudyFragment2 extends Fragment implements TodoListView {

    View v;
    @BindView(R.id.linear_no_income)
    LinearLayout linear_no_income;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private String mTitle;

    SegmentTabLayout tabLayout_5;
    private String[] mTitles_3 = {"待办", "完成"};

    RvTodoListAdapter rvTodoListAdapter;

    private String type = "0";
    List<TodoListDataBean> todoListDataBeanList = new ArrayList<>();
    List<TodoListBean> todoListBeanList = new ArrayList<>();
    List<TodoListBean> todoListBeans = new ArrayList<>();

    private IosLoadDialog dialog;

    private int page=0;

    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_MORE = 2;

    //当前状态
    private int state = STATE_NORMAL;

    TodoListPresenter todoListPresenter;


    public static StudyFragment2 getInstance(String title) {
        StudyFragment2 sf = new StudyFragment2();
//        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_life, null);

        unbinder = ButterKnife.bind(this, v);
        
        initView();

        todoListPresenter = new TodoListPresenter(this);
        todoListPresenter.loadTodoList(getContext(),type);

        return v;
    }

    private void initView() {

        tabLayout_5 = ViewFindUtils.find(v, R.id.tl_5);
        tabLayout_5.setTabData(mTitles_3);
        tabLayout_5.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Toast.makeText(getContext(), mTitles_3[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        refreshLayout.setEnableLoadMore(true);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
            }
        });

    }

    private void loadMoreData() {

        page = ++page;
        state = STATE_MORE;
        todoListPresenter.loadTodoList(getContext(),type);

    }

    private void refreshData() {

        page = 0;
        state = STATE_REFRESH;
        todoListPresenter.loadTodoList(getContext(),type);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 获取todo列表数据
     * @param model
     */
    @Override
    public void getTodoList(TodoListModel model) {
        if (model.getErrorCode().equals("0")){
            todoListDataBeanList.clear();
            todoListBeanList.clear();
            todoListBeans.clear();
            todoListDataBeanList = model.getData().getTodoList();
            for (int i = 0;i<todoListDataBeanList.size();i++){

                todoListBeans = todoListDataBeanList.get(i).getTodoList();
                for (int y = 0; y<todoListBeanList.size();y++){

                }

                todoListBeanList.addAll(todoListBeans);

            }


            if (todoListDataBeanList.size() < 0) {

                linear_no_income.setVisibility(View.VISIBLE);
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            } else
                showData();


        }else {
            Utils.showToast(this.getContext(),model.getErrorMsg());
        }
    }


    private void showData() {

        switch (state) {
            case STATE_NORMAL:
                rvTodoListAdapter = new RvTodoListAdapter(todoListBeanList,getContext());
                //Recyclerview添加头部布局
//                rvHomeAdapter.setHeaderView(viewHeader);
                recyclerView.setAdapter(rvTodoListAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                // 设置item及item中控件的点击事件
//                rvWechartArticleAdapter.setOnItemClickListener(MyItemClickListener);
//                item分割线
//                recyclerview_mycommunity.setItemAnimator(new DefaultItemAnimator());
//                recyclerview_mycommunity.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


                break;

            case STATE_REFRESH:
//                if (rvTodoListAdapter != null)
//                    rvTodoListAdapter.clearData();
//                rvTodoListAdapter.addData(todoListBeanList);
//                recyclerView.scrollToPosition(0);
////                rvWechartArticleAdapter = new RvWechartArticleAdapter(wechartArticleDatasBeanList);
//////                //Recyclerview添加头部布局
//////                rvHomeAdapter.setHeaderView(viewHeader);
////                recyclerView.setAdapter(rvSystemArticleAdapter);
////                recyclerView.setLayoutManager(new LinearLayoutManager(this));
//                refreshLayout.finishRefresh();
//                rvTodoListAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
                break;

            case STATE_MORE:
//                rvTodoListAdapter.addData(rvTodoListAdapter.getDatas().size(), todoListBeanList);
//                recyclerView.scrollToPosition(rvTodoListAdapter.getDatas().size());
                refreshLayout.finishLoadMore();
                break;

        }
        // 设置item及item中控件的点击事件
        rvTodoListAdapter.setOnItemClickListener(MyItemClickListener);

    }

    /**
     * item＋item里的控件点击监听事件
     */
    private RvTodoListAdapter.OnItemClickListener MyItemClickListener = new RvTodoListAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()) {


                default:
//                    Intent intent = new Intent(mContext, BannerActivity.class);
//                    intent.putExtra("url",linkList.get(position));
//                    intent.putExtra("title",titleList.get(position));
//                    startActivity(intent);
                    break;
            }
            Log.e("click",position+"");
        }

        @Override
        public void onItemLongClick(View v) {

        }
    };


}
