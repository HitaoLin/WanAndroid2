package com.yhcxxt.wanandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.entity.TabEntity;
import com.yhcxxt.wanandroid.fragments.LifeFragment;
import com.yhcxxt.wanandroid.fragments.OtherFragment;
import com.yhcxxt.wanandroid.fragments.StudyFragment;
import com.yhcxxt.wanandroid.fragments.StudyFragment2;
import com.yhcxxt.wanandroid.fragments.WorkFragment;
import com.yhcxxt.wanandroid.utils.StatusBarUtil;
import com.yhcxxt.wanandroid.utils.ViewFindUtils;

import java.util.ArrayList;

/**
 * <pre>
 *     author:LHT
 *     date:20190719
 *     desc:清单 Activity
 * </pre>
 */
public class TodoActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout linear_back;//返回
    private TextView tv_title;//标题
    private LinearLayout ll_record;//记录

    private CommonTabLayout mTabLayout_2;
    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"学习", "生活", "工作", "其他"};
    private int[] mIconUnselectIds = {
            R.mipmap.study_uncheck, R.mipmap.life_uncheck,
            R.mipmap.work_uncheck, R.mipmap.other_uncheck};
    private int[] mIconSelectIds = {
            R.mipmap.study_check, R.mipmap.life_check,
            R.mipmap.work_check, R.mipmap.other_check};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private View mDecorView;
    private ViewPager mViewPager;


    private int mColor;

    @Override
    protected void setStatusBar() {
        mColor = getResources().getColor(R.color.progress_bar_web_start);
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
        setContentView(R.layout.activity_todo);

        initView();
        tv_title.setText("学习清单");
    }


    private void initView() {

        linear_back = findViewById(R.id.linear_back);
        tv_title = findViewById(R.id.tv_title);

        linear_back.setOnClickListener(this);
        tv_title.setText("学习清单");

        ll_record = findViewById(R.id.ll_record);
        ll_record.setOnClickListener(this);

        mTabLayout_2 = findViewById(R.id.tl_2);
        mViewPager = findViewById(R.id.viewpager_dividend_details);

        initTab();

    }

    private void initTab() {
//        for (String title : mTitles) {
//            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
//        }
        mFragments.add(StudyFragment2.getInstance("Switch ViewPager "));
//        mFragments.add(LifeFragment.getInstance("Switch ViewPager "));
//        mFragments.add(WorkFragment.getInstance("Switch ViewPager "));
//        mFragments.add(OtherFragment.getInstance("Switch ViewPager "));


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mDecorView = getWindow().getDecorView();
        mViewPager = ViewFindUtils.find(mDecorView, R.id.vp_2);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        /** with ViewPager */
        mTabLayout_2 = ViewFindUtils.find(mDecorView, R.id.tl_2);
        tl_2();

    }

    private void tl_2() {
        mTabLayout_2.setTabData(mTabEntities);
        mTabLayout_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
                if (position == 0) {
                    tv_title.setText("学习清单");
                }
                if (position == 1) {
                    tv_title.setText("生活清单");
                }
                if (position == 2) {
                    tv_title.setText("工作清单");
                }
                if (position == 3) {
                    tv_title.setText("其他清单");
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout_2.setCurrentTab(position);
                if (position == 0) {
                    tv_title.setText("学习清单");
                }
                if (position == 1) {
                    tv_title.setText("生活清单");
                }
                if (position == 2) {
                    tv_title.setText("工作清单");
                }
                if (position == 3) {
                    tv_title.setText("其他清单");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_back://返回
                finish();
                break;

            case R.id.ll_record://记录
                startActivity(new Intent(this, RecordActivity.class));
                break;
        }
    }

}
