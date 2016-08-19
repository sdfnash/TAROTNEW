package com.tarot.sdfnash.tarot.registnew.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.netease.sdfnash.uikit.common.fragment.TFragment;
import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.registnew.Model.CommentDetailModel;
import com.tarot.sdfnash.tarot.registnew.RegistHttpClient;
import com.tarot.sdfnash.tarot.registnew.View.DipPixUtil;
import com.tarot.sdfnash.tarot.registnew.View.EmptyLayout;
import com.tarot.sdfnash.tarot.registnew.View.PagingListView;
import com.tarot.sdfnash.tarot.registnew.adapter.AbstractAdapter;

/**
 * Created by sdfnash on 16/8/14.
 */
public class MainCommentFragment extends TFragment{
    private EmptyLayout mEmptyLayout;
    private PagingListView mPagingListView;
    private ListView mListView;
    private int mPage = 1;
    private CommentAdapter mAdapter;
    private int tID,sID,num;
    private static final String TAG = MainCommentFragment.class.getSimpleName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_comment_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPagingListView =  findView(R.id.listView);
        mListView = mPagingListView.getListView();
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        //initEmptyLayout();

        initListView();

        if (mAdapter.isEmpty()) {
//            showLoadingDialog();
            refreshData();
        } else {
            mListView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPagingListView.setRefreshing(true);
                    refreshData();
                }
            }, 500);
        }
    }

//    public void initEmptyLayout() {
////        mPagingListView.setEmptyViewTip(R.string.no_recommend_student);
////        mPagingListView.setEmptyBtnListenr();
//        mEmptyLayout = mPagingListView.getEmptyView();
//        mEmptyLayout.setEmptyLayoutIcon(R.drawable.ic_orders_blankpage);
//        mEmptyLayout.setEmptyLayoutInstructionText(R.string.sms_center);
//        mEmptyLayout.setEmptyLayoutButtonVisibility(View.INVISIBLE);
//    }

    private void initListView() {
        mPagingListView.setShowEmptyView(false);

//        TextView header = new TextView(this);
//        header.setText("asdfjkl;");
        mListView.setDividerHeight(0);
        View footView = new View(getActivity());
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DipPixUtil.dip2px(getActivity(), 20));
        footView.setLayoutParams(params);
        mListView.addFooterView(footView);

        mAdapter = new CommentAdapter(getActivity());
//        mAdapter.setData(mListManager.getList());
        mListView.setAdapter(mAdapter);

        mPagingListView.setDataListener(new PagingListView.DataListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }

            @Override
            public void onLoading() {
                loadData();
            }
        });

        mPagingListView.hiddenFooterView();
    }

    private void refreshData() {
        RegistHttpClient.getInstance().clistCode(tID, sID, mPage, num, new RegistHttpClient.ClistHttpCallBack<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }

            @Override
            public void onFailed(int code, String errorMsg) {

            }
        });
    }

    private void loadData(){

    }

    public class CommentAdapter extends AbstractAdapter<CommentDetailModel>{

        public CommentAdapter(Context context){super(context);}
        @Override
        public int getLayoutId() {
            return 0;
        }

        @Override
        public void initView(int position, View convertView, CommentDetailModel item) {

        }
    }
}
