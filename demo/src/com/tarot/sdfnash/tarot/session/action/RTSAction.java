package com.tarot.sdfnash.tarot.session.action;

import android.widget.Toast;

import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.rts.activity.RTSActivity;
import com.netease.sdfnash.uikit.common.util.sys.NetworkUtil;
import com.netease.sdfnash.uikit.session.actions.BaseAction;

/**
 * Created by huangjun on 2015/7/7.
 */
public class RTSAction extends BaseAction {

    public RTSAction() {
        super(R.drawable.message_plus_rts_selector, R.string.input_panel_RTS);
    }

    @Override
    public void onClick() {
        if (NetworkUtil.isNetAvailable(getActivity())) {
            RTSActivity.startSession(getActivity(), getAccount(), RTSActivity.FROM_INTERNAL);
        } else {
            Toast.makeText(getActivity(), R.string.network_is_not_available, Toast.LENGTH_SHORT).show();
        }

    }
}
