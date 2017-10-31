package com.tarot.sdfnash.tarot.registnew.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.sdfnash.uikit.common.activity.UI;
import com.netease.sdfnash.uikit.common.ui.imageview.HeadImageView;
import com.netease.sdfnash.uikit.model.ToolBarOptions;
import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.config.preference.Preferences;
import com.tarot.sdfnash.tarot.registnew.Model.CommentListModel;
import com.tarot.sdfnash.tarot.registnew.Model.CommentShowModel;
import com.tarot.sdfnash.tarot.registnew.RegistHttpClient;
import com.tarot.sdfnash.tarot.registnew.View.DipPixUtil;
import com.tarot.sdfnash.tarot.registnew.View.PagingListView;
import com.tarot.sdfnash.tarot.registnew.adapter.AbstractAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyCommentActivity extends UI {
    private PagingListView mPagingListView;
    private ListView mListView;
    private int mPage = 1;
    private CommentAdapter mAdapter;
    private RadioGroup mGroupComment;
    private RadioButton mBtnGood, mBtnMiddle, mBtnBad,mBtnAll;
    private int tID, sID, num =10,haoping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);
        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.my_comment;
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

        View footView = new View(MyCommentActivity.this);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DipPixUtil.dip2px(MyCommentActivity.this, 20));
        footView.setLayoutParams(params);
        mListView.addFooterView(footView);
        mListView.setDividerHeight(1);
        mAdapter = new CommentAdapter(MyCommentActivity.this);
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
        RegistHttpClient.getInstance().myClist(Preferences.getUserId(), Preferences.getUserToken(), 1, num, new RegistHttpClient.MyClistHttpCallBack<CommentListModel.Data>() {
            @Override
            public void onSuccess(CommentListModel.Data model) {
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
        RegistHttpClient.getInstance().myClist(Preferences.getUserId(), Preferences.getUserToken(), mPage, num, new RegistHttpClient.MyClistHttpCallBack<CommentListModel.Data>() {
            @Override
            public void onSuccess(CommentListModel.Data model) {
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
            HeadImageView headImageView = (HeadImageView) convertView.findViewById(R.id.img_avatar);
            TextView mTvName = (TextView) convertView.findViewById(R.id.tv_nickname);
            TextView mTvComment = (TextView) convertView.findViewById(R.id.tv_comment);
            Button mBtnChange = (Button) convertView.findViewById(R.id.btn_good_comment);
            Button mBtnDelete = (Button) convertView.findViewById(R.id.btn_delete_comment);
            TextView mTvTime=(TextView)convertView.findViewById(R.id.tv_time);
            mBtnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MyCommentActivity.this, CommitCommentActivity.class);
                    i.putExtra("tId", item.getTls_id());
                    i.putExtra("cmtId", item.getId());
                    i.putExtra("type",CommitCommentActivity.CHANGE_COMMENT);
                    startActivityForResult(i, 0x01);
                }
            });
            mBtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //// TODO: 16/8/20
                    RegistHttpClient.getInstance().deleteCode(Preferences.getUserId(), Preferences.getUserToken(), item.getId(), new RegistHttpClient.DeleteHttpCallBack<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Toast.makeText(MyCommentActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                            refreshData();
                        }

                        @Override
                        public void onFailed(int code, String errorMsg) {
                            Toast.makeText(MyCommentActivity.this,errorMsg,Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            });
            headImageView.doLoadImage(true,item.getId(),item.getTls_photo(),HeadImageView.DEFAULT_AVATAR_THUMB_SIZE);
            mTvName.setText(item.getTls_name());
            mTvTime.setText(timeTrans(Long.parseLong(item.getAdd_date())));
            mTvComment.setText(item.getComment());
            if (Preferences.getUserId().equals(item.getU_id())) {
                mBtnChange.setVisibility(View.GONE);
                mBtnDelete.setVisibility(View.GONE);
            } else {
                if(item.getCan_edit()==0){
                    mBtnChange.setVisibility(View.GONE);
                }else if(item.getCan_edit()==1){
                    mBtnChange.setVisibility(View.VISIBLE);
                }

                mBtnDelete.setVisibility(View.VISIBLE);
            }


        }
    }
}
