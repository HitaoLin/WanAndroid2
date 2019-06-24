package com.yhcxxt.wanandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.bean.Tab;
import com.yhcxxt.wanandroid.fragments.HomeFragment;
import com.yhcxxt.wanandroid.fragments.PersonFragment;
import com.yhcxxt.wanandroid.fragments.ProjectFragment;
import com.yhcxxt.wanandroid.fragments.ProjectFragment2;
import com.yhcxxt.wanandroid.fragments.ProjectFragment4;
import com.yhcxxt.wanandroid.fragments.SystemFragment;
import com.yhcxxt.wanandroid.fragments.SystemFragment2;
import com.yhcxxt.wanandroid.fragments.WechartFragment;
import com.yhcxxt.wanandroid.utils.StatusBarUtil;
import com.yhcxxt.wanandroid.widget.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private FragmentTabHost mTabhost;
    private LayoutInflater mInflater;//加载布局管理器
    private List<Tab> mTabs = new ArrayList<>(5);
    private Toolbar mToolBar;

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
        setContentView(R.layout.activity_main);
        initTab();

    }

    private void initToolBar() {
        mToolBar = findViewById(R.id.toolbar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "NavigationClicked", Toast.LENGTH_SHORT).show();
            }
        });

        mToolBar.inflateMenu(R.menu.menu_toolbar);
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_settings){
                    Toast.makeText(MainActivity.this, "action_settings Clicked", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    private void initTab() {

        Tab tab_home = new Tab(R.string.home,R.drawable.selector_icon_home, HomeFragment.class);
        Tab tab_hot = new Tab(R.string.hot,R.drawable.selector_icon_hot, SystemFragment2.class);
        Tab tab_category = new Tab(R.string.catagory,R.drawable.selector_icon_category, WechartFragment.class);
//        Tab tab_cart = new Tab(R.string.cart,R.drawable.selector_icon_cart, ProjectFragment2.class);
        Tab tab_cart = new Tab(R.string.cart,R.drawable.selector_icon_cart, ProjectFragment4.class);
        Tab tab_mine = new Tab(R.string.mine,R.drawable.selector_icon_mine, PersonFragment.class);

        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_category);
        mTabs.add(tab_cart);
        mTabs.add(tab_mine);

        mInflater = LayoutInflater.from(this);
        mTabhost = findViewById(R.id.tabhost);
        mTabhost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);

        for (Tab tab : mTabs){
            FragmentTabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec, tab.getFragment(),null);
        }

        mTabhost.setCurrentTab(0);

    }

    private View buildIndicator(Tab tab){
        View view = mInflater.inflate(R.layout.tab_indicator,null);
        ImageView img = view.findViewById(R.id.icon_tab);
        TextView text = view.findViewById(R.id.txt_indicator);
        img.setImageResource(tab.getIcon());
        text.setText(tab.getTitle());
        return view;
    }

}
