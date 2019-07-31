package com.yhcxxt.wanandroid.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gavin.com.library.PowerfulStickyDecoration;
import com.gavin.com.library.listener.OnGroupClickListener;
import com.gavin.com.library.listener.PowerGroupListener;
import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.adapter.SimpleAdapter;
import com.yhcxxt.wanandroid.model.TodoListBean;
import com.yhcxxt.wanandroid.model.TodoListDataBean;
import com.yhcxxt.wanandroid.model.TodoListModel;
import com.yhcxxt.wanandroid.presenter.TodoListPresenter;
import com.yhcxxt.wanandroid.utils.DensityUtil;
import com.yhcxxt.wanandroid.utils.Utils;
import com.yhcxxt.wanandroid.utils.ViewFindUtils;
import com.yhcxxt.wanandroid.view.TodoListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     auhtor:LHT
 *     date:20190719
 *     desc:学习清单  Fragment
 * </pre>
 */
@SuppressLint("ValidFragment")
public class StudyFragment extends Fragment implements TodoListView {

//    RecyclerView mRv;

    RecyclerView.Adapter mAdapter;
    List<TodoListDataBean> todoListDataBeanList = new ArrayList<>();
    List<TodoListBean> todoListBeanList = new ArrayList<>();
    @BindView(R.id.recyclerview)
    RecyclerView mRv;
    Unbinder unbinder;

    private String title;
    private String date;
    List<String> titleList = new ArrayList<>();
    List<String> dateList = new ArrayList<>();

    PowerfulStickyDecoration decoration;

    View v;
    SegmentTabLayout tabLayout_5;
    private String[] mTitles_3 = {"待办", "完成"};

    TodoListPresenter todoListPresenter;

    private String mTitle;

    public static StudyFragment getInstance(String title) {
        StudyFragment sf = new StudyFragment();
//        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_study, null);

        initView();

        todoListPresenter = new TodoListPresenter(this);
        todoListPresenter.loadTodoList(this.getContext(), "0");
        unbinder = ButterKnife.bind(this, v);
        return v;
    }


    private void initView() {
//        mRv = v.findViewById(R.id.rv);

        tabLayout_5 = ViewFindUtils.find(v, R.id.tl_5);
        tabLayout_5.setTabData(mTitles_3);
        tabLayout_5.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Toast.makeText(getContext(), mTitles_3[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    /**
     * Todo列表数据
     *
     * @param model
     */
    @Override
    public void getTodoList(TodoListModel model) {
        if (model.getErrorCode().equals("0")) {
            todoListDataBeanList = model.getData().getTodoList();
            for (int i = 0; i < todoListDataBeanList.size(); i++) {

                todoListBeanList = todoListDataBeanList.get(0).getTodoList();
                for (int x = 0; x < todoListBeanList.size(); x++) {
                    title = todoListBeanList.get(x).getTitle();
                    titleList.add(title);

                    date = todoListBeanList.get(x).getDateStr();
                    dateList.add(date);
                }

            }
            todoListBeanList.addAll(todoListBeanList);

            initRecycler();

        } else {
            Utils.showToast(getContext(), model.getErrorMsg());
        }
    }

    private void initRecycler() {

        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(manager);
        //------------- PowerfulStickyDecoration 使用部分  ----------------
        decoration = PowerfulStickyDecoration.Builder
                .init(new PowerGroupListener() {
                    @Override
                    public String getGroupName(int position) {
                        //获取组名，用于判断是否是同一组
                        if (todoListBeanList.size() > position) {
                            return todoListBeanList.get(position).getDateStr();
                        }
                        return null;
                    }

                    @Override
                    public View getGroupView(int position) {
                        //获取自定定义的组View
                        if (todoListBeanList.size() > position) {
                            final View view = getLayoutInflater().inflate(R.layout.item_time, null, false);
                            ((TextView) view.findViewById(R.id.tv_time)).setText(todoListBeanList.get(position).getDateStr());
//                            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
//                            imageView.setImageResource(todoListBeanList.get(position).getIcon());
                            return view;
                        } else {
                            return null;
                        }
                    }
                })
                .setCacheEnable(true)
                .setGroupHeight(DensityUtil.dip2px(StudyFragment.this.getContext(), 40))
                .setOnClickListener(new OnGroupClickListener() {
                    @Override
                    public void onClick(int position, int id) {
//                        if (todoListBeanList.size() > position) {
//                            //修改数据
//                            changeExpandedState(position);
//                            City city = todoListBeanList.get(position);
//                            //修改悬浮窗
//                            final View view = getLayoutInflater().inflate(R.layout.item_time, null, false);
//                            ((TextView) view.findViewById(R.id.tv_time)).setText(todoListBeanList.get(position).getDateStr());
//                            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
////                            imageView.setImageResource(todoListBeanList.get(position).getIcon());
////                            ImageView ivExpanded = (ImageView) view.findViewById(R.id.iv_expanded);
//                            int rotation = TodoListBean.isExpanded() ? 0 : 180;
//                            ivExpanded.setRotation(rotation);
//                            //修改数据后，刷新指定的悬浮窗
//                            decoration.notifyRedraw(mRv, view, position);
//                            mAdapter.notifyDataSetChanged();
//                        }
                    }
                })
                .build();
        //----------------                 -------------
        //下面是平时的RecyclerView操作
        mRv.addItemDecoration(decoration);
        mAdapter = new SimpleAdapter(this.getContext(), todoListBeanList);
        mRv.setAdapter(mAdapter);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
