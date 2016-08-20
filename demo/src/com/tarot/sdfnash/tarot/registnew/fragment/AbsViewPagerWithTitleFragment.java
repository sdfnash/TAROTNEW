package com.tarot.sdfnash.tarot.registnew.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.registnew.View.UnScrollViewPager;
import com.tarot.sdfnash.tarot.registnew.View.indicator.IconPagerAdapter;
import com.tarot.sdfnash.tarot.registnew.View.indicator.PageTabLabel;


/**
 * Created by hlj on 2014/9/6.
 *
 * 带标题的fragment
 *
 * 使用PageIndicator+自定义view实现tab
 */
public abstract class AbsViewPagerWithTitleFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private static final String TAG = AbsViewPagerWithTitleFragment.class.getSimpleName();

    protected UnScrollViewPager mViewPager = null;
    protected PageTabLabel mIndicator = null;

    protected View rightHintView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = getContentLayoutId() <= 0 ? R.layout.fragment_viewpager_with_title : getContentLayoutId();
        View view = inflater.inflate(layoutId, container, false);

        mIndicator = (PageTabLabel) view.findViewById(R.id.viewpager_indicator);
        if (getIndicatorWith() > 0) {
            view.findViewById(R.id.viewpager_indicator).getLayoutParams().width = getIndicatorWith();
        }
        mViewPager = (UnScrollViewPager) view.findViewById(R.id.viewpager_vp);
        mViewPager.setCanScroll(canScroll());
        mViewPager.setAdapter(new SampleFragmentPagerAdapter(getAdapterFragmentManager()));
        mIndicator.setViewPager(mViewPager);
        mIndicator.setOnPageChangeListener(this);

        ViewStub vs = (ViewStub) view.findViewById(R.id.viewpager_vs_title);
        int titleId = getCustomTitleId();
        if (titleId > 0) {
            vs.setLayoutResource(titleId);
            vs.inflate();
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 如果是indicator类型的title可以自己设定宽度
     * 
     * @return 宽度，默认是0，表示match_parent
     */
    protected int getIndicatorWith() {
        return 0;
    }

    /**
     * 获取页面资源id,返回<=0 则使用默认的
     */
    protected int getContentLayoutId() {
        return 0;
    }

    protected FragmentManager getAdapterFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    /**
     * 刷新标题
     */
    public void notifyUpdateTitle() {
        if (mIndicator != null) {
            mIndicator.notifyDataSetChanged();
        }
    }

    /**
     * 获取定制的title样式ID，默认返回0，表示除了tab没有其他元素
     */
    protected int getCustomTitleId() {
        return 0;
    }

    /**
     * 能否左右滑动切换tab，默认是可以，子类可以重写
     */
    protected boolean canScroll() {
        return true;
    }

    /**
     * @return fragment 页数
     */
    protected abstract int getCount();

    /**
     * @param position 第几页
     * @return 当前页的 fragment 实例
     */
    protected abstract Fragment getFragment(int position);

    /**
     * @return 当前页的标题
     */
    protected abstract CharSequence getFragmentTitle(int position);

    /**
     * 自定义的title view
     */
    protected View getFragmentTabView(int position) {
        View view;
        if (position == 0) {
            view = LayoutInflater.from(this.getActivity()).inflate(R.layout.layout_tab_item_left, null);
        } else if (position == getCount() - 1) {
            view = LayoutInflater.from(this.getActivity()).inflate(R.layout.layout_tab_item_right, null);
            rightHintView = view.findViewById(R.id.layout_tab_item_right_hint);
        } else {
            // 右面有竖线
            view = LayoutInflater.from(this.getActivity()).inflate(R.layout.layout_tab_item_middle, null);
        }
        return view;
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageSelected(int arg0) {
        Log.d(TAG, "onPageSelected i:" + arg0);
    }

    public class SampleFragmentPagerAdapter extends FragmentStatePagerAdapter implements IconPagerAdapter {

        public SampleFragmentPagerAdapter() {
            super(getActivity().getSupportFragmentManager());
        }

        public SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return AbsViewPagerWithTitleFragment.this.getCount();
        }

        @Override
        public Fragment getItem(int position) {
            return AbsViewPagerWithTitleFragment.this.getFragment(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return AbsViewPagerWithTitleFragment.this.getFragmentTitle(position);
        }

        @Override
        public View getTabView(int index) {
            return AbsViewPagerWithTitleFragment.this.getFragmentTabView(index);
        }
    }

}
