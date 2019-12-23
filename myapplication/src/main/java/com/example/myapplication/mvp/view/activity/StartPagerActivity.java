package com.example.myapplication.mvp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.myapplication.R;
import com.gyf.barlibrary.ImmersionBar;

import java.util.Timer;
import java.util.TimerTask;

public class StartPagerActivity extends AppCompatActivity {
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==1001){
                count++;
                if(count==3){
                    Intent intent = new Intent(StartPagerActivity.this,StartActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(0,R.anim.out);
                    timer.cancel();

                }
            }
        }
    };
    Timer timer;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_pager);
        ImmersionBar.with(this).init();



        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1001);
            }
        },0,1500);

    }
}
