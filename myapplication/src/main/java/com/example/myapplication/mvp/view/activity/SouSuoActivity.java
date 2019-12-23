package com.example.myapplication.mvp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SouSuoActivity extends AppCompatActivity {

    private TextView sou_quxiao;
    private RelativeLayout tops;
    private RelativeLayout center;
    private TextView zuijin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
        initView();
        sou_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        sou_quxiao = (TextView) findViewById(R.id.sou_quxiao);
        tops = (RelativeLayout) findViewById(R.id.tops);
        center = (RelativeLayout) findViewById(R.id.center);
        zuijin = (TextView) findViewById(R.id.zuijin);
    }
}
