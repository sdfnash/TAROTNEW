package com.tarot.sdfnash.tarot.chatroom.viewholder;

import android.widget.TextView;

import com.tarot.sdfnash.tarot.chatroom.helper.ChatRoomNotificationHelper;
import com.netease.sdfnash.uikit.R;
import com.netease.sdfnash.uikit.session.viewholder.MsgViewHolderBase;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomNotificationAttachment;

public class ChatRoomMsgViewHolderNotification extends MsgViewHolderBase {

    protected TextView notificationTextView;

    @Override
    protected int getContentResId() {
        return R.layout.nim_message_item_notification;
    }

    @Override
    protected void inflateContentView() {
        notificationTextView = (TextView) view.findViewById(R.id.message_item_notification_label);
    }

    @Override
    protected void bindContentView() {
        notificationTextView.setText(ChatRoomNotificationHelper.getNotificationText((ChatRoomNotificationAttachment) message.getAttachment()));
    }

    @Override
    protected boolean isMiddleItem() {
        return true;
    }
}

