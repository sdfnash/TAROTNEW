package com.tarot.sdfnash.tarot.registnew.activity;

import android.content.Context;
import android.os.Bundle;
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
import com.tarot.sdfnash.tarot.registnew.Model.CommentShowModel;
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
    private CommentAdapter mAdapter;
    private RadioButton mBtnGood, mBtnMiddle, mBtnBad,mBtnAll;
    private int tID, sID, num =10,haoping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.my_order;
        setToolBar(R.id.toolbar, options);
        mGroupComment = findView(R.id.radio_group_comment);
        mBtnGood = findView(R.id.radio_comment_good);
        mBtnMiddle = findView(R.id.radio_comment_middle);
        mBtnBad = findView(R.id.radio_comment_bad);
        mPagingListView = findView(R.id.listView);
        mListView = mPagingListView.getListView();
        mGroupComment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mBtnGood.getId()) {
                    haoping = 1;
                } else if (checkedId == mBtnMiddle.getId()) {
                    haoping = 2;
                } else if (checkedId == mBtnBad.getId()) {
                    haoping = 3;
                } else {
                    haoping = 1;
                }
            }
        });
        init(savedInstanceState);
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
        mAdapter = new CommentAdapter(OrderActivity.this);
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

    }
    private void loadData(){

    }

    public String timeTrans(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        String date = sdf.format(new Date(time));
        return date;
    }

    public class CommentAdapter extends AbstractAdapter<CommentShowModel> {

        public CommentAdapter(Context context) {
            super(context);
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_comment_list;
        }

        @Override
        public void initView(int position, View convertView, final CommentShowModel item) {
//            HeadImageView headImageView = (HeadImageView) convertView.findViewById(R.id.img_avatar);
            TextView mTvName = (TextView) convertView.findViewById(R.id.tv_order_name);
            TextView mTvTaroter = (TextView) convertView.findViewById(R.id.tv_taroter);
            TextView mTvTarotPrice = (TextView) convertView.findViewById(R.id.tv_tarot_price);
         //   Button mBtnChange = (Button) convertView.findViewById(R.id.btn_good_comment);
            Button mBtnDelete = (Button) convertView.findViewById(R.id.btn_delete_comment);
            TextView mTvTime=(TextView)convertView.findViewById(R.id.tv_order_status);
//            mBtnChange.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(OrderActivity.this, CommitCommentActivity.class);
//                    i.putExtra("tId", tID);
//                    i.putExtra("cmtId", item.getId());
//                    startActivityForResult(i, 0x01);
//                }
//            });
            mBtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //// TODO: 16/8/20



                }
            });
          //  headImageView.loadBuddyAvatar(item.getPhoto_s());



        }
    }
}
