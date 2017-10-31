package com.tarot.sdfnash.tarot.registnew.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.netease.sdfnash.uikit.common.activity.UI;
import com.netease.sdfnash.uikit.model.ToolBarOptions;
import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.config.preference.Preferences;
import com.tarot.sdfnash.tarot.registnew.Model.OrderListModel;
import com.tarot.sdfnash.tarot.registnew.RegistHttpClient;
import com.tarot.sdfnash.tarot.registnew.View.DipPixUtil;
import com.tarot.sdfnash.tarot.registnew.View.PagingListView;
import com.tarot.sdfnash.tarot.registnew.adapter.AbstractAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderActivity extends UI {
    private PagingListView mPagingListView;
    private ListView mListView;
    private int mPage = 1;
    private RadioGroup mGroupComment;
    private OrderAdapter mAdapter;
    private RadioButton mBtnGood, mBtnMiddle, mBtnBad,mBtnAll;
    private int tID, sID, num =10,haoping=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_order);
            ToolBarOptions options = new ToolBarOptions();
            options.titleId = R.string.my_order;
            setToolBar(R.id.toolbar, options);
            mGroupComment = findView(R.id.radio_group_comment);
            mBtnAll = findView(R.id.radio_comment_all);
            mBtnGood = findView(R.id.radio_comment_good);
            mBtnMiddle = findView(R.id.radio_comment_middle);
            mBtnBad = findView(R.id.radio_comment_bad);
            mPagingListView = findView(R.id.listView);
            mListView = mPagingListView.getListView();
            mGroupComment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == mBtnGood.getId()) {//paying
                        haoping = 3;
                    } else if (checkedId == mBtnMiddle.getId()) {//no comment
                        haoping = 2;
                    } else if (checkedId == mBtnBad.getId()) {//has commented
                        haoping = 1;
                    } else if (checkedId == mBtnAll.getId()) {//all
                        haoping = 0;
                    }
                    refreshData();
                }
            });
            init(savedInstanceState);
        }catch (Exception e){
            Log.e("sdfnash",e.toString());
        }
    }

    private void init(Bundle savedInstanceState) {
        //initEmptyLayout();

        initListView();

        if (mAdapter.isEmpty()) {
//            showLoadingDialog();
            refreshData();
        } else {
            mListView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPagingListView.setRefreshing(true);
                    refreshData();
                }
            }, 500);
        }
    }

    private void initListView() {
        mPagingListView.setShowEmptyView(false);

//        TextView header = new TextView(this);
//        header.setText("asdfjkl;");

        View footView = new View(OrderActivity.this);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DipPixUtil.dip2px(OrderActivity.this, 20));
        footView.setLayoutParams(params);
        mListView.addFooterView(footView);
        mListView.setDividerHeight(1);
        mAdapter = new OrderAdapter(OrderActivity.this);
//        mAdapter.setData(mListManager.getList());
        mListView.setAdapter(mAdapter);

        mPagingListView.setDataListener(new PagingListView.DataListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }

            @Override
            public void onLoading() {
                loadData();
            }
        });

        mPagingListView.hiddenFooterView();
    }

    private void refreshData(){
        RegistHttpClient.getInstance().getOrderList(Preferences.getUserId(), Preferences.getUserToken(), haoping, 1, 10, new RegistHttpClient.OrderListHttpCallBack<OrderListModel.DataBean>() {
            @Override
            public void onSuccess(OrderListModel.DataBean model) {
                mAdapter.setData(model.getList());
                mAdapter.notifyDataSetChanged();
                mPagingListView.setRefreshing(false);
                if (model.getPage() < model.getTotal()) {
                    mPage++;
                    mPagingListView.showFooterView();
                    mPagingListView.setLoadingEnable(true);
                } else {
                    mPagingListView.hiddenFooterView();
                    mPagingListView.setLoadingEnable(false);
                }
            }

            @Override
            public void onFailed(int code, String errorMsg) {

            }
        });
    }
    private void loadData(){
        RegistHttpClient.getInstance().getOrderList(Preferences.getUserId(), Preferences.getUserToken(), haoping, mPage, 10, new RegistHttpClient.OrderListHttpCallBack<OrderListModel.DataBean>() {
            @Override
            public void onSuccess(OrderListModel.DataBean model) {
                mAdapter.addData(model.getList());
                mAdapter.notifyDataSetChanged();
                mPagingListView.setRefreshing(false);
                if (model.getPage() < model.getTotal()) {
                    mPage++;
                    mPagingListView.showFooterView();
                    mPagingListView.setLoadingEnable(true);
                } else {
                    mPagingListView.hiddenFooterView();
                    mPagingListView.setLoadingEnable(false);
                }
            }

            @Override
            public void onFailed(int code, String errorMsg) {

            }
        });
    }

    public String timeTrans(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = sdf.format(new Date(time*1000));
        return date;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==0x01){
                refreshData();
            }
        }
    }

    public class OrderAdapter extends AbstractAdapter<OrderListModel.DataBean.ListBean> {

        public OrderAdapter(Context context) {
            super(context);
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_order_list;
        }

        @Override
        public void initView(int position, View convertView, final OrderListModel.DataBean.ListBean item) {
//            HeadImageView headImageView = (HeadImageView) convertView.findViewById(R.id.img_avatar);
            try {
                TextView mTvName = (TextView) convertView.findViewById(R.id.tv_order_name);
                TextView mTvTaroter = (TextView) convertView.findViewById(R.id.tv_taroter);
                TextView mTvTarotPrice = (TextView) convertView.findViewById(R.id.tv_tarot_price);
                //   Button mBtnChange = (Button) convertView.findViewById(R.id.btn_good_comment);
                Button mBtnDelete = (Button) convertView.findViewById(R.id.btn_delete_comment);
                TextView mTvTime = (TextView) convertView.findViewById(R.id.tv_order_status);
                if (!TextUtils.isEmpty(item.getService())) {
                    mTvName.setText("服务项目：" + item.getService());
                }
                if (!TextUtils.isEmpty(item.getUser_name())) {
                    mTvTaroter.setText("用户：" + item.getUser_name());
                }

                mTvTarotPrice.setText("价格：" + String.valueOf(item.getAmount()));
                mTvTime.setText(timeTrans(Long.parseLong(item.getStatus_date())));
                if (Integer.parseInt(item.getStatus()) == 9) {//finish
                    if(item.getIs_comment()!=null){
                        if (Integer.parseInt(item.getIs_comment()) == 0) {
                            mBtnDelete.setVisibility(View.VISIBLE);
                            mBtnDelete.setText("待评价");
                        } else {
                            mBtnDelete.setVisibility(View.GONE);
                        }
                    }else {
                        mBtnDelete.setVisibility(View.GONE);
                    }

                } else if (Integer.parseInt(item.getStatus()) == 0) {//paying
                    mBtnDelete.setVisibility(View.VISIBLE);
                    mBtnDelete.setText("待支付");
                }

//            mBtnChange.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(OrderActivity.this, CommitCommentActivity.class);
//                    i.putExtra("tId", tID);
//                    i.putExtra("cmtId", item.getId());
//                    startActivityForResult(i, 0x01);
//                }
//            });
//            mBtnDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(Integer.parseInt(item.getStatus())==2){//finish
//
//                    }else if(Integer.parseInt(item.getStatus())==0){//paying
//                        Intent i=new Intent(OrderActivity.this,ClientSDKActivity.class);
//                        i.putExtra("orderId",item.getId());
//                        i.putExtra("price",item.getAmount());
//                        i.putExtra("tId",item.getT_id());
//                        i.putExtra("service",item.getService());
//                        i.putExtra("name",item.getTls_name());
//                        i.putExtra("time",timeTrans(Long.parseLong(item.getStatus_date())));
//                        startActivity(i);
//                    }else if(Integer.parseInt(item.getStatus())==9){//comment will
//                        Intent i = new Intent(OrderActivity.this, CommitCommentActivity.class);
//                       //// TODO: 2016/9/4 need tls ID
//                        i.putExtra("tId", item.getT_id());
//                        i.putExtra("name",item.getTls_name());
//                        i.putExtra("cmtId", item.getId());
//                        i.putExtra("type",CommitCommentActivity.NEW_COMMENT);
//                        startActivityForResult(i,0x01);
//                    }
////                    else if(Integer.parseInt(item.getIs_comment())==4){//change comment
////                        Intent i = new Intent(OrderActivity.this, CommitCommentActivity.class);
////                        i.putExtra("tId", item.getTls_name());
////                        i.putExtra("cmtId", item.getId());
////                        i.putExtra("type",CommitCommentActivity.NEW_COMMENT);
////                        startActivityForResult(i,0x01);
////                    }
//
//
//
//                }
//            });
                //  headImageView.loadBuddyAvatar(item.getPhoto_s());


            }catch (Exception e){
                Log.e("viewsdfnash",e.toString());
            }
        }
    }
}
