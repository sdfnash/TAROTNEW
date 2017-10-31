package com.tarot.sdfnash.tarot.registnew.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.sdfnash.uikit.common.activity.UI;
import com.netease.sdfnash.uikit.common.ui.imageview.ImageViewEx;
import com.netease.sdfnash.uikit.model.ToolBarOptions;
import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.config.preference.Preferences;
import com.tarot.sdfnash.tarot.registnew.Model.TarotListModel;
import com.tarot.sdfnash.tarot.registnew.Model.TarotModel;
import com.tarot.sdfnash.tarot.registnew.RegistHttpClient;
import com.tarot.sdfnash.tarot.session.SessionHelper;

import java.util.List;

public class MyCollectionActivity extends UI {
    private SwipeRefreshLayout swipeRefreshLayout;//用于下拉刷新list
    private TextView refresh_notice;

    private TarotListAdapter mAdapter;
    public Handler mHandler;

    private GridView find_grid;
    private List<TarotModel> tarotList;//存储从服务器端传过来的塔罗师信息列
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.my_collection;
        setToolBar(R.id.toolbar, options);
        findViews();
        setTaLuoShiFounds();
        setFind_grid();
    }

    private void findViews() {

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.list_refresh);
        refresh_notice = (TextView) findViewById(R.id.refresh_notice);

        find_grid = (GridView) findViewById(R.id.find_grid);
        mAdapter = new TarotListAdapter();
        find_grid.setAdapter(mAdapter);
        find_grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int position1=position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MyCollectionActivity.this);
                builder.setMessage("取消关注");
                builder.setTitle("提示");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        RegistHttpClient.getInstance().setCollection(Preferences.getUserId(), Preferences.getUserToken(), tarotList.get(position1).getId(), "0", new RegistHttpClient.SetTarotCollectionHttpCallBack<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MyCollectionActivity.this,"取消关注成功",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailed(int code, String errorMsg) {

                            }
                        });
                    }
                });
                builder.setCancelable(true);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();

                return false;
            }
        });
    }

    private void setTaLuoShiFounds() {


        RegistHttpClient.getInstance().getCollectionList(Preferences.getUserId(), Preferences.getUserToken(), new RegistHttpClient.TarotCollectionListHttpCallBack<TarotListModel.DataBean>() {
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
                LayoutInflater mInflater = LayoutInflater.from(MyCollectionActivity.this);
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
            vh.mImgAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(MyCollectionActivity.this, TarotActivity.class);
                    i.putExtra("tls_accid",tarotList.get(position).getYx_accid());
                    i.putExtra("t_id",tarotList.get(position).getId());
                    startActivity(i);
                }
            });
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
                    Intent i=new Intent(MyCollectionActivity.this, MainMessageActivity.class);
                    i.putExtra("tId",tarotList.get(position).getId());
                    i.putExtra("yx_accid",tarotList.get(position).getYx_accid());

                    startActivity(i);
                }
            });
            vh.mTvChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SessionHelper.startP2PSession(MyCollectionActivity.this, tarotList.get(position).getYx_accid());
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
