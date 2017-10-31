package com.tarot.sdfnash.tarot.registnew.activity;

import android.os.Bundle;

import com.netease.sdfnash.uikit.common.activity.UI;
import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.registnew.fragment.MainServiceFragment;

public class ServiceTarotActivity extends UI {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_tarot);
        MainServiceFragment f=new MainServiceFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.layout,f).commit();
    }
}
