package com.tarot.sdfnash.tarot.chatroom.adapter;

import android.content.Context;

import com.netease.sdfnash.uikit.common.adapter.TAdapter;
import com.netease.sdfnash.uikit.common.adapter.TAdapterDelegate;

import java.util.List;

public class ChatRoomAdapter extends TAdapter {

    public ChatRoomAdapter(Context context, List<?> items, TAdapterDelegate delegate, ViewHolderEventListener
            viewHolderEventListener) {
        super(context, items, delegate);

        this.viewHolderEventListener = viewHolderEventListener;
    }

    public interface ViewHolderEventListener {
        void onItemClick(String roomId);
    }

    private ViewHolderEventListener viewHolderEventListener;

    public ViewHolderEventListener getEventListener() {
        return viewHolderEventListener;
    }
}
