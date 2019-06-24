package com.yhcxxt.wanandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.activity.LoginActivity;
import com.yhcxxt.wanandroid.activity.MainActivity;
import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.config.SPConfig;
import com.yhcxxt.wanandroid.dialog.MyDialog;
import com.yhcxxt.wanandroid.model.LoginModel;
import com.yhcxxt.wanandroid.presenter.LoginPresenter;
import com.yhcxxt.wanandroid.utils.SPUtils;
import com.yhcxxt.wanandroid.view.LoginView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *<pre>
 *     author:LHT
 *     date:20190621
 *     desc:登录  Fragment
 *</pre>
 */
public class LoginFragment extends Fragment implements View.OnClickListener, LoginView {

    View view;
    private EditText etName;
    private EditText etPassword;
    private TextView tvLogin;
    private TextView tvRegister;

    String name;//用户名
    String password;//密码

    LoginPresenter loginPresenter;
    List<String> collectList = new ArrayList<>();
    String flag = "0";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login,container,false);
        loginPresenter = new LoginPresenter(this);
        initView();
        return view;
    }

    private void initView() {

        etName = view.findViewById(R.id.et_name);
        etPassword = view.findViewById(R.id.et_password);
        tvLogin = view.findViewById(R.id.tv_login);
        tvRegister = view.findViewById(R.id.tv_register);

        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        //这里只有一句，所以没加｛｝，现在两句就得加｛｝,这就是两句
        //然后v 就是代表OnClick里的形参，v可以随便定义名字,这都是不会报错的，如果要用到形参就得这样子
//        tvRegister.setOnClickListener(v-> ((LoginActivity) getActivity()).toRegisterFragment());
       /* tvRegister.setOnClickListener(new View.OnClickListener() {
            //->就是指这块方法体，只有一句就不用｛｝，超过一句就要加｛｝
            @Override
            public void onClick(View v) {

            }
        });*/

    }

    public static Fragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login://登录
                name = etName.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                if (name.isEmpty()){
                    Toast.makeText(getContext(), "账号不能为空！", Toast.LENGTH_SHORT).show();
                }else if (password.isEmpty()){
                    Toast.makeText(getContext(), "密码不能为空！", Toast.LENGTH_SHORT).show();
                }else
                loginPresenter.loadLogin(this.getContext(),name,password);
                break;

            case R.id.tv_register://注册
                ((LoginActivity) getActivity()).toRegisterFragment();
                break;
        }
    }

    /**
     * 登录
     * @param model
     */
    @Override
    public void getLoginData(LoginModel model) {

        if (model.getErrorCode().equals("0")){

            collectList = model.getData().getCollectIds();
            name = model.getData().getUsername();

            Intent intent = new Intent(this.getContext(),MainActivity.class);
            intent.putExtra("collectList", (Serializable) collectList);
            intent.putExtra("name",name);
            intent.putExtra("id",1);

            SPUtils.put(this.getContext(), SPConfig.USERNAME, name);
            ConfigValue.User_Name = name;

            flag = "1";
            SPUtils.put(this.getContext(), SPConfig.FLAG, flag);
            ConfigValue.Flag = flag;

            startActivity(intent);

        }else {
            Toast.makeText(this.getContext(), model.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }

    }
}
