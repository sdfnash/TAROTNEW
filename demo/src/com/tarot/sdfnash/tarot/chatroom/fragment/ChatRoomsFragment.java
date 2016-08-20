package com.tarot.sdfnash.tarot.chatroom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.chatroom.activity.ChatRoomActivity;
import com.tarot.sdfnash.tarot.chatroom.adapter.ChatRoomAdapter;
import com.tarot.sdfnash.tarot.chatroom.thridparty.ChatRoomHttpClient;
import com.tarot.sdfnash.tarot.chatroom.viewholder.ChatRoomViewHolder;
import com.netease.sdfnash.uikit.common.adapter.TAdapterDelegate;
import com.netease.sdfnash.uikit.common.adapter.TViewHolder;
import com.netease.sdfnash.uikit.common.fragment.TFragment;
import com.netease.sdfnash.uikit.common.ui.ptr.PullToRefreshBase;
import com.netease.sdfnash.uikit.common.ui.ptr.PullToRefreshGridView;
import com.netease.sdfnash.uikit.common.util.log.LogUtil;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 直播间列表fragment
 * Created by huangjun on 2015/12/11.
 */
public class ChatRoomsFragment extends TFragment implements TAdapterDelegate, ChatRoomAdapter.ViewHolderEventListener {

    private static final String TAG = ChatRoomsFragment.class.getSimpleName();
    private View loadingFrame;
    private PullToRefreshGridView gridView;
    private List<ChatRoomInfo> items = new ArrayList<>();
    private ChatRoomAdapter adapter;

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
        return ChatRoomViewHolder.class;
    }

    @Override
    public boolean enabled(int position) {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_rooms, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initAdapter();
        findViews();
    }

    public void onCurrent() {
        fetchData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void findViews() {
        // loading
        loadingFrame = findView(com.netease.sdfnash.uikit.R.id.contact_loading_frame);
        loadingFrame.setVisibility(View.GONE);

        gridView = findView(R.id.chat_room_grid_view);
        gridView.setAdapter(adapter);
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                onFetchDataDone(false);
            }
        });
    }

    @Override
    public void onItemClick(String roomId) {
        ChatRoomActivity.start(getActivity(), roomId);
    }

    private void initAdapter() {
        adapter = new ChatRoomAdapter(getActivity(), items, this, this);
    }

    private void fetchData() {
        ChatRoomHttpClient.getInstance().fetchChatRoomList(new ChatRoomHttpClient.ChatRoomHttpCallback<List<ChatRoomInfo>>() {
            @Override
            public void onSuccess(List<ChatRoomInfo> rooms) {
                if (items.isEmpty()) {
                    items.addAll(rooms);
                }

                onFetchDataDone(true);
            }

            @Override
            public void onFailed(int code, String errorMsg) {
                onFetchDataDone(false);
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "fetch chat room list failed, code=" + code, Toast.LENGTH_SHORT);
                }

                LogUtil.d(TAG, "fetch chat room list failed, code:" + code
                        + " errorMsg:" + errorMsg);
            }
        });
    }

    private void onFetchDataDone(boolean success) {
        loadingFrame.setVisibility(View.GONE);
        gridView.onRefreshComplete();
        if (success) {
            refresh();
        }
    }

    private void refresh() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

}
