package com.yhcxxt.wanandroid.fragments;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.activity.BannerActivity;
import com.yhcxxt.wanandroid.activity.MainActivity;
import com.yhcxxt.wanandroid.activity.SerachActivity;
import com.yhcxxt.wanandroid.adapter.RvHomeAdapter;
import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.model.ArticleDatasBean;
import com.yhcxxt.wanandroid.model.ArticleModel;
import com.yhcxxt.wanandroid.model.BannerBean;
import com.yhcxxt.wanandroid.model.BannerModel;
import com.yhcxxt.wanandroid.presenter.ArticlePresenter;
import com.yhcxxt.wanandroid.presenter.BannerPresenter;
import com.yhcxxt.wanandroid.utils.MyImageLoder;
import com.yhcxxt.wanandroid.view.ArticleView;
import com.yhcxxt.wanandroid.view.BannerView;
import com.yhcxxt.wanandroid.views.IosLoadDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      author:LHT
 *      time:2019/4/19
 *      desc:首页 Fragment
 * </pre>
 */
public class HomeFragment extends Fragment implements BannerView, OnBannerListener, ArticleView {

    private EditText et_search;//搜索框

    private Banner banner;
    private RecyclerView recyclerView;
    private RvHomeAdapter rvHomeAdapter;
    View view;
    //定义头部view
    View viewHeader;
    private MyImageLoder myImageLoder;
    private String imagePath;
    private String imageTitle;
    private String bannerUrl;
    private String link;
    private String title;
    private List<String> imagePathList = new ArrayList<>();
    private List<String> imageTitleList = new ArrayList<>();
    private List<String> bannerUrlList = new ArrayList<>();
    private List<String> linkList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    BannerPresenter bannerPresenter;
    ArticlePresenter articlePresenter;
    List<BannerBean> bannerDatas;
    List<ArticleDatasBean> articleDatas;

    private SmartRefreshLayout refreshLayout;

    int page = 0;

    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_MORE = 2;

    private IosLoadDialog dialog;

    //当前状态
    private int state = STATE_NORMAL;

    private ImageView item_home_love;

    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        dialog = new IosLoadDialog(this.getContext());
        dialog.show();

//        banner = view.findViewById(R.id.banner);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        et_search = view.findViewById(R.id.et_search);
        bannerPresenter = new BannerPresenter(this);
        articlePresenter = new ArticlePresenter(this);
        bannerPresenter.loadBanner(this.getContext());

        initEvent();
        initRecyclerView();
        initRefreshLayout();

        articlePresenter.loadArticle(this.getContext(), String.valueOf(page));
        return view;
    }

    private void initEvent() {
        et_search.setOnClickListener(v -> toSearchActivity());
    }

    private void initRefreshLayout() {

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
        articlePresenter.loadArticle(this.getContext(), String.valueOf(page));

    }

    private void refreshData() {

        page = 0;
        state = STATE_REFRESH;
        articlePresenter.loadArticle(this.getContext(), String.valueOf(page));

    }


    private void initRecyclerView() {
        recyclerView = view.findViewById(R.id.recyclerview);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        //添加轮播图到Recyclerview的Header
        viewHeader = LayoutInflater.from(this.getContext()).inflate(R.layout.banner_layout, (ViewGroup) recyclerView.getParent(), false);
        banner = viewHeader.findViewById(R.id.banner);


        initBanner();


    }

    private void initBanner() {

        myImageLoder = new MyImageLoder();
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                //设置图片加载器
                .setImageLoader(myImageLoder)
                //设置banner动画效果
                .setBannerAnimation(Transformer.ZoomOutSlide)
                //设置轮播时间
                .setDelayTime(3000)
                //设置自动轮播，默认为true
                .isAutoPlay(true)
                //设置指示器位置（当banner模式中有指示器时）
                .setIndicatorGravity(BannerConfig.CENTER)
                //轮播图的监听
                .setOnBannerListener(this)
                //设置图片集合
                .setImages(imagePathList)
                //设置标题集合（当banner样式有显示title时）
                .setBannerTitles(imageTitleList)
                //banner设置方法全部调用完毕时最后调用
                .start();

    }

    /**
     * Banner 数据
     * @param model
     */
    @Override
    public void getBannerData(BannerModel model) {
        bannerDatas = model.getData();
        for (int i = 0; i < bannerDatas.size(); i++) {

            imagePath = model.getData().get(i).getImagePath();
            imagePathList.add(imagePath);
            imageTitle = model.getData().get(i).getTitle();
            imageTitleList.add(imageTitle);
            bannerUrl = model.getData().get(i).getUrl();
            bannerUrlList.add(bannerUrl);

        }


        initBanner();

    }

    private void showData() {

        switch (state) {
            case STATE_NORMAL:
                rvHomeAdapter = new RvHomeAdapter(articleDatas);
                //Recyclerview添加头部布局
                rvHomeAdapter.setHeaderView(viewHeader);
                recyclerView.setAdapter(rvHomeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                // 设置item及item中控件的点击事件
                rvHomeAdapter.setOnItemClickListener(MyItemClickListener);
                //item分割线
//                recyclerview_mycommunity.setItemAnimator(new DefaultItemAnimator());
//                recyclerview_mycommunity.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


                break;

            case STATE_REFRESH:
                if (rvHomeAdapter != null)
                    rvHomeAdapter.clearData();
//                rvHomeAdapter.addData(articleDatas);
//                recyclerView.scrollToPosition(0);
                rvHomeAdapter = new RvHomeAdapter(articleDatas);
                //Recyclerview添加头部布局
                rvHomeAdapter.setHeaderView(viewHeader);
                recyclerView.setAdapter(rvHomeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                refreshLayout.finishRefresh();
                rvHomeAdapter.notifyDataSetChanged();
                break;

            case STATE_MORE:
                rvHomeAdapter.addData(rvHomeAdapter.getDatas().size()+1, articleDatas);
                recyclerView.scrollToPosition(rvHomeAdapter.getDatas().size()+1);
                refreshLayout.finishLoadMore();
                break;

        }
        // 设置item及item中控件的点击事件
        rvHomeAdapter.setOnItemClickListener(MyItemClickListener);

    }

    @Override
    public void OnBannerClick(int position) {
//        Uri uri = Uri.parse(bannerUrlList.get(position));
//        Intent intentAbout = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intentAbout);
        Intent intent = new Intent(this.getContext(), BannerActivity.class);
        intent.putExtra("url",bannerUrlList.get(position));
        intent.putExtra("title",imageTitleList.get(position));
        startActivity(intent);
    }

    /**
     * item＋item里的控件点击监听事件
     */
    private RvHomeAdapter.OnItemClickListener MyItemClickListener = new RvHomeAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()) {


                default:
                    Intent intent = new Intent(getContext(),BannerActivity.class);
                    intent.putExtra("url",linkList.get(position-1));
                    intent.putExtra("title",titleList.get(position-1));
                    startActivity(intent);
                    break;
            }
        }

        @Override
        public void onItemLongClick(View v) {

        }
    };

    /**
     * 首页文章列表
     * @param model
     */
    @Override
    public void getArticleData(ArticleModel model) {

        articleDatas = model.getData().getDatas();

        for (int i = 0; i < articleDatas.size(); i++) {
            title = articleDatas.get(i).getTitle();
            titleList.add(title);
            link = articleDatas.get(i).getLink();
            linkList.add(link);

        }
        if (articleDatas.size()<0){

        }else
        showData();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        });


    }

    private void toSearchActivity() {
        Intent intent = new Intent(getContext(), SerachActivity.class);
       //.makeSceneTransitionAnimation  至少为 minSdkVersion 21
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity,
                Pair.create(et_search, getString(R.string.share_edit))//,
               // Pair.create(et_search, getString(R.string.share_image))
        );
        startActivity(intent, options.toBundle());
    }

}
