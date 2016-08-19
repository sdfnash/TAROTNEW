package com.tarot.sdfnash.tarot.main.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.config.preference.Preferences;
import com.tarot.sdfnash.tarot.main.activity.MainActivity;
import com.tarot.sdfnash.tarot.main.activity.SettingsActivity;
import com.tarot.sdfnash.tarot.registnew.activity.AboutActivity;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;

public class MineNewFragment extends MainTabFragment implements View.OnClickListener {
    private RelativeLayout mLayoutJudge, mLayoutNotice, mLayoutOrder, mLayoutSetting, mLayoutAbout;
    private Button mBtnLogOut;

    @Override
    public void onClick(View v) {
        if (v.equals(mLayoutAbout)) {
            Intent i=new Intent(getActivity(), AboutActivity.class);
            startActivity(i);
        } else if (v.equals(mLayoutJudge)) {

        } else if (v.equals(mLayoutNotice)) {

        } else if (v.equals(mLayoutOrder)) {

        } else if (v.equals(mLayoutSetting)) {
            Intent i = new Intent(getActivity(), SettingsActivity.class);
            startActivity(i);
        } else if (v.equals(mBtnLogOut)) {
            logout();
        }
    }

    @Override
    protected void onInit() {
        init();
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
