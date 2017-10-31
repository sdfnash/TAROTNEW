package com.tarot.sdfnash.tarot.registnew.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.sdfnash.uikit.common.activity.UI;
import com.netease.sdfnash.uikit.common.ui.imageview.HeadImageView;
import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.config.preference.Preferences;
import com.tarot.sdfnash.tarot.registnew.Model.TarotHomePageModel;
import com.tarot.sdfnash.tarot.registnew.RegistHttpClient;
import com.tarot.sdfnash.tarot.session.SessionHelper;

import java.util.List;

public class TarotActivity extends UI implements View.OnClickListener {

    private HeadImageView mImgAvatar;
    private TextView mTvName, mTvNoticeNum;
    private ImageView mImgStar;
    private Button mBtnNotice;
    private RatingBar mRateSpeed, mRateRight, mRateHelp, mRateAttitude;
    private TextView mTvSpeed, mTvHelp, mTvRight, mTvAttitude, mTvInsult;
    private TextView mTvPercent, mTvGood, mTvMiddle, mTvBad;
    private String tls_accid, t_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarot);
        mTvSpeed = findView(R.id.tv_point_speed);
        mTvAttitude = findView(R.id.tv_point_attitude);
        mTvHelp = findView(R.id.tv_point_help);
        mTvRight = findView(R.id.tv_point_right);
        mRateSpeed = findView(R.id.speed_bar);
        mRateAttitude = findView(R.id.attitude_bar);
        mRateHelp = findView(R.id.help_bar);
        mRateRight = findView(R.id.right_bar);
        mRateRight.setIsIndicator(false);
        mRateHelp.setIsIndicator(false);
        mRateAttitude.setIsIndicator(false);
        mRateSpeed.setIsIndicator(false);
        mTvPercent = findView(R.id.tv_percent);
        mTvGood = findView(R.id.tv_good_comment);
        mTvMiddle = findView(R.id.tv_middle_comment);
        mTvBad = findView(R.id.tv_bad_comment);
        mImgAvatar = findView(R.id.setting_tarot_avatar);
        mTvName = findView(R.id.setting_name);

        mImgStar = findView(R.id.img_star);
        mBtnNotice = findView(R.id.btn_good_comment);
        mBtnNotice.setOnClickListener(this);
        mTvInsult = findView(R.id.btn_insult);
        mTvInsult.setOnClickListener(this);
        mTvNoticeNum = findView(R.id.tv_num);
        initService();
        initComment();
        tls_accid = getIntent().getStringExtra("tls_accid");
        t_id = getIntent().getStringExtra("t_id");
        RegistHttpClient.getInstance().getTarotHomePage(t_id, new RegistHttpClient.GetTarotHomePageHttpCallBack<TarotHomePageModel.Data>() {
            @Override
            public void onSuccess(TarotHomePageModel.Data data) {
                mTvGood.setText(data.getInfo().getFans());
                mTvMiddle.setText(data.getInfo().getTotal_count());
                mTvBad.setText(data.getInfo().getTotal_sold());
                mTvPercent.setText(data.getInfo().getHaopinglv()+"%");

                mTvSpeed.setText(String.valueOf(data.getInfo().getAvg_star1()));
                mTvAttitude.setText(String.valueOf(data.getInfo().getAvg_star2()));
                mTvRight.setText(String.valueOf(data.getInfo().getAvg_star3()));
                mTvHelp.setText(String.valueOf(data.getInfo().getAvg_star4()));

                mRateSpeed.setRating(Float.parseFloat(data.getInfo().getAvg_star1()));
                mRateAttitude.setRating(Float.parseFloat(data.getInfo().getAvg_star2()));
                mRateRight.setRating(Float.parseFloat(data.getInfo().getAvg_star3()));
                mRateHelp.setRating(Float.parseFloat(data.getInfo().getAvg_star4()));
                mImgAvatar.doLoadImage(true, "homeHeader", data.getInfo().getTls_photo(), HeadImageView.DEFAULT_AVATAR_THUMB_SIZE);
                mTvName.setText(data.getInfo().getTls_nickname());
                if (Double.parseDouble(data.getInfo().getAvg_star()) >= 0 && Double.parseDouble(data.getInfo().getAvg_star()) < 2) {
                    mImgStar.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_01));
                } else if (Double.parseDouble(data.getInfo().getAvg_star()) >= 2 && Double.parseDouble(data.getInfo().getAvg_star()) < 3) {
                    mImgStar.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_02));
                } else if (Double.parseDouble(data.getInfo().getAvg_star()) >= 3 && Double.parseDouble(data.getInfo().getAvg_star()) < 4) {
                    mImgStar.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_03));
                } else if (Double.parseDouble(data.getInfo().getAvg_star()) >= 4 && Double.parseDouble(data.getInfo().getAvg_star()) < 5) {
                    mImgStar.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_04));
                } else if (Double.parseDouble(data.getInfo().getAvg_star()) == 5) {
                    mImgStar.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_05));
                }
                mTvNoticeNum.setText("已关注：" + data.getInfo().getFans());
                loadService(data.getServicelist());
                loadComment(data.getCommentlist());
            }

            @Override
            public void onFailed(int code, String errorMsg) {

            }
        });

    }

    private RelativeLayout mLayoutS1, mLayoutS2, mLayoutS3, mLayoutS4;
    private TextView mTvSName1, mTvSName2, mTvSName3, mTvSName4;
    private TextView mTvSDese1, mTvSDese2, mTvSDese3, mTvSDese4;
    private TextView mTvSPrice1, mTvSPrice2, mTvSPrice3, mTvSPrice4;
    private TextView mTvSGood1, mTvSGood2, mTvSGood3, mTvSGood4;
    private TextView mTvSMore;

    private void initService() {
        mLayoutS1 = findView(R.id.item_service_1);
        mLayoutS2 = findView(R.id.item_service_2);
        mLayoutS3 = findView(R.id.item_service_3);
        mLayoutS4 = findView(R.id.item_service_4);
        mTvSName1 = (TextView) mLayoutS1.findViewById(R.id.tv_name);
        mTvSName2 = (TextView) mLayoutS2.findViewById(R.id.tv_name);
        mTvSName3 = (TextView) mLayoutS3.findViewById(R.id.tv_name);
        mTvSName4 = (TextView) mLayoutS4.findViewById(R.id.tv_name);
        mTvSDese1 = (TextView) mLayoutS1.findViewById(R.id.tv_des);
        mTvSDese2 = (TextView) mLayoutS2.findViewById(R.id.tv_des);
        mTvSDese3 = (TextView) mLayoutS3.findViewById(R.id.tv_des);
        mTvSDese4 = (TextView) mLayoutS4.findViewById(R.id.tv_des);
        mTvSPrice1 = (TextView) mLayoutS1.findViewById(R.id.tv_price);
        mTvSPrice2 = (TextView) mLayoutS2.findViewById(R.id.tv_price);
        mTvSPrice3 = (TextView) mLayoutS3.findViewById(R.id.tv_price);
        mTvSPrice4 = (TextView) mLayoutS4.findViewById(R.id.tv_price);
        mTvSGood1 = (TextView) mLayoutS1.findViewById(R.id.tv_good);
        mTvSGood2 = (TextView) mLayoutS2.findViewById(R.id.tv_good);
        mTvSGood3 = (TextView) mLayoutS3.findViewById(R.id.tv_good);
        mTvSGood4 = (TextView) mLayoutS4.findViewById(R.id.tv_good);
        mTvSMore = findView(R.id.more_service);
        mTvSMore.setOnClickListener(this);
    }

    private void loadService(List<TarotHomePageModel.Data.Servicelist> list) {
        if (list != null) {
            switch (list.size()) {
                case 0: {
                    mLayoutS1.setVisibility(View.GONE);
                    mLayoutS2.setVisibility(View.GONE);
                    mLayoutS3.setVisibility(View.GONE);
                    mLayoutS4.setVisibility(View.GONE);
                    break;
                }
                case 1: {
                    mLayoutS1.setVisibility(View.VISIBLE);
                    mLayoutS2.setVisibility(View.GONE);
                    mLayoutS3.setVisibility(View.GONE);
                    mLayoutS4.setVisibility(View.GONE);
                    setServiceStatus(mTvSName1, mTvSGood1, mTvSPrice1, mTvSDese1, list.get(0));
                    break;
                }
                case 2: {
                    mLayoutS1.setVisibility(View.VISIBLE);
                    mLayoutS2.setVisibility(View.VISIBLE);
                    mLayoutS3.setVisibility(View.GONE);
                    mLayoutS4.setVisibility(View.GONE);
                    setServiceStatus(mTvSName1, mTvSGood1, mTvSPrice1, mTvSDese1, list.get(0));
                    setServiceStatus(mTvSName2, mTvSGood2, mTvSPrice2, mTvSDese2, list.get(1));
                    break;
                }
                case 3: {
                    mLayoutS1.setVisibility(View.VISIBLE);
                    mLayoutS2.setVisibility(View.VISIBLE);
                    mLayoutS3.setVisibility(View.VISIBLE);
                    mLayoutS4.setVisibility(View.GONE);
                    setServiceStatus(mTvSName1, mTvSGood1, mTvSPrice1, mTvSDese1, list.get(0));
                    setServiceStatus(mTvSName2, mTvSGood2, mTvSPrice2, mTvSDese2, list.get(1));
                    setServiceStatus(mTvSName3, mTvSGood3, mTvSPrice3, mTvSDese3, list.get(2));
                    break;
                }
                case 4: {
                    mLayoutS1.setVisibility(View.VISIBLE);
                    mLayoutS2.setVisibility(View.VISIBLE);
                    mLayoutS3.setVisibility(View.VISIBLE);
                    mLayoutS4.setVisibility(View.VISIBLE);
                    setServiceStatus(mTvSName1, mTvSGood1, mTvSPrice1, mTvSDese1, list.get(0));
                    setServiceStatus(mTvSName2, mTvSGood2, mTvSPrice2, mTvSDese2, list.get(1));
                    setServiceStatus(mTvSName3, mTvSGood3, mTvSPrice3, mTvSDese3, list.get(2));
                    setServiceStatus(mTvSName4, mTvSGood4, mTvSPrice4, mTvSDese4, list.get(3));
                    break;
                }


            }
        }else {
            mLayoutS1.setVisibility(View.GONE);
            mLayoutS2.setVisibility(View.GONE);
            mLayoutS3.setVisibility(View.GONE);
            mLayoutS4.setVisibility(View.GONE);
        }
    }

    private void setServiceStatus(TextView mTvName, TextView mTvGood, TextView mTvPrice, TextView mTvDes, TarotHomePageModel.Data.Servicelist list) {
        if (!TextUtils.isEmpty(list.getGood_count())) {
            mTvGood.setText("近77天好评数:" + list.getGood_count());
        }
        mTvPrice.setText(list.getPrice());
        mTvDes.setText(list.getDescription());
        mTvName.setText(list.getName());
    }

    private RelativeLayout mLayoutC1,mLayoutC2,mLayoutC3,mLayoutC4;
    private TextView mTvCName1, mTvCName2, mTvCName3, mTvCName4;
    private TextView mTvCDeCe1, mTvCDeCe2, mTvCDeCe3, mTvCDeCe4;
    private TextView mTvCPrice1, mTvCPrice2, mTvCPrice3, mTvCPrice4;
    private HeadImageView mTvCGood1, mTvCGood2, mTvCGood3, mTvCGood4;
    private TextView mTvCMore;
    private void initComment() {
        mLayoutC1 = findView(R.id.item_comment_1);
        mLayoutC2 = findView(R.id.item_comment_2);
        mLayoutC3 = findView(R.id.item_comment_3);
        mLayoutC4 = findView(R.id.item_comment_4);
        mTvCName1 = (TextView) mLayoutC1.findViewById(R.id.tv_nickname);
        mTvCName2 = (TextView) mLayoutC2.findViewById(R.id.tv_nickname);
        mTvCName3 = (TextView) mLayoutC3.findViewById(R.id.tv_nickname);
        mTvCName4 = (TextView) mLayoutC4.findViewById(R.id.tv_nickname);
        mTvCDeCe1 = (TextView) mLayoutC1.findViewById(R.id.tv_time);
        mTvCDeCe2 = (TextView) mLayoutC2.findViewById(R.id.tv_time);
        mTvCDeCe3 = (TextView) mLayoutC3.findViewById(R.id.tv_time);
        mTvCDeCe4 = (TextView) mLayoutC4.findViewById(R.id.tv_time);
        mTvCPrice1 = (TextView) mLayoutC1.findViewById(R.id.tv_comment);
        mTvCPrice2 = (TextView) mLayoutC2.findViewById(R.id.tv_comment);
        mTvCPrice3 = (TextView) mLayoutC3.findViewById(R.id.tv_comment);
        mTvCPrice4 = (TextView) mLayoutC4.findViewById(R.id.tv_comment);
        mTvCGood1 = (HeadImageView) mLayoutC1.findViewById(R.id.img_avatar);
        mTvCGood2 = (HeadImageView) mLayoutC2.findViewById(R.id.img_avatar);
        mTvCGood3 = (HeadImageView) mLayoutC3.findViewById(R.id.img_avatar);
        mTvCGood4 = (HeadImageView) mLayoutC4.findViewById(R.id.img_avatar);
        mTvCMore = findView(R.id.more_comment);
        mTvCMore.setOnClickListener(this);
    }
    private void loadComment(List<TarotHomePageModel.Data.Commentlist> list) {
        if (list != null) {
            switch (list.size()) {
                case 0: {
                    mLayoutC1.setVisibility(View.GONE);
                    mLayoutC2.setVisibility(View.GONE);
                    mLayoutC3.setVisibility(View.GONE);
                    mLayoutC4.setVisibility(View.GONE);
                    break;
                }
                case 1: {
                    mLayoutC1.setVisibility(View.VISIBLE);
                    mLayoutC2.setVisibility(View.GONE);
                    mLayoutC3.setVisibility(View.GONE);
                    mLayoutC4.setVisibility(View.GONE);
                    setCommentStatus(mTvCName1, mTvCGood1, mTvCPrice1, mTvCDeCe1, list.get(0));
                    break;
                }
                case 2: {
                    mLayoutC1.setVisibility(View.VISIBLE);
                    mLayoutC2.setVisibility(View.VISIBLE);
                    mLayoutC3.setVisibility(View.GONE);
                    mLayoutC4.setVisibility(View.GONE);
                    setCommentStatus(mTvCName1, mTvCGood1, mTvCPrice1, mTvCDeCe1, list.get(0));
                    setCommentStatus(mTvCName2, mTvCGood2, mTvCPrice2, mTvCDeCe2, list.get(1));
                    break;
                }
                case 3: {
                    mLayoutC1.setVisibility(View.VISIBLE);
                    mLayoutC2.setVisibility(View.VISIBLE);
                    mLayoutC3.setVisibility(View.VISIBLE);
                    mLayoutC4.setVisibility(View.GONE);
                    setCommentStatus(mTvCName1, mTvCGood1, mTvCPrice1, mTvCDeCe1, list.get(0));
                    setCommentStatus(mTvCName2, mTvCGood2, mTvCPrice2, mTvCDeCe2, list.get(1));
                    setCommentStatus(mTvCName3, mTvCGood3, mTvCPrice3, mTvCDeCe3, list.get(2));
                    break;
                }
                case 4: {
                    mLayoutC1.setVisibility(View.VISIBLE);
                    mLayoutC2.setVisibility(View.VISIBLE);
                    mLayoutC3.setVisibility(View.VISIBLE);
                    mLayoutC4.setVisibility(View.VISIBLE);
                    setCommentStatus(mTvCName1, mTvCGood1, mTvCPrice1, mTvCDeCe1, list.get(0));
                    setCommentStatus(mTvCName2, mTvCGood2, mTvCPrice2, mTvCDeCe2, list.get(1));
                    setCommentStatus(mTvCName3, mTvCGood3, mTvCPrice3, mTvCDeCe3, list.get(2));
                    setCommentStatus(mTvCName4, mTvCGood4, mTvCPrice4, mTvCDeCe4, list.get(3));
                    break;
                }


            }
        }else {
            mLayoutC1.setVisibility(View.GONE);
            mLayoutC2.setVisibility(View.GONE);
            mLayoutC3.setVisibility(View.GONE);
            mLayoutC4.setVisibility(View.GONE);
        }
    }

    private void setCommentStatus(TextView mTvName, HeadImageView mTvGood, TextView mTvPrice, TextView mTvDes, TarotHomePageModel.Data.Commentlist list) {
        if (!TextUtils.isEmpty(list.getPhoto())) {
            mTvGood.doLoadImage(true, "homeHeader", list.getPhoto(), HeadImageView.DEFAULT_AVATAR_THUMB_SIZE);
        }
        mTvPrice.setText(list.getModify_date());
        mTvDes.setText(list.getComment());
       if(!TextUtils.isEmpty(list.getNickname())){
           mTvName.setText(list.getNickname());
       }
    }
    @Override
    public void onClick(View v) {
        if (v.equals(mTvInsult)) {
            SessionHelper.startP2PSession(TarotActivity.this, tls_accid);
        } else if (v.equals(mBtnNotice)) {
            RegistHttpClient.getInstance().setCollection(Preferences.getUserId(), Preferences.getUserToken(), t_id, "1", new RegistHttpClient.SetTarotCollectionHttpCallBack<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(TarotActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailed(int code, String errorMsg) {

                }
            });
        } else if (v.equals(mTvSMore)) {
            Intent i=new Intent(TarotActivity.this, MainMessageActivity.class);
            i.putExtra("tId",t_id);
            i.putExtra("yx_accid",tls_accid);
            i.putExtra("page",0);
            startActivity(i);
        }else if(v.equals(mTvCMore)){
            Intent i=new Intent(TarotActivity.this, MainMessageActivity.class);
            i.putExtra("tId",t_id);
            i.putExtra("yx_accid",tls_accid);
            i.putExtra("page",1);
            startActivity(i);
        }
    }
}
