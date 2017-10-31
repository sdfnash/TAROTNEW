package com.tarot.sdfnash.tarot.main.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.sdfnash.uikit.common.ui.imageview.HeadImageView;
import com.tarot.sdfnash.tarot.DemoCache;
import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.config.preference.Preferences;
import com.tarot.sdfnash.tarot.main.activity.MainActivity;
import com.tarot.sdfnash.tarot.main.activity.SettingsActivity;
import com.tarot.sdfnash.tarot.registnew.activity.AboutActivity;
import com.tarot.sdfnash.tarot.registnew.activity.CommentTarotActivity;
import com.tarot.sdfnash.tarot.registnew.activity.OrderActivity;
import com.tarot.sdfnash.tarot.registnew.activity.ServiceTarotActivity;
import com.tarot.sdfnash.tarot.registnew.activity.UserInfoActivity;

public class MineNewFragment extends MainTabFragment implements View.OnClickListener {
    private RelativeLayout mLayoutJudge, mLayoutNotice, mLayoutOrder, mLayoutSetting, mLayoutAbout;
    private Button mBtnLogOut;
    private HeadImageView mHeaderImage;
    private TextView mTvName;
    @Override
    public void onClick(View v) {
        if (v.equals(mLayoutAbout)) {
            Intent i=new Intent(getActivity(), AboutActivity.class);
            startActivity(i);
        } else if (v.equals(mLayoutJudge)) {
            Intent i=new Intent(getActivity(), CommentTarotActivity.class);
            startActivity(i);
           // Toast.makeText(getActivity(),"doing",Toast.LENGTH_SHORT).show();
        } else if (v.equals(mLayoutNotice)) {
            Intent i=new Intent(getActivity(), ServiceTarotActivity.class);
            startActivity(i);
           // Toast.makeText(getActivity(),"doing",Toast.LENGTH_SHORT).show();
        } else if (v.equals(mLayoutOrder)) {
            Intent i=new Intent(getActivity(), OrderActivity.class);
            startActivity(i);
        } else if (v.equals(mLayoutSetting)) {
            Intent i = new Intent(getActivity(), SettingsActivity.class);
            startActivity(i);
        } else if (v.equals(mBtnLogOut)) {
            logout();
        }else if (v.equals(mHeaderImage)) {
            UserInfoActivity.startFromLogin(getActivity(), null);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mHeaderImage != null) {
            if (!TextUtils.isEmpty(DemoCache.getAccount())) {
                mHeaderImage.loadBuddyAvatar(DemoCache.getAccount());
            } else {
                mHeaderImage.setImageResource(R.drawable.ic_logo);
            }
        }
        if(mTvName!=null){
            mTvName.setText(Preferences.getKeyUserNickname() + "/" + Preferences.getUserAccount());
        }
    }

    @Override
    protected void onInit() {
        init();
        initUser();
    }

    public void init() {
        mLayoutAbout = (RelativeLayout) getView().findViewById(R.id.setting_about);
        mLayoutJudge = (RelativeLayout) getView().findViewById(R.id.setting_judge);
        mLayoutNotice = (RelativeLayout) getView().findViewById(R.id.setting_notice);
        mLayoutOrder = (RelativeLayout) getView().findViewById(R.id.setting_order);
        mLayoutSetting = (RelativeLayout) getView().findViewById(R.id.setting_setting);
        mBtnLogOut = (Button) getView().findViewById(R.id.settings_button_logout);
        mLayoutAbout.setOnClickListener(this);
        mLayoutJudge.setOnClickListener(this);
        mLayoutNotice.setOnClickListener(this);
        mLayoutOrder.setOnClickListener(this);
        mLayoutSetting.setOnClickListener(this);
        mBtnLogOut.setOnClickListener(this);

    }

    public void initUser(){
        mHeaderImage=(HeadImageView)getView().findViewById(R.id.setting_tarot_avatar);
        mHeaderImage.setOnClickListener(this);
        mTvName=(TextView)getView().findViewById(R.id.setting_name);
        if(!TextUtils.isEmpty(DemoCache.getAccount())){
            mHeaderImage.loadBuddyAvatar(DemoCache.getAccount());
        }else{
            mHeaderImage.setImageResource(R.drawable.ic_logo);
        }
        mTvName.setText(Preferences.getKeyUserNickname()+"/"+Preferences.getUserAccount());
    }
    private void logout() {
        removeLoginState();
        MainActivity.logout(getActivity(), false);

        getActivity().finish();
        NIMClient.getService(AuthService.class).logout();
    }

    /**
     * 清除登陆状态
     */
    private void removeLoginState() {
        Preferences.saveUserToken("");
    }
}
