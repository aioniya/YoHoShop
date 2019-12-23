package com.example.myapplication.mvp.view.activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.EncryptUtils;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreCompoent;
import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.DuShuBean;
import com.example.myapplication.mvp.model.entity.RegisterEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity<LrePresenter> implements  LreContact.LreView, View.OnClickListener {

    private ImageView register_fanhui;
    private ImageView register_chazi;
    private ImageView register_cuowu;
    private RelativeLayout tops;
    private ImageView shouji;
    private EditText register_shoujihao;
    private Spinner wode_spinner;
    private ImageView wode_cuowu;
    private RelativeLayout edone;
    private ImageView anquan;
    private EditText register_yanzhengma;
    private RelativeLayout wode_donghuatwo;
    private RelativeLayout edittwo;
    private EditText register_mima;
    private EditText register_kouling;
    private CheckBox register_check;
    private RelativeLayout wode_donghuaone;
    private TextView register_huanyipi;
    private ImageView register_imageone;
    private ImageView register_imagetwo;
    private ImageView register_imagethree;
    private ImageView register_imagefour;
    private Button register_zhuce;
    private TextView register_xieyi;

    ArrayList<DuShuBean> list = new ArrayList();
    ArrayList<Integer> imagelist = new ArrayList();
    ArrayList<String> spinnerlist = new ArrayList();
    float arr[] = new float[]{90, 180, 270};


    float sumone = 90;
    float sumtwo = 90;
    float sumthree = 180;
    float sumfour = 270;


    private String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJa4C5IKvNRcLWXiLFcF4F+i1S2QAusCMszlQeJV84UetEkczjUdJ4dWbnpRkeAmXCTzRHyO67XKS6GSCuKVO/sf7cyll0i6e+d0MSWB2CTxojYingZSV6ZQO8K1Z3fJyFYSHiRhDwJ4idC80zTyKagsWV29uNa38iQYr4FwbNqZAgMBAAECgYAxV1k6W1eMMg0OsKeRabQVuwoNG3tJEnQtDdSu0zKg3vdohAyh6MR7EvmiA7g86HH8CsPd/y/9WJe/8j6sBO0Ye9gt7eyQ2NiwWvlTuwNmngcSTapVvVI6NEyJFMfQt9PB1EHLNAXlz8jtJUyA7C48jReQD9p/SzAP0VxG7lwyMQJBAOjE7hAZ/6fyP3DB1fG7jr9gONZcz3TUaqx6BUn4GKZnckW08ht9Xqcqft5Hthu8BbLM9ptQ0U8QZekrJwD6ya0CQQClwstZMPu8jLhsgugVwodcG1mPEOiw9Yjnmt9+WTI07Ll2uFv//hRXBnahBBnZbucUYEbUY3kqUX9b3e9TmEodAkEAybPMbxt4VDoxCy6Mi/pxChkBZ4/pHV3sSiU6bAyWn6vIc+sGWRfca5MBePA/N+1IKtY9Y/02QwL8rH5+P/URyQJAL/hdjORGFdzLimuf6pwvPBKWKncEQCHuisghIZmClBpl2duklELddAnkztg2+tvDd/wcw14+NGb9aoKhvhl2aQJAbvcgoPU+xs0CjeexH+TS2S/jKkTRpvP2CpPK/k71m13xWdE8RtMkYY1measRmlIwOfWze7ll/PGT4dxWf31FNg==";
    private EditText register_zhanghao;



    int yanzhengcount = 30;
    Timer timer;
    boolean flag = false;
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);


            if (msg.what == 3003) {
                yanzhengcount--;
                button_yanzhengma.setText(yanzhengcount + "s");
                button_yanzhengma.setEnabled(false);
                button_yanzhengma.setBackgroundColor(Color.parseColor("#B0B0B0"));
                if (yanzhengcount == 0) {
                    button_yanzhengma.setBackgroundColor(Color.parseColor("#454545"));
                    button_yanzhengma.setText("发送验证码");
                    button_yanzhengma.setEnabled(true);
                    yanzhengcount = 30;
                    timer.cancel();

                }
            }

        }

    };


    EventHandler ehh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            // TODO 此处不可直接处理UI线程，处理后续操作需传到主线程中操作

            if (result == SMSSDK.RESULT_COMPLETE) {

                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功

                    submit();
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {

                    //获取验证码成功
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {

                    //返回支持发送验证码的国家列表
                }
            } else {
                ((Throwable) data).printStackTrace();
            }


        }
    };
    private Button button_yanzhengma;


    public void showimage() {

        //随机图片
        int randomimage1 = (int) (Math.random() * (8 - 0)) + 0;
        int randomimage2 = (int) (Math.random() * (8 - 0)) + 0;
        int randomimage3 = (int) (Math.random() * (8 - 0)) + 0;
        int randomimage4 = (int) (Math.random() * (8 - 0)) + 0;

        Integer integer1 = imagelist.get(randomimage1);
        Integer integer2 = imagelist.get(randomimage2);
        Integer integer3 = imagelist.get(randomimage3);
        Integer integer4 = imagelist.get(randomimage4);


        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), integer1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), integer2);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), integer3);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), integer4);

        register_imageone.setImageBitmap(bitmap1);
        register_imagetwo.setImageBitmap(bitmap2);
        register_imagethree.setImageBitmap(bitmap3);
        register_imagefour.setImageBitmap(bitmap4);

    }

    public void show() {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(register_imageone, "rotation", 0f, sumone);
        objectAnimator1.setDuration(5);
        objectAnimator1.start();
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(register_imagetwo, "rotation", 0f, sumtwo);
        objectAnimator2.setDuration(5);
        objectAnimator2.start();
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(register_imagethree, "rotation", 0f, sumthree);
        objectAnimator4.setDuration(5);
        objectAnimator4.start();
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(register_imagefour, "rotation", 0f, sumfour);
        objectAnimator3.setDuration(5);
        objectAnimator3.start();
    }

    public void SHOWIMAGE() {
        showimage();
        int random1 = (int) (Math.random() * (3 - 0)) + 0;
        int random2 = (int) (Math.random() * (3 - 0)) + 0;
        int random3 = (int) (Math.random() * (3 - 0)) + 0;
        int random4 = (int) (Math.random() * (3 - 0)) + 0;

        sumone = arr[random1];
        sumtwo = arr[random2];
        sumthree = arr[random3];
        sumfour = arr[random4];

        show();
    }

    private void showspinner() {

        spinnerlist.clear();
        spinnerlist.add("+86");
        spinnerlist.add("+06");


        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(RegisterActivity.this, R.layout.support_simple_spinner_dropdown_item, spinnerlist);
        wode_spinner.setAdapter(stringArrayAdapter);
    }

    private void initView() {
        register_fanhui = (ImageView) findViewById(R.id.register_fanhui);
        register_chazi = (ImageView) findViewById(R.id.register_chazi);
        tops = (RelativeLayout) findViewById(R.id.tops);
        shouji = (ImageView) findViewById(R.id.shouji);
        register_shoujihao = (EditText) findViewById(R.id.register_shoujihao);
        wode_spinner = (Spinner) findViewById(R.id.wode_spinner);
        wode_cuowu = (ImageView) findViewById(R.id.wode_cuowu);
        edone = (RelativeLayout) findViewById(R.id.edone);
        anquan = (ImageView) findViewById(R.id.anquan);
        register_yanzhengma = (EditText) findViewById(R.id.register_yanzhengma);
        wode_donghuatwo = (RelativeLayout) findViewById(R.id.wode_donghuatwo);
        edittwo = (RelativeLayout) findViewById(R.id.edittwo);
        register_mima = (EditText) findViewById(R.id.register_mima);
        register_kouling = (EditText) findViewById(R.id.register_kouling);
        register_check = (CheckBox) findViewById(R.id.register_check);
        wode_donghuaone = (RelativeLayout) findViewById(R.id.wode_donghuaone);
        register_huanyipi = (TextView) findViewById(R.id.register_huanyipi);
        register_imageone = (ImageView) findViewById(R.id.register_imageone);
        register_imagetwo = (ImageView) findViewById(R.id.register_imagetwo);
        register_imagethree = (ImageView) findViewById(R.id.register_imagethree);
        register_imagefour = (ImageView) findViewById(R.id.register_imagefour);
        register_cuowu = (ImageView) findViewById(R.id.register_cuowu);
        register_zhuce = (Button) findViewById(R.id.register_zhuce);
        register_xieyi = (TextView) findViewById(R.id.register_xieyi);
        register_zhanghao = (EditText) findViewById(R.id.register_zhanghao);

        register_zhuce.setOnClickListener(this);
        button_yanzhengma = (Button) findViewById(R.id.button_yanzhengma);
        button_yanzhengma.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_zhuce:


                String telRegex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([1-3]|[5-9]))|(18[0-9]))\\d{8}$";
                String s = register_shoujihao.getText().toString();

                boolean matches = s.matches(telRegex);
                if (matches == true) {
                    for (int i = 0; i < list.size(); i++) {

                        if (list.get(i).getOne() + list.get(i).getTwo() + list.get(i).getThree() + list.get(i).getFour() == 1440) {
                            flag = true;
                        } else {
                            flag = false;
                        }
                    }
                    if (flag == true) {

                        if (!register_yanzhengma.getText().toString().isEmpty()){

                            SMSSDK.submitVerificationCode("86",register_shoujihao.getText().toString(),register_yanzhengma.getText().toString());
                        }
                    } else if (flag == false) {
                        Toast.makeText(RegisterActivity.this, "请将图片归置正确", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                }



                break;
            case R.id.button_yanzhengma:

                if(register_shoujihao.getText().toString().length()<=0){
                    Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    SMSSDK.getVerificationCode("86", register_shoujihao.getText().toString());
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            mHandler.sendEmptyMessage(3003);

                        }
                    },0,1000);

                }

                break;
        }
    }

    private void submit() {
        // validate
        String shoujihao = register_shoujihao.getText().toString().trim();
        String yanzhengma = register_yanzhengma.getText().toString().trim();
        String mima = register_mima.getText().toString().trim();
        String kouling = register_kouling.getText().toString().trim();
        String zhanghao = register_zhanghao.getText().toString().trim();

        if (TextUtils.isEmpty(shoujihao) || TextUtils.isEmpty(yanzhengma) || TextUtils.isEmpty(mima) || TextUtils.isEmpty(kouling)) {
            return;
        } else {

            //rsq加密pwdStr
            //rsa加密后密码Base64->防乱码
            byte[] buf = EncryptUtils.encryptRSA(mima.getBytes(),
                    Base64.decode(privateKey.getBytes(), Base64.DEFAULT)
                    , false, "RSA/ECB/PKCS1Padding");
            byte[] buff = Base64.encode(buf, Base64.DEFAULT);
            String rasPwd = new String(buff);
            Log.e("ZXY", "rasPwd:" + rasPwd);
            //生成请求参数
            JSONObject job = new JSONObject();
            try {
                job.put("username", zhanghao);
                job.put("password", rasPwd);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mPresenter.lreAll(job.toString(), ApiContact.REGISTER);
        }

        // TODO validate success, do something


    }


    @Override
    public void success(BaseEntity baseEntity, int type) {

        if (type == ApiContact.REGISTER) {
            RegisterEntity registerEntity = (RegisterEntity) baseEntity;
            String msg = registerEntity.getMsg();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
        return R.layout.activity_register;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initView();

        register_xieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, YouHuoActivity.class);
                startActivity(intent);
            }
        });


        imagelist.clear();
        imagelist.add(R.mipmap.nvyi);
        imagelist.add(R.mipmap.nver);
        imagelist.add(R.mipmap.ncsan);
        imagelist.add(R.mipmap.ncsi);
        imagelist.add(R.mipmap.qqone);
        imagelist.add(R.mipmap.qqtwo);
        imagelist.add(R.mipmap.qqthree);
        imagelist.add(R.mipmap.qqfour);


        //设置spinner
        showspinner();

        //设置图片
        SHOWIMAGE();


        register_huanyipi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SHOWIMAGE();

            }
        });

        register_zhanghao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String mima = register_mima.getText().toString();
                String yanzhengma = register_yanzhengma.getText().toString();
                String shouji = register_shoujihao.getText().toString();


//                String zhanghao = register_zhanghao.getText().toString();
//                String mima = register_mima.getText().toString();
//                String yanzhengma = register_yanzhengma.getText().toString();
//                String shouji = register_shoujihao.getText().toString();
                if (s.length() <= 0 || shouji.length() <= 0||yanzhengma.length() <= 0||mima.length() <= 0) {
                    register_zhuce.setEnabled(false);
                    register_zhuce.setBackgroundColor(Color.parseColor("#B0B0B0"));
                } else {
                    register_zhuce.setEnabled(true);
                    register_zhuce.setBackgroundColor(Color.parseColor("#444444"));
                }




            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        register_shoujihao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String zhanghao = register_zhanghao.getText().toString();
                String mima = register_mima.getText().toString();
                String yanzhengma = register_yanzhengma.getText().toString();




                if (s.length() <= 0 || yanzhengma.length() <= 0||zhanghao.length() <= 0||mima.length() <= 0) {
                    register_zhuce.setEnabled(false);
                    register_zhuce.setBackgroundColor(Color.parseColor("#B0B0B0"));
                } else {
                    register_zhuce.setEnabled(true);
                    register_zhuce.setBackgroundColor(Color.parseColor("#444444"));
                }



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        register_yanzhengma.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                String zhanghao = register_zhanghao.getText().toString();
                String mima = register_mima.getText().toString();
                String shouji = register_shoujihao.getText().toString();



                if (s.length() <= 0 || shouji.length() <= 0||mima.length() <= 0||zhanghao.length() <= 0) {
                    register_zhuce.setEnabled(false);
                    register_zhuce.setBackgroundColor(Color.parseColor("#B0B0B0"));
                } else {
                    register_zhuce.setEnabled(true);
                    register_zhuce.setBackgroundColor(Color.parseColor("#444444"));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        register_mima.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {




                String yanzhengma = register_yanzhengma.getText().toString();
                String zhanghao = register_zhanghao.getText().toString();
                String shouji = register_shoujihao.getText().toString();


                if (s.length() <= 0||shouji.length() <= 0||yanzhengma.length() <= 0||zhanghao.length() <= 0) {
                    register_zhuce.setEnabled(false);
                    register_zhuce.setBackgroundColor(Color.parseColor("#B0B0B0"));
                } else {
                    register_zhuce.setEnabled(true);
                    register_zhuce.setBackgroundColor(Color.parseColor("#444444"));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        register_kouling.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() <= 0) {
                    register_cuowu.setVisibility(View.GONE);
                } else {
                    register_cuowu.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        register_imageone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                sumone += 90;
                @SuppressLint("ObjectAnimatorBinding")
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(register_imageone, "rotation", 0f, sumone);
                objectAnimator.setDuration(5);
                objectAnimator.start();
                list.add(new DuShuBean(sumone, sumtwo, sumthree, sumfour));
                if (sumone == 450) {
                    sumone = 90;
                }
            }
        });
        register_imagetwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                sumtwo += 90;
                @SuppressLint("ObjectAnimatorBinding")
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(register_imagetwo, "rotation", 0f, sumtwo);
                objectAnimator.setDuration(5);
                objectAnimator.start();
                list.add(new DuShuBean(sumone, sumtwo, sumthree, sumfour));
                if (sumtwo == 450) {
                    sumtwo = 90;
                }
            }
        });
        register_imagethree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                sumthree += 90;
                @SuppressLint("ObjectAnimatorBinding")
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(register_imagethree, "rotation", 0f, sumthree);
                objectAnimator.setDuration(5);
                objectAnimator.start();
                list.add(new DuShuBean(sumone, sumtwo, sumthree, sumfour));
                if (sumthree == 450) {
                    sumthree = 90;
                }
            }
        });

        register_imagefour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                sumfour += 90;
                @SuppressLint("ObjectAnimatorBinding")
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(register_imagefour, "rotation", 0f, sumfour);
                objectAnimator.setDuration(5);
                objectAnimator.start();
                list.add(new DuShuBean(sumone, sumtwo, sumthree, sumfour));
                if (sumfour == 450) {
                    sumfour = 90;
                }
            }
        });

        register_check.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (register_check.isChecked()) {
                    register_check.setBackground(getDrawable(R.mipmap.zhengyan));
                    register_mima.setInputType(128);

                } else {
                    register_check.setBackground(getDrawable(R.mipmap.biyan));
                    register_mima.setInputType(129);
                }
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

    @Override
    protected void onResume() {
        super.onResume();
        SMSSDK.registerEventHandler(ehh);
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(ehh);
    }
}
