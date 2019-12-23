package com.example.myapplication.mvp.view.activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreCompoent;
import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.GoodsEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.google.android.material.appbar.AppBarLayout;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

public class GoodsActivity extends BaseActivity<LrePresenter> implements View.OnClickListener, LreContact.LreView {

    private AppBarLayout appBarLayout;
    private ImageView goods_image;
    private TextView goods_name;
    private Button goods_fanhui;
    private Button goods_fenxiang;
    private Button goods_gongneng;
    private Button goods_gouwuche;
    PopupWindow popupWindow;
    RelativeLayout yanse_yanse;
    NestedScrollView goods_NestedScrollView;


    String image;
    private RelativeLayout yanse;
    private TextView goods_title;

    String id;
    String name;
    private RelativeLayout tools;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goods_fanhui:
                finish();
                break;
            case R.id.goods_fenxiang:

                break;
            case R.id.goods_gongneng:

                break;
            case R.id.goods_gouwuche:

                popupWindow = new PopupWindow(this);
                popupWindow.setFocusable(true);

                popupWindow.setBackgroundDrawable(null);
                popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(1200);
                View inflate = LayoutInflater.from(this).inflate(R.layout.layout_gouwuche, null);
                ImageView goods_guanbi = inflate.findViewById(R.id.goods_guanbi);
                ImageView gouwuche_image = inflate.findViewById(R.id.gouwuche_image);
                Button gouwuche_button = inflate.findViewById(R.id.gouwuche_button);
                TextView chima = inflate.findViewById(R.id.chima);
                RadioGroup radioGroup = inflate.findViewById(R.id.group);
                RadioButton radioButtonone = inflate.findViewById(R.id.gouwuche_radioone);
                RadioButton radioButtontwo = inflate.findViewById(R.id.gouwuche_radiotwo);
                RadioButton radioButtonthree = inflate.findViewById(R.id.gouwuche_radiothree);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (radioButtonone.isChecked()) {
                            chima.setText("选中尺码: " + radioButtonone.getText().toString());
                        }
                        if (radioButtontwo.isChecked()) {
                            chima.setText("选中尺码: " + radioButtontwo.getText().toString());
                        }
                        if (radioButtonthree.isChecked()) {
                            chima.setText("选中尺码: " + radioButtonthree.getText().toString());
                        }
                    }
                });
                gouwuche_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("goods", "点击了");
                        JSONObject job = new JSONObject();

                        try {
                            job.put("userid", "1");
                            job.put("goodsid", id + "");
                            job.put("shopname", name + "");
                            job.put("shopcolor", "1");
                            job.put("shopsize", "1");
                            job.put("shopnum", "1");
                            job.put("shopprice", "50");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mPresenter.lreAll(job.toString(), ApiContact.CAR_LIST);


                    }
                });
                if (image != null) {
                    Glide.with(this).load(image).into(gouwuche_image);
                }


                goods_guanbi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setContentView(inflate);
                popupWindow.setOutsideTouchable(true);


                popupWindow.setAnimationStyle(R.style.popuwindows);
                popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);

                break;
        }
    }


    @Override
    public void success(BaseEntity baseEntity, int type) {

        if (type == ApiContact.CAR_LIST) {
            GoodsEntity goodsEntity = (GoodsEntity) baseEntity;
            String msg = goodsEntity.getMsg();
            Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
            if(msg.equals("添加成功")){
                popupWindow.dismiss();
            }
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
        return R.layout.activity_goods;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        String ids = intent.getStringExtra("id");
        String names = intent.getStringExtra("title");
        id = ids;
        name = names;


        initView();


        image = intent.getStringExtra("image");
        if (image != null) {
            Glide.with(this).load(image).into(goods_image);
            goods_name.setText(names);
        }
        ObjectAnimator alpha = ObjectAnimator.ofFloat(tools, "alpha", 0f, 0f);
        alpha.setDuration(0);
        alpha.start();

        goods_NestedScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float scaleY = goods_NestedScrollView.getScrollY();
                Log.e("scaleYsss",scaleY+"");

                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        if(scaleY==0){
                            ObjectAnimator alpha = ObjectAnimator.ofFloat(tools, "alpha", 0f, 0f);
                            alpha.setDuration(0);
                            alpha.start();
                        }
                        if(scaleY>=100){
                            ObjectAnimator alpha = ObjectAnimator.ofFloat(tools, "alpha", 0f, 0.1f);
                            alpha.setDuration(0);
                            alpha.start();
                        }
                        if(scaleY>=150){
                            ObjectAnimator alpha = ObjectAnimator.ofFloat(tools, "alpha", 0f, 0.2f);
                            alpha.setDuration(0);
                            alpha.start();
                        }
                        if(scaleY>=200){
                            ObjectAnimator alpha = ObjectAnimator.ofFloat(tools, "alpha", 0f, 0.3f);
                            alpha.setDuration(0);
                            alpha.start();
                        }
                        if(scaleY>=250){
                            ObjectAnimator alpha = ObjectAnimator.ofFloat(tools, "alpha", 0f, 0.4f);
                            alpha.setDuration(0);
                            alpha.start();
                        }
                        if(scaleY>=300){
                            ObjectAnimator alpha = ObjectAnimator.ofFloat(tools, "alpha", 0f, 0.5f);
                            alpha.setDuration(0);
                            alpha.start();
                        }
                        if(scaleY>=320){
                            ObjectAnimator alpha = ObjectAnimator.ofFloat(tools, "alpha", 0f, 0.6f);
                            alpha.setDuration(0);
                            alpha.start();
                        }
                        if(scaleY>=340){
                            ObjectAnimator alpha = ObjectAnimator.ofFloat(tools, "alpha", 0f, 0.7f);
                            alpha.setDuration(0);
                            alpha.start();
                        }
                        if(scaleY>=360){
                            ObjectAnimator alpha = ObjectAnimator.ofFloat(tools, "alpha", 0f, 0.8f);
                            alpha.setDuration(0);
                            alpha.start();
                        }
                        if(scaleY>=380){
                            ObjectAnimator alpha = ObjectAnimator.ofFloat(tools, "alpha", 0f, 0.9f);
                            alpha.setDuration(0);
                            alpha.start();
                        }
                        if(scaleY>=400){
                            ObjectAnimator alpha = ObjectAnimator.ofFloat(tools, "alpha", 0f, 1f);
                            alpha.setDuration(0);
                            alpha.start();
                        }


                        break;
                }

                return false;
            }
        });


    }



    private void initView() {

//        setTranslucentStatus(this,true);
        goods_image = (ImageView) findViewById(R.id.goods_image);
        goods_name = (TextView) findViewById(R.id.goods_name);
        goods_fanhui = (Button) findViewById(R.id.goods_fanhui);
        goods_fenxiang = (Button) findViewById(R.id.goods_fenxiang);
        goods_gongneng = (Button) findViewById(R.id.goods_gongneng);
        goods_gouwuche = (Button) findViewById(R.id.goods_gouwuche);
        goods_NestedScrollView = (NestedScrollView) findViewById(R.id.goods_NestedScrollView);


        goods_fanhui.setOnClickListener(this);
        goods_fenxiang.setOnClickListener(this);
        goods_gongneng.setOnClickListener(this);
        goods_gouwuche.setOnClickListener(this);
        yanse = (RelativeLayout) findViewById(R.id.yanse);
        tools = (RelativeLayout) findViewById(R.id.tools);
        goods_title = (TextView) findViewById(R.id.goods_title);
        goods_title.setOnClickListener(this);

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
