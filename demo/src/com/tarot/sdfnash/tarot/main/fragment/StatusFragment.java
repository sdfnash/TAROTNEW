package com.tarot.sdfnash.tarot.main.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.config.preference.Preferences;
import com.tarot.sdfnash.tarot.registnew.Model.GetStatusModel;
import com.tarot.sdfnash.tarot.registnew.RegistHttpClient;

/**
 * Created by sdfnash on 16/9/16.
 */
public class StatusFragment extends MainTabFragment implements View.OnClickListener {

    TextView mTvStatus;
    Button mBtnOpen, mBtnClose;
    int status = 0;

    @Override
    protected void onInit() {
        mTvStatus = (TextView) getView().findViewById(R.id.tv_status);
        mBtnClose = (Button) getView().findViewById(R.id.btn_offline);
        mBtnOpen = (Button) getView().findViewById(R.id.btn_online);
        mBtnClose.setOnClickListener(this);
        mBtnOpen.setOnClickListener(this);
        RegistHttpClient.getInstance().getStatus(Preferences.getUserId(), new RegistHttpClient.GetStatusHttpCallBack<GetStatusModel.Data>() {
            @Override
            public void onSuccess(GetStatusModel.Data data) {
                if(!TextUtils.isEmpty(data.getOnline_status())){
                    status=Integer.parseInt(data.getOnline_status());
                    if (status == 0) {
                        mTvStatus.setText("您当前状态为:忙碌");
                        mBtnOpen.setClickable(true);
                        mBtnClose.setClickable(false);
                    } else if (status == 1) {
                        mTvStatus.setText("您当前状态为:在线");
                        mBtnOpen.setClickable(false);
                        mBtnClose.setClickable(true);
                    }
                }
            }

            @Override
            public void onFailed(int code, String errorMsg) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnClose)) {
            mBtnOpen.setClickable(true);
            mBtnClose.setClickable(false);
            RegistHttpClient.getInstance().setStatus(Preferences.getUserId(), Preferences.getUserToken(), 0, new RegistHttpClient.SetStatusHttpCallBack<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    mTvStatus.setText("您当前状态为:忙碌");
                    status = 0;
                    mBtnOpen.setClickable(true);
                    mBtnClose.setClickable(false);
                }

                @Override
                public void onFailed(int code, String errorMsg) {

                }
            });
        } else if (v.equals(mBtnOpen)) {
            mBtnOpen.setClickable(false);
            mBtnClose.setClickable(true);
            RegistHttpClient.getInstance().setStatus(Preferences.getUserId(), Preferences.getUserToken(), 1, new RegistHttpClient.SetStatusHttpCallBack<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    mTvStatus.setText("您当前状态为:在线");
                    status = 1;
                    mBtnOpen.setClickable(false);
                    mBtnClose.setClickable(true);
                }

                @Override
                public void onFailed(int code, String errorMsg) {

                }
            });
        }
    }
}
