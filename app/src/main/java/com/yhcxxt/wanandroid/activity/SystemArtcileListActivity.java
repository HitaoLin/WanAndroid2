package com.yhcxxt.wanandroid.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.adapter.RvSystemArticleAdapter;
import com.yhcxxt.wanandroid.adapter.RvWechartArticleAdapter;
import com.yhcxxt.wanandroid.model.SystemArticleListDatasBean;
import com.yhcxxt.wanandroid.model.SystemArticleListModel;
import com.yhcxxt.wanandroid.model.WechartArticleDatasBean;
import com.yhcxxt.wanandroid.presenter.SystemArticleListPresenter;
import com.yhcxxt.wanandroid.utils.StatusBarUtil;
import com.yhcxxt.wanandroid.view.SystemArtcileListView;
import com.yhcxxt.wanandroid.views.IosLoadDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *      author:LHT
 *      time:2019/5/7
 *      desc:知识体系下文章列表 Activity
 * </pre>
 */
public class SystemArtcileListActivity extends BaseActivity implements View.OnClickListener, SystemArtcileListView {

    private LinearLayout linear_back;//返回
    private TextView tv_title;//标题

    private String name;//标题
    private String id;

    private String link;//url
    private String title;//标题
    private List<String> linkList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private IosLoadDialog dialog;

    private RvSystemArticleAdapter rvSystemArticleAdapter;

    List<SystemArticleListDatasBean> systemArticleListDatasBeanList = new ArrayList<>();

    private int page=0;

    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_MORE = 2;

    //当前状态
    private int state = STATE_NORMAL;

    private SystemArticleListPresenter systemArticleListPresenter;

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
        setContentView(R.layout.activity_system_artcile_list);

        ButterKnife.bind(this);

        dialog = new IosLoadDialog(this);
        dialog.show();

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");

//        Log.e("name",name+"---"+id);

        initView();

        systemArticleListPresenter = new SystemArticleListPresenter(this);
        systemArticleListPresenter.loadSystemArticleList(this, String.valueOf(page),id);

    }

    private void initView() {

        linear_back = findViewById(R.id.linear_back);
        tv_title = findViewById(R.id.tv_title);

        linear_back.setOnClickListener(this);
        tv_title.setText(name);

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
        systemArticleListPresenter.loadSystemArticleList(this, String.valueOf(page),id);

    }

    private void refreshData() {

        page = 0;
        state = STATE_REFRESH;
        systemArticleListPresenter.loadSystemArticleList(this, String.valueOf(page),id);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_back://返回
                finish();
                break;
        }
    }

    /**
     * 知识体系下文章列表 请求返回
     * @param model
     */
    @Override
    public void getSystemArtcileListData(SystemArticleListModel model) {

        systemArticleListDatasBeanList = model.getData().getDatas();

        for (int i = 0; i < systemArticleListDatasBeanList.size(); i++) {
//            chapterId = wechartArticleDatasBeanList.get(i).getChapterId();
//            chapterIdList.add(chapterId);

            link = systemArticleListDatasBeanList.get(i).getLink();
            linkList.add(link);

            title = systemArticleListDatasBeanList.get(i).getTitle();
            titleList.add(title);

        }
        if (systemArticleListDatasBeanList.size()<0){

        }else
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
                rvSystemArticleAdapter = new RvSystemArticleAdapter(systemArticleListDatasBeanList);
                //Recyclerview添加头部布局
//                rvHomeAdapter.setHeaderView(viewHeader);
                recyclerView.setAdapter(rvSystemArticleAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                // 设置item及item中控件的点击事件
//                rvWechartArticleAdapter.setOnItemClickListener(MyItemClickListener);
//                item分割线
//                recyclerview_mycommunity.setItemAnimator(new DefaultItemAnimator());
//                recyclerview_mycommunity.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


                break;

            case STATE_REFRESH:
                if (rvSystemArticleAdapter != null)
                    rvSystemArticleAdapter.clearData();
                rvSystemArticleAdapter.addData(systemArticleListDatasBeanList);
                recyclerView.scrollToPosition(0);
//                rvWechartArticleAdapter = new RvWechartArticleAdapter(wechartArticleDatasBeanList);
////                //Recyclerview添加头部布局
////                rvHomeAdapter.setHeaderView(viewHeader);
//                recyclerView.setAdapter(rvSystemArticleAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                refreshLayout.finishRefresh();
                rvSystemArticleAdapter.notifyDataSetChanged();
                break;

            case STATE_MORE:
                rvSystemArticleAdapter.addData(rvSystemArticleAdapter.getDatas().size(), systemArticleListDatasBeanList);
                recyclerView.scrollToPosition(rvSystemArticleAdapter.getDatas().size());
                refreshLayout.finishLoadMore();
                break;

        }
        // 设置item及item中控件的点击事件
        rvSystemArticleAdapter.setOnItemClickListener(MyItemClickListener);

    }

    /**
     * item＋item里的控件点击监听事件
     */
    private RvSystemArticleAdapter.OnItemClickListener MyItemClickListener = new RvSystemArticleAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()) {


                default:
                    Intent intent = new Intent(mContext, BannerActivity.class);
                    intent.putExtra("url",linkList.get(position));
                    intent.putExtra("title",titleList.get(position));
                    startActivity(intent);
                    break;
            }
            Log.e("click",position+"");
        }

        @Override
        public void onItemLongClick(View v) {

        }
    };


}
