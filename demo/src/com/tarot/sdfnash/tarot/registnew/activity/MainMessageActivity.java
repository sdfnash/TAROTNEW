package com.tarot.sdfnash.tarot.registnew.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.registnew.fragment.MainMessageFragment;

public class MainMessageActivity extends AppCompatActivity {
    private String tId,sId,yx_accid;
    private int page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_message);
        tId=getIntent().getStringExtra("tId");
        sId=getIntent().getStringExtra("sId");
        page=getIntent().getIntExtra("page",1);
        yx_accid=getIntent().getStringExtra("yx_accid");
        final MainMessageFragment f=new MainMessageFragment();
        f.settId(tId);
        f.setsId(sId);
        f.setYx_accid(yx_accid);
        getSupportFragmentManager().beginTransaction().add(R.id.main_message_fragment,f).commit();
        Handler h=new Handler();
        h.post(new Runnable() {
            @Override
            public void run() {
                f.setCurrentItem(page);
            }
        });

    }
}
