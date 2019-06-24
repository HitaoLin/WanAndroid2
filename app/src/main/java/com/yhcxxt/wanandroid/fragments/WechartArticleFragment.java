package com.yhcxxt.wanandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.activity.BannerActivity;
import com.yhcxxt.wanandroid.adapter.RvHomeAdapter;
import com.yhcxxt.wanandroid.adapter.RvWechartArticleAdapter;
import com.yhcxxt.wanandroid.adapter.RvWechartArticleAdapter2;
import com.yhcxxt.wanandroid.model.ArticleDatasBean;
import com.yhcxxt.wanandroid.model.ArticleModel;
import com.yhcxxt.wanandroid.model.WechartArticleDatasBean;
import com.yhcxxt.wanandroid.model.WechartArticleModel;
import com.yhcxxt.wanandroid.presenter.WechartArticlePresenter;
import com.yhcxxt.wanandroid.view.ArticleView;
import com.yhcxxt.wanandroid.view.WechartArticleView;
import com.yhcxxt.wanandroid.views.IosLoadDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *      author:LHT
 *      time:2019/4/30
 *      desc:微信公众号推文 Fragment
 * </pre>
 */
public class WechartArticleFragment extends LazyLoadFragment implements WechartArticleView {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private IosLoadDialog dialog;

    private RvWechartArticleAdapter rvWechartArticleAdapter;

    private int page = 1;
    private WechartArticlePresenter wechartArticlePresenter;

    private static String chapterId;//公众号Id
    String id;

    private String link;//url
    private String title;//标题
    private List<String> linkList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    List<WechartArticleDatasBean> wechartArticleDatasBeanList = new ArrayList<>();
    List<String> chapterIdList = new ArrayList<>();//公众号Id集合

    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_MORE = 2;

    //当前状态
    private int state = STATE_NORMAL;


    Bundle bundle;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_wechart_article;
    }

    @Override
    protected void init() {

        ButterKnife.bind(this, rootView);

        bundle = this.getArguments();
        if (bundle != null){
            chapterId = bundle.getString("chapterId");
        }
        Log.e("chapterId",chapterId);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        wechartArticlePresenter = new WechartArticlePresenter(this);

        refreshLayout.setEnableLoadMore(true);



    }

    @Override
    protected void lazyLoad() {
//        Log.e("chapterId",chapterId);

        dialog = new IosLoadDialog(this.getContext());
        dialog.show();

        id = chapterId;

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.e("chapterIdrefre",id);
                refreshData();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.e("chapterIdmore",chapterId);
                loadMoreData();
            }
        });

        wechartArticlePresenter.loadArticle(getActivity(),id,String.valueOf(page));
    }

    private void loadMoreData() {

        page = ++page;
        state = STATE_MORE;
        wechartArticlePresenter.loadArticle(getActivity(),id,String.valueOf(page));
        Log.e("wechartpage",page+"");

    }

    private void refreshData() {

        page = 1;
        state = STATE_REFRESH;
        wechartArticlePresenter.loadArticle(getActivity(),id ,String.valueOf(page));
//        Log.e("chapterIdrefre",chapterId);
    }

    /**
     * 微信公众号文章 数据
     * @param model
     */
    @Override
    public void getWechartArticleData(WechartArticleModel model) {
        wechartArticleDatasBeanList = model.getData().getDatas();

        for (int i = 0; i < wechartArticleDatasBeanList.size(); i++) {
//            chapterId = wechartArticleDatasBeanList.get(i).getChapterId();
//            chapterIdList.add(chapterId);

            link = wechartArticleDatasBeanList.get(i).getLink();
            linkList.add(link);

            title = wechartArticleDatasBeanList.get(i).getTitle();
            titleList.add(title);

        }
        if (wechartArticleDatasBeanList.size()<0){

        }else
            showData();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        });
    }

    private void showData() {

        switch (state) {
            case STATE_NORMAL:
                rvWechartArticleAdapter = new RvWechartArticleAdapter(wechartArticleDatasBeanList);
                //Recyclerview添加头部布局
//                rvHomeAdapter.setHeaderView(viewHeader);
                recyclerView.setAdapter(rvWechartArticleAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                // 设置item及item中控件的点击事件
//                rvWechartArticleAdapter.setOnItemClickListener(MyItemClickListener);
//                item分割线
//                recyclerview_mycommunity.setItemAnimator(new DefaultItemAnimator());
//                recyclerview_mycommunity.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


                break;

            case STATE_REFRESH:
                if (rvWechartArticleAdapter != null)
                    rvWechartArticleAdapter.clearData();
                rvWechartArticleAdapter.addData(wechartArticleDatasBeanList);
                recyclerView.scrollToPosition(0);
//                rvWechartArticleAdapter = new RvWechartArticleAdapter(wechartArticleDatasBeanList);
////                //Recyclerview添加头部布局
////                rvHomeAdapter.setHeaderView(viewHeader);
//                recyclerView.setAdapter(rvWechartArticleAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                refreshLayout.finishRefresh();
                rvWechartArticleAdapter.notifyDataSetChanged();
                break;

            case STATE_MORE:
                rvWechartArticleAdapter.addData(rvWechartArticleAdapter.getDatas().size(), wechartArticleDatasBeanList);
                recyclerView.scrollToPosition(rvWechartArticleAdapter.getDatas().size());
                refreshLayout.finishLoadMore();
                break;

        }
        // 设置item及item中控件的点击事件
        rvWechartArticleAdapter.setOnItemClickListener(MyItemClickListener);

    }

    /**
     * item＋item里的控件点击监听事件
     */
    private RvWechartArticleAdapter.OnItemClickListener MyItemClickListener = new RvWechartArticleAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()) {


                default:
                    Intent intent = new Intent(getContext(), BannerActivity.class);
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
