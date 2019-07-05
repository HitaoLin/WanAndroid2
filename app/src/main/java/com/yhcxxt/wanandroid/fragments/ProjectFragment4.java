package com.yhcxxt.wanandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.superluo.textbannerlibrary.ITextBannerItemClickListener;
import com.superluo.textbannerlibrary.TextBannerView;
import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.activity.BannerActivity;
import com.yhcxxt.wanandroid.adapter.GridRecycleAdapter;
import com.yhcxxt.wanandroid.adapter.LinerRecycleAdapter;
import com.yhcxxt.wanandroid.adapter.LinerRecycleAdapter2;
import com.yhcxxt.wanandroid.adapter.RvWechartArticleAdapter;
import com.yhcxxt.wanandroid.model.BannerBean;
import com.yhcxxt.wanandroid.model.BannerModel;
import com.yhcxxt.wanandroid.model.ProjectClassificationDatas;
import com.yhcxxt.wanandroid.model.ProjectClassificationModel;
import com.yhcxxt.wanandroid.model.ProjectListDatasBean;
import com.yhcxxt.wanandroid.model.ProjectListModel;
import com.yhcxxt.wanandroid.presenter.BannerPresenter;
import com.yhcxxt.wanandroid.presenter.ProjectClassificationPresenter;
import com.yhcxxt.wanandroid.presenter.ProjectListPresenter;
import com.yhcxxt.wanandroid.view.BannerView;
import com.yhcxxt.wanandroid.view.ProjectClassificationView;
import com.yhcxxt.wanandroid.view.ProjectListView;
import com.yhcxxt.wanandroid.views.IosLoadDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * <pre>
 *     author:LHT
 *     date:20190622
 *     desc:项目 Fragment4
 * </pre>
 */
public class ProjectFragment4 extends Fragment implements ProjectClassificationView, ProjectListView, BannerView {

    View view;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private LinearLayout linear_no_income;//暂无数据

    //广告条
    private TextBannerView tvBanner;
    //设置数据
    List<String> listBanner = new ArrayList<>();
    List<BannerBean> bannerDatas;

    BannerPresenter bannerPresenter;
    private String desc;//Banner 标题内容
    private String bannerUrl;
    private List<String> descList = new ArrayList<>();
    private List<String> bannerUrlList = new ArrayList<>();

    private VerticalTabLayout tabLayout;
    private RecyclerView recyclerView;

    private GridRecycleAdapter adapter2;
    //    private LinerRecycleAdapter adapter;
    private LinerRecycleAdapter2 adapter;

    List<ProjectListDatasBean> list = new ArrayList<>();
    List<Integer> listList = new ArrayList<>();


    ProjectClassificationPresenter projectClassificationPresenter;//项目分类
    List<ProjectClassificationDatas> projectClassificationDatasList = new ArrayList<>();
    String classifiation;//项目分类
    List<String> classifiationList = new ArrayList<>();

    ProjectListPresenter projectListPresenter;//项目列表数据

    String title;//标题
    String link;//连接
    List<String> titleList = new ArrayList<>();
    List<String> linkList = new ArrayList<>();

    private String id;
    List<String> idList = new ArrayList<>();
    String judgeId;//判断id
    String originalId;//原来id

    IosLoadDialog dialog;

    ImageView iv;

    private int page = 1;
    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_MORE = 2;

    //当前状态
    private int state = STATE_NORMAL;

    private int pos = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_project4, container, false);
        Fresco.initialize(this.getContext());
        ButterKnife.bind(this, view);

        dialog = new IosLoadDialog(this.getContext());
        dialog.show();

        projectClassificationPresenter = new ProjectClassificationPresenter(this);
        projectListPresenter = new ProjectListPresenter(this);
        initView();

        initdEvent();


        return view;
    }

    private void initdEvent() {

        tabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
//                    adapter.setList(list.get(position).getItemName());
//                    tvName.setText(list.get(position).getName());
                dialog.show();

                state = STATE_NORMAL;
                page = 1;

                pos = position;
                Log.e("position", position + "" + "..............id:" + projectClassificationDatasList.get(pos).getId());
                originalId = projectClassificationDatasList.get(pos).getId();
                projectListPresenter.loadProjectList(getContext(), String.valueOf(page), projectClassificationDatasList.get(pos).getId());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabReselected(TabView tab, int position) {
            }
        });

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

    private void initView() {

        linear_no_income = view.findViewById(R.id.linear_no_income);

        tabLayout = view.findViewById(R.id.tab_layout);
        recyclerView = view.findViewById(R.id.recycler_view);

        iv = view.findViewById(R.id.iv);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        refreshLayout.setEnableLoadMore(true);


        //初始化TextBannerView
        tvBanner = view.findViewById(R.id.tv_banner);

        bannerPresenter = new BannerPresenter(this);
        bannerPresenter.loadBanner(getContext());

        initData();

    }

    private void loadMoreData() {

        page = ++page;
        state = STATE_MORE;
        projectListPresenter.loadProjectList(getContext(), String.valueOf(page), projectClassificationDatasList.get(pos).getId());
        Log.e("wechartpage", page + "");

    }

    private void refreshData() {

        page = 1;
        state = STATE_REFRESH;
        projectListPresenter.loadProjectList(getContext(), String.valueOf(page), projectClassificationDatasList.get(pos).getId());
//        Log.e("chapterIdrefre",chapterId);
    }


    private void initData() {

        projectClassificationPresenter.loadProjectClassification(getContext());

    }

    /**
     * 项目分类
     *
     * @param model
     */
    @Override
    public void getProjectClassificationData(ProjectClassificationModel model) {
        if (model.getErrorCode().equals("0")) {

            projectClassificationDatasList = model.getData();
            for (int i = 0; i < projectClassificationDatasList.size(); i++) {

                classifiation = model.getData().get(i).getName();
                classifiationList.add(classifiation);

                id = projectClassificationDatasList.get(i).getId();
                idList.add(id);


                tabLayout.addTab(new QTabView(getContext()).setTitle(
                        new QTabView.TabTitle.Builder().setContent(classifiation).build()));

            }

            judgeId = idList.get(0);
            originalId = projectClassificationDatasList.get(pos).getId();

            projectListPresenter.loadProjectList(getContext(), String.valueOf(page), projectClassificationDatasList.get(pos).getId());


        }
    }

    /**
     * 项目列表数据
     *
     * @param model
     */
    @Override
    public void getProjectListData(ProjectListModel model) {
        if (model.getErrorCode().equals("0")) {


            list = model.getData().getDatas();

            if (judgeId != originalId) {
                titleList.clear();
                linkList.clear();
                /**
                 * 如果id不同，得重新赋值
                 */
                judgeId = originalId;
            }


            for (int i = 0; i < list.size(); i++) {
                title = model.getData().getDatas().get(i).getTitle();
                link = model.getData().getDatas().get(i).getLink();

                titleList.add(title);
                linkList.add(link);

                listList.add(i);

            }

            if (list.size() < 0) {

            } else
                showData();

/**
 * 加载布局
 */
////            GridLayoutManager glm = new GridLayoutManager(getContext(),3);
//            LinearLayoutManager glm = new LinearLayoutManager(getContext());
//            recyclerView.setLayoutManager(glm);
////            adapter = new GridRecycleAdapter(getContext(),list);
//            adapter = new LinerRecycleAdapter2(getContext(), list);
////            adapter = new GridRecycleAdapter(getContext());
////            tvName.setText(list.get(0).getName());
//            recyclerView.setAdapter(adapter);


//            iv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    GridLayoutManager glm = new GridLayoutManager(getContext(),2);
//                    recyclerView.setLayoutManager(glm);
//                    adapter2 = new GridRecycleAdapter(getContext(), list);
//                    recyclerView.setAdapter(adapter2);
//                }
//            });


            dialog.dismiss();

        } else {
            Toast.makeText(getContext(), model.getErrorMsg(), Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    }

    /**
     * 广告条数据
     *
     * @param model
     */
    @Override
    public void getBannerData(BannerModel model) {
        bannerDatas = model.getData();
        for (int i = 0; i < bannerDatas.size(); i++) {

            desc = model.getData().get(i).getTitle();
            descList.add(desc);

            bannerUrl = model.getData().get(i).getUrl();
            bannerUrlList.add(bannerUrl);

        }

        initBanner();

    }

    private void initBanner() {

        tvBanner.setDatas(descList);

        //设置TextBannerView点击监听事件，返回点击的data数据, 和position位置
        tvBanner.setItemOnClickListener(new ITextBannerItemClickListener() {
            @Override
            public void onItemClick(String data, int position) {
//                Log.i("点击了：", String.valueOf(position) + ">>" + data);
//                Toast.makeText(getContext(), String.valueOf(position) + ">>" + data, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), BannerActivity.class);
                intent.putExtra("url", bannerUrlList.get(position));
                intent.putExtra("title", descList.get(position));
                startActivity(intent);
            }
        });

    }


    private void showData() {

        switch (state) {
            case STATE_NORMAL:

                adapter = new LinerRecycleAdapter2(list);
                //Recyclerview添加头部布局
//                rvHomeAdapter.setHeaderView(viewHeader);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                // 设置item及item中控件的点击事件
//                rvWechartArticleAdapter.setOnItemClickListener(MyItemClickListener);
//                item分割线
//                recyclerview_mycommunity.setItemAnimator(new DefaultItemAnimator());
//                recyclerview_mycommunity.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

                //没有数据时显示图片--暂无数据
                if (adapter == null || adapter.getItemCount() == 0) {
                    linear_no_income.setVisibility(View.VISIBLE);
                } else {
                    linear_no_income.setVisibility(View.GONE);
                }

                break;

            case STATE_REFRESH:

                if (adapter != null)
                    adapter.clearData();
                adapter.addData(list);
                recyclerView.scrollToPosition(0);
//                rvWechartArticleAdapter = new RvWechartArticleAdapter(wechartArticleDatasBeanList);
////                //Recyclerview添加头部布局
////                rvHomeAdapter.setHeaderView(viewHeader);
//                recyclerView.setAdapter(rvWechartArticleAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                refreshLayout.finishRefresh();
                adapter.notifyDataSetChanged();

                //没有数据时显示图片--暂无数据
                if (adapter == null || adapter.getItemCount() == 0) {
                    linear_no_income.setVisibility(View.VISIBLE);
                } else {
                    linear_no_income.setVisibility(View.GONE);
                }

                break;

            case STATE_MORE:
                adapter.addData(adapter.getDatas().size(), list);
                recyclerView.scrollToPosition(adapter.getDatas().size());
                refreshLayout.finishLoadMore();


                //没有数据时显示图片--暂无数据
                if (adapter == null || adapter.getItemCount() == 0) {
                    linear_no_income.setVisibility(View.VISIBLE);
                } else {
                    linear_no_income.setVisibility(View.GONE);
                }

                break;

        }
        // 设置item及item中控件的点击事件
        adapter.setOnItemClickListener(MyItemClickListener);

    }

    /**
     * item＋item里的控件点击监听事件
     */
    private LinerRecycleAdapter2.OnItemClickListener MyItemClickListener = new LinerRecycleAdapter2.OnItemClickListener() {

        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()) {


                default:
                    Log.e("pos", position + ".........page:" + page);
                    Intent intent = new Intent(getContext(), BannerActivity.class);
                    Log.e("linkList", linkList.size() + "");
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
