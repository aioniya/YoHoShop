package com.example.myapplication.mvp.view.activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.EncryptUtils;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreCompoent;
import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.DuShuBean;
import com.example.myapplication.mvp.model.entity.LoginEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.view.fragment.FragmentMine;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class WoDeActivity extends BaseActivity<LrePresenter> implements View.OnClickListener, LreContact.LreView {

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);



            if(msg.what==3003){
                yanzhengcount--;
                wode_yanzhengma.setText(yanzhengcount+"s");
                wode_yanzhengma.setEnabled(false);
                wode_yanzhengma.setBackgroundColor(Color.parseColor("#B0B0B0"));
                if(yanzhengcount == 0){
                    wode_yanzhengma.setBackgroundColor(Color.parseColor("#454545"));
                    wode_yanzhengma.setText("发送验证码");
                    wode_yanzhengma.setEnabled(true);
                    yanzhengcount = 30;
                    timer.cancel();

                }
            }

        }

    };

    EventHandler ehh =new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object data) {
            // TODO 此处不可直接处理UI线程，处理后续操作需传到主线程中操作

            if (result == SMSSDK.RESULT_COMPLETE) {

                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(WoDeActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        }
                    });

                    MainActivity.spedit.putBoolean("loginflag",true).commit();
                    String s = wode_shoujihao.getText().toString();
                    Log.e("000--",s);
                    EventBus.getDefault().postSticky(s);

                    setResult(1002);
                    finish();


                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){

                    //获取验证码成功
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){

                    //返回支持发送验证码的国家列表
                }
            }else{
                ((Throwable)data).printStackTrace();
            }


        }
    };





    private ImageView tu;
    private ImageView shouji;
    private Spinner wode_spinner;
    private RelativeLayout edone;
    private ImageView anquan;
    private ImageView wode_chazi;
    private TextView wode_zhuce;
    private CheckBox wode_check;
    private RelativeLayout wode_donghuaone;
    private RelativeLayout wode_donghuatwo;
    private RelativeLayout edittwo;
    private LinearLayout xian;
    private TextView wode_xuanze;

    int count = 0;
    float sumone = 90;
    float sumtwo = 90;
    float sumthree = 180;
    float sumfour = 270;
    private EditText wode_shoujihao;
    private EditText wode_mimaone;
    private EditText wode_mimatwo;
    private TextView wode_wangjimima;
    private TextView wode_huanyipi;

    private ImageView wode_tuone;
    private ImageView wode_tutwo;
    private ImageView wode_tuthree;
    private ImageView wode_tufour;
    private Button wode_denglu;
    private TextView wode_xieyi;
    private Button wode_weixin;
    private Button wode_qq;
    private Button wode_zhifubao;
    private Button wode_weibo;
    private Button wode_yanzhengma;
    boolean flag = false;

    ArrayList<DuShuBean> list = new ArrayList();
    ArrayList<Integer> imagelist = new ArrayList();
    ArrayList<String> spinnerlist = new ArrayList();
    private ImageView wode_cuowu;

    float arr[]=new float[]{90,180,270};

    private  String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJa4C5IKvNRcLWXiLFcF4F+i1S2QAusCMszlQeJV84UetEkczjUdJ4dWbnpRkeAmXCTzRHyO67XKS6GSCuKVO/sf7cyll0i6e+d0MSWB2CTxojYingZSV6ZQO8K1Z3fJyFYSHiRhDwJ4idC80zTyKagsWV29uNa38iQYr4FwbNqZAgMBAAECgYAxV1k6W1eMMg0OsKeRabQVuwoNG3tJEnQtDdSu0zKg3vdohAyh6MR7EvmiA7g86HH8CsPd/y/9WJe/8j6sBO0Ye9gt7eyQ2NiwWvlTuwNmngcSTapVvVI6NEyJFMfQt9PB1EHLNAXlz8jtJUyA7C48jReQD9p/SzAP0VxG7lwyMQJBAOjE7hAZ/6fyP3DB1fG7jr9gONZcz3TUaqx6BUn4GKZnckW08ht9Xqcqft5Hthu8BbLM9ptQ0U8QZekrJwD6ya0CQQClwstZMPu8jLhsgugVwodcG1mPEOiw9Yjnmt9+WTI07Ll2uFv//hRXBnahBBnZbucUYEbUY3kqUX9b3e9TmEodAkEAybPMbxt4VDoxCy6Mi/pxChkBZ4/pHV3sSiU6bAyWn6vIc+sGWRfca5MBePA/N+1IKtY9Y/02QwL8rH5+P/URyQJAL/hdjORGFdzLimuf6pwvPBKWKncEQCHuisghIZmClBpl2duklELddAnkztg2+tvDd/wcw14+NGb9aoKhvhl2aQJAbvcgoPU+xs0CjeexH+TS2S/jKkTRpvP2CpPK/k71m13xWdE8RtMkYY1measRmlIwOfWze7ll/PGT4dxWf31FNg==";
    Timer timer;
    int yanzhengcount=30;


    MainActivity mainActivity;

    public void showimage(){

        //随机图片
        int randomimage1 = (int)(Math.random()*(8-0))+0;
        int randomimage2 = (int)(Math.random()*(8-0))+0;
        int randomimage3 = (int)(Math.random()*(8-0))+0;
        int randomimage4 = (int)(Math.random()*(8-0))+0;

        Integer integer1 = imagelist.get(randomimage1);
        Integer integer2 = imagelist.get(randomimage2);
        Integer integer3 = imagelist.get(randomimage3);
        Integer integer4 = imagelist.get(randomimage4);


        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), integer1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), integer2);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), integer3);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), integer4);

        wode_tuone.setImageBitmap(bitmap1);
        wode_tutwo.setImageBitmap(bitmap2);
        wode_tuthree.setImageBitmap(bitmap3);
        wode_tufour.setImageBitmap(bitmap4);

    }
    public void show(){
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(wode_tuone, "rotation", 0f, sumone);
        objectAnimator1.setDuration(5);
        objectAnimator1.start();
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(wode_tutwo, "rotation", 0f, sumtwo);
        objectAnimator2.setDuration(5);
        objectAnimator2.start();
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(wode_tuthree, "rotation", 0f, sumthree);
        objectAnimator4.setDuration(5);
        objectAnimator4.start();
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(wode_tufour, "rotation", 0f, sumfour);
        objectAnimator3.setDuration(5);
        objectAnimator3.start();
    }
    private void initViews() {


        tu = (ImageView) findViewById(R.id.tu);
        shouji = (ImageView) findViewById(R.id.shouji);
        wode_spinner = (Spinner) findViewById(R.id.wode_spinner);
        edone = (RelativeLayout) findViewById(R.id.edone);
        anquan = (ImageView) findViewById(R.id.anquan);
        wode_chazi = (ImageView) findViewById(R.id.wode_chazi);
        wode_zhuce = (TextView) findViewById(R.id.wode_zhuce);
        wode_check = (CheckBox) findViewById(R.id.wode_check);

        wode_donghuaone = (RelativeLayout) findViewById(R.id.wode_donghuaone);

        wode_donghuatwo = (RelativeLayout) findViewById(R.id.wode_donghuatwo);

        edittwo = (RelativeLayout) findViewById(R.id.edittwo);

        xian = (LinearLayout) findViewById(R.id.xian);

        wode_xuanze = (TextView) findViewById(R.id.wode_xuanze);

        wode_shoujihao = (EditText) findViewById(R.id.wode_shoujihao);

        wode_mimaone = (EditText) findViewById(R.id.wode_mimaone);

        wode_mimatwo = (EditText) findViewById(R.id.wode_mimatwo);

        wode_wangjimima = (TextView) findViewById(R.id.wode_wangjimima);
        wode_huanyipi = (TextView) findViewById(R.id.wode_huanyipi);
        wode_huanyipi.setOnClickListener(this);
        wode_tuone = (ImageView) findViewById(R.id.wode_tuone);
        wode_tuone.setOnClickListener(this);
        wode_tutwo = (ImageView) findViewById(R.id.wode_tutwo);
        wode_tutwo.setOnClickListener(this);
        wode_tuthree = (ImageView) findViewById(R.id.wode_tuthree);
        wode_tuthree.setOnClickListener(this);
        wode_tufour = (ImageView) findViewById(R.id.wode_tufour);
        wode_tufour.setOnClickListener(this);
        wode_denglu = (Button) findViewById(R.id.wode_denglu);
        wode_denglu.setOnClickListener(this);
        wode_xieyi = (TextView) findViewById(R.id.wode_xieyi);
        wode_weixin = (Button) findViewById(R.id.wode_weixin);
        wode_weixin.setOnClickListener(this);
        wode_qq = (Button) findViewById(R.id.wode_qq);
        wode_yanzhengma = (Button) findViewById(R.id.wode_yanzhengma);
        wode_qq.setOnClickListener(this);
        wode_zhifubao = (Button) findViewById(R.id.wode_zhifubao);
        wode_zhifubao.setOnClickListener(this);
        wode_weibo = (Button) findViewById(R.id.wode_weibo);
        wode_weibo.setOnClickListener(this);
        wode_cuowu = (ImageView) findViewById(R.id.wode_cuowu);
        wode_cuowu.setOnClickListener(this);


        wode_denglu.setEnabled(false);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wode_denglu:

                break;
            case R.id.wode_weixin:

                break;
            case R.id.wode_qq:

                break;
            case R.id.wode_zhifubao:

                break;
            case R.id.wode_weibo:

                break;
        }
    }
    public void sendCode(Context context) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    // 国家代码，如“86”
                    String country = (String) phoneMap.get("86");
                    // 手机号码，如“13800138000”
                    String phone = (String) phoneMap.get("17600781029");
                    // TODO 利用国家代码和手机号码进行后续的操作
                } else{
                    // TODO 处理错误的结果
                }
            }
        });
        page.show(context);
    }
    //登录方法
    //登录方法
    private void login(String name,String pwd){

        String nameStr = name;
        String pwdStr = pwd;
        Log.e("name",name);
        Log.e("pwd",pwd);
        //rsq加密pwdStr
        //rsa加密后密码Base64->防乱码
        byte[] buf = EncryptUtils.encryptRSA(pwdStr.getBytes(),
                android.util.Base64.decode(privateKey.getBytes(), android.util.Base64.DEFAULT)
                ,false,"RSA/ECB/PKCS1Padding");
        byte[]buff = android.util.Base64.encode(buf, android.util.Base64.DEFAULT);
        String rasPwd = new String(buff);
        Log.e("ZXY","rasPwd:"+rasPwd);
        //生成请求参数
        JSONObject job = new JSONObject();
        try {
            job.put("username",nameStr);
            job.put("password",rasPwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(job.toString(),ApiContact.LOGINUSER);
    }


    @Override
    public void success(BaseEntity baseEntity, int type) {
        if(type == ApiContact.LOGINUSER){
            String msg = baseEntity.getMsg();
            if(msg.equals("请求成功")){
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                MainActivity.spedit.putBoolean("loginflag",true).commit();
                String s = wode_shoujihao.getText().toString();
                Log.e("000",s);
                EventBus.getDefault().postSticky(s);

                setResult(1002);
                finish();
            }else{
                Toast.makeText(this, "登录失败！！", Toast.LENGTH_SHORT).show();
            }
//            LoginEntity loginEntity = (LoginEntity) baseEntity;
//            List<LoginEntity.ValuesBean> values = loginEntity.getValues();
//            for (int i = 0; i < values.size(); i++) {
//                LoginEntity.ValuesBean valuesBean = values.get(i);
//                Log.e("",valuesBean.getNick_name());
//            }

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

        return R.layout.activity_wo_de;
    }

    @Override
    protected void onResume() {
        super.onResume();
        SMSSDK.registerEventHandler(ehh);
    }
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(ehh);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        initViews();

        mainActivity = new MainActivity();



















        spinnerlist.clear();
        spinnerlist.add("+86");
        spinnerlist.add("+06");


        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(WoDeActivity.this, R.layout.support_simple_spinner_dropdown_item, spinnerlist);

        wode_spinner.setAdapter(stringArrayAdapter);

        imagelist.clear();
        imagelist.add(R.mipmap.nvyi);
        imagelist.add(R.mipmap.nver);
        imagelist.add(R.mipmap.ncsan);
        imagelist.add(R.mipmap.ncsi);
        imagelist.add(R.mipmap.qqone);
        imagelist.add(R.mipmap.qqtwo);
        imagelist.add(R.mipmap.qqthree);
        imagelist.add(R.mipmap.qqfour);



        showimage();


        int random1 = (int)(Math.random()*(2-0))+0;
        int random2 = (int)(Math.random()*(2-0))+0;
        int random3 = (int)(Math.random()*(2-0))+0;
        int random4 = (int)(Math.random()*(2-0))+0;

        sumone = arr[random1];
        sumtwo = arr[random2];
        sumthree = arr[random3];
        sumfour = arr[random4];
        show();



        wode_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WoDeActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });
        wode_huanyipi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showimage();



                int random1 = (int)(Math.random()*(3-0))+0;
                int random2 = (int)(Math.random()*(3-0))+0;
                int random3 = (int)(Math.random()*(3-0))+0;
                int random4 = (int)(Math.random()*(3-0))+0;


                sumone = arr[random1];
                sumtwo = arr[random2];
                sumthree = arr[random3];
                sumfour = arr[random4];

                show();
            }
        });





        wode_cuowu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wode_shoujihao.setText("");
            }
        });

        wode_shoujihao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()<=0){
                    wode_cuowu.setVisibility(View.GONE);
                }else{
                    wode_cuowu.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        wode_denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count%2==0){
                    String telRegex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([1-3]|[5-9]))|(18[0-9]))\\d{8}$";
                    String s = wode_shoujihao.getText().toString();

                    boolean matches = s.matches(telRegex);
                    if(matches==true){
                        for (int i = 0; i < list.size(); i++) {

                            if (list.get(i).getOne() + list.get(i).getTwo() + list.get(i).getThree() + list.get(i).getFour() == 1440) {
                                flag = true;
                            } else {
                                flag = false;
                            }
                        }
                        if (flag == true) {

                            if(!wode_yanzhengma.getText().toString().isEmpty()){

                                SMSSDK.submitVerificationCode("86", wode_shoujihao.getText().toString(),wode_mimatwo.getText().toString());
                            }
                        }else if(flag == false){
                            Toast.makeText(WoDeActivity.this, "请将图片归置正确", Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(WoDeActivity.this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    for (int i = 0; i < list.size(); i++) {

                        if (list.get(i).getOne() + list.get(i).getTwo() + list.get(i).getThree() + list.get(i).getFour() == 1440) {
                            flag = true;
                        } else {
                            flag = false;
                        }
                    }
                    if (flag == true) {
                        Toast.makeText(WoDeActivity.this, "归置正确", Toast.LENGTH_SHORT).show();



                        login(wode_shoujihao.getText().toString(),wode_mimaone.getText().toString());




                    }else if(flag == false){
                        Toast.makeText(WoDeActivity.this, "请将图片归置正确", Toast.LENGTH_SHORT).show();

                    }
                }



            }
        });
        wode_xieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WoDeActivity.this, YouHuoActivity.class);
                startActivity(intent);

            }
        });


        wode_tuone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                sumone += 90;
                @SuppressLint("ObjectAnimatorBinding")
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(wode_tuone, "rotation", 0f, sumone);
                objectAnimator.setDuration(5);
                objectAnimator.start();
                list.add(new DuShuBean(sumone, sumtwo, sumthree, sumfour));
                if (sumone == 450) {
                    sumone = 90;
                }
            }
        });
        wode_tutwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                sumtwo += 90;
                @SuppressLint("ObjectAnimatorBinding")
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(wode_tutwo, "rotation", 0f, sumtwo);
                objectAnimator.setDuration(5);
                objectAnimator.start();
                list.add(new DuShuBean(sumone, sumtwo, sumthree, sumfour));
                if (sumtwo == 450) {
                    sumtwo = 90;
                }
            }
        });
        wode_tuthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                sumthree += 90;
                @SuppressLint("ObjectAnimatorBinding")
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(wode_tuthree, "rotation", 0f, sumthree);
                objectAnimator.setDuration(5);
                objectAnimator.start();
                list.add(new DuShuBean(sumone, sumtwo, sumthree, sumfour));
                if (sumthree == 450) {
                    sumthree = 90;
                }
            }
        });

        wode_tufour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                sumfour += 90;
                @SuppressLint("ObjectAnimatorBinding")
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(wode_tufour, "rotation", 0f, sumfour);
                objectAnimator.setDuration(5);
                objectAnimator.start();
                list.add(new DuShuBean(sumone, sumtwo, sumthree, sumfour));
                if (sumfour == 450) {
                    sumfour = 90;
                }
            }
        });


        wode_chazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.flag = 1;
                finish();
            }
        });


        wode_check.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (wode_check.isChecked()) {
                    wode_check.setBackground(getDrawable(R.mipmap.zhengyan));
//                    wode_mimaone.setInputType(EditorInfo.TYPE_NULL);
                    wode_mimaone.setInputType(128);


                } else {
                    wode_check.setBackground(getDrawable(R.mipmap.biyan));
                    wode_mimaone.setInputType(129);
//                    wode_mimaone.setInputType(EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD|EditorInfo.TYPE_CLASS_NUMBER);

                }

            }
        });

        wode_xuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count % 2 == 0) {
                    wode_xuanze.setText("手机验证码登录");
                    wode_wangjimima.setVisibility(View.VISIBLE);
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.shouji);
                    shouji.setImageBitmap(bitmap);


                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(wode_donghuatwo, "translationY", 150f, 0f);
                    objectAnimator.setDuration(800);
                    objectAnimator.start();


                    showimage();
                    int random1 = (int)(Math.random()*(3-0))+0;
                    int random2 = (int)(Math.random()*(3-0))+0;
                    int random3 = (int)(Math.random()*(3-0))+0;
                    int random4 = (int)(Math.random()*(3-0))+0;
                    sumone = arr[random1];
                    sumtwo = arr[random2];
                    sumthree = arr[random3];
                    sumfour = arr[random4];
                    show();

                    wode_mimatwo.setText("");


                } else {
                    wode_wangjimima.setVisibility(View.GONE);
                    wode_xuanze.setText("账号密码登录");
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.yyy);
                    shouji.setImageBitmap(bitmap);

                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(wode_donghuatwo, "translationY", 0f, 150f);
                    objectAnimator.setDuration(800);
                    objectAnimator.start();



                    showimage();
                    int random1 = (int)(Math.random()*(3-0))+0;
                    int random2 = (int)(Math.random()*(3-0))+0;
                    int random3 = (int)(Math.random()*(3-0))+0;
                    int random4 = (int)(Math.random()*(3-0))+0;
                    sumone = arr[random1];
                    sumtwo = arr[random2];
                    sumthree = arr[random3];
                    sumfour = arr[random4];
                    show();

                    wode_mimaone.setText("");
                }
            }
        });

        wode_mimaone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = wode_shoujihao.getText().toString();
                if(s.length()<=0||s1.length()<=0){
                    wode_denglu.setEnabled(false);
                    wode_denglu.setBackgroundColor(Color.parseColor("#B0B0B0"));
                }else{
                    wode_denglu.setEnabled(true);
                    wode_denglu.setBackgroundColor(Color.parseColor("#444444"));
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        wode_shoujihao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editone = wode_mimaone.getText().toString();
                String edittwo = wode_mimatwo.getText().toString();


                if(s.length()<=0||editone.length()<=0){
                    wode_denglu.setEnabled(false);
                    wode_denglu.setBackgroundColor(Color.parseColor("#B0B0B0"));

                }else{
                    wode_denglu.setEnabled(true);
                    wode_denglu.setBackgroundColor(Color.parseColor("#444444"));
                }
                if(s.length()<=0||edittwo.length()<=0){
                    wode_denglu.setEnabled(false);
                    wode_denglu.setBackgroundColor(Color.parseColor("#B0B0B0"));

                }else{
                    wode_denglu.setEnabled(true);
                    wode_denglu.setBackgroundColor(Color.parseColor("#444444"));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        wode_mimatwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String s1 = wode_shoujihao.getText().toString();

                if(s.length()<=0||s1.length()<=0){
                    wode_denglu.setEnabled(false);
                    wode_denglu.setBackgroundColor(Color.parseColor("#B0B0B0"));

                }else{
                    wode_denglu.setEnabled(true);
                    wode_denglu.setBackgroundColor(Color.parseColor("#444444"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        wode_yanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendCode(WoDeActivity.this);
                if(wode_shoujihao.getText().toString().length()<=0){
                    Toast.makeText(WoDeActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    SMSSDK.getVerificationCode("86", wode_shoujihao.getText().toString());
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            mHandler.sendEmptyMessage(3003);

                        }
                    },0,1000);

                }




            }
        });


    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}
