package com.netease.sdfnash.uikit.session.viewholder;

import com.netease.sdfnash.uikit.R;
import com.netease.sdfnash.uikit.session.activity.WatchMessagePictureActivity;

/**
 * Created by zhoujianghua on 2015/8/4.
 */
public class MsgViewHolderPicture extends MsgViewHolderThumbBase {

    @Override
    protected int getContentResId() {
        return R.layout.nim_message_item_picture;
    }

    @Override
    protected void onItemClick() {
        WatchMessagePictureActivity.start(context, message);
    }

    @Override
    protected String thumbFromSourceFile(String path) {
        return path;
    }
}
