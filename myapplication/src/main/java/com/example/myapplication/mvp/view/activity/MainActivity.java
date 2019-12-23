package com.example.myapplication.mvp.view.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.alipay.sdk.app.EnvUtils;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreCompoent;

import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.di.module.MenuMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.contact.MenuContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.example.myapplication.mvp.model.entity.ShopCarEntity;
import com.example.myapplication.mvp.model.entity.UfoBean;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.presenter.MenuPresenter;
import com.example.myapplication.mvp.view.fragment.FragmentGift;
import com.example.myapplication.mvp.view.fragment.FragmentHome;
import com.example.myapplication.mvp.view.fragment.FragmentMine;
import com.example.myapplication.mvp.view.fragment.FragmentType;
import com.example.myapplication.mvp.view.fragment.FragmentUfo;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class MainActivity extends BaseActivity<LrePresenter> implements LreContact.LreView, View.OnClickListener {
    public static FragmentHome fragmentHome;
    public static FragmentType fragmentType;
    public static FragmentUfo fragmentUfo;
    public static FragmentGift fragmentGift;
    public static FragmentMine fragmentMine;
    private FrameLayout main_frame;

    private RadioButton main_home_bt;
    private RadioButton main_type_bt;
    private RadioButton main_ufo_bt;
    private RadioButton main_gift_bt;
    private RadioButton main_mine_bt;
    private RadioGroup main_group;
    private ImageView main_aj;
    private Button main_gouwuche;


    public static SharedPreferences sp;
    public static SharedPreferences.Editor spedit;


    public Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 9005) {
                flag=1;

            }

        }
    };
    private Button main_piaofu;

    public static int flag = 0;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreCompoent.builder().appComponent(appComponent).lreMoudle(new LreMoudle(this)).build().inject(this);

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        return R.layout.activity_main;


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {




        JSONObject job = new JSONObject();

        try {
            job.put("userid", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(job.toString(), ApiContact.SHOPCAR_LIST);




        sp = getSharedPreferences("flag", MODE_PRIVATE);
        spedit = sp.edit();



        init();
        FragmentHome fragmentHome = new FragmentHome();
        Bundle bundle = new Bundle();
        bundle.putString("ltz", "ltzqwe");

        fragmentHome.setArguments(bundle);


        main_gouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShopCarActivity.class);
                startActivity(intent);

            }
        });

    }

    private void init() {
        main_piaofu = (Button) findViewById(R.id.main_piaofu);

        main_frame = findViewById(R.id.main_frame);
        main_group = findViewById(R.id.main_group);
        main_aj = (ImageView) findViewById(R.id.main_aj);
        main_gouwuche = (Button) findViewById(R.id.main_gouwuche);

        main_home_bt=findViewById(R.id.main_home_bt);
        main_home_bt.setOnClickListener(this);

        main_type_bt=findViewById(R.id.main_type_bt);
    main_type_bt.setOnClickListener(this);
        main_ufo_bt=findViewById(R.id.main_ufo_bt);
    main_ufo_bt.setOnClickListener(this);
        main_gift_bt=findViewById(R.id.main_gift_bt);
    main_gift_bt.setOnClickListener(this);
        main_mine_bt=findViewById(R.id.main_mine_bt);
    main_mine_bt.setOnClickListener(this);

        showid(R.id.main_home_bt);

        ObjectAnimator image_aj = ObjectAnimator.ofFloat(main_aj, "rotationY", 0f, 360f);
        image_aj.setDuration(3000);
        image_aj.start();
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        show();
        showid(id);
    }

    public void showid(int id) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (id) {
            case R.id.main_home_bt:
                main_gouwuche.setVisibility(View.VISIBLE);
                main_piaofu.setVisibility(View.VISIBLE);
                if (fragmentHome == null) {
                    fragmentHome = new FragmentHome();
                    fragmentTransaction.add(R.id.main_frame, fragmentHome);
                } else {
                    fragmentTransaction.show(fragmentHome);
                }
                break;
            case R.id.main_type_bt:
                main_gouwuche.setVisibility(View.VISIBLE);
                main_piaofu.setVisibility(View.VISIBLE);
                if (fragmentType == null) {
                    fragmentType = new FragmentType();
                    fragmentTransaction.add(R.id.main_frame, fragmentType);
                } else {
                    fragmentTransaction.show(fragmentType);
                }
                break;
            case R.id.main_ufo_bt:
                main_gouwuche.setVisibility(View.VISIBLE);
                main_piaofu.setVisibility(View.VISIBLE);
                if (fragmentUfo == null) {
                    fragmentUfo = new FragmentUfo();
                    fragmentTransaction.add(R.id.main_frame, fragmentUfo);
                } else {
                    fragmentTransaction.show(fragmentUfo);
                }
                break;
            case R.id.main_gift_bt:
                main_gouwuche.setVisibility(View.VISIBLE);
                main_piaofu.setVisibility(View.VISIBLE);
                if (fragmentGift == null) {
                    fragmentGift = new FragmentGift();
                    fragmentTransaction.add(R.id.main_frame, fragmentGift);
                } else {
                    fragmentTransaction.show(fragmentGift);
                }
                break;
            case R.id.main_mine_bt:


                main_gouwuche.setVisibility(View.GONE);
                main_piaofu.setVisibility(View.GONE);

                boolean loginflag = sp.getBoolean("loginflag", false);
                if (loginflag == true) {

                    if (fragmentMine == null) {
                        fragmentMine = new FragmentMine();
                        fragmentTransaction.add(R.id.main_frame, fragmentMine);
                    } else {
                        fragmentTransaction.show(fragmentMine);
                    }

                } else {
                    Intent intent = new Intent(MainActivity.this, WoDeActivity.class);
                    startActivityForResult(intent, 1000);
                }


                break;
        }
        fragmentTransaction.commit();


    }


    private void show() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fragmentHome != null) {
            fragmentTransaction.hide(fragmentHome);
        }

        if (fragmentType != null) {
            fragmentTransaction.hide(fragmentType);
        }

        if (fragmentUfo != null) {
            fragmentTransaction.hide(fragmentUfo);
        }

        if (fragmentGift != null) {
            fragmentTransaction.hide(fragmentGift);
        }


        if (fragmentMine != null) {
            fragmentTransaction.hide(fragmentMine);
        }
        fragmentTransaction.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == 1002) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                if (fragmentMine == null) {
                    fragmentMine = new FragmentMine();
                    fragmentTransaction.add(R.id.main_frame, fragmentMine);
                } else {
                    fragmentTransaction.show(fragmentMine);
                }
                fragmentTransaction.commit();
            }
        }
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
        mPresenter.lreAll(job.toString(), ApiContact.SHOPCAR_LIST);
//        if(flag ==1){
//            showid(R.id.main_home_bt);
//            main_home_bt.setChecked(true);
//        }
    }

    @Override
    public void success(BaseEntity baseEntity, int type) {
        if (type == ApiContact.SHOPCAR_LIST) {
            ShopCarEntity shopCarEntity = (ShopCarEntity) baseEntity;
            List<ShopCarEntity.ValuesBean> values = shopCarEntity.getValues();

            Log.e("values",values.size()+"");
            main_piaofu.setText(values.size()+"");
            MainActivity.spedit.putInt("shopsum",values.size()).commit();

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

}
