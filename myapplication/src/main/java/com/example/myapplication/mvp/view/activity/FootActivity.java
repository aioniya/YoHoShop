package com.example.myapplication.mvp.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreCompoent;
import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.FootBean;
import com.example.myapplication.mvp.model.entity.FootEntity;
import com.example.myapplication.mvp.model.entity.ShouCangBean;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.view.adpater.FootRecyclerViewAdpater;
import com.example.myapplication.mvp.view.adpater.ShopShouCangRecyclerViewAdpater;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FootActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {

    private TextView foot_fanhui;
    private TextView foot_qingkong;
    private RecyclerView foot_recyclerview;

    ArrayList<FootBean> listbean = new ArrayList();

    FootRecyclerViewAdpater footRecyclerViewAdpater;


    private void initView() {
        foot_fanhui = (TextView) findViewById(R.id.foot_fanhui);
        foot_qingkong = (TextView) findViewById(R.id.foot_qingkong);

        foot_recyclerview = (RecyclerView) findViewById(R.id.foot_recyclerview);
    }

    @Override
    public void success(BaseEntity baseEntity, int type) {
        if(type == ApiContact.FOOT_LIST){
            FootEntity footEntity = (FootEntity) baseEntity;

            List<FootEntity.ValuesBean> values = footEntity.getValues();
            for (int i = 0; i < values.size(); i++) {

                listbean.add(new FootBean(values.get(i).getGoods_name(), Api.APP_DOMAIN+values.get(i).getGoods_img_path(),values.get(i).getGoods_discount_price()));
                Log.e("valuessss1",values.get(i).getGoods_name());
                Log.e("valuessss2",Api.APP_DOMAIN+values.get(i).getGoods_img_path());
                Log.e("valuessss3",values.get(i).getGoods_discount_price());
            }

            footRecyclerViewAdpater.notifyDataSetChanged();
        }

    }

    @Override
    public void error(String error) {

    }

    @Override
    public void refreshSuccess(BaseEntity entity) {

    }

    @Override
    public void loadSuccess(BaseEntity entity) {

    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreCompoent.builder().appComponent(appComponent).lreMoudle(new LreMoudle(this)).build().inject(this);

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_foot;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initView();

        JSONObject job = new JSONObject();
        try {
            job.put("userid", "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(job.toString(), ApiContact.FOOT_LIST);


        footRecyclerViewAdpater = new FootRecyclerViewAdpater(listbean,this) {
            @Override
            public void show(baseview holder, int position) {
                holder.ce_shanchu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listbean.remove(position);
                        footRecyclerViewAdpater.notifyDataSetChanged();
                    }
                });
            }
        };
        foot_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        foot_recyclerview.setAdapter(footRecyclerViewAdpater);


        foot_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        foot_qingkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listbean.clear();
                footRecyclerViewAdpater.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showMessage(@NonNull String message) {




    }
}
