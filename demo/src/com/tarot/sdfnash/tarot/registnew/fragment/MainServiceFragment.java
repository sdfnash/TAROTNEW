package com.tarot.sdfnash.tarot.registnew.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.netease.sdfnash.uikit.common.ui.imageview.HeadImageView;
import com.tarot.sdfnash.tarot.R;
import com.netease.sdfnash.uikit.common.fragment.TFragment;
import com.tarot.sdfnash.tarot.config.preference.Preferences;
import com.tarot.sdfnash.tarot.registnew.Model.ServiceListModel;
import com.tarot.sdfnash.tarot.registnew.Model.ServiceModel;
import com.tarot.sdfnash.tarot.registnew.RegistHttpClient;
import com.tarot.sdfnash.tarot.session.SessionHelper;

import java.util.List;

/**
 * Created by sdfnash on 16/8/14.
 */
public class MainServiceFragment extends TFragment implements View.OnClickListener {

    private HeadImageView mImgAvatar;
    private TextView mTvName;
    private ImageView mImgPoint;
    private ListView mList;
    private TextView mTvInsult;
    private String yxTlsId, tId;
    private List<ServiceModel> modelList;
    private ServiceListAdapter mAdapter;
    private static final String TAG = MainServiceFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_service_fragment, container, false);
    }

    public void setYxTlsId(String yxTlsId) {
        this.yxTlsId = yxTlsId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImgAvatar = findView(R.id.img_service_avatar);
        mTvName = findView(R.id.tv_service_name);
        mImgPoint = findView(R.id.img_service_point);
        mList = findView(R.id.service_list);
        mTvInsult = findView(R.id.btn_insult);
        mAdapter=new ServiceListAdapter();
        mList.setAdapter(mAdapter);
        tId= Preferences.getUserId();
        yxTlsId=Preferences.getYXAcount();
        RegistHttpClient.getInstance().getServiceList(tId, new RegistHttpClient.GetTarotServiceListHttpCallBack<ServiceListModel.Data>() {
            @Override
            public void onSuccess(ServiceListModel.Data data) {
               // mImgAvatar.loadBuddyAvatar(data.getTls().getYx_accid());
                mImgAvatar.doLoadImage(true,"serviceHeader",data.getTls().getPhoto_s(),HeadImageView.DEFAULT_AVATAR_THUMB_SIZE);
                mTvName.setText(data.getTls().getNickname());
                if (Double.parseDouble(data.getTls().getStar()) >= 1 && Double.parseDouble(data.getTls().getStar()) < 2) {
                    mImgPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_01));
                } else if (Double.parseDouble(data.getTls().getStar()) >= 2 && Double.parseDouble(data.getTls().getStar()) < 3) {
                    mImgPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_02));
                } else if (Double.parseDouble(data.getTls().getStar()) >= 3 && Double.parseDouble(data.getTls().getStar()) < 4) {
                    mImgPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_03));
                } else if (Double.parseDouble(data.getTls().getStar()) >= 4 && Double.parseDouble(data.getTls().getStar()) < 5) {
                    mImgPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_04));
                } else if (Double.parseDouble(data.getTls().getStar()) >= 5) {
                    mImgPoint.setImageDrawable(getResources().getDrawable(R.drawable.ic_stat_05));
                }
                modelList = data.getList();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(int code, String errorMsg) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mTvInsult)) {
            SessionHelper.startP2PSession(getActivity(), yxTlsId);
        }
    }

    public class ServiceListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if(modelList==null){
                return 0;
            }else {
                return modelList.size();
            }

        }

        @Override
        public Object getItem(int position) {
            return modelList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh = null;
            if (convertView == null) {
                vh = new ViewHolder();
                LayoutInflater mInflater = LayoutInflater.from(getActivity());
                convertView = mInflater.inflate(R.layout.item_service_list, null);
                vh.mTvName = (TextView) convertView.findViewById(R.id.tv_name);
                vh.mTvDes = (TextView) convertView.findViewById(R.id.tv_des);
                vh.mTvGood = (TextView) convertView.findViewById(R.id.tv_good);
                vh.mTvPrice = (TextView) convertView.findViewById(R.id.tv_price);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            if(!TextUtils.isEmpty(modelList.get(position).getGood_count())){
                vh.mTvGood.setText("近77天好评数:" + modelList.get(position).getGood_count());
            }
            vh.mTvPrice.setText(modelList.get(position).getPrice());
            vh.mTvDes.setText(modelList.get(position).getDescription());
            vh.mTvName.setText(modelList.get(position).getName());
            return convertView;
        }

        public class ViewHolder {
            TextView mTvName, mTvDes, mTvPrice, mTvGood;

        }
    }
}
