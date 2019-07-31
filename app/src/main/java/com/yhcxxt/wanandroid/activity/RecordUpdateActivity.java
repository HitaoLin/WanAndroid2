package com.yhcxxt.wanandroid.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.utils.StatusBarUtil;
import com.yhcxxt.wanandroid.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *     author:LHT
 *     date:20190724
 *     desc:更新Todo  Activity
 * </pre>
 */
public class RecordUpdateActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.rl_time)
    RelativeLayout rlTime;
    @BindView(R.id.bt_conserve)
    Button btConserve;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.rb_study)
    RadioButton rbStudy;
    @BindView(R.id.rb_life)
    RadioButton rbLife;
    @BindView(R.id.rb_work)
    RadioButton rbWork;
    @BindView(R.id.rb_other)
    RadioButton rbOther;
    @BindView(R.id.rg)
    RadioGroup rg;

    private LinearLayout linear_back;//返回
    private TextView tv_title;//标题

    private String title;
    private String content;
    private String time;
    private String type;
    private String id;


    private int mColor;

    @Override
    protected void setStatusBar() {
        mColor = getResources().getColor(R.color.colorPrimary);
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
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);

        rbStudy = findViewById(R.id.rb_study);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        time = intent.getStringExtra("time");
        content = intent.getStringExtra("content");
        id = intent.getStringExtra("id");
        type = intent.getStringExtra("type");


        initView();
        initData();
        initRadioGroup();

    }

    private void initRadioGroup() {

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_study://学习
                        type = "0";
                        break;

                    case R.id.rb_life://生活
                        type = "1";
                        break;

                    case R.id.rb_work://工作
                        type = "2";
                        break;

                    case R.id.rb_other://其他
                        type = "3";
                        break;
                }
            }
        });

    }

    private void initData() {

        tvTime.setText("" + time);
        etTitle.setText("" + title);
        etContent.setText("" + content);

    }

    private void initView() {

        linear_back = findViewById(R.id.linear_back);
        tv_title = findViewById(R.id.tv_title);

        linear_back.setOnClickListener(this);
        tv_title.setText("编辑");

        rg.clearCheck();

        if (type.equals("0") ){
            rbStudy.setChecked(true);
        }else if (type.equals("1")){
            rbLife.setChecked(true);
        }else if (type.equals("2")){
            rbWork.setChecked(true);
        }else if (type.equals("3")){
            rbOther.setChecked(true);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_back://返回
                finish();
                break;
        }
    }

    @OnClick({R.id.rl_time, R.id.bt_conserve, R.id.tv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_time://选择时间
                Calendar calender = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int
                            dayOfMonth) {
                        String mouth1 = "";
                        String day1 = "";
                        if (monthOfYear < 9) {
                            mouth1 = "0" + (monthOfYear + 1);
                        } else {
                            mouth1 = String.valueOf(monthOfYear + 1);
                        }
                        if (dayOfMonth <= 9) {
                            day1 = "0" + dayOfMonth;
                        } else {
                            day1 = String.valueOf(dayOfMonth);
                        }

                        String dateStr = String.valueOf(year) + "-" + mouth1 + "-" + day1;
                        tvTime.setText(dateStr);


                    }
                }, calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;

            case R.id.tv_clear://清空
                etContent.setText("");
                break;

            case R.id.bt_conserve://保存

                title = etTitle.getText().toString().trim();
                content = etContent.getText().toString().trim();
                time = tvTime.getText().toString().trim();

                if (title.isEmpty()) {
                    Utils.showToast(this, "请输入待办事件名称！");
                }

//                addTodoPresenter.loadArticle(this, title, content, time, type);

                break;
        }
    }

}
