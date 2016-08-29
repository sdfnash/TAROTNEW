package com.tarot.sdfnash.tarot.registnew.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.registnew.View.indicator.DisplayUtils;
import com.tarot.sdfnash.tarot.registnew.View.indicator.PageTabLabel;



public class MainMessageFragment extends AbsViewPagerWithTitleFragment {

    private static final String TAG = MainMessageFragment.class.getSimpleName();

    public String tId,sId,yx_accid;

    public static final int TAB_MESSAGE = 0;
    public static final int TAB_STUDENT = 1;
    public static final int TAB_COUNT = 2;
    int count = 0;
    private Fragment[] tabs = new Fragment[TAB_COUNT];

    private ImageView mTvContract;

    @Override
    protected FragmentManager getAdapterFragmentManager() {
        return getChildFragmentManager();
    }

    @Override
    protected int getCount() {
        return TAB_COUNT;
    }

    /**
     * 能否左右滑动切换tab
     */
    @Override
    protected boolean canScroll() {
        return true;
    }

    @Override
    protected int getIndicatorWith() {
        return DisplayUtils.getScreenWidthPixels(getActivity()) / 2;
    }

    @Override
    public Fragment getFragment(int position) {
        switch (position) {
            case TAB_MESSAGE: {
                if (tabs[TAB_MESSAGE] == null) {
                    tabs[TAB_MESSAGE] = new MainServiceFragment();
                }
                return tabs[TAB_MESSAGE];
            }
            case TAB_STUDENT: {
                if (tabs[TAB_STUDENT] == null) {
                    tabs[TAB_STUDENT] = new MainCommentFragment();
                    ( (MainCommentFragment) tabs[TAB_STUDENT]).setTID(tId);
                    ( (MainCommentFragment) tabs[TAB_STUDENT]).setTls_accid(yx_accid);
                }
                return tabs[TAB_STUDENT];
            }
        }
        return null;
    }

    @Override
    protected CharSequence getFragmentTitle(int position) {
        switch (position) {
            case TAB_MESSAGE:
                return getString(R.string.main_message_title_message);
            case TAB_STUDENT:
                return getString(R.string.main_message_title_student);
        }
        return "";
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        mTvContract = (ImageView) getView().findViewById(R.id.img_right_contract);
//        mTvContract.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent();
//                i.setClass(getActivity(), NewContractActivity.class);
//                startActivity(i);
//            }
//        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

//    @Override
//    protected int getCustomTitleId() {
//        return R.layout.layout_right_contract;
//    }

    public void setIcon(int index, int ResId) {
//        mIndicator.se
    }

    public void setCurrentItem(int position) {
        mIndicator.setCurrentItem(position);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vuew = super.onCreateView(inflater, container, savedInstanceState);
        mIndicator.setOnTabReselectedListener(new PageTabLabel.OnTabReselectedListener() {
            @Override
            public void onTabReselected(int position) {
                switch (position) {
                    case 0:

                        if (count > 0) {
                            rightHintView.setVisibility(View.VISIBLE);
                        } else {
                            rightHintView.setVisibility(View.GONE);
                        }
                        break;
                    case 1:

                        rightHintView.setVisibility(View.GONE);
                        break;
                    default:

                        break;
                }
            }
        });
        return vuew;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getYx_accid() {
        return yx_accid;
    }

    public void setYx_accid(String yx_accid) {
        this.yx_accid = yx_accid;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
