package com.tarot.sdfnash.tarot.registnew.View.indicator;

import android.view.View;

public interface IconPagerAdapter {
    /**
     * Get icon representing the page at {@code index} in the adapter.
     */
    // int getIconResId(int index);

    View getTabView(int index);

    // From PagerAdapter
    int getCount();
}
