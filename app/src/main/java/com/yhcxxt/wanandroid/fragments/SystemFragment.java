package com.yhcxxt.wanandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.activity.BannerActivity;
import com.yhcxxt.wanandroid.adapter.RvHomeAdapter;
import com.yhcxxt.wanandroid.model.ArticleDatasBean;
import com.yhcxxt.wanandroid.model.ArticleModel;
import com.yhcxxt.wanandroid.model.BannerBean;
import com.yhcxxt.wanandroid.presenter.ArticlePresenter;
import com.yhcxxt.wanandroid.presenter.BannerPresenter;
import com.yhcxxt.wanandroid.view.ArticleView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 *      author:LHT
 *      time:2019/4/30
 *      desc:知识体系 Fragment
 * </pre>
 */
public class SystemFragment extends Fragment implements ArticleView {

    private RecyclerView recyclerView;
    private RvHomeAdapter rvHomeAdapter;
    ArticlePresenter articlePresenter;
    List<ArticleDatasBean> articleDatas;

    private String link;
    private String title;
    private List<String> linkList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    private SmartRefreshLayout refreshLayout;


    int page = 0;

    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_MORE = 2;

    //当前状态
    private int state = STATE_NORMAL;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_system, container, false);

        refreshLayout = view.findViewById(R.id.refreshLayout);
        recyclerView = view.findViewById(R.id.recyclerview);
        articlePresenter = new ArticlePresenter(this);

        initRefreshLayout();
//        get(view);
        articlePresenter.loadArticle(this.getContext(), page + "");

        return view;
    }

    public void get(View view) {
        OkHttpClient client = new OkHttpClient();
        //构造Request对象
        //采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
        Request request = new Request.Builder().get().url("https://www.wanandroid.com/article/list/0/json").build();
        Call call = client.newCall(request);
        //异步调用并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
                Log.e("responseStr", responseStr);
                try {
                    JSONObject jsonObject = new JSONObject(responseStr);
                    String data = jsonObject.getString("data");
                    Log.e("data", data);
                    JSONObject jsonObjectdata = new JSONObject(data);
                    String datas = jsonObjectdata.getString("datas");
                    Log.e("datas", datas);
                    JSONArray jsonArray = new JSONArray(datas);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        Log.e("jsonObject1", jsonObject1.toString());
//                        articleDatas.add(jsonObject1);
                        String link = jsonObject1.getString("link");
                        Log.e("link", link);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                articleDatas = model.getData().getDatas();
//                showData();
            }
        });
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
//        articlePresenter.loadArticle(this.getContext(),"0","json");
        articlePresenter.loadArticle(this.getContext(), page + "");

    }

    private void refreshData() {

        page = 0;
        state = STATE_REFRESH;
//        articlePresenter.loadArticle(this.getContext(),"0","json");
        articlePresenter.loadArticle(this.getContext(), String.valueOf(page));

    }

    @Override
    public void getArticleData(ArticleModel model) {

        articleDatas = model.getData().getDatas();
        for (int i = 0; i < articleDatas.size(); i++) {
            String author = model.getData().getDatas().get(i).getAuthor();
            Log.e("author", author);
            title = articleDatas.get(i).getTitle();
            titleList.add(title);
            link = articleDatas.get(i).getLink();
            linkList.add(link);

        }
        if (articleDatas.size()<0){

        }
        showData();


    }

    private void showData() {

        switch (state) {
            case STATE_NORMAL:
                rvHomeAdapter = new RvHomeAdapter(articleDatas);
                //Recyclerview添加头部布局
//                rvHomeAdapter.setHeaderView(viewHeader);

                recyclerView.setAdapter(rvHomeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                // 设置item及item中控件的点击事件
//                rvHomeAdapter.setOnItemClickListener(MyItemClickListener);
                //item分割线
//                recyclerview_mycommunity.setItemAnimator(new DefaultItemAnimator());
//                recyclerview_mycommunity.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


                break;

            case STATE_REFRESH:
                if (rvHomeAdapter != null)
                    rvHomeAdapter.clearData();
                rvHomeAdapter.addData(articleDatas);
                recyclerView.scrollToPosition(0);
                refreshLayout.finishRefresh();
                rvHomeAdapter.notifyDataSetChanged();
                break;

            case STATE_MORE:
                rvHomeAdapter.addData(rvHomeAdapter.getDatas().size(), articleDatas);
                recyclerView.scrollToPosition(rvHomeAdapter.getDatas().size());
                refreshLayout.finishLoadMore();
                break;

        }
        // 设置item及item中控件的点击事件
        rvHomeAdapter.setOnItemClickListener(MyItemClickListener);

    }

    /**
     * item＋item里的控件点击监听事件
     */
    private RvHomeAdapter.OnItemClickListener MyItemClickListener = new RvHomeAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()) {


                default:
                    Intent intent = new Intent(getContext(), BannerActivity.class);
                    intent.putExtra("url", linkList.get(position));
                    intent.putExtra("title", titleList.get(position));
                    startActivity(intent);
                    break;
            }
        }

        @Override
        public void onItemLongClick(View v) {

        }
    };


}
