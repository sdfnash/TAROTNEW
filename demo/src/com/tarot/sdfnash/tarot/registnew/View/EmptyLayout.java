package com.tarot.sdfnash.tarot.registnew.View;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tarot.sdfnash.tarot.R;


/**
 * Created by sdfnash on 16/4/19.
 */
public class EmptyLayout extends FrameLayout {

    private View mContentView;

    private Button mButton;

    private ImageView mImageView;

    private TextView mTextView;

    private onEmptyLayoutButtonClickListener mListener;

    public interface onEmptyLayoutButtonClickListener {
        void onEmptyLayoutButtonClick(View v);
    }

    public EmptyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmptyLayout(Context context) {
        super(context);
        init();
    }

    private void init() {

        mContentView = LayoutInflater.from(getContext()).inflate(R.layout.layout_empty_pager, null);
        addView(mContentView);
        mImageView = (ImageView) findViewById(R.id.layout_empty_pager_img);
        mButton = (Button) findViewById(R.id.layout_empty_pager_btn);
        mTextView = (TextView) findViewById(R.id.layout_empty_pager_txt);

    }

    public void setEmptyLayoutIcon(Drawable drawable) {
        if (mImageView != null) {
            mImageView.setImageDrawable(drawable);
        }
    }

    public void setEmptyLayoutIcon(int resId) {
        mImageView.setImageResource(resId);
    }

    public void setTextGravity(int gravity) {
        mTextView.setGravity(gravity);
    }

    public void setEmptyLayoutIcon(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }

    public void setEmptyLayoutInstructionText(CharSequence s) {
        if (mTextView != null) {
            mTextView.setVisibility(View.VISIBLE);
            mTextView.setText(s);
        }
    }

    public void setEmptyLayoutInstructionText(int resId) {
        if (mTextView != null) {
            mTextView.setVisibility(View.VISIBLE);
            mTextView.setText(resId);
        }
    }

    public void setEmptyLayoutInstructionTextVisibility(int visibility) {
        if (mTextView != null) {
            mTextView.setVisibility(visibility);
        }
    }

    public void setEmptyLayoutButtonText(CharSequence s) {
        if (mButton != null) {
            mButton.setVisibility(View.VISIBLE);
            mButton.setText(s);
        }
    }

    public void setEmptyLayoutButtonText(int resId) {
        if (mButton != null) {
            mButton.setVisibility(View.VISIBLE);
            mButton.setText(resId);
        }
    }

    public void setEmptyLayoutButtonVisibility(int visibility) {
        if (mButton != null) {
            mButton.setVisibility(visibility);
        }
    }

    public void setEmptyLayoutButtonTextColor(int color) {
        if (mButton != null) {
            mButton.setTextColor(color);
        }
    }

    public void setEmptyLayoutButtonTextColor(ColorStateList colors) {
        if (mButton != null) {
            mButton.setTextColor(colors);
        }
    }

    public void setEmptyLayoutButtonBackground(Drawable background) {
        if (mButton != null) {
            mButton.setBackgroundDrawable(background);
        }
    }

    public void setEmptyLayoutBackground(int color) {
        if (mContentView != null) {
            ColorDrawable cd = new ColorDrawable(color);
            mContentView.setBackgroundDrawable(cd);
        }
    }

    public void setEmptyLayoutButtonBackground(int color) {
        if (mButton != null) {
            mButton.setBackgroundColor(color);
        }
    }

    public void setEmptyLayoutButtonBackgroundResource(int resid) {
        if (mButton != null) {
            mButton.setBackgroundResource(resid);
        }
    }

    public void setOnEmptyLayoutButtonClickListener(onEmptyLayoutButtonClickListener listener) {
        mListener = listener;

        if (mListener != null) {
            mButton.setVisibility(View.VISIBLE);
            mButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onEmptyLayoutButtonClick(v);
                    }
                }
            });
        } else {
            mButton.setVisibility(View.GONE);
            mButton.setOnClickListener(null);
        }
    }

}
