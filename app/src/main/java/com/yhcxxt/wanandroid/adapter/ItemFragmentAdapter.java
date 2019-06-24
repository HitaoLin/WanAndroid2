package com.yhcxxt.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ylwx on 2018/8/5.
 * TabLayout
 */

public class ItemFragmentAdapter extends FragmentPagerAdapter {
//    private String[] names;
    private List<String> names;
    private List<Fragment> fragments;
    private Context context;


    public ItemFragmentAdapter(FragmentManager fm, List<String> names, List<Fragment> fragments, Context context) {
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
        return fragments.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return names.get(position);
    }


}
