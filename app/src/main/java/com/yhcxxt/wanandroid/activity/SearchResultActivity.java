package com.yhcxxt.wanandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.adapter.RvSearchAdapter;
import com.yhcxxt.wanandroid.adapter.RvSystemArticleAdapter;
import com.yhcxxt.wanandroid.model.SearchDatasBean;
import com.yhcxxt.wanandroid.model.SearchModel;
import com.yhcxxt.wanandroid.model.SystemArticleListDatasBean;
import com.yhcxxt.wanandroid.presenter.SearchPresenter;
import com.yhcxxt.wanandroid.utils.StatusBarUtil;
import com.yhcxxt.wanandroid.view.SearchView;
import com.yhcxxt.wanandroid.views.IosLoadDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *      author:LHT
 *      time:20190528
 *      desc:搜索结果 Activity
 * </pre>
 */
public class SearchResultActivity extends BaseActivity implements SearchView, View.OnClickListener {

    private LinearLayout linear_no_data;//没有相关数据
    private LinearLayout linear_back;//返回
    private TextView tv_title;//标题

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private String content;

    SearchPresenter searchPresenter;//搜索

    private IosLoadDialog dialog;

    private RvSearchAdapter rvSearchAdapter;

    private String link;//url
    private String title;//标题
    private List<String> linkList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();


    List<SearchDatasBean> searchDatasBeanArrayList = new ArrayList<>();
    private String total;

    private int page = 0;

    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_MORE = 2;

    //当前状态
    private int state = STATE_NORMAL;

    private int mColor;

    @Override
    protected void setStatusBar() {
        mColor = getResources().getColor(R.color.wks_bg);
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
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        searchPresenter = new SearchPresenter(this);

        dialog = new IosLoadDialog(this);
        dialog.show();

        Intent intent = getIntent();
        content = intent.getStringExtra("content");
        Log.e("content", content);
        initView();

        searchPresenter.loadSearch(this, String.valueOf(page), content);



    }

    private void initView() {

        linear_no_data = findViewById(R.id.linear_no_data);
        linear_back = findViewById(R.id.linear_back);
        tv_title = findViewById(R.id.tv_title);

        linear_back.setOnClickListener(this);
        tv_title.setText(content);

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
        searchPresenter.loadSearch(this, String.valueOf(page), content);

    }

    private void refreshData() {

        page = 0;
        state = STATE_REFRESH;
        searchPresenter.loadSearch(this, String.valueOf(page), content);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_back://返回
                finish();
                break;
        }
    }


    /**
     * 搜索
     *
     * @param model
     */
    @Override
    public void getSearchData(SearchModel model) {

        searchDatasBeanArrayList = model.getData().getDatas();
        for (int i = 0; i < searchDatasBeanArrayList.size(); i++) {

            link = searchDatasBeanArrayList.get(i).getLink();
            linkList.add(link);

            title = searchDatasBeanArrayList.get(i).getTitle();
            titleList.add(title);

        }

        total = model.getData().getTotal();

        if (total.equals("0")){
            recyclerView.setVisibility(View.GONE);
            linear_no_data.setVisibility(View.VISIBLE);
        }

        if (searchDatasBeanArrayList.size() < 0) {



        } else
            showData();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        });


    }


    private void showData() {

        switch (state) {
            case STATE_NORMAL:
                rvSearchAdapter = new RvSearchAdapter(searchDatasBeanArrayList);
                //Recyclerview添加头部布局
//                rvHomeAdapter.setHeaderView(viewHeader);
                recyclerView.setAdapter(rvSearchAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                // 设置item及item中控件的点击事件
//                rvWechartArticleAdapter.setOnItemClickListener(MyItemClickListener);
//                item分割线
//                recyclerview_mycommunity.setItemAnimator(new DefaultItemAnimator());
//                recyclerview_mycommunity.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


                break;

            case STATE_REFRESH:
                if (rvSearchAdapter != null)
                    rvSearchAdapter.clearData();
                rvSearchAdapter.addData(searchDatasBeanArrayList);
                recyclerView.scrollToPosition(0);
//                rvWechartArticleAdapter = new RvWechartArticleAdapter(wechartArticleDatasBeanList);
////                //Recyclerview添加头部布局
////                rvHomeAdapter.setHeaderView(viewHeader);
//                recyclerView.setAdapter(rvSystemArticleAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                refreshLayout.finishRefresh();
                rvSearchAdapter.notifyDataSetChanged();
                break;

            case STATE_MORE:
                rvSearchAdapter.addData(rvSearchAdapter.getDatas().size(), searchDatasBeanArrayList);
                recyclerView.scrollToPosition(rvSearchAdapter.getDatas().size());
                refreshLayout.finishLoadMore();
                break;

        }
        // 设置item及item中控件的点击事件
        rvSearchAdapter.setOnItemClickListener(MyItemClickListener);

    }

    /**
     * item＋item里的控件点击监听事件
     */
    private RvSearchAdapter.OnItemClickListener MyItemClickListener = new RvSearchAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()) {


                default:
                    Intent intent = new Intent(mContext, BannerActivity.class);
                    intent.putExtra("url", linkList.get(position));
                    intent.putExtra("title", titleList.get(position));
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
