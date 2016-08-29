package com.tarot.sdfnash.tarot.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.registnew.Model.LoginModel;
import com.tarot.sdfnash.tarot.registnew.RegistHttpClient;
import com.tarot.sdfnash.tarot.registnew.TimeDownButton;
import com.netease.sdfnash.uikit.common.activity.UI;
import com.netease.sdfnash.uikit.common.ui.dialog.DialogMaker;
import com.netease.sdfnash.uikit.common.ui.widget.ClearableEditTextWithIcon;
import com.netease.sdfnash.uikit.common.util.sys.NetworkUtil;

public class ForgetPasswordActivity extends UI {
    private ClearableEditTextWithIcon registerAccountEdit;
    private ClearableEditTextWithIcon registerNickNameEdit;
    private ClearableEditTextWithIcon registerPasswordEdit;
    private TimeDownButton mTimeDownBtn;
    private Button mBtnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
       mBtnGet=findView(R.id.forget_get);
        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForget();
            }
        });
        registerAccountEdit = findView(R.id.edit_register_account);
        registerNickNameEdit = findView(R.id.edit_register_nickname);
        registerPasswordEdit = findView(R.id.edit_register_password);
        mTimeDownBtn = findView(R.id.get_sms_code_btn);
        mTimeDownBtn.setTimingText("重新发送(%ds)");
        mTimeDownBtn.setTimingFinishedText("重新发送");
        mTimeDownBtn.setTimeDownListener(new TimeDownButton.TimeDownButtonListener() {
            @Override
            public void onClick(View view) {
                if (checkPhoneNum(true)) {
                    getSmsCode();
                }
            }

            @Override
            public void onShouldTimedown(View view) {
                mTimeDownBtn.setClickable(false);
                mTimeDownBtn.setTextColor(getResources().getColor(R.color.color_gray_cbd0d8));
            }

            @Override
            public void onFinisedTimedown(View view) {
                mTimeDownBtn.setClickable(true);
                if (checkPhoneNum(false)) {
                    mTimeDownBtn.setTextColor(getResources().getColor(R.color.color_blue_0888ff));
                }
            }
        });


        registerAccountEdit.setIconResource(R.drawable.user_account_icon);
        registerNickNameEdit.setIconResource(R.drawable.nick_name_icon);
        registerPasswordEdit.setIconResource(R.drawable.user_pwd_lock_icon);

    }

    private boolean checkPhoneNum(boolean isNeedToast) {
        String phoneNum = registerAccountEdit.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum)) {
//            mInputPhoneNumEditText.requestFocus();
//            mInputPhoneNumEditText.setError("请输入你的手机号");
            if (isNeedToast) {
                Toast.makeText(ForgetPasswordActivity.this, "请输入你的手机号", Toast.LENGTH_SHORT).show();
            }
            return false;
        }


        if (phoneNum.matches("^1[0-9][0-9]{9}") == false) {
//                mInputPhoneNumEditText.requestFocus();
//                mInputPhoneNumEditText.setError("请输入正确的手机号");
            if (isNeedToast) {

                Toast.makeText(ForgetPasswordActivity.this, "手机号不符合规范", Toast.LENGTH_SHORT).show();
            }
            return false;
        }


        return true;
    }

    public void getSmsCode() {
        RegistHttpClient.getInstance().sendSmsCode(registerAccountEdit.getText().toString(), new RegistHttpClient.SendSmsHttpCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ForgetPasswordActivity.this, "请留意短信", Toast.LENGTH_SHORT).show();
                mTimeDownBtn.startUp(60);
            }

            @Override
            public void onFailed(int code, String errorMsg) {
                Toast.makeText(ForgetPasswordActivity.this, String.valueOf(code), Toast.LENGTH_LONG).show();
            }
        });
    }






    private void getForget() {


        if (!NetworkUtil.isNetAvailable(ForgetPasswordActivity.this)) {
            Toast.makeText(ForgetPasswordActivity.this, R.string.network_is_not_available, Toast.LENGTH_SHORT).show();
            return;
        }

        DialogMaker.showProgressDialog(this, getString(R.string.registering), false);

        // 注册流程
        final String account = registerAccountEdit.getText().toString();
        final String code = registerNickNameEdit.getText().toString();
        final String password = registerPasswordEdit.getText().toString();
        RegistHttpClient.getInstance().repassCode(account, code, password, new RegistHttpClient.RepassHttpCallback<LoginModel.DataBean>() {
            @Override
            public void onSuccess(LoginModel.DataBean aVoid) {
                DialogMaker.dismissProgressDialog();
                Toast.makeText(ForgetPasswordActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailed(int code, String errorMsg) {
                Toast.makeText(ForgetPasswordActivity.this, errorMsg, Toast.LENGTH_SHORT)
                        .show();
                DialogMaker.dismissProgressDialog();
            }
        });


    }


}
