package com.tarot.sdfnash.tarot.registnew.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.sdfnash.uikit.common.activity.UI;
import com.netease.sdfnash.uikit.model.ToolBarOptions;
import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.config.preference.Preferences;
import com.tarot.sdfnash.tarot.registnew.Model.CommentDetailModel;
import com.tarot.sdfnash.tarot.registnew.RegistHttpClient;

public class CommitCommentActivity extends UI implements RatingBar.OnRatingBarChangeListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    public static final String CHANGE_COMMENT = "change";
    public static final String NEW_COMMENT = "new";
    private RadioGroup mGroupComment, mGroupWill;
    private RadioButton mBtnGood, mBtnMiddle, mBtnBad, mBtnWill, mBtnUnWill;
    private EditText mEditComment;
    private Button mBtnCommit;
    private String cmtId;//订单id
    private RatingBar mBarSpeed, mBarHelp, mBarService, mBarRight;
    private int mSpeed, mHelp, mService, mRight;
    private boolean isChange;
    private int o_id;//订单id
    private int haoping=1, want=1;
    String comment,t_id,name;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_comment);
        ToolBarOptions options = new ToolBarOptions();
        setToolBar(R.id.toolbar, options);
        cmtId = getIntent().getStringExtra("cmtId");
        t_id=getIntent().getStringExtra("t_id");
        name=getIntent().getStringExtra("name");
        title=findView(R.id.title_comment);
        if(!TextUtils.isEmpty(name)){
            title.setText("评价"+name+"的服务");
        }

        mGroupComment = findView(R.id.radio_group_comment);
        mGroupWill = findView(R.id.radio_group_will);
        mBtnGood = findView(R.id.radio_comment_good);
        mBtnMiddle = findView(R.id.radio_comment_middle);
        mBtnBad = findView(R.id.radio_comment_bad);
        mBtnWill = findView(R.id.radio_again_will);
        mBtnUnWill = findView(R.id.radio_again_unwill);
        mEditComment = findView(R.id.comment_edit);
        mBarHelp = findView(R.id.ratebar_help);
        mBarRight = findView(R.id.ratebar_right);
        mBarService = findView(R.id.ratebar_service);
        mBarSpeed = findView(R.id.ratebar_speed);
        mBtnCommit = findView(R.id.commit_btn);
        mBarHelp.setStepSize(1);
        mBarRight.setStepSize(1);
        mBarSpeed.setStepSize(1);
        mBarService.setStepSize(1);
        mBarHelp.setIsIndicator(false);
        mBarSpeed.setIsIndicator(false);
        mBarRight.setIsIndicator(false);
        mBarService.setIsIndicator(false);
        mBarRight.setOnRatingBarChangeListener(this);
        mBarSpeed.setOnRatingBarChangeListener(this);
        mBarService.setOnRatingBarChangeListener(this);
        mBarHelp.setOnRatingBarChangeListener(this);
        mBtnCommit.setOnClickListener(this);
        if (CHANGE_COMMENT.equals(getIntent().getStringExtra("type"))) {
            isChange = true;
            RegistHttpClient.getInstance().detialCode(Preferences.getUserId(), Preferences.getUserToken(), Integer.parseInt(cmtId), new RegistHttpClient.DetialHttpCallBack<CommentDetailModel.DataBean>() {
                @Override
                public void onSuccess(CommentDetailModel.DataBean model) {
                    if (want == 0) {
                        mBtnUnWill.setChecked(true);
                    } else if (want == 1) {
                        mBtnWill.setChecked(true);
                    } else {
                        mBtnWill.setChecked(true);
                    }
                    if (!TextUtils.isEmpty(model.getComment())) {
                        mEditComment.setText(model.getComment());
                    }
                    mBarSpeed.setRating(Float.parseFloat(model.getStar1()));
                    mBarService.setRating(Float.parseFloat(model.getStar2()));
                    mBarRight.setRating(Float.parseFloat(model.getStar3()));
                    mBarHelp.setRating(Float.parseFloat(model.getStar4()));
                    mBtnGood.setChecked(true);
                }

                @Override
                public void onFailed(int code, String errorMsg) {

                }
            });
        }
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if (ratingBar.equals(mBarHelp)) {
            mHelp = (int) rating;
        } else if (ratingBar.equals(mBarSpeed)) {
            mSpeed = (int) rating;
        } else if (ratingBar.equals(mBarService)) {
            mService = (int) rating;
        } else if (ratingBar.equals(mBarRight)) {
            mRight = (int) rating;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group.equals(mGroupComment)) {
            if (checkedId == mBtnGood.getId()) {
                haoping = 1;
            } else if (checkedId == mBtnMiddle.getId()) {
                haoping = 2;
            } else if (checkedId == mBtnBad.getId()) {
                haoping = 3;
            } else {
                haoping = 1;
            }
        } else if (group.equals(mGroupWill)) {
            if (checkedId == mBtnWill.getId()) {
                want = 1;
            } else if (checkedId == mBtnUnWill.getId()) {
                want = 0;
            } else {
                want = 1;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnCommit)) {
            if (!TextUtils.isEmpty(mEditComment.getText().toString())) {
                comment = mEditComment.getText().toString();
            }

            if (isChange) {//修改
                RegistHttpClient.getInstance().editCode(Preferences.getUserId(), Preferences.getUserToken()
                        , Integer.parseInt(cmtId), comment, mSpeed, mService, mRight, mHelp, want, haoping, new RegistHttpClient.EditHttpCallBack<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                setResult(Activity.RESULT_OK);
                                finish();
                            }

                            @Override
                            public void onFailed(int code, String errorMsg) {
                                Toast.makeText(CommitCommentActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {//新增
                RegistHttpClient.getInstance().addCode(Preferences.getUserId(), Preferences.getUserToken()
                        ,Integer.parseInt(cmtId), comment, mSpeed, mService, mRight, mHelp, want, haoping, new RegistHttpClient.AddHttpCallBack<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                setResult(Activity.RESULT_OK);
                                finish();
                            }

                            @Override
                            public void onFailed(int code, String errorMsg) {
                                Toast.makeText(CommitCommentActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
    }
}
