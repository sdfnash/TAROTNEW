package com.tarot.sdfnash.tarot.registnew.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.netease.sdfnash.uikit.common.fragment.TFragment;
import com.netease.sdfnash.uikit.common.ui.imageview.HeadImageView;
import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.config.preference.Preferences;
import com.tarot.sdfnash.tarot.registnew.Model.CommentListModel;
import com.tarot.sdfnash.tarot.registnew.Model.CommentShowModel;
import com.tarot.sdfnash.tarot.registnew.RegistHttpClient;
import com.tarot.sdfnash.tarot.registnew.View.DipPixUtil;
import com.tarot.sdfnash.tarot.registnew.View.EmptyLayout;
import com.tarot.sdfnash.tarot.registnew.View.PagingListView;
import com.tarot.sdfnash.tarot.registnew.activity.CommitCommentActivity;
import com.tarot.sdfnash.tarot.registnew.adapter.AbstractAdapter;
import com.tarot.sdfnash.tarot.session.SessionHelper;

/**
 * Created by sdfnash on 16/8/14.
 */
public class MainCommentFragment extends TFragment implements View.OnClickListener {
    private EmptyLayout mEmptyLayout;
    private PagingListView mPagingListView;
    private ListView mListView;
    private int mPage = 1;
    private CommentAdapter mAdapter;
    private int tID, sID, num=10;
    private String tls_accid;
    private static final String TAG = MainCommentFragment.class.getSimpleName();
    private HeadImageView mImgAvatar;
    private TextView mTvName, mTvPoint, mTvSpeed, mTvHelp, mTvRight, mTvAttitude, mTvInsult;
    private ImageView mImgPoint;
    private RatingBar mRateSpeed, mRateRight, mRateHelp, mRateAttitude;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_comment_fragment, container, false);
    }

    public void setTID(String tId){
        this.tID=Integer.valueOf(tId);
    }

    public void setTls_accid(String tls_accid) {
        this.tls_accid = tls_accid;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mImgAvatar = findView(R.id.img_comment_avatar);
        mTvName = findView(R.id.tv_comment_name);
        mTvPoint = findView(R.id.tv_total_comment);
        mTvSpeed = findView(R.id.tv_point_speed);
        mTvAttitude = findView(R.id.tv_point_attitude);
        mTvHelp = findView(R.id.tv_point_help);
        mTvRight = findView(R.id.tv_point_right);
        mTvInsult = findView(R.id.btn_insult);
        mTvInsult.setOnClickListener(this);
        mPagingListView = findView(R.id.listView);
        mListView = mPagingListView.getListView();
        mRateSpeed = findView(R.id.speed_bar);
        mRateAttitude = findView(R.id.attitude_bar);
        mRateHelp = findView(R.id.help_bar);
        mRateRight = findView(R.id.right_bar);
        mRateRight.setIsIndicator(false);
        mRateHelp.setIsIndicator(false);
        mRateAttitude.setIsIndicator(false);
        mRateSpeed.setIsIndicator(false);
        init(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mTvInsult)) {
            SessionHelper.startP2PSession(getActivity(), tls_accid);
        }
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

        RegistHttpClient.getInstance().clistCode(tID, sID, mPage, num, new RegistHttpClient.ClistHttpCallBack<CommentListModel.Data>() {
            @Override
            public void onSuccess(CommentListModel.Data model) {

                refreshHeader(model);
                mAdapter.setData(model.getList());
                mAdapter.notifyDataSetChanged();
                mPagingListView.setRefreshing(false);
                if (model.getPage() < model.getTotal()) {
                    mPage++;
                    mPagingListView.showFooterView();
                } else {
                    mPagingListView.hiddenFooterView();
                }

            }

            @Override
            public void onFailed(int code, String errorMsg) {

            }
        });
    }

    private void loadData() {
        RegistHttpClient.getInstance().clistCode(tID, sID, mPage, num, new RegistHttpClient.ClistHttpCallBack<CommentListModel.Data>() {
            @Override
            public void onSuccess(CommentListModel.Data model) {
                refreshHeader(model);
                mAdapter.addData(model.getList());
                mAdapter.notifyDataSetChanged();
                mPagingListView.setRefreshing(false);
                if (model.getPage() < model.getTotal()) {
                    mPage++;
                    mPagingListView.showFooterView();
                } else {
                    mPagingListView.hiddenFooterView();
                }

            }

            @Override
            public void onFailed(int code, String errorMsg) {

            }
        });
    }

    private void refreshHeader(CommentListModel.Data model) {
        mImgAvatar.loadBuddyAvatar(model.getInfo().getTls_photo());
        mTvName.setText(model.getInfo().getTls_nickname());
        if (model.getInfo().getAvg_star()>=1&& model.getInfo().getAvg_star()<2 ) {
            mImgPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_01));
        } else if (model.getInfo().getAvg_star()>=2&& model.getInfo().getAvg_star()<3 ) {
            mImgPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_02));
        } else if (model.getInfo().getAvg_star()>=3&& model.getInfo().getAvg_star()<4 ) {
            mImgPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_03));
        } else if (model.getInfo().getAvg_star()>=4&& model.getInfo().getAvg_star()<5 ) {
            mImgPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_04));
        } else if (model.getInfo().getAvg_star()>=5) {
            mImgPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_05));
        }
        mTvPoint.setText(String.valueOf(model.getInfo().getAvg_star()));
        mTvSpeed.setText(String.valueOf(model.getInfo().getAvg_star1()));
        mTvAttitude.setText(String.valueOf(model.getInfo().getAvg_star2()));
        mTvRight.setText(String.valueOf(model.getInfo().getAvg_star3()));
        mTvHelp.setText(String.valueOf(model.getInfo().getAvg_star4()));
        tls_accid = model.getInfo().getTls_accid();
        mRateSpeed.setRating((float) model.getInfo().getAvg_star1());
        mRateAttitude.setRating((float) model.getInfo().getAvg_star2());
        mRateRight.setRating((float) model.getInfo().getAvg_star3());
        mRateHelp.setRating((float) model.getInfo().getAvg_star4());

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==0x01){
                refreshData();
            }
        }
    }

    public class CommentAdapter extends AbstractAdapter<CommentShowModel> {

        public CommentAdapter(Context context) {
            super(context);
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_comment_list;
        }

        @Override
        public void initView(int position, View convertView, final CommentShowModel item) {
            HeadImageView headImageView = (HeadImageView) convertView.findViewById(R.id.img_avatar);
            TextView mTvName = (TextView) convertView.findViewById(R.id.tv_nickname);
            TextView mTvComment = (TextView) convertView.findViewById(R.id.tv_comment);
            Button mBtnChange = (Button) convertView.findViewById(R.id.btn_good_comment);
            Button mBtnDelete = (Button) convertView.findViewById(R.id.btn_delete_comment);
            mBtnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), CommitCommentActivity.class);
                    i.putExtra("tId", tID);
                    i.putExtra("cmtId",item.getId());
                    startActivityForResult(i,0x01);
                }
            });
            mBtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   //// TODO: 16/8/20  
                    refreshData();
                }
            });
            headImageView.loadBuddyAvatar(item.getPhoto_s());
            mTvName.setText(item.getNickname());
            mTvComment.setText(item.getComment());
            if (item.getU_id().equals(Preferences.getUserAccount())) {
                mBtnChange.setVisibility(View.GONE);
                mBtnDelete.setVisibility(View.GONE);
            } else {
                mBtnChange.setVisibility(View.VISIBLE);
                mBtnDelete.setVisibility(View.VISIBLE);
            }


        }
    }
}
