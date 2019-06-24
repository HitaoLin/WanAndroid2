package com.yhcxxt.wanandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.utils.StatusBarUtil;

/**
 * <pre>
 *     author:LHT
 *     date:20190622
 *     desc:工具
 * </pre>
 */
public class ToolActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout linear_back;//返回
    private TextView tv_title;//标题

    private LinearLayout linearJson;//json转bean
    private LinearLayout linearTodo;//记录
    private LinearLayout linearOpenapis;//开放api
    private LinearLayout linearMd5;//md5
    private LinearLayout linearBase64;//base64
    private LinearLayout linearQR;//二维码生成
    private LinearLayout linearColorPicker;//取色器
    private LinearLayout linearIconfont;//iconfont图标查找
    private LinearLayout linearTinypng;//图片压缩
    private LinearLayout linearBinaryConversion;//进制转换
    private LinearLayout linearShortLinks;//短链接
    private LinearLayout linearQuickTable;//速查表
    private LinearLayout linearRegularExpression;//正则表达式
    private LinearLayout linearUrl;//URL按序解析
    private LinearLayout linearHttps;//https安全检测

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
        setContentView(R.layout.activity_tool);

        initView();

    }

    private void initView() {

        linear_back = findViewById(R.id.linear_back);
        tv_title = findViewById(R.id.tv_title);

        linear_back.setOnClickListener(this);
        tv_title.setText("工具");

        linearJson = (LinearLayout) findViewById(R.id.linear_json);
        linearTodo = (LinearLayout) findViewById(R.id.linear_todo);
        linearOpenapis = (LinearLayout) findViewById(R.id.linear_openapis);
        linearMd5 = (LinearLayout) findViewById(R.id.linear_md5);
        linearBase64 = (LinearLayout) findViewById(R.id.linear_base64);
        linearQR = (LinearLayout) findViewById(R.id.linear_QR);
        linearColorPicker = (LinearLayout) findViewById(R.id.linear_color_picker);
        linearIconfont = (LinearLayout) findViewById(R.id.linear_iconfont);
        linearTinypng = (LinearLayout) findViewById(R.id.linear_tinypng);
        linearBinaryConversion = (LinearLayout) findViewById(R.id.linear_binary_conversion);
        linearShortLinks = (LinearLayout) findViewById(R.id.linear_short_links);
        linearQuickTable = (LinearLayout) findViewById(R.id.linear_quick_table);
        linearRegularExpression = (LinearLayout) findViewById(R.id.linear_regular_expression);
        linearUrl = (LinearLayout) findViewById(R.id.linear_url);
        linearHttps = (LinearLayout) findViewById(R.id.linear_https);

        linearJson.setOnClickListener(this);
        linearTodo.setOnClickListener(this);
        linearOpenapis.setOnClickListener(this);
        linearMd5.setOnClickListener(this);
        linearBase64.setOnClickListener(this);
        linearQR.setOnClickListener(this);
        linearColorPicker.setOnClickListener(this);
        linearIconfont.setOnClickListener(this);
        linearTinypng.setOnClickListener(this);
        linearBinaryConversion.setOnClickListener(this);
        linearShortLinks.setOnClickListener(this);
        linearQuickTable.setOnClickListener(this);
        linearRegularExpression.setOnClickListener(this);
        linearUrl.setOnClickListener(this);
        linearHttps.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_back://返回
               finish();
                break;

            case R.id.linear_json://json转bean
                Intent intent = new Intent(this,BannerActivity.class);
                intent.putExtra("url","https://www.wanandroid.com/tools/bejson");
                intent.putExtra("title","JSON格式转Bean");
                startActivity(intent);
                break;

            case R.id.linear_todo://记录
                Intent intentTodo = new Intent(this,BannerActivity.class);
                intentTodo.putExtra("url","https://www.wanandroid.com/login");
                intentTodo.putExtra("title","Todo");
                startActivity(intentTodo);
                break;

            case R.id.linear_openapis://开放api
                Intent intentOpenapis = new Intent(this,BannerActivity.class);
                intentOpenapis.putExtra("url","https://www.wanandroid.com/openapis");
                intentOpenapis.putExtra("title","OpenApis");
                startActivity(intentOpenapis);
                break;

            case R.id.linear_md5://md5
                Intent intentMd5 = new Intent(this,BannerActivity.class);
                intentMd5.putExtra("url","https://www.wanandroid.com/tools/digit");
                intentMd5.putExtra("title","MD5,SHA等数字摘要");
                startActivity(intentMd5);
                break;

            case R.id.linear_base64://base64
                Intent intentBase64 = new Intent(this,BannerActivity.class);
                intentBase64.putExtra("url","https://www.wanandroid.com/tools/base64");
                intentBase64.putExtra("title","Base64转换");
                startActivity(intentBase64);
                break;

            case R.id.linear_QR://二维码生成
                Intent intentQR = new Intent(this,BannerActivity.class);
                intentQR.putExtra("url","https://www.wanandroid.com/tools/qrcode");
                intentQR.putExtra("title","二维码生成");
                startActivity(intentQR);
                break;
            case R.id.linear_color_picker://取色器
                Intent intentColor = new Intent(this,BannerActivity.class);
                intentColor.putExtra("url","https://www.wanandroid.com/tools/color");
                intentColor.putExtra("title","取色器");
                startActivity(intentColor);
                break;

            case R.id.linear_iconfont://iconfont图标查找
                Intent intentIconfont = new Intent(this,BannerActivity.class);
                intentIconfont.putExtra("url","https://www.iconfont.cn/");
                intentIconfont.putExtra("title","iconfont");
                startActivity(intentIconfont);
                break;

            case R.id.linear_tinypng://图片压缩
                Intent intentTinypng = new Intent(this,BannerActivity.class);
                intentTinypng.putExtra("url","https://tinypng.com/");
                intentTinypng.putExtra("title","tinypng");
                startActivity(intentTinypng);
                break;

            case R.id.linear_binary_conversion://进制转换
                Intent intentBinary = new Intent(this,BannerActivity.class);
                intentBinary.putExtra("url","https://www.wanandroid.com/tools/decimal");
                intentBinary.putExtra("title","进制转化");
                startActivity(intentBinary);
                break;

            case R.id.linear_short_links://短链接
                Intent intentLinks = new Intent(this,BannerActivity.class);
                intentLinks.putExtra("url","https://dwz.cn/");
                intentLinks.putExtra("title","短链接");
                startActivity(intentLinks);
                break;

            case R.id.linear_quick_table://速查表
                Intent intentTable = new Intent(this,BannerActivity.class);
                intentTable.putExtra("url","https://www.wanandroid.com/article/list/0?cid=398");
                intentTable.putExtra("title","速查表");
                startActivity(intentTable);
                break;

            case R.id.linear_regular_expression://正则表达式
                Intent intentRegular = new Intent(this,BannerActivity.class);
                intentRegular.putExtra("url","http://tool.oschina.net/regex");
                intentRegular.putExtra("title","正则表达式");
                startActivity(intentRegular);
                break;

            case R.id.linear_url://URL按序解析
                Intent intentUrl = new Intent(this,BannerActivity.class);
                intentUrl.putExtra("url","http://t.codekk.com/url-order");
                intentUrl.putExtra("title","URL按序解析");
                startActivity(intentUrl);
                break;

            case R.id.linear_https://https安全检测
                Intent intentHttps = new Intent(this,BannerActivity.class);
                intentHttps.putExtra("url","https://www.upyun.com/https");
                intentHttps.putExtra("title","https安全检测");
                startActivity(intentHttps);
                break;

        }
    }
}
