package com.tarot.sdfnash.tarot.registnew;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by Liuyi on 2016/8/5.
 */
public class TimeDownButton extends Button implements Runnable, View.OnClickListener {

    public interface TimeDownButtonListener {
        public void onClick(View view);

        public void onShouldTimedown(View view);

        public void onFinisedTimedown(View view);
    }

    private String mInitText;
    private String mTimingText = "%ds后\n重新获取";
    private String mTimingFinishedText;
    private int mTimeVal;
    private boolean mIsTimeDown;
    private TimeDownButtonListener mListener;

    public TimeDownButton(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public TimeDownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    public TimeDownButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnClickListener(this);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);

        if (TextUtils.isEmpty(mTimingFinishedText))
            mTimingFinishedText = text.toString();
        if (TextUtils.isEmpty(mInitText)) {
            mInitText = text.toString();
        }
    }

    /**
     * @param l 倒计时监听事件
     */
    public void setTimeDownListener(TimeDownButtonListener l) {
        mListener = l;
    }

    /**
     * 开始倒计时
     *
     * @param timeval 倒计时的时间间隔
     */
    public void startUp(int timeval) {
        if (timeval <= 0) {
            throw new IllegalArgumentException();
        }
        if (mIsTimeDown) return;
        mIsTimeDown = true;
        mTimeVal = timeval;
        setText(getTimingText());
        postDelayed(this, 1000);
        if (mListener != null) {
            mListener.onShouldTimedown(this);
        }
    }

    /**
     * 停止倒计时
     */
    public void cancelTimedown() {
        if (!isTiming()) {
            return;
        }
        mTimeVal = 1;
        setText(mInitText);
        mIsTimeDown = false;
        removeCallbacks(this);
        if (mListener != null) {
            mListener.onFinisedTimedown(this);
        }
    }

    public void setTimingText(String timingText) {
        mTimingText = timingText;
    }

    public void setTimingFinishedText(String timingFinishedText) {
        mTimingFinishedText = timingFinishedText;
    }

    public void setInitText(String initText) {
        mInitText = initText;
    }

    public String getTimingText() {
        String text = String.format(mTimingText, mTimeVal);
        return text;
    }

    public String getTimingFinishedText() {
        if (TextUtils.isEmpty(mTimingFinishedText)) {
            return mInitText;
        }
        return mTimingFinishedText;
    }

    public String getInitText() {
        return mInitText;
    }


    @Override
    public void run() {
        mTimeVal--;

        if (mTimeVal <= 0) {
            if (mListener != null) {
                mListener.onFinisedTimedown(this);
            }
            setText(getTimingFinishedText());
            mIsTimeDown = false;
            return;
        } else {
            setText(getTimingText());
            postDelayed(this, 1000);
        }
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onClick(this);
        }
    }

    /**
     * 是否正在计时
     *
     * @return
     */
    public boolean isTiming() {
        return mIsTimeDown;
    }

    /**
     * @return mTimeVal 当前倒计时长
     */
    public int getTimeVal() {
        return mTimeVal;
    }
}