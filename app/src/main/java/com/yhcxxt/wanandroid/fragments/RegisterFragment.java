package com.yhcxxt.wanandroid.fragments;

import android.app.Instrumentation;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.activity.LoginActivity;
import com.yhcxxt.wanandroid.model.RegisterModel;
import com.yhcxxt.wanandroid.presenter.RegisterPresenter;
import com.yhcxxt.wanandroid.view.RegisterView;

public class RegisterFragment extends Fragment implements View.OnClickListener, RegisterView {

    View view;
    private EditText etName;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private TextView tvRegister;
    private TextView tvLogin;

    String username;
    String password;
    String repassword;

    RegisterPresenter registerPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_register, container, false);

        registerPresenter = new RegisterPresenter(this);

        initView();

        return view;
    }

    private void initView() {

        etName = view.findViewById(R.id.et_name);
        etPassword = view.findViewById(R.id.et_password);
        etConfirmPassword = view.findViewById(R.id.et_confirm_password);
        tvRegister = view.findViewById(R.id.tv_register);
        tvLogin = view.findViewById(R.id.tv_login);

        tvRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);

    }

    public static Fragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register://注册
                username = etName.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                repassword = etConfirmPassword.getText().toString().trim();

                if (username.isEmpty()) {
                    Toast.makeText(getContext(), "用户名不能为空！", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(getContext(), "密码不能为空！", Toast.LENGTH_SHORT).show();
                } else if (repassword.isEmpty()) {
                    Toast.makeText(getContext(), "确认密码不能为空！", Toast.LENGTH_SHORT).show();
                }

                registerPresenter.loadRegister(getContext(), username, password, repassword);

                break;

            case R.id.tv_login://登录
                toLoginFragment();
//                ((LoginActivity) getActivity()).toLoginFragment();
                break;
        }
    }

    private void toLoginFragment() {

        //模拟返回键
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * 注册
     *
     * @param model
     */
    @Override
    public void getRegisterData(RegisterModel model) {

        if (model.getErrorCode().equals("0")) {
            toLoginFragment();
            Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), model.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }

    }
}
