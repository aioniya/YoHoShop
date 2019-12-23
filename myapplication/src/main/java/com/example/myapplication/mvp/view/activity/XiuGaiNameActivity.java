package com.example.myapplication.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreCompoent;
import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.UpdateUserEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

public class XiuGaiNameActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {

    private TextView xiugainame_fanhui;
    private TextView xiugainame_baocun;
    private EditText xiugai_name;


    private void initView() {
        xiugainame_fanhui = (TextView) findViewById(R.id.xiugainame_fanhui);
        xiugainame_baocun = (TextView) findViewById(R.id.xiugainame_baocun);
        xiugai_name =findViewById(R.id.xiugai_name);
    }

    @Override
    public void success(BaseEntity baseEntity, int type) {

        if(type == ApiContact.UPDATE_USER){
            UpdateUserEntity updateUserEntity = (UpdateUserEntity) baseEntity;
            Toast.makeText(this, updateUserEntity.getMsg(), Toast.LENGTH_SHORT).show();

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
        return R.layout.activity_xiu_gai_name;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initView();


        Intent intent = getIntent();

        String userid = intent.getStringExtra("userid");
        String nickname = intent.getStringExtra("nickname");
        String usersex = intent.getStringExtra("usersex");
        String userbirthday = intent.getStringExtra("userbirthday");
        String userheight = intent.getStringExtra("userheight");
        String userweight = intent.getStringExtra("userweight");


        xiugainame_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        xiugainame_baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = xiugai_name.getText().toString();

                JSONObject job = new JSONObject();
                try {
                    job.put("userid",userid);
                    job.put("nickname",s);
                    job.put("usersex",usersex);
                    job.put("userbirthday",userbirthday);
                    job.put("userheight",userheight);
                    job.put("userweight",userweight);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mPresenter.lreAll(job.toString(), ApiContact.UPDATE_USER);

            }
        });
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }
}
