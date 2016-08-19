package com.tarot.sdfnash.tarot.registnew.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tarot.sdfnash.tarot.R;

/**
 * Created by sdfnash on 16/4/20.
 */
public class PagingFooterView extends FrameLayout {

    private Context mContext;

    private int mHeight;

    private View mContentView;
    private TextView mLoadingTipTextView;
    private ProgressWheel mLoadingProgressWheel;

    private boolean isFootViewVisiable = false;


    public PagingFooterView(Context context) {
        super(context);
        mContext = context;

        initView();
    }

    private void initView() {
        mContentView = LayoutInflater.from(mContext).inflate(R.layout.layout_edu_common_loading_footer, null);
        mLoadingTipTextView = (TextView) mContentView.findViewById(R.id.layout_edu_common_loading_footer_tip_textView);
        mLoadingProgressWheel = (ProgressWheel) mContentView.findViewById(R.id.layout_edu_common_loading_progressWheel);

        mHeight = DipPixUtil.dip2px(mContext, 60);

        addView(mContentView);

        showFooterView();

        setEnabled(false);
    }

    public void setTipText(String tip) {
        mLoadingTipTextView.setText(tip);
    }

    public void setProgressBraVisibility(int visibility) {
        mLoadingProgressWheel.setVisibility(visibility);
    }

    public void setTipTextViewVisibility(int visibility) {
        mLoadingTipTextView.setVisibility(visibility);

    }

    public View getContentView() {
        return mContentView;
    }

    public int getFooterViewHeight() {
        return mHeight;
    }

    public void hiddenFooterView() {
//        FrameLayout.LayoutParams params = (LayoutParams) mContentView.getLayoutParams();
//        params.height = 0;
//        mContentView.setLayoutParams(params);
        removeView(mContentView);
        isFootViewVisiable = false;
    }

    public void showFooterView() {
        LayoutParams params = (LayoutParams) mContentView.getLayoutParams();
        params.height = mHeight;
        mContentView.setLayoutParams(params);
        removeView(mContentView);
        addView(mContentView);
        isFootViewVisiable = true;
    }

    public boolean isFooterViewVisiable() {
        return isFootViewVisiable;
    }
}

