package com.tarot.sdfnash.tarot.registnew.View;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.tarot.sdfnash.tarot.registnew.View.draglistview.DragSortListView;


/**
 * Created by sdfnash on 16/4/19.
 */
public class CustomerSwipeRefreshLayout extends SwipeRefreshLayout {
    private ViewGroup mRefreshView;

    public CustomerSwipeRefreshLayout(Context context) {
        super(context);
    }

    public CustomerSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setRefreshView(ViewGroup refreshView) {
        mRefreshView = refreshView;
    }

    public boolean canChildScrollUp() {
        if (mRefreshView == null) {
            return super.canChildScrollUp();
        }
        boolean canScrollUp;
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mRefreshView instanceof AbsListView) {
                AbsListView absListView = (AbsListView) mRefreshView;
                canScrollUp = absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                canScrollUp = ViewCompat.canScrollVertically(mRefreshView, -1) || mRefreshView.getScrollY() > 0;
            }
        } else {
            canScrollUp = ViewCompat.canScrollVertically(mRefreshView, -1);
        }

        if (mRefreshView instanceof DragSortListView) {
            DragSortListView dragListView = (DragSortListView) mRefreshView;
            canScrollUp = canScrollUp || dragListView.getDragState() != DragSortListView.IDLE;
        }

        return canScrollUp;
    }
}
