package com.yhcxxt.wanandroid.fragments;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.activity.SerachActivity;
import com.yhcxxt.wanandroid.adapter.ItemFragmentAdapter;
import com.yhcxxt.wanandroid.adapter.RvHomeAdapter;
import com.yhcxxt.wanandroid.adapter.WechartItemFragmentAdapter;
import com.yhcxxt.wanandroid.model.WechartListData;
import com.yhcxxt.wanandroid.model.WechartListModel;
import com.yhcxxt.wanandroid.presenter.WechartListPresenter;
import com.yhcxxt.wanandroid.view.WechartListView;

import java.util.ArrayList;
import java.util.List;
/**
 * <pre>
 *      author:LHT
 *      time:2019/4/30
 *      desc:微信公众号 Fragment
 * </pre>
 */
public class WechartFragment extends Fragment implements WechartListView {

    private EditText et_search;//搜索框

    private List<Fragment> mFragments= new ArrayList<>();
    private WechartItemFragmentAdapter imAdapter;
//    private String[] names = {"1","2"};
    private List<String> names = new ArrayList<>();
    View view;

    WechartListPresenter wechartListPresenter;


    private String id;//公众号Id
    private String name;//公众号名称
    private List<String> idList = new ArrayList<>();//公众号Id 集合
//    private List<String> nameList = new ArrayList<>();//公众号名称 集合

    private List<WechartListData> wechartListDataList;

    private TabLayout tablayout_dividend_details;
    private ViewPager viewpager_dividend_details;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_wechart,container,false);

        wechartListPresenter = new WechartListPresenter(this);
        wechartListPresenter.loadWechartList(getContext());


        return view;
    }

    private void initView() {
        tablayout_dividend_details = view.findViewById(R.id.tablayout_dividend_details);
        viewpager_dividend_details = view.findViewById(R.id.viewpager_dividend_details);

        et_search = view.findViewById(R.id.et_search);

        init();
        initEvent();
    }

    private void initEvent() {
        et_search.setOnClickListener(v -> toSearchActivity());
    }

    //页面
    private void init() {

        for (int i = 0; i<names.size();i++){
            mFragments.add(new WechartArticleFragment());

        }

        for (int i = 0;i<mFragments.size();i++){
            WechartArticleFragment wechartArticleFragment = (WechartArticleFragment) mFragments.get(i);
            Bundle bundle = new Bundle();
            bundle.putString("chapterId",idList.get(i));
            wechartArticleFragment.setArguments(bundle);
        }

        imAdapter = new WechartItemFragmentAdapter(getChildFragmentManager(), names, mFragments, getContext());
        viewpager_dividend_details.setAdapter(imAdapter);
        tablayout_dividend_details.setupWithViewPager(viewpager_dividend_details);

    }

    /**
     * 获取公众号列表
     * @param model
     */
    @Override
    public void getWechartListData(WechartListModel model) {

        wechartListDataList = model.getData();

        for (int i = 0;i<wechartListDataList.size();i++){
            id = wechartListDataList.get(i).getId();
            idList.add(id);
            name = wechartListDataList.get(i).getName();
            names.add(name);
            Log.e("names",name);
        }

       initView();
    }

    private void toSearchActivity() {
        Intent intent = new Intent(getContext(), SerachActivity.class);
        //.makeSceneTransitionAnimation  至少为 minSdkVersion 21
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                Pair.create(et_search, getString(R.string.share_edit))//,
                // Pair.create(et_search, getString(R.string.share_image))
        );
        startActivity(intent, options.toBundle());
    }


}
