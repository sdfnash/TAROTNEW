package com.tarot.sdfnash.tarot.registnew.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.sdfnash.uikit.common.activity.UI;
import com.netease.sdfnash.uikit.model.ToolBarOptions;
import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.config.preference.Preferences;
import com.tarot.sdfnash.tarot.registnew.Model.CreateOrderModel;
import com.tarot.sdfnash.tarot.registnew.Model.ServiceListModel;
import com.tarot.sdfnash.tarot.registnew.Model.ServiceModel;
import com.tarot.sdfnash.tarot.registnew.RegistHttpClient;

import java.util.List;

public class CreateOrderActivity extends UI {

    ListView list;
    Button btn;
    TextView tv, input;
    String accid, price, s_id;
    EditText edit;

    LinearLayout mLayout;
    private List<ServiceModel> modelList;
    private ServiceListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.create_order;
        setToolBar(R.id.toolbar, options);
        accid = getIntent().getStringExtra("user_accid");
        mLayout=(LinearLayout) View.inflate(this, R.layout.footer_create_order, null);
        btn = (Button) mLayout.findViewById(R.id.btn_create);
        list = findView(R.id.service_item);
        edit = (EditText) findView(R.id.edit_price);
        input = (TextView) mLayout.findViewById(R.id.tv_input);

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                input.setText("您输入的实际价格为" + s.toString() + "元");
                price=s.toString();
            }
        });

        mAdapter = new ServiceListAdapter();
        list.addFooterView(mLayout);
        list.setAdapter(mAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv.setText("您选择的服务是：" + modelList.get(position).getName() + "，服务价格为：" + modelList.get(position).getPrice());
                s_id = modelList.get(position).getId();
//                price=modelList.get(position).getPrice();
            }
        });
        tv = findView(R.id.tv_select);
        RegistHttpClient.getInstance().getServiceList(Preferences.getUserId(), new RegistHttpClient.GetTarotServiceListHttpCallBack<ServiceListModel.Data>() {
            @Override
            public void onSuccess(ServiceListModel.Data data) {
                modelList = data.getList();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(int code, String errorMsg) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setClickable(false);
                if (TextUtils.isEmpty(s_id) || TextUtils.isEmpty(price)) {
                    Toast.makeText(CreateOrderActivity.this, "请选择订单", Toast.LENGTH_SHORT).show();
                    return;
                }
                RegistHttpClient.getInstance().createOrder(Preferences.getUserId(), Preferences.getUserToken(), "wx", accid, s_id, price, "wx", new RegistHttpClient.CreateOrderHttpCallBack<CreateOrderModel.Data>() {
                    @Override
                    public void onSuccess(CreateOrderModel.Data data) {
                        Toast.makeText(CreateOrderActivity.this, "生成订单成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailed(int code, String errorMsg) {
                        Toast.makeText(CreateOrderActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                        btn.setClickable(true);
                    }
                });
            }
        });

    }

    public class ServiceListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (modelList == null) {
                return 0;
            } else {
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
                LayoutInflater mInflater = LayoutInflater.from(CreateOrderActivity.this);
                convertView = mInflater.inflate(R.layout.item_service_list, null);
                vh.mTvName = (TextView) convertView.findViewById(R.id.tv_name);
                vh.mTvDes = (TextView) convertView.findViewById(R.id.tv_des);
                vh.mTvGood = (TextView) convertView.findViewById(R.id.tv_good);
                vh.mTvPrice = (TextView) convertView.findViewById(R.id.tv_price);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            if (!TextUtils.isEmpty(modelList.get(position).getGood_count())) {
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
