package com.example.myapplication.mvp.view.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreCompoent;
import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.TouXiangEntity;
import com.example.myapplication.mvp.model.entity.UpdateUserEntity;
import com.example.myapplication.mvp.model.entity.UserEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.wyp.avatarstudio.AvatarStudio;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class GeRenActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {

    private TextView geren_fanhui;
    private TextView geren_sex;
    private TextView geren_nianyue;
    private TextView geren_name;
    private RelativeLayout geren_touxiang;
    private TextView geren_nicheng_jian;
    private RelativeLayout geren_nicheng;
    private RelativeLayout geren_qianming;
    private RelativeLayout geren_huiyuan;
    private TextView geren_ma_jian;
    private RelativeLayout geren_erweima;
    private TextView geren_kouling_jian;
    private RelativeLayout geren_kouling;
    private TextView geren_xingbie_jian;
    private RelativeLayout geren_xingbie;
    private TextView geren_shengri_jian;
    private RelativeLayout geren_shengri;
    private RelativeLayout geren_shengao;
    private RelativeLayout geren_tizhong;
    private RelativeLayout geren_wodexingbie;
    private ImageView touxiangs;
    public static String touxiang=null;


    String user_id =null;
    String nick_name=null;
    String user_head = null;
    String user_sex = null;
    String user_weight = null;
    String user_birthday = null;
    String user_height = null;

    private void initView() {

        geren_fanhui = (TextView) findViewById(R.id.geren_fanhui);
        geren_qianming =findViewById(R.id.geren_qianming);
        geren_nicheng =findViewById(R.id.geren_nicheng);
        geren_nicheng_jian =findViewById(R.id.geren_nicheng_jian);
        geren_touxiang =findViewById(R.id.geren_touxiang);
        geren_tizhong =findViewById(R.id.geren_tizhong);
        geren_shengao =findViewById(R.id.geren_shengao);
        geren_shengri =findViewById(R.id.geren_shengri);
        geren_shengri_jian =findViewById(R.id.geren_shengri_jian);
        geren_wodexingbie =findViewById(R.id.geren_wodexingbie);
        geren_xingbie_jian =findViewById(R.id.geren_xingbie_jian);
        geren_kouling =findViewById(R.id.geren_kouling);
        geren_kouling_jian =findViewById(R.id.geren_kouling_jian);
        geren_erweima =findViewById(R.id.geren_erweima);
        geren_erweima =findViewById(R.id.geren_erweima);
        geren_ma_jian =findViewById(R.id.geren_ma_jian);
        geren_huiyuan =findViewById(R.id.geren_huiyuan);
        touxiangs =findViewById(R.id.touxiangs);
        geren_name =findViewById(R.id.geren_name);
        geren_nianyue =findViewById(R.id.geren_nianyue);
        geren_sex =findViewById(R.id.geren_sex);
    }

    @Override
    public void success(BaseEntity baseEntity, int type) {
        Log.e("s----------s","123");
        if(type==ApiContact.TOUXIANG_LIST){
            TouXiangEntity touXiangEntity = (TouXiangEntity) baseEntity;
            Log.e("touXiangEntity",touXiangEntity.getMsg());
            if(touXiangEntity!=null){
                String msg = touXiangEntity.getMsg();

                if(msg.equals("修改成功")){
                    Glide.with(GeRenActivity.this).load(touxiang).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(touxiangs);
                }

            }

        }
        if(type == ApiContact.USER_LIST){
            UserEntity userEntity = (UserEntity) baseEntity;
            List<UserEntity.ValuesBean> values = userEntity.getValues();
            for (int i = 0; i <values.size() ; i++) {
                 user_id = values.get(i).getUser_id();
                 nick_name = values.get(i).getNick_name();
                 user_head = Api.APP_DOMAIN+values.get(i).getUser_head();
                 user_sex = values.get(i).getUser_sex();
                 user_birthday = values.get(i).getUser_birthday();
                 user_height = values.get(i).getUser_height();
                 user_weight = values.get(i).getUser_weight();

                //头像
                Glide.with(GeRenActivity.this).load(user_head).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(touxiangs);
                //名字
                geren_name.setText(nick_name);
                //sex
                geren_sex.setText(user_sex);
                //bri
                geren_nianyue.setText(user_birthday);
            }
        }
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
        return R.layout.activity_ge_ren;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


        initView();
        geren_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        geren_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AvatarStudio.Builder builder = new AvatarStudio.Builder(GeRenActivity.this);
                builder.setTextColor(Color.BLACK)
                        .setText("相机","相册","取消")
                        .needCrop(true)
                        .dimEnabled(true)
                        .show(new AvatarStudio.CallBack() {
                            @Override
                            public void callback(String uri) {
                                touxiang=uri;


                                File file = new File(uri);
                                List<MultipartBody.Part> partList = filesToMultipartBodyParts(file);
                                mPresenter.LoadTu(partList, ApiContact.TOUXIANG_LIST);



                            }
                        });
            }
        });

        geren_erweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GeRenActivity.this, ErWeiMaActivity.class);
                startActivity(intent);
            }
        });
        geren_nicheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GeRenActivity.this,XiuGaiNameActivity.class);
                intent.putExtra("userid",user_id);
                intent.putExtra("nickname",nick_name);
                intent.putExtra("usersex",user_sex);
                intent.putExtra("userbirthday",user_birthday);
                intent.putExtra("userheight",user_height);
                intent.putExtra("userweight",user_weight);

                startActivity(intent);

            }
        });

        //性别

        geren_wodexingbie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                AlertDialog.Builder builder = new AlertDialog.Builder(GeRenActivity.this);
                builder.setTitle("提示");
                builder.setNegativeButton("女", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        JSONObject jobb = new JSONObject();
                        try {
                            jobb.put("userid","1");
                            jobb.put("nickname",nick_name);
                            jobb.put("usersex","女");
                            jobb.put("userbirthday",user_birthday);
                            jobb.put("userheight",user_height);
                            jobb.put("userweight",user_weight);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mPresenter.lreAll(jobb.toString(), ApiContact.UPDATE_USER);


                        JSONObject job = new JSONObject();
                        try {
                            job.put("userid", "1");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mPresenter.lreAll(job.toString(), ApiContact.USER_LIST);
                    }
                });


                builder.setPositiveButton("男", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        JSONObject jobb = new JSONObject();
                        try {
                            jobb.put("userid","1");
                            jobb.put("nickname",nick_name);
                            jobb.put("usersex","男");
                            jobb.put("userbirthday",user_birthday);
                            jobb.put("userheight",user_height);
                            jobb.put("userweight",user_weight);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mPresenter.lreAll(jobb.toString(), ApiContact.UPDATE_USER);


                        JSONObject job = new JSONObject();
                        try {
                            job.put("userid", "1");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mPresenter.lreAll(job.toString(), ApiContact.USER_LIST);
                    }
                });

                builder.create().show();


            }
        });
        //生日
        geren_shengri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //身高
        geren_shengao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    private List<MultipartBody.Part> filesToMultipartBodyParts(File file) {

            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file );
            builder.addFormDataPart("uploadedfile",System.currentTimeMillis()+".jpg",requestBody);
            builder.addFormDataPart("userid","1");

        return builder.build().parts();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }

    @Override
    protected void onResume() {
        super.onResume();

        JSONObject job = new JSONObject();

        try {
            job.put("userid", "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(job.toString(), ApiContact.USER_LIST);
    }
}
