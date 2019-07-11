package com.yhcxxt.wanandroid.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yhcxxt.wanandroid.R;

import java.util.List;


/**
 * Created by yi.huangxing on 17/12/13.类描述:
 */

public class SeachRecordAdapter extends BaseRecycleAdapter<String> {
    public SeachRecordAdapter(List<String> datas, Context mContext) {
        super(datas, mContext);
    }

    @Override
    protected void bindData(BaseViewHolder holder, final int position) {

        TextView textView= (TextView) holder.getView(R.id.historyTv);
        RelativeLayout rl = (RelativeLayout) holder.getView(R.id.rl);
        textView.setText(datas.get(position));
        
        //
        holder.getView(R.id.deleteIv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null!=mRvItemOnclickListener){
                    mRvItemOnclickListener.RvItemOnclick(position);
                }
            }
        });

        holder.getView(R.id.rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=mItemOnclickListener){
                    mItemOnclickListener.ItemOnclick(position);
                }
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_search_history;
    }
}
