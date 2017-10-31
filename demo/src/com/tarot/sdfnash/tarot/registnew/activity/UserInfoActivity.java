package com.tarot.sdfnash.tarot.registnew.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.netease.sdfnash.uikit.common.activity.UI;
import com.netease.sdfnash.uikit.common.media.picker.PickImageHelper;
import com.netease.sdfnash.uikit.common.ui.imageview.HeadImageView;
import com.netease.sdfnash.uikit.common.ui.widget.ClearableEditTextWithIcon;
import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.config.preference.Preferences;
import com.tarot.sdfnash.tarot.main.activity.MainActivity;
import com.tarot.sdfnash.tarot.registnew.Model.UpLoadModel;
import com.tarot.sdfnash.tarot.registnew.RegistHttpClient;

import java.io.File;

public class UserInfoActivity extends UI implements View.OnClickListener {

    private ClearableEditTextWithIcon mNameEdit;
    private ImageView mComplete, mPhotoCamera;
    private HeadImageView mImgAvatar;
    private static final int PICK_AVATAR_REQUEST = 0x0E;
    public  static final int USER_INFO=0x02;
    private Uri mSelectedImageUrl;

    public boolean isUpLoad;
    public static boolean isFromLogin;
    private String photo,photo_s;

    public static void startFromLogin(Context context, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(context, UserInfoActivity.class);
        isFromLogin=true;
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mNameEdit = (ClearableEditTextWithIcon) findViewById(R.id.dialog_name);
        mNameEdit.setIconResource(R.drawable.user_account_icon);
        mImgAvatar = (HeadImageView) findViewById(R.id.dialog_avatar);
        mComplete = (ImageView) findViewById(R.id.dialog_commit);
        mPhotoCamera = (ImageView) findViewById(R.id.dialog_camera);
        mComplete.setOnClickListener(this);
        mImgAvatar.setOnClickListener(this);
        //  mPhotoCamera.setOnClickListener(this);
        mComplete.setClickable(false);

        mNameEdit.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
                if (TextUtils.isEmpty(mNameEdit.getText()) || TextUtils.isEmpty(String.valueOf(mSelectedImageUrl))) {
                    mComplete.setClickable(false);
                    //   mComplete.setImageDrawable(getResources().getDrawable(R.drawable.shape_white_trans_radius));
                } else {
                    mComplete.setClickable(true);
                    //  mComplete.setImageDrawable(getResources().getDrawable(R.drawable.shape_white_trans_radius));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (temp.length() > 7) {
//                    int selectionStart = mNameEdit.getSelectionStart();
//                    int selectionEnd = mNameEdit.getSelectionEnd();
//                    s.delete(selectionStart - 1, selectionEnd);
//                    int tempSelection = mNameEdit.getSelectionEnd();
//                    mNameEdit.setText(s);
//                    mNameEdit.setSelection(tempSelection);
//                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mComplete)) {
            if (isUpLoad) {
                if(!TextUtils.isEmpty(photo)&&!TextUtils.isEmpty(mNameEdit.getText().toString())){
                    RegistHttpClient.getInstance().saveinfoCode(Preferences.getUserId(), Preferences.getUserToken(), mNameEdit.getText().toString(), photo, photo_s, new RegistHttpClient.SaveInfoHttpCallback<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Preferences.saveUserNickName(mNameEdit.getText().toString().trim());
                            Preferences.saveUserPhoto(photo);
                            RegistHttpClient.getInstance().updateYXUerInfoCode(Preferences.getUserId(), Preferences.getUserToken(), new RegistHttpClient.UpdateYXUerInfoHttpCallback<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(UserInfoActivity.this,"数据修改成功",Toast.LENGTH_SHORT).show();
                                    if(isFromLogin){
                                        MainActivity.start(UserInfoActivity.this);
                                        finish();
                                    }else{
                                        finish();
                                    }
                                }

                                @Override
                                public void onFailed(int code, String errorMsg) {

                                }
                            });
                        }

                        @Override
                        public void onFailed(int code, String errorMsg) {
                            Toast.makeText(UserInfoActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(UserInfoActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(UserInfoActivity.this, "请上传头像", Toast.LENGTH_SHORT).show();
            }
        } else if (v.equals(mImgAvatar) || v.equals(mPhotoCamera)) {
            choosePic();
        }
    }

    public void choosePic() {
        PickImageHelper.PickImageOption option = new PickImageHelper.PickImageOption();
        option.titleResId = R.string.set_head_image;
        option.crop = true;
        option.multiSelect = false;
        option.cropOutputImageWidth = 720;
        option.cropOutputImageHeight = 720;
        PickImageHelper.pickImage(UserInfoActivity.this, PICK_AVATAR_REQUEST, option);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == PICK_AVATAR_REQUEST) {
            String path = data.getStringExtra(com.netease.sdfnash.uikit.session.constant.Extras.EXTRA_FILE_PATH);
            updateAvatar(path);
        }
    }

    /**
     * 更新头像
     */
    private void updateAvatar(final String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }

        File file = new File(path);
        if (file == null) {
            return;
        }

        RegistHttpClient.getInstance().upLoadCode(Preferences.getUserId(), Preferences.getUserToken(), path, file, new RegistHttpClient.UpLoadHttpCallback<UpLoadModel.DataBean>() {
            @Override
            public void onFailed(int code, String errorMsg) {
                Toast.makeText(UserInfoActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(UpLoadModel.DataBean model) {
                Toast.makeText(UserInfoActivity.this, "头像上传成功", Toast.LENGTH_SHORT).show();
                photo = model.getPhoto_url();
                photo_s=model.getThumb_photo_url();
                mImgAvatar.setImageURI(Uri.parse(path));
                isUpLoad = true;
            }
        });
    }


}
