package com.yhcxxt.wanandroid.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.adapter.BaseRecycleAdapter;
import com.yhcxxt.wanandroid.adapter.RvSearchAdapter;
import com.yhcxxt.wanandroid.adapter.RvSearchHistoryAdapter;
import com.yhcxxt.wanandroid.adapter.SeachRecordAdapter;
import com.yhcxxt.wanandroid.db.DbDao;
import com.yhcxxt.wanandroid.greendao.GreendaoManager;
import com.yhcxxt.wanandroid.greendao.MyApplication;
import com.yhcxxt.wanandroid.greendao.SearchHistory;
import com.yhcxxt.wanandroid.greendao.SearchHistoryDao;
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
public class SerachActivity extends BaseActivity implements View.OnClickListener, SearchHotWordsView {

    private RelativeLayout searchRelative;
    private ImageView backIv;
    private EditText searchEdit;//搜索输入框
    private ImageView searchIv;
    private TextView searchTv;//搜索
    private NestedScrollView searchScrollView;
    private TagFlowLayout searchHotFlowLayout;
    private ImageView searchClearAllIv;//删除所有记录
    private TextView searchHistoryEmptyTv;
    private RecyclerView searchHistoryRecycler;

    SeachRecordAdapter seachRecordAdapter;

    RvSearchHistoryAdapter rvSearchHistoryAdapter;
    List<SearchHistory> searchHistoryList;
    String searchHistoryDate;
    List<SearchHistory> searchHistoryDateList = new ArrayList<>();
    SearchHistory searchHistory;


    private String searchEditText;//搜索内容

    SearchHotWordsPresenter searchHotWordsPresenter;//搜索热词

    private List<SearchHotWordsDatas> searchHotWordsDatasList = new ArrayList<>();
    private String name;//搜索热词
    private List<String> nameList = new ArrayList<>();

    private SearchHistoryDao searchHistoryDao;
    private long id = 0L;
    long i = 0;
    Long userId = null;
    private GreendaoManager<SearchHistory, SearchHistoryDao> greendaoManager;

    View view;


    private SeachRecordAdapter mAdapter;
    private DbDao mDbDao;


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

        view = View.inflate(this, R.layout.activity_serach, null);

        mDbDao = new DbDao(this);

        searchHistoryDao = MyApplication.getInstances().getDaoSession().getSearchHistoryDao();
        //得到该Dao对象的管理器（如果你本地就一张表,GreendaoManager可以写成单例,通过构造方法传参，如果多张表不建议写成单例）
        greendaoManager = new GreendaoManager<>(searchHistoryDao);

        searchHotWordsPresenter = new SearchHotWordsPresenter(this);
        initView();
        searchHotWordsPresenter.loadProjectList(this);


        searchHistoryRecycler = (RecyclerView) findViewById(R.id.searchHistoryRecycler);
        searchHistoryRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SeachRecordAdapter(mDbDao.queryData(""), this);

        mAdapter.setRvItemOnclickListener(new BaseRecycleAdapter.RvItemOnclickListener() {
            @Override
            public void RvItemOnclick(int position) {
                mDbDao.delete(mDbDao.queryData("").get(position));

                mAdapter.updata(mDbDao.queryData(""));


            }
        });
        searchHistoryRecycler.setAdapter(mAdapter);

        //item 点击事件
        mAdapter.setItemOnclickListener(new BaseRecycleAdapter.ItemOnclickListener() {
            @Override
            public void ItemOnclick(int position) {

//                Toast.makeText(mContext, position+":"+ mDbDao.queryData("").get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SerachActivity.this, SearchResultActivity.class);
                intent.putExtra("content", mDbDao.queryData("").get(position));
                startActivity(intent);

            }
        });


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

        ((SimpleItemAnimator) searchHistoryRecycler.getItemAnimator()).setSupportsChangeAnimations(false);

        backIv.setOnClickListener(this);
        searchTv.setOnClickListener(this);
        searchClearAllIv.setOnClickListener(this);

        //搜索历史
        initSearchHistory();


    }

    /**
     * date:20190704
     * desc:搜索历史
     */
    private void initSearchHistory() {

//
//        searchHistoryList = searchHistoryDao.loadAll();
//        String userName = "";
//        for (int i = 0; i < searchHistoryList.size(); i++) {
//            userName = searchHistoryList.get(i).getName();
//            userId = searchHistoryList.get(i).getId();
//
//            SearchHistory searchHistory = new SearchHistory(userId, userName);
//            searchHistoryDateList.add(searchHistory);
//
//        }
//
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        searchHistoryRecycler.setLayoutManager(linearLayoutManager);
//        rvSearchHistoryAdapter = new RvSearchHistoryAdapter(searchHistoryDateList);
//        searchHistoryRecycler.setAdapter(rvSearchHistoryAdapter);
//        searchHistoryRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backIv://返回
                finish();
                break;
            case R.id.searchTv://搜索

//                if (id == 0){
//                    if (userId == null){
//                        id++;
//                    }else {
//                        id = userId +1;
//                    }
//                }else {
//                    id++;
//                }
//
//
                searchEditText = searchEdit.getText().toString().trim();
//
////                SearchHistory searchHistory = new SearchHistory(id,searchEditText);
////                this.greendaoManager.insertOrReplace(searchHistory);
//                searchHistory = new SearchHistory(id, searchEditText);
//                searchHistoryDao.insert(searchHistory);


                if (searchEditText.isEmpty()) {
                    Utils.showToast(this, "请输入内容！");
                } else {
                    Intent intent = new Intent(this, SearchResultActivity.class);
                    intent.putExtra("content", searchEditText);
                    startActivity(intent);
                }

                if (searchEdit.getText().toString().trim().length() != 0) {
                    boolean hasData = mDbDao.hasData(searchEdit.getText().toString().trim());
                    if (!hasData) {
                        mDbDao.insertData(searchEdit.getText().toString().trim());
                    } else {
//                        Toast.makeText(this, "该内容已在历史记录中", Toast.LENGTH_SHORT).show();
                    }

                    //
                    mAdapter.updata(mDbDao.queryData(""));

                } else {
//                    Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.searchClearAllIv://删除所有搜索纪录
//                searchHistoryDao.deleteAll();
                mDbDao.deleteData();
                mAdapter.updata(mDbDao.queryData(""));
                break;
        }
    }

    /**
     * 搜索热词
     *
     * @param model
     */
    @Override
    public void getSearchHotWordsData(SearchHotWordsModel model) {

        searchHotWordsDatasList = model.getData();

        for (int i = 0; i < searchHotWordsDatasList.size(); i++) {

            name = searchHotWordsDatasList.get(i).getName();
            nameList.add(name);
            Log.e("name", name);
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

                //添加到历史纪录
                boolean hasData = mDbDao.hasData(searchHotWordsDatasList.get(position).getName());
                if (!hasData) {
                    mDbDao.insertData(searchHotWordsDatasList.get(position).getName());
                } else {
//                        Toast.makeText(this, "该内容已在历史记录中", Toast.LENGTH_SHORT).show();
                }

                mAdapter.updata(mDbDao.queryData(""));


                Intent intent = new Intent(parent.getContext(), SearchResultActivity.class);
                intent.putExtra("content", searchHotWordsDatasList.get(position).getName());
                parent.getContext().startActivity(intent);
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                initSearchHistory();
            }
        });

    }

}
