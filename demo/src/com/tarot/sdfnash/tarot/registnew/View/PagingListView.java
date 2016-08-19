package com.tarot.sdfnash.tarot.registnew.View;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;

import com.tarot.sdfnash.tarot.R;

/**
 * Created by sdfnash on 16/8/14.
 */
public class PagingListView extends FrameLayout {

    private View mContentView;
    private CustomerListView mListView;
    private EmptyLayout mEmptyLayout;
    private FrameLayout mTopContainerLayout;
    private CustomerSwipeRefreshLayout mSwipeRefreshLayout;

    private AbsListView.OnScrollListener mCustomerScrollListener;
    private DataListener mDataListener;

    private boolean isRefreshingEnable = true;
    private boolean isLoadingEnable = true;

    private PagingFooterView mFooterView;

    /**
     * 加载回调
     */
    private AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // 当不滚动时
            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                // 判断是否滚动到底部
                if (view.getLastVisiblePosition() == view.getCount() - 1) {
                    if (mDataListener != null && isLoadingEnable) {
                        mDataListener.onLoading();
                    }
                }
            }

            if (mCustomerScrollListener != null) {
                mCustomerScrollListener.onScrollStateChanged(view, scrollState);
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (mCustomerScrollListener != null) {
                mCustomerScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
            }
        }
    };

    /**
     * 刷新回调
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {

        @Override
        public void onRefresh() {
            if (isRefreshingEnable) {
                if (mDataListener != null) {
                    mDataListener.onRefresh();
                }
            }
        }
    };

    public PagingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PagingListView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mContentView = LayoutInflater.from(getContext()).inflate(R.layout.layout_edu_paging_listview, null);
        this.addView(mContentView);

        mListView = (CustomerListView) mContentView.findViewById(R.id.edu_paging_listview);
        mEmptyLayout = (EmptyLayout) mContentView.findViewById(R.id.edu_paging_listview_empty_view);
        mTopContainerLayout = (FrameLayout) mContentView.findViewById(R.id.edu_paging_listview_top_container_layout);
        mEmptyLayout.setClickable(true);
        mEmptyLayout.setTextGravity(Gravity.CENTER);
        mListView.setEmptyView(mEmptyLayout);
        mSwipeRefreshLayout = (CustomerSwipeRefreshLayout) mContentView.findViewById(R.id.edu_paging_listview_refreshViewLayout);
        mSwipeRefreshLayout.setRefreshView(mListView);

        initListView();

        initRefreshLayout();
    }

    private void initListView() {

        mListView.setOverScrollMode(AbsListView.OVER_SCROLL_NEVER);

        mFooterView = new PagingFooterView(getContext());
        mListView.addFooterView(mFooterView, null, false);
        mListView.setOnScrollListener(mOnScrollListener);

    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.color_blue_3a9efb));
        mSwipeRefreshLayout.setOnRefreshListener(mRefreshListener);
    }

    public void setOnCustomerScrollListener(AbsListView.OnScrollListener listener) {
        mCustomerScrollListener = listener;
    }

    public void setDataListener(DataListener listener) {
        mDataListener = listener;
    }

    public void showFooterView() {
        if (mFooterView != null) {
            mFooterView.showFooterView();
        }
    }

    public void hiddenFooterView() {
        if (mFooterView != null) {
            mFooterView.hiddenFooterView();
        }
    }

    public void setRefreshEnable(boolean isEnable) {
        isRefreshingEnable = isEnable;
        mSwipeRefreshLayout.setEnabled(isEnable);
    }

    public void setFastScrollEnabled(boolean isEnable) {
        mListView.setScrollbarFadingEnabled(isEnable);
        mListView.setFastScrollEnabled(isEnable);
    }

    public void setLoadingEnable(boolean isEnable) {
        isLoadingEnable = isEnable;
        if (!isLoadingEnable) {
            hiddenFooterView();
        }
    }

    public int getFooterViewHeight() {
        return mFooterView.getFooterViewHeight();
    }

    public boolean isFooterViewVisable() {
        return mFooterView.isFooterViewVisiable();
    }

    public void setEmptyViewTip(int resId) {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyLayoutInstructionText(resId);
            mEmptyLayout.setTextGravity(Gravity.CENTER);
        }
    }

    public void setShowEmptyView(boolean isShowEmptyView) {
        if (isShowEmptyView) {
            if (mEmptyLayout != null) {
                mListView.setEmptyView(mEmptyLayout);
                mEmptyLayout.setVisibility(View.VISIBLE);
            }
        } else {
            if (mEmptyLayout != null) {
                mListView.setEmptyView(null);
                mEmptyLayout.setVisibility(View.GONE);
            }
        }
    }

    public EmptyLayout getEmptyView() {
        return mEmptyLayout;
    }

    public void addTopContentView(View view) {
        mTopContainerLayout.addView(view);
    }

    public void addTopContentView(View view, LayoutParams params) {
        mTopContainerLayout.addView(view, params);
    }

    public void removeTopContentView(View view) {
        mTopContainerLayout.removeView(view);
    }

    public void removeTopContentView() {
        mTopContainerLayout.removeAllViews();
    }

    public void setEmptyBtnListenr() {

    }

    public CustomerSwipeRefreshLayout getSwipeLayout() {
        return mSwipeRefreshLayout;
    }

    public boolean isRefreshing() {
        return mSwipeRefreshLayout.isRefreshing();
    }

    public void setRefreshing(boolean isRefreshing) {
        mSwipeRefreshLayout.setRefreshing(isRefreshing);
    }

    public CustomerListView getListView() {
        return mListView;
    }

    public interface DataListener {
        void onRefresh();

        void onLoading();
    }

}

