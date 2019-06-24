package com.yhcxxt.wanandroid.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.superluo.textbannerlibrary.ITextBannerItemClickListener;
import com.superluo.textbannerlibrary.TextBannerView;
import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.activity.BannerActivity;
import com.yhcxxt.wanandroid.adapter.RvProjectClassificationAdapter;
import com.yhcxxt.wanandroid.model.BannerBean;
import com.yhcxxt.wanandroid.model.BannerModel;
import com.yhcxxt.wanandroid.model.ProjectClassificationDatas;
import com.yhcxxt.wanandroid.model.ProjectClassificationModel;
import com.yhcxxt.wanandroid.model.ProjectListModel;
import com.yhcxxt.wanandroid.presenter.BannerPresenter;
import com.yhcxxt.wanandroid.presenter.ProjectClassificationPresenter;
import com.yhcxxt.wanandroid.presenter.ProjectListPresenter;
import com.yhcxxt.wanandroid.view.BannerView;
import com.yhcxxt.wanandroid.view.ProjectClassificationView;
import com.yhcxxt.wanandroid.view.ProjectListView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      author:LHT
 *      time:20190523
 *      desc:项目 Fragment
 * </pre>
 */
public class ProjectFragment2 extends Fragment implements BannerView, ProjectClassificationView, ProjectListView {

    private TextBannerView tvBanner;
    //设置数据
    List<String> list = new ArrayList<>();
    List<BannerBean> bannerDatas;

    BannerPresenter bannerPresenter;
    private String desc;//Banner 标题内容
    private String bannerUrl;
    private List<String> descList = new ArrayList<>();
    private List<String> bannerUrlList = new ArrayList<>();

    private RecyclerView recyclerView;

    private List<ProjectClassificationDatas> projectClassificationDatasList = new ArrayList<>();

    private RvProjectClassificationAdapter projectClassificationAdapter;
    ProjectClassificationPresenter projectClassificationPresenter;//项目分类
    private String name;//项目分类名称
    private String id;
    private List<String> nameList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();

    private ProjectClassificationDatas projectClassificationDatas;

    private ProjectListPresenter projectListPresenter;//项目列表

    private RecyclerView recRight;
    private Context mContext;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_project2, container, false);

        mContext = this.getContext();

        recyclerView = view.findViewById(R.id.rec_left);

        //初始化TextBannerView
        tvBanner = view.findViewById(R.id.tv_banner);

        bannerPresenter = new BannerPresenter(this);
        bannerPresenter.loadBanner(getContext());

//        initFloat();

        addData();

        recRight = view.findViewById(R.id.rec_right);

        initData1();

//        initLeft();
//        initRight();


        return view;
    }

    //获取数据(若请求服务端数据,请求到的列表需有序排列)
    private void initData1() {

    }

    private void addData() {

        projectClassificationPresenter = new ProjectClassificationPresenter(this);
        projectClassificationPresenter.loadProjectClassification(this.getContext());

        projectListPresenter = new ProjectListPresenter(this);


    }

    private void initFloat() {
        /**
         * date:20190430
         * explain: float 和 double 在存储的时候，存在精度损失的问题，很可能在值的比较时，得到不正确的结果。
         * address:https://blog.csdn.net/u011936655/article/details/51959969
         */
        double a = 0.58;
        double b = 0.58 * 100;

        //(0.5+0.08)*100  两个不能转换的会出现问题
        double a1 = 0.58;
        double b1 = 0.1;
        double c1 = (a1 + b1) * 100;

        int a2 = 50;
        int b2 = 8;
        int c2 = (a2 + b2) * 100 / 100;


        //加法 add()函数     减法substract()函数
        //乘法multipy()函数    除法divide()函数    绝对值abs()函数
        BigDecimal bigDecimal1 = new BigDecimal(Double.toString(0.58));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(100));


//        tv.setText(b+"");
        Log.e("b", b + "");
        Log.e("c", c1 + "");
        Log.e("c2", c2 + "");
        Log.e("bigD", bigDecimal1.multiply(bigDecimal2) + "");

    }

    private void initData() {

//        list.add("学好Java、Android、C#、C、ios、html+css+js");
//        list.add("走遍天下都不怕！！！！！");
//        list.add("不是我吹，就怕你做不到，哈哈");
//        list.add("superluo");
//        list.add("你是最棒的，奔跑吧孩子！");


//调用setDatas(List<String>)方法后,TextBannerView自动开始轮播
//注意：此方法目前只接受List<String>类型
//        tvBanner.setDatas(list);
        tvBanner.setDatas(descList);

//
//        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
/**这里可以设置带图标的数据（1.0.2新方法），比setDatas方法多了带图标参数；
 第一个参数：数据 。
 第二参数：drawable.
 第三参数:drawable尺寸。
 第四参数:图标位置仅支持Gravity.LEFT、Gravity.TOP、Gravity.RIGHT、Gravity.BOTTOM
 */
//        tvBanner.setDatasWithDrawableIcon(list, drawable, 18, Gravity.LEFT);


//设置TextBannerView点击监听事件，返回点击的data数据, 和position位置
        tvBanner.setItemOnClickListener(new ITextBannerItemClickListener() {
            @Override
            public void onItemClick(String data, int position) {
//                Log.i("点击了：", String.valueOf(position) + ">>" + data);
//                Toast.makeText(getContext(), String.valueOf(position) + ">>" + data, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), BannerActivity.class);
                intent.putExtra("url", bannerUrlList.get(position));
                intent.putExtra("title", descList.get(position));
                startActivity(intent);
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        tvBanner.startViewAnimator();
    }

    @Override
    public void onStop() {
        super.onStop();
        tvBanner.stopViewAnimator();
    }

    @Override
    public void getBannerData(BannerModel model) {
        bannerDatas = model.getData();
        for (int i = 0; i < bannerDatas.size(); i++) {

            desc = model.getData().get(i).getTitle();
            descList.add(desc);

            bannerUrl = model.getData().get(i).getUrl();
            bannerUrlList.add(bannerUrl);

        }

        initData();
    }

    /**
     * 获取 项目分类 数据
     * @param model
     */
    @Override
    public void getProjectClassificationData(ProjectClassificationModel model) {
        projectClassificationDatasList = model.getData();
        for (int i = 0; i<projectClassificationDatasList.size();i++){
            name = model.getData().get(i).getName();
            nameList.add(name);
            id = model.getData().get(i).getId();
            idList.add(id);
            Log.e("projectclass",name +"........"+id);
        }

        projectClassificationAdapter = new RvProjectClassificationAdapter(projectClassificationDatasList);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(), LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        //添加分割线-----刷新会导致重复添加
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(projectClassificationAdapter);




    }

    /**
     * 获得资源 dimens (dp)
     *
     * @param context
     * @param id      资源id
     * @return
     */
    public float getDimens(Context context, int id) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float px = context.getResources().getDimension(id);
        return px / dm.density;
    }

    /**
     * 项目列表
     * @param model
     */
    @Override
    public void getProjectListData(ProjectListModel model) {

    }
}
