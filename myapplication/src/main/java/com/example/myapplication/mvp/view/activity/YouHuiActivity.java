package com.example.myapplication.mvp.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreCompoent;
import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.YouHuiEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.view.adpater.YouHuiRecyclerViewAdpater;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class YouHuiActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {

    private TextView youhuiquan_fanhui;
    private RecyclerView youhuiquan_recyclerview;
    List<YouHuiEntity.ValuesBean> values;
    ArrayList<YouHuiEntity.ValuesBean> list = new ArrayList<>();

    YouHuiRecyclerViewAdpater youHuiRecyclerViewAdpater;
    private void initView() {
        youhuiquan_fanhui = (TextView) findViewById(R.id.youhuiquan_fanhui);
        youhuiquan_recyclerview = (RecyclerView) findViewById(R.id.youhuiquan_recyclerview);
    }

    @Override
    public void success(BaseEntity baseEntity, int type) {

        if(type == ApiContact.YOUHUI_LIST){
            list.clear();
            YouHuiEntity youHuiEntity = (YouHuiEntity) baseEntity;
            values = youHuiEntity.getValues();
            Log.e("youHuiEntity",values.toString());

            list.addAll(values);
            youHuiRecyclerViewAdpater.notifyDataSetChanged();
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
        return R.layout.activity_you_hui;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initView();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_id","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(jsonObject.toString(), ApiContact.YOUHUI_LIST);

        youhuiquan_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        youHuiRecyclerViewAdpater = new YouHuiRecyclerViewAdpater(list, this) {
            @Override
            public void OnClick(baseview holder, int position) {
                holder.youhui_shuoming.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(holder.youhui_shuoming.isChecked()){
                            holder.youhui_shuoming.setText("使用说明 ∧");
                            holder.linearLayout.setVisibility(View.VISIBLE);
                        }else{
                            holder.youhui_shuoming.setText("使用说明 ∨");
                            holder.linearLayout.setVisibility(View.GONE);
                        }





//                        int count = list.get(position).getCount();
//                        Toast.makeText(YouHuiActivity.this, count+"", Toast.LENGTH_SHORT).show();
//                        count++;
//                        if(count%2==0){
//                            holder.youhui_shuoming.setText("使用说明 ∧");
//                            holder.linearLayout.setVisibility(View.VISIBLE);
//                        }else{
//                            holder.youhui_shuoming.setText("使用说明 ∨");
//                            holder.linearLayout.setVisibility(View.GONE);
//                        }
                    }
                });

            }
        };
        youhuiquan_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        youhuiquan_recyclerview.setAdapter(youHuiRecyclerViewAdpater);

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
