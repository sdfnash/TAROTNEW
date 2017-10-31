package com.tarot.sdfnash.tarot.main.model;

import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.main.fragment.MainTabFragment;
import com.tarot.sdfnash.tarot.main.fragment.MineNewFragment;
import com.tarot.sdfnash.tarot.main.fragment.SessionListFragment;
import com.tarot.sdfnash.tarot.main.fragment.StatusFragment;
import com.tarot.sdfnash.tarot.main.reminder.ReminderId;

public enum MainTab {
    RECENT_CONTACTS(0, ReminderId.SESSION, StatusFragment.class, R.string.main_tab_session, R.layout.fragment_status),
    CONTACT(1, ReminderId.CONTACT, SessionListFragment.class, R.string.main_tab_contact, R.layout.session_list),
    CHAT_ROOM(2, ReminderId.INVALID, MineNewFragment.class, R.string.chat_room, R.layout.fragment_mine_new);

    public final int tabIndex;

    public final int reminderId;

    public final Class<? extends MainTabFragment> clazz;

    public final int resId;

    public final int fragmentId;

    public final int layoutId;

    MainTab(int index, int reminderId, Class<? extends MainTabFragment> clazz, int resId, int layoutId) {
        this.tabIndex = index;
        this.reminderId = reminderId;
        this.clazz = clazz;
        this.resId = resId;
        this.fragmentId = index;
        this.layoutId = layoutId;
    }

    public static final MainTab fromReminderId(int reminderId) {
        for (MainTab value : MainTab.values()) {
            if (value.reminderId == reminderId) {
                return value;
            }
        }

        return null;
    }

    public static final MainTab fromTabIndex(int tabIndex) {
        for (MainTab value : MainTab.values()) {
            if (value.tabIndex == tabIndex) {
                return value;
            }
        }

        return null;
    }
}
