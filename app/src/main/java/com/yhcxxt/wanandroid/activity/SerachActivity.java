package com.yhcxxt.wanandroid.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.model.SearchHotWordsDatas;
import com.yhcxxt.wanandroid.model.SearchHotWordsModel;
import com.yhcxxt.wanandroid.model.SearchModel;
import com.yhcxxt.wanandroid.presenter.SearchHotWordsPresenter;
import com.yhcxxt.wanandroid.presenter.SearchPresenter;
import com.yhcxxt.wanandroid.utils.StatusBarUtil;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.view.SearchHotWordsView;
import com.yhcxxt.wanandroid.view.SearchView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 *      author:LHT
 *      time:20190526
 *      desc:搜索 Activity
 * </pre>
 */
public class SerachActivity extends BaseActivity implements View.OnClickListener, SearchHotWordsView{

    private RelativeLayout searchRelative;
    private ImageView backIv;
    private EditText searchEdit;
    private ImageView searchIv;
    private TextView searchTv;
    private NestedScrollView searchScrollView;
    private TagFlowLayout searchHotFlowLayout;
    private ImageView searchClearAllIv;
    private TextView searchHistoryEmptyTv;
    private RecyclerView searchHistoryRecycler;

    private String searchEditText;//搜索内容

    SearchHotWordsPresenter searchHotWordsPresenter;//搜索热词

    private List<SearchHotWordsDatas> searchHotWordsDatasList = new ArrayList<>();
    private String name;//搜索热词
    private List<String> nameList = new ArrayList<>();



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
        setContentView(R.layout.activity_serach);


        searchHotWordsPresenter = new SearchHotWordsPresenter(this);
        initView();
        searchHotWordsPresenter.loadProjectList(this);


    }

    private void initView() {

        searchRelative = (RelativeLayout) findViewById(R.id.searchRelative);
        backIv = (ImageView) findViewById(R.id.backIv);
        searchEdit = (EditText) findViewById(R.id.searchEdit);
        searchIv = (ImageView) findViewById(R.id.searchIv);
        searchTv = (TextView) findViewById(R.id.searchTv);
        searchScrollView = (NestedScrollView) findViewById(R.id.searchScrollView);
        searchHotFlowLayout = (TagFlowLayout) findViewById(R.id.searchHotFlowLayout);
        searchClearAllIv = (ImageView) findViewById(R.id.searchClearAllIv);
        searchHistoryEmptyTv = (TextView) findViewById(R.id.searchHistoryEmptyTv);
        searchHistoryRecycler = (RecyclerView) findViewById(R.id.searchHistoryRecycler);

        backIv.setOnClickListener(this);
        searchTv.setOnClickListener(this);






    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backIv://返回
                finish();
                break;
            case R.id.searchTv://搜索
                searchEditText = searchEdit.getText().toString().trim();
                if (searchEditText.isEmpty()){
                    Utils.showToast(this,"请正确输入！");
                }else {
                    Intent intent = new Intent(this,SearchResultActivity.class);
                    intent.putExtra("content",searchEditText);
                    startActivity(intent);
                }

                break;
        }
    }

    /**
     * 搜索热词
     * @param model
     */
    @Override
    public void getSearchHotWordsData(SearchHotWordsModel model) {

        searchHotWordsDatasList = model.getData();

        for (int i = 0;i<searchHotWordsDatasList.size();i++){

            name = searchHotWordsDatasList.get(i).getName();
            nameList.add(name);
            Log.e("name",name);
        }

        searchHotFlowLayout.setAdapter(new TagAdapter<String>(nameList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                //字体颜色随机
                Random random = new Random();
                int r = random.nextInt(150);
                int g = random.nextInt(150);
                int b = random.nextInt(150);
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.tfl2, parent, false);
                tv.setTextColor(Color.rgb(r, g, b));
                tv.setText(s);


                return tv;

            }

        });

        searchHotFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                Intent intent = new Intent(parent.getContext(), SearchResultActivity.class);
                intent.putExtra("content", searchHotWordsDatasList.get(position).getName());
                parent.getContext().startActivity(intent);
                return false;
            }
        });

    }


}
