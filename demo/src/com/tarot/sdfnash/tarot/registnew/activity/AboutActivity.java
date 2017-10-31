package com.tarot.sdfnash.tarot.registnew.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.netease.sdfnash.uikit.common.activity.UI;
import com.tarot.sdfnash.tarot.R;

public class AboutActivity extends UI {
    private TextView t5,t6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        t5 = (TextView)findViewById(R.id.t5);
        t6 = (TextView)findViewById(R.id.t6);

        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://form.mikecrm.com/u0Thsx"));
                startActivity(intent);
            }
        });

        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.taluo.com/app/xieyi.htm"));
                startActivity(intent);
            }
        });

    }
}
