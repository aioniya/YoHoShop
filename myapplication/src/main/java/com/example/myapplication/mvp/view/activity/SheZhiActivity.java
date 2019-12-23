package com.example.myapplication.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SheZhiActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView shezhi_fanhui;
    private RelativeLayout tops;
    private Button tuichudenglu;
    private RelativeLayout bottoms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);
        initView();


        shezhi_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void initView() {
        shezhi_fanhui = (TextView) findViewById(R.id.shezhi_fanhui);

        tops = (RelativeLayout) findViewById(R.id.tops);
        tops.setOnClickListener(this);
        tuichudenglu = (Button) findViewById(R.id.tuichudenglu);
        tuichudenglu.setOnClickListener(this);
        bottoms = (RelativeLayout) findViewById(R.id.bottoms);
        bottoms.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tuichudenglu:
                MainActivity.spedit.putBoolean("loginflag", false).commit();
                MainActivity.flag = 1;
                finish();

//                Intent intent = new Intent(SheZhiActivity.this,WoDeActivity.class);
//                startActivity(intent);

                break;
        }
    }
}
