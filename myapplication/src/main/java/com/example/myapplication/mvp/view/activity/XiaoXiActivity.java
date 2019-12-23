package com.example.myapplication.mvp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class XiaoXiActivity extends AppCompatActivity {

    private TextView xiaoxi_fanhui;

    private SmartRefreshLayout xiaoxi_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_xi);
        initView();


        xiaoxi_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xiaoxi_refresh.setEnableLoadmore(false);
        xiaoxi_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });

    }

    private void initView() {
        xiaoxi_fanhui = (TextView) findViewById(R.id.xiaoxi_fanhui);

        xiaoxi_refresh = (SmartRefreshLayout) findViewById(R.id.xiaoxi_refresh);
    }
}
