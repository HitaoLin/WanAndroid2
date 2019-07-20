package com.yhcxxt.wanandroid.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yhcxxt.wanandroid.R;

/**
 * <pre>
 *     auhtor:LHT
 *     date:20190719
 *     desc:学习清单  Fragment
 * </pre>
 */
@SuppressLint("ValidFragment")
public class StudyFragment extends Fragment {
    private String mTitle;

    public static StudyFragment getInstance(String title) {
        StudyFragment sf = new StudyFragment();
//        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_study, null);
//        TextView card_title_tv = (TextView) v.findViewById(R.id.card_title_tv);
//        card_title_tv.setText(mTitle);

        return v;
    }
}
