package com.tarot.sdfnash.tarot.registnew.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.tarot.sdfnash.tarot.R;
import com.tarot.sdfnash.tarot.registnew.fragment.MainMessageFragment;

public class MainMessageActivity extends AppCompatActivity {
    private String tId,sId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_message);
        tId=getIntent().getStringExtra("tId");
        final MainMessageFragment f=new MainMessageFragment();
        Handler h=new Handler();
        h.post(new Runnable() {
            @Override
            public void run() {
                f.setCurrentItem(1);
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.main_message_fragment,f).commit();
    }
}
