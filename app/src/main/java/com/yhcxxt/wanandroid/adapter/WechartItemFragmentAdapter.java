package com.yhcxxt.wanandroid.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yhcxxt.wanandroid.fragments.WechartArticleFragment;

import java.util.List;

/**
 * <pre>
 *      author:LHT
 *      time:20190506
 *      desc:微信公众号 Adapter
 * </pre>
 */

public class WechartItemFragmentAdapter extends FragmentPagerAdapter {
//    private String[] names;
    private List<String> names;
    private List<Fragment> fragments;
    private Context context;


    public WechartItemFragmentAdapter(FragmentManager fm, List<String> names, List<Fragment> fragments, Context context) {
        super(fm);
        this.names = names;
        this.fragments = fragments;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return names.get(position);
    }






}
