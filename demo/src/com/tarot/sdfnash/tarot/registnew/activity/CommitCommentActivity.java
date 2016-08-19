package com.tarot.sdfnash.tarot.registnew.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import com.netease.sdfnash.uikit.common.activity.UI;
import com.netease.sdfnash.uikit.model.ToolBarOptions;
import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.registnew.Model.CommentDetailModel;
import com.tarot.sdfnash.tarot.registnew.RegistHttpClient;

public class CommitCommentActivity extends UI implements RatingBar.OnRatingBarChangeListener,View.OnClickListener{
    public static final String CHANGE_COMMENT="change";
    public static final String NEW_COMMENT="new";
    private RadioGroup mGroupComment,mGroupWill;
    private RadioButton mBtnGood,mBtnMiddle,mBtnBad,mBtnWill,mBtnUnWill;
    private EditText mEditComment;
    private Button mBtnCommit;
    private String uid,ticket,cmtId;
    private RatingBar mBarSpeed,mBarHelp,mBarService,mBarRight;
    private float mSpeed,mHelp,mService,mRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_comment);
        ToolBarOptions options = new ToolBarOptions();
        setToolBar(R.id.toolbar, options);
        mGroupComment=findView(R.id.radio_comment_good);
        mGroupWill=findView(R.id.radio_group_will);
        mBtnGood=findView(R.id.radio_comment_good);
        mBtnMiddle=findView(R.id.radio_comment_middle);
        mBtnBad=findView(R.id.radio_comment_bad);
        mBtnWill=findView(R.id.radio_again_will);
        mBtnUnWill=findView(R.id.radio_again_unwill);
        mEditComment=findView(R.id.comment_edit);
        mBarHelp=findView(R.id.ratebar_help);
        mBarRight=findView(R.id.ratebar_right);
        mBarService=findView(R.id.ratebar_service);
        mBarSpeed=findView(R.id.ratebar_speed);
        mBtnCommit=findView(R.id.commit_btn);
        mBarHelp.setStepSize(1);
        mBarRight.setStepSize(1);
        mBarSpeed.setStepSize(1);
        mBarService.setStepSize(1);
        mBarRight.setOnRatingBarChangeListener(this);
        mBarSpeed.setOnRatingBarChangeListener(this);
        mBarService.setOnRatingBarChangeListener(this);
        mBarHelp.setOnRatingBarChangeListener(this);
        mBtnCommit.setOnClickListener(this);
        if(CHANGE_COMMENT.equals(getIntent().getStringExtra("type"))){
            RegistHttpClient.getInstance().detialCode(Integer.parseInt(uid), ticket, Integer.parseInt(cmtId), new RegistHttpClient.DeleteHttpCallBack<CommentDetailModel.DataBean>() {
                @Override
                public void onSuccess(CommentDetailModel.DataBean model) {

                }

                @Override
                public void onFailed(int code, String errorMsg) {

                }
            });
        }
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if(ratingBar.equals(mBarHelp)){
            mHelp=rating;
        }else if(ratingBar.equals(mBarSpeed)){
            mSpeed=rating;
        }else if(ratingBar.equals(mBarService)){
            mService=rating;
        }else if(ratingBar.equals(mBarRight)){
            mRight=rating;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBtnCommit)){

        }
    }
}
