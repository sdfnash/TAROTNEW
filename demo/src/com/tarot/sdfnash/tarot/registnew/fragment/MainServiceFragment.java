package com.tarot.sdfnash.tarot.registnew.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.netease.sdfnash.uikit.common.ui.imageview.HeadImageView;
import com.tarot.sdfnash.tarot.R;
import com.netease.sdfnash.uikit.common.fragment.TFragment;

/**
 * Created by sdfnash on 16/8/14.
 */
public class MainServiceFragment extends TFragment{

    private HeadImageView mImgAvatar;
    private TextView mTvName;
    private ImageView mImgStar;
    private ListView mList;
    private TextView mTvInsult;

    private static final String TAG = MainServiceFragment.class.getSimpleName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_service_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
