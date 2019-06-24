package com.yhcxxt.wanandroid.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.fragments.LoginFragment;
import com.yhcxxt.wanandroid.fragments.RegisterFragment;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button backBtn;
    private FrameLayout frameContain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        replaceFragment(LoginFragment.newInstance());

    }

    /**
     * 移除注册界面
     */
    public void toRegisterFragment() {
        replaceFragment(RegisterFragment.newInstance());
    }

    /**
     * 移除登录界面
     */
    public void toLoginFragment() {
        replaceFragment(LoginFragment.newInstance());
    }

    /**
     * 切换登录和注册
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       transaction.replace(R.id.frameContain,fragment);
       if (fragment instanceof RegisterFragment) transaction.addToBackStack(null);
       transaction.commit();

    }


    private void initView() {

        backBtn = (Button) findViewById(R.id.backBtn);
        frameContain = (FrameLayout) findViewById(R.id.frameContain);

        backBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backBtn://返回
                finish();
                break;
        }
    }


}
