package com.yhcxxt.wanandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.adapter.RvTodoListAdapter;
import com.yhcxxt.wanandroid.entity.TabEntity;
import com.yhcxxt.wanandroid.model.TodoListBean;
import com.yhcxxt.wanandroid.model.TodoListDataBean;
import com.yhcxxt.wanandroid.model.TodoListModel;
import com.yhcxxt.wanandroid.presenter.TodoListPresenter;
import com.yhcxxt.wanandroid.utils.StatusBarUtil;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.utils.ViewFindUtils;
import com.yhcxxt.wanandroid.view.TodoListView;
import com.yhcxxt.wanandroid.views.IosLoadDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *     author:LHT
 *     date:20190719
 *     desc:清单 Activity
 * </pre>
 */
public class TodoActivity2 extends BaseActivity implements View.OnClickListener, TodoListView {

    @BindView(R.id.linear_no_income)
    LinearLayout linear_no_income;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tl_2)
    CommonTabLayout tl2;
    private LinearLayout linear_back;//返回
    private TextView tv_title;//标题
    private LinearLayout ll_record;//记录

    private CommonTabLayout mTabLayout_2;
    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    SegmentTabLayout tabLayout_5;
    private String[] mTitles_3 = {"待办", "完成"};
    private int tabb = 0;

    private String[] mTitles = {"学习", "生活", "工作", "其他"};
    private int[] mIconUnselectIds = {
            R.mipmap.study_uncheck, R.mipmap.life_uncheck,
            R.mipmap.work_uncheck, R.mipmap.other_uncheck};
    private int[] mIconSelectIds = {
            R.mipmap.study_check, R.mipmap.life_check,
            R.mipmap.work_check, R.mipmap.other_check};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private View mDecorView;
    private ViewPager mViewPager;

    RvTodoListAdapter rvTodoListAdapter;

    private String type = "0";
    List<TodoListDataBean> todoListDataBeanList = new ArrayList<>();
    List<TodoListBean> todoListBeanList = new ArrayList<>();
    List<TodoListBean> todoListBeans = new ArrayList<>();
    private String id;
    List<String> idList = new ArrayList<>();
    private String time;
    List<String> timeList = new ArrayList<>();
    private String title;
    List<String> titleList = new ArrayList<>();
    private String content;
    List<String> contentList = new ArrayList<>();

    private IosLoadDialog dialog;

    private int page = 0;

    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_MORE = 2;

    //当前状态
    private int state = STATE_NORMAL;

    TodoListPresenter todoListPresenter;


    private int mColor;

    @Override
    protected void setStatusBar() {
        mColor = getResources().getColor(R.color.progress_bar_web_start);
        StatusBarUtil.setColor(this, mColor, 0);
        if (mColor == getResources().getColor(R.color.white)) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//白色
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo2);
        ButterKnife.bind(this);

        mContext = this;
        initView();
        tv_title.setText("学习清单");

        todoListPresenter = new TodoListPresenter(this);
        todoListPresenter.loadTodoList(this, type);
    }


    private void initView() {

        linear_back = findViewById(R.id.linear_back);
        tv_title = findViewById(R.id.tv_title);

        linear_back.setOnClickListener(this);
        tv_title.setText("学习清单");

        ll_record = findViewById(R.id.ll_record);
        ll_record.setOnClickListener(this);

        mTabLayout_2 = findViewById(R.id.tl_2);
        mViewPager = findViewById(R.id.viewpager_dividend_details);

        initTab();

        initTabLayout();

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

    private void initTabLayout() {

        tabLayout_5 = findViewById(R.id.tl_5);
        tabLayout_5.setTabData(mTitles_3);
        tabLayout_5.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                Toast.makeText(mContext, mTitles_3[position], Toast.LENGTH_SHORT).show();
                tabb = position;

                todoListPresenter.loadTodoList(mContext, type);

            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }

    private void loadMoreData() {

        page = ++page;
        state = STATE_MORE;
        todoListPresenter.loadTodoList(this, type);

    }

    private void refreshData() {

        page = 0;
        state = STATE_REFRESH;
        todoListPresenter.loadTodoList(this, type);

    }

    private void initTab() {
//        for (String title : mTitles) {
//            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
//        }
//        mFragments.add(StudyFragment2.getInstance("Switch ViewPager "));
//        mFragments.add(LifeFragment.getInstance("Switch ViewPager "));
//        mFragments.add(WorkFragment.getInstance("Switch ViewPager "));
//        mFragments.add(OtherFragment.getInstance("Switch ViewPager "));


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mDecorView = getWindow().getDecorView();
        mViewPager = ViewFindUtils.find(mDecorView, R.id.vp_2);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        /** with ViewPager */
        mTabLayout_2 = ViewFindUtils.find(mDecorView, R.id.tl_2);
        tl_2();

    }

    private void tl_2() {
        mTabLayout_2.setTabData(mTabEntities);
        mTabLayout_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
                if (position == 0) {
                    type = "0";
                    tv_title.setText("学习清单");
                    todoListPresenter.loadTodoList(mContext, type);
                    tabLayout_5.setCurrentTab(0);
                }
                if (position == 1) {
                    type = "1";
                    tv_title.setText("生活清单");
                    todoListPresenter.loadTodoList(mContext, type);
                    tabLayout_5.setCurrentTab(0);
                }
                if (position == 2) {
                    type = "2";
                    tv_title.setText("工作清单");
                    todoListPresenter.loadTodoList(mContext, type);
                    tabLayout_5.setCurrentTab(0);
                }
                if (position == 3) {
                    type = "3";
                    tv_title.setText("其他清单");
                    todoListPresenter.loadTodoList(mContext, type);
                    tabLayout_5.setCurrentTab(0);
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout_2.setCurrentTab(position);
                idList.clear();
                timeList.clear();
                contentList.clear();
                titleList.clear();
                if (position == 0) {
                    tv_title.setText("学习清单");
                }
                if (position == 1) {
                    tv_title.setText("生活清单");
                }
                if (position == 2) {
                    tv_title.setText("工作清单");
                }
                if (position == 3) {
                    tv_title.setText("其他清单");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);
    }

    /**
     * 获取todo列表数据
     *
     * @param model
     */
    @Override
    public void getTodoList(TodoListModel model) {
        if (model.getErrorCode().equals("0")) {
            todoListDataBeanList.clear();
            todoListBeanList.clear();
            todoListBeans.clear();


            if (tabb == 1){
                todoListDataBeanList = model.getData().getDoneList();
            }else {
                todoListDataBeanList = model.getData().getTodoList();
            }

            for (int i = 0; i < todoListDataBeanList.size(); i++) {

                todoListBeans = todoListDataBeanList.get(i).getTodoList();
                for (int y = 0; y < todoListBeanList.size(); y++) {

                    id = todoListBeans.get(y).getId();
                    idList.add(id);
//                    time = todoListBeans.get(y).getDateStr();
//                    timeList.add(time);
//                    content = todoListBeans.get(y).getContent();
//                    contentList.add(content);
//                    title = todoListBeans.get(y).getTitle();
//                    titleList.add(title);

                }

                todoListBeanList.addAll(todoListBeans);

            }


            if (todoListDataBeanList.size() < 0) {

                linear_no_income.setVisibility(View.VISIBLE);
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            } else
                showData();


        } else {
            Utils.showToast(this, model.getErrorMsg());
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_back://返回
                finish();
                break;

            case R.id.ll_record://记录
                startActivity(new Intent(this, RecordActivity.class));
                break;
        }
    }

    private void showData() {

        switch (state) {
            case STATE_NORMAL:
                rvTodoListAdapter = new RvTodoListAdapter(todoListBeanList, this);
                //Recyclerview添加头部布局
//                rvHomeAdapter.setHeaderView(viewHeader);
                recyclerView.setAdapter(rvTodoListAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                // 设置item及item中控件的点击事件
//                rvWechartArticleAdapter.setOnItemClickListener(MyItemClickListener);
//                item分割线
//                recyclerview_mycommunity.setItemAnimator(new DefaultItemAnimator());
//                recyclerview_mycommunity.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


                break;

            case STATE_REFRESH:
                if (rvTodoListAdapter != null)
                    rvTodoListAdapter.clearData();
                rvTodoListAdapter.addData(todoListBeanList);
                recyclerView.scrollToPosition(0);
////                rvWechartArticleAdapter = new RvWechartArticleAdapter(wechartArticleDatasBeanList);
//////                //Recyclerview添加头部布局
//////                rvHomeAdapter.setHeaderView(viewHeader);
////                recyclerView.setAdapter(rvSystemArticleAdapter);
////                recyclerView.setLayoutManager(new LinearLayoutManager(this));
//                refreshLayout.finishRefresh();
                rvTodoListAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
                break;

            case STATE_MORE:
                rvTodoListAdapter.addData(rvTodoListAdapter.getDatas().size(), todoListBeanList);
                recyclerView.scrollToPosition(rvTodoListAdapter.getDatas().size());
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
                    Intent intent = new Intent(TodoActivity2.this, RecordUpdateActivity.class);
                    intent.putExtra("id",idList.get(position));
//                    intent.putExtra("time",timeList.get(position));
//                    intent.putExtra("content",contentList.get(position));
//                    intent.putExtra("title",titleList.get(position));
                    intent.putExtra("type",type);
                    startActivity(intent);
                    break;
            }
            Log.e("click", position + "");
        }

        @Override
        public void onItemLongClick(View v) {

        }
    };


}
