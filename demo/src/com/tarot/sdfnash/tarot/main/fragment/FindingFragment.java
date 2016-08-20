package com.tarot.sdfnash.tarot.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.registnew.Model.TarotListModel;
import com.tarot.sdfnash.tarot.registnew.RegistHttpClient;
import com.tarot.sdfnash.tarot.registnew.activity.MainMessageActivity;
import com.netease.sdfnash.uikit.common.ui.imageview.ImageViewEx;
import com.tarot.sdfnash.tarot.session.SessionHelper;

import java.util.List;


public class FindingFragment extends MainTabFragment {


    private SwipeRefreshLayout swipeRefreshLayout;//用于下拉刷新list
    private TextView refresh_notice;

    private TarotListAdapter mAdapter;
    public Handler mHandler;

    private GridView find_grid;
    private List<TarotListModel.DataBean.Tarot> tarotList;//存储从服务器端传过来的塔罗师信息列


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCurrent();
    }

    @Override
    protected void onInit() {
        findViews();
        setTaLuoShiFounds();
        setFind_grid();
    }

    private void findViews() {

        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.list_refresh);
        refresh_notice = (TextView) getView().findViewById(R.id.refresh_notice);

        find_grid = (GridView) getView().findViewById(R.id.find_grid);
        mAdapter = new TarotListAdapter();
        find_grid.setAdapter(mAdapter);
    }


    /**
     * 设置塔罗师对象列表
     */

    private void setTaLuoShiFounds() {


        RegistHttpClient.getInstance().getList(1, 0, new RegistHttpClient.TarotListHttpCallBack<TarotListModel.DataBean>() {
            @Override
            public void onSuccess(TarotListModel.DataBean tarotListModel) {
                /**
                 * id : 3
                 * nickname : aaa23
                 * photo :
                 * photo_s :
                 * about : aaa
                 * online_status : 1
                 * star : 3.0
                 * yx_accid : taluoshi_3
                 */
                tarotList=tarotListModel.getList();
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailed(int code, String errorMsg) {

            }
        });

    }

    public class TarotListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (tarotList != null) {
                return tarotList.size();
            } else {
                return 0;
            }

        }

        @Override
        public Object getItem(int position) {
            return tarotList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder vh = null;
            if (convertView == null) {
                vh = new ViewHolder();
                LayoutInflater mInflater = LayoutInflater.from(getActivity());
                convertView = mInflater.inflate(R.layout.find_grid_item, null);
                vh.mImgAvatar = (ImageViewEx) convertView.findViewById(R.id.tarot_head);
                vh.mTvName = (TextView) convertView.findViewById(R.id.tarot_name);
                vh.mTvPoint = (ImageView) convertView.findViewById(R.id.tarot_star);
                vh.mTvComment = (TextView) convertView.findViewById(R.id.tarot_judge);
                vh.mTvChat = (TextView) convertView.findViewById(R.id.tarot_comm);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.mImgAvatar.load(tarotList.get(position).getPhoto());
            vh.mTvName.setText(tarotList.get(position).getNickname());
            if("1.0".equals(tarotList.get(position).getStar())){
                vh.mTvPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_01));
            }else if("2.0".equals(tarotList.get(position).getStar())){
                vh.mTvPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_02));
            }else if("3.0".equals(tarotList.get(position).getStar())){
                vh.mTvPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_03));
            }else if("4.0".equals(tarotList.get(position).getStar())){
                vh.mTvPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_04));
            }else if("5.0".equals(tarotList.get(position).getStar())){
                vh.mTvPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_05));
            }
            /**
             * id : 3
             * nickname : aaa23
             * photo :
             * photo_s :
             * about : aaa
             * online_status : 1
             * star : 3.0
             * yx_accid : taluoshi_3
             */
            vh.mTvComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getActivity(), MainMessageActivity.class);
                    i.putExtra("tId",tarotList.get(position).getId());
                    i.putExtra("yx_accid",tarotList.get(position).getYx_accid());

                    startActivity(i);
                }
            });
            vh.mTvChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SessionHelper.startP2PSession(getActivity(), tarotList.get(position).getYx_accid());
                }
            });

            return convertView;
        }

        class ViewHolder {
            ImageViewEx mImgAvatar;
            TextView mTvName, mTvComment, mTvChat;
            ImageView mTvPoint;
        }

    }

    /**
     * 设置塔罗师大厅网格显示
     */
    private void setFind_grid() {


//        adapter = new SimpleAdapter(FindingFragment.this.getActivity(), list, R.layout.find_grid_item,
//                new String[]{"imageId", "name", "starNum", "judge", "comm"},
//                new int[]{R.id.taluoshi_head, R.id.taluoshi_name, R.id.taluoshi_star, R.id.taluoshi_judge, R.id.taluoshi_comm});
//
//        for (int i = 0; i < taLuoShiFounds.size(); i++) {
//            HashMap map = new HashMap();
//            map.put("imageId", taLuoShiFounds.get(i).getImageId());
//            map.put("name", taLuoShiFounds.get(i).getName());
//            map.put("starNum", taLuoShiFounds.get(i).getStarNum());
//            map.put("judge", "评价");
//            if (taLuoShiFounds.get(i).getFlag()) {
//                map.put("comm", "立即咨询");
//            } else {
//                map.put("comm", "留言提醒");
//            }
//            list.add(map);
//        }



        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {//这里!下拉刷新联网获取最新数据!

                        try {
                            Thread.sleep(2000);
                            setTaLuoShiFounds();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(1);
                    }
                }).start();

            }
        });
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {//这里!下拉刷新的界面刷新
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        swipeRefreshLayout.setRefreshing(false);
                        mAdapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }
        };


    }
}
