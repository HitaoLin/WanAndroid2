package com.yhcxxt.wanandroid.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wildma.pictureselector.ImageUtils;
import com.wildma.pictureselector.PictureSelector;
import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.activity.AboutUsActivity;
import com.yhcxxt.wanandroid.activity.CollectActivity;
import com.yhcxxt.wanandroid.activity.LoginActivity;
import com.yhcxxt.wanandroid.activity.MainActivity;
import com.yhcxxt.wanandroid.activity.SplashActivity;
import com.yhcxxt.wanandroid.activity.TodoActivity;
import com.yhcxxt.wanandroid.activity.ToolActivity;
import com.yhcxxt.wanandroid.config.ConfigValue;
import com.yhcxxt.wanandroid.config.SPConfig;
import com.yhcxxt.wanandroid.http.OkHttpClientManager;
import com.yhcxxt.wanandroid.model.CollectModel;
import com.yhcxxt.wanandroid.presenter.CollectPresenter;
import com.yhcxxt.wanandroid.utils.SPUtils;
import com.yhcxxt.wanandroid.utils.TypefacesUtil;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.CollectView;
import com.yhcxxt.wanandroid.views.CircleImageView;
import com.yhcxxt.wanandroid.widget.Lead;
import com.yhcxxt.wanandroid.widget.LeadTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      author:LHT
 *      time:2019/4/30
 *      desc:我的 Fragment
 * </pre>
 */
public class PersonFragment extends Fragment implements View.OnClickListener, CollectView {

    LeadTextView leadTv;
    private static final int mDuration = 2000;
    private CircleImageView ivHeader;//头像
    private TextView tvName;//用户名
    private LinearLayout linearCollect;//收藏
    private LinearLayout linearFeedback;//反馈建议
    private LinearLayout linearVersionSetting;//版本更新
    private LinearLayout linearNightPattern;//夜间模式
    private LinearLayout linearLanguageSetting;//语言设置
    private LinearLayout linearFontSize;//字体大小
    private LinearLayout linearLogOut;//退出登录
    private LinearLayout linear_about;//关于我们
    private LinearLayout linear_tool;//工具
    private LinearLayout linear_todo;

    private List<String> collectList = new ArrayList<>();
    private String name;
    private String username;
    //    private String password;
    private String flag;
    String path;
    int id;
    private String cookie;

    CollectPresenter collectPresenter;//判断登录状态

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_person, container, false);

        Intent intent = getActivity().getIntent();
        collectList = intent.getStringArrayListExtra("collectList");
        name = intent.getStringExtra("name");

        //-------------------------------
//        id = getActivity().getIntent().getIntExtra("id", 0);
//        if (id == 1) {
//            getActivity().getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.frameContain,new PersonFragment())
//                    .addToBackStack(null)
//                    .commit();
//        }


        //获取登录状态
//        flag = (String) SPUtils.get(this.getContext(), SPConfig.FLAG, "");
//        ConfigValue.Flag = (String) SPUtils.get(this.getContext(), SPConfig.FLAG, "");

        path = (String) SPUtils.get(this.getContext(), SPConfig.PICTUREPATH, "");
        ConfigValue.Picture_Path = (String) SPUtils.get(this.getContext(), SPConfig.PICTUREPATH, "");

        username = (String) SPUtils.get(this.getContext(), SPConfig.USERNAME, "");
        ConfigValue.User_Name = (String) SPUtils.get(this.getContext(), SPConfig.USERNAME, "");

//        cookie = (String) SPUtils.get(this.getContext(), SPConfig.COOKIE, "");
//        ConfigValue.Cookie = (String) SPUtils.get(this.getContext(), SPConfig.COOKIE, "");

        cookie = OkHttpClientManager.getCooki();
        Log.e("cookie",cookie);

        collectPresenter = new CollectPresenter(this);
        collectPresenter.loadCollect(this.getContext(), "0");

//        initView();


//
//        password = (String) SPUtils.get(this.getContext(), SPConfig.PASSWORD, "");
//        ConfigValue.Password = (String) SPUtils.get(this.getContext(), SPConfig.PASSWORD, "");

        return view;
    }

    private void initView() {

        ivHeader = view.findViewById(R.id.iv_header);
        tvName = view.findViewById(R.id.tv_name);
        linearCollect = view.findViewById(R.id.linear_collect);
        linearFeedback = view.findViewById(R.id.linear_feedback);
        linearVersionSetting = view.findViewById(R.id.linear_version_setting);
        linearNightPattern = view.findViewById(R.id.linear_night_pattern);
        linearLanguageSetting = view.findViewById(R.id.linear_language_setting);
        linearFontSize = view.findViewById(R.id.linear_font_size);
        linearLogOut = view.findViewById(R.id.linear_log_out);
        linear_about = view.findViewById(R.id.linear_about);
        linear_tool = view.findViewById(R.id.linear_tool);
        linear_todo = view.findViewById(R.id.linear_todo);

        ivHeader.setOnClickListener(this);
        linearCollect.setOnClickListener(this);
        linearFeedback.setOnClickListener(this);
        linearVersionSetting.setOnClickListener(this);
        linearNightPattern.setOnClickListener(this);
        linearLanguageSetting.setOnClickListener(this);
        linearFontSize.setOnClickListener(this);
        linearLogOut.setOnClickListener(this);
        linear_about.setOnClickListener(this);
        linear_tool.setOnClickListener(this);
        tvName.setOnClickListener(this);
        linear_todo.setOnClickListener(this);

//        if (cookie.equals("")||cookie.isEmpty()){
        if (flag.equals("1")) {
            tvName.setText(username);
            if (path.isEmpty()) {
                ivHeader.setImageResource(R.mipmap.bg_person2);
            } else
                ivHeader.setImageBitmap(ImageUtils.getBitmap(path));

        } else {
            tvName.setText("登 录");
            /**
             * 区别：
             * setBackgroundResource()和setImageResource()
             *
             * 布局 .xml 的属性 background 相当于setBackgroundResource()
             * 布局 .xml 的属性 src 相当于setImageResource()
             */
            ivHeader.setImageResource(R.mipmap.bg_person2);
        }

        initLeadText();

    }

    private void initLeadText(){
        leadTv = view.findViewById(R.id.leadTv);
        leadTv.setTypeface(TypefacesUtil.get(this.getContext(),"Satisfy-Regular.ttf"));
        final Lead lead = new Lead(mDuration);
        lead.start(leadTv);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_header://头像
                if (flag.equals("1")) {

                    /**
                     * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
                     * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
                     */
                    PictureSelector
                            .create(this, PictureSelector.SELECT_REQUEST_CODE)
                            .selectPicture(true, 500, 500, 1, 1);

                } else {
                    startActivity(new Intent(this.getContext(), LoginActivity.class));
                }
                break;

            case R.id.tv_name://用户名

                if (flag.equals("1")) {

                } else {
                    startActivity(new Intent(this.getContext(), LoginActivity.class));
                }

//                if (cookie.equals("")||cookie.isEmpty()){
//                    startActivity(new Intent(this.getContext(), LoginActivity.class));
//                }


                break;

            case R.id.linear_collect://收藏
//                if (flag.equals("1")) {
//                    Toast.makeText(this.getContext(), "敬请期待！", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this.getContext(), CollectActivity.class));
//                } else {
//                    Toast.makeText(this.getContext(), "请先登录！", Toast.LENGTH_SHORT).show();
//                }
                break;

            case R.id.linear_todo:
                startActivity(new Intent(this.getContext(), TodoActivity.class));
                break;

            case R.id.linear_feedback://反馈建议
                break;

            case R.id.linear_version_setting://版本更新
                Toast.makeText(this.getContext(), "已是最新版本", Toast.LENGTH_SHORT).show();
                break;

            case R.id.linear_night_pattern://夜间模式
                break;

            case R.id.linear_language_setting://语言设置
                break;

            case R.id.linear_font_size://字体大小
                break;

            case R.id.linear_tool://工具
                startActivity(new Intent(getContext(), ToolActivity.class));
                break;


            case R.id.linear_about://关于我们
                startActivity(new Intent(getContext(), AboutUsActivity.class));
                break;

            case R.id.linear_log_out://退出登录
                if (flag.equals("1")) {

                    Utils.showDialog(getActivity(), "温馨提示", "确定要现在退出登陆吗？", "", "", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (v.getId()) {
                                case R.id.txtDialogCancel:
                                    Log.e("toast", "取消");
                                    Utils.dismissDialog();
                                    break;
                                case R.id.txtDialogSure:
                                    Log.e("toast", "确定");
                                    flag = "0";
                                    SPUtils.put(getActivity(), SPConfig.FLAG, flag);
                                    ConfigValue.Flag = flag;
                                    Utils.dismissDialog();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    break;
                            }
                        }
                    });


                } else {
                    Toast.makeText(this.getContext(), "您未登录！", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                ivHeader.setImageBitmap(ImageUtils.getBitmap(picturePath));

                SPUtils.put(this.getContext(), SPConfig.PICTUREPATH, picturePath);
                ConfigValue.Picture_Path = picturePath;

            }
        }
    }

    /**
     * 判断登录状态
     *
     * @param model
     */
    @Override
    public void getCollect(CollectModel model) {

        if (model.getErrorCode().equals("0")) {
            flag = "1";
        } else {
            flag = "0";
        }

        initView();
    }
}
