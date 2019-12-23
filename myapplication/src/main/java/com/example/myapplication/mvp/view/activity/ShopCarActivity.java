package com.example.myapplication.mvp.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreCompoent;
import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.ShopCarEntity;
import com.example.myapplication.mvp.model.entity.ShouCangBean;
import com.example.myapplication.mvp.model.entity.UfoBean;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.view.adpater.ShopCarRecyclerViewAdpater;
import com.example.myapplication.mvp.view.adpater.ShopcaroneRecyclerViewAdpater;
import com.example.myapplication.zhifubao.PayResult;
import com.example.myapplication.zhifubao.util.OrderInfoUtil2_0;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopCarActivity extends BaseActivity<LrePresenter> implements LreContact.LreView, View.OnClickListener {

    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID ="2016101000655936";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    /**
     *  pkcs8 格式的商户私钥。
     *
     * 	如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 	使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * 	RSA2_PRIVATE。
     *
     * 	建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 	工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCbQvq33S+hokraIICWXSSfSTWP2ftogpiKGHl/oiWRw4FVuB3rH7VMB9Bra8XH64oQv4XM2UxM30J5BhCL5ucdPrn9JwtHFFxDaZQPsPGEBqtjb7i12K50iSboGRL4XrPq9LKptvj/+Ou/FWZW9TpltvFSF9dLB5kg6TBwpk11WkT4KzH6QxQGbH4PozdUR/6I4Akqbnas3xFhKn8Ad3+GZBY9kmA3m3PvDReRo42jGgTHTuy8xf092cv3jmKjtkyBNnODYyJKiiXsuDQMW2x8KdFOu1MezaBNVjS5wzwlrqjbLtPBmWGhcJCnngYDiQyE3urH+9/5JLkzFk6SvgXrAgMBAAECggEBAIDbIueru5dC8Sp9JRsl7HoCMWDVAbFoCZ861sNV2CZUgqeYqcP0yety5MfyZvAYDLr/qNUtVwTJmgqFPQBPP2pnH8b5O4PfYv9TxV0MdjUjr+RT2ZGaZpczKMYh4TuepWWPdiQiRh9zAHzTDt+GoNO/9CI0xSpxAOZughRMASct034gbq0OAM6AtkhfK3YIM1NtX9D5TuNguP7JinsJIMDXEl7uE2y1QJwFh6aF7z8eFTBiUxdamk1Ml3jLmWfHU5PREuCtkDgLfBtg23B6JWa7TdFlow11Y7lUfM+DprXibzJ0sujarPkO+E8P9AKXN5mYHUxeTaPqUz0Z/qN2RlkCgYEA+eW6X3SvVSwJjuxOzJYV36ofZME9k7poxnmtPevWL/vPWlm8aLF9YrDIa7NoTXs19KKFs87NNShKkkIIdAhd32XeHQSJG+mhte16F7jFzZVj5VefW6JMxNfKRDNgBYtxO0x8glCxX5NpVcK2+wD5KBbKmi0IkElrF2KyOdKlDz0CgYEAnw2fFvpWN0tNMLv+LXdKoZXUM7QcavsC3pBdz/2tdafxJL62V1aiKu+EB34Ot567hez6Cta46yPPzn1Q3kN1jdYVX3jnssCfv0IC8HpI2da+XqKTUiLc55IbDLQnJ721a1AWy1FCuZs+EgHaGW7w+JNNDKmP0S85We/E5ys+vEcCgYEAlBeWKTeHKFxelbpowEk5UUEKNbVm/oL70JMPHaEDX5BEw3BmfUyh17iYLWs2pqQbx4wA3Bvz9EqaVq6XP9wtdVRzGpMzhb0gviWrsx8m6bxcZO9WWwGFqSmQjnY7s019nvym1LtpQhCj3dT0hy9+eEl5a4DAkHFR65vrruSsOKkCgYACxOTxLHiVy9zTLWK2fe4xs/8Wy1r3Tdl18IOBnIQJmVvsbNmV2a9HGhZtXvd4oO3Y3hBYYfakOWJj0PC4feotBv4mRF+cUBg35zlP4IEmMcPst93ivzKjYXrRFM3dtkLTVHihH5AO1TIWdCvM546QdVgREV6soKrZ8v+1mp4j/wKBgHqsgl2BhDi32Ojnmk8cgY1oRjnc37NeN/dqqTkd0PTnWMUhDtM6Nm9K+iQ3hNQYDn8Sm4ua4Qpspwp0CjMDotBa3mePbZDCCiSJnj4FZqgdHpEPNPlpuo18q522ajgRYuZDWXmpEdjgRYukRyk7GkbLOVYpH938Ohz9OgMH14KP";
    public static final String RSA_PRIVATE =  "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCbQvq33S+hokraIICWXSSfSTWP2ftogpiKGHl/oiWRw4FVuB3rH7VMB9Bra8XH64oQv4XM2UxM30J5BhCL5ucdPrn9JwtHFFxDaZQPsPGEBqtjb7i12K50iSboGRL4XrPq9LKptvj/+Ou/FWZW9TpltvFSF9dLB5kg6TBwpk11WkT4KzH6QxQGbH4PozdUR/6I4Akqbnas3xFhKn8Ad3+GZBY9kmA3m3PvDReRo42jGgTHTuy8xf092cv3jmKjtkyBNnODYyJKiiXsuDQMW2x8KdFOu1MezaBNVjS5wzwlrqjbLtPBmWGhcJCnngYDiQyE3urH+9/5JLkzFk6SvgXrAgMBAAECggEBAIDbIueru5dC8Sp9JRsl7HoCMWDVAbFoCZ861sNV2CZUgqeYqcP0yety5MfyZvAYDLr/qNUtVwTJmgqFPQBPP2pnH8b5O4PfYv9TxV0MdjUjr+RT2ZGaZpczKMYh4TuepWWPdiQiRh9zAHzTDt+GoNO/9CI0xSpxAOZughRMASct034gbq0OAM6AtkhfK3YIM1NtX9D5TuNguP7JinsJIMDXEl7uE2y1QJwFh6aF7z8eFTBiUxdamk1Ml3jLmWfHU5PREuCtkDgLfBtg23B6JWa7TdFlow11Y7lUfM+DprXibzJ0sujarPkO+E8P9AKXN5mYHUxeTaPqUz0Z/qN2RlkCgYEA+eW6X3SvVSwJjuxOzJYV36ofZME9k7poxnmtPevWL/vPWlm8aLF9YrDIa7NoTXs19KKFs87NNShKkkIIdAhd32XeHQSJG+mhte16F7jFzZVj5VefW6JMxNfKRDNgBYtxO0x8glCxX5NpVcK2+wD5KBbKmi0IkElrF2KyOdKlDz0CgYEAnw2fFvpWN0tNMLv+LXdKoZXUM7QcavsC3pBdz/2tdafxJL62V1aiKu+EB34Ot567hez6Cta46yPPzn1Q3kN1jdYVX3jnssCfv0IC8HpI2da+XqKTUiLc55IbDLQnJ721a1AWy1FCuZs+EgHaGW7w+JNNDKmP0S85We/E5ys+vEcCgYEAlBeWKTeHKFxelbpowEk5UUEKNbVm/oL70JMPHaEDX5BEw3BmfUyh17iYLWs2pqQbx4wA3Bvz9EqaVq6XP9wtdVRzGpMzhb0gviWrsx8m6bxcZO9WWwGFqSmQjnY7s019nvym1LtpQhCj3dT0hy9+eEl5a4DAkHFR65vrruSsOKkCgYACxOTxLHiVy9zTLWK2fe4xs/8Wy1r3Tdl18IOBnIQJmVvsbNmV2a9HGhZtXvd4oO3Y3hBYYfakOWJj0PC4feotBv4mRF+cUBg35zlP4IEmMcPst93ivzKjYXrRFM3dtkLTVHihH5AO1TIWdCvM546QdVgREV6soKrZ8v+1mp4j/wKBgHqsgl2BhDi32Ojnmk8cgY1oRjnc37NeN/dqqTkd0PTnWMUhDtM6Nm9K+iQ3hNQYDn8Sm4ua4Qpspwp0CjMDotBa3mePbZDCCiSJnj4FZqgdHpEPNPlpuo18q522ajgRYuZDWXmpEdjgRYukRyk7GkbLOVYpH938Ohz9OgMH14KP";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        showAlert(PayDemoActivity.this, getString(R.string.pay_success) + payResult);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                        showAlert(PayDemoActivity.this, getString(R.string.pay_failed) + payResult);
                    }
                    break;
                }

                default:
                    break;
            }
        };
    };

    float sum = 0;
    int count = 0;
    int countes=0;
    private TextView shopcar_fanhui;
    private TextView biaoti;
    private RecyclerView shopcar_recyclerviewone;
    private RecyclerView shopcar_recyclerviewtwo;

    ArrayList<UfoBean> list = new ArrayList<>();
    ArrayList<ShopCarEntity.ValuesBean> valueslist = new ArrayList<>();
    public static ArrayList<ShouCangBean> shouCangBeans = new ArrayList<>();


    ShopCarRecyclerViewAdpater shopCarRecyclerViewAdpater;
    ShopcaroneRecyclerViewAdpater shopCaroneRecyclerViewAdpater;

    private RelativeLayout tops;
    private CheckBox shopcar_quanxuan;
    private Button shopcar_jiesuan;
    private RelativeLayout bottomt;
    private TextView shopcar_zongji;
    private TextView shopcar_bianji;
    private Button shopcarone_yidong;
    private TextView shopcar_count;

    String dingdans = null;

    @Override
    public void success(BaseEntity baseEntity, int type) {
        if (type == ApiContact.SHOPCAR_LIST) {
            ShopCarEntity shopCarEntity = (ShopCarEntity) baseEntity;
            List<ShopCarEntity.ValuesBean> values = shopCarEntity.getValues();
            valueslist.addAll(values);
            List<ShopCarEntity.RecommendGoodsBean> recommend_goods = shopCarEntity.getRecommend_goods();

            MainActivity.spedit.putInt("shopsum",values.size()).commit();



            for (int i = 0; i < recommend_goods.size(); i++) {
                list.add(new UfoBean(Api.APP_DOMAIN + recommend_goods.get(i).getGoods_img_path(), recommend_goods.get(i).getGoods_name()));
            }


            shopCarRecyclerViewAdpater.notifyDataSetChanged();
            shopCaroneRecyclerViewAdpater.notifyDataSetChanged();
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
        return R.layout.activity_shop_car;



    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initViews();

        Intent intent = getIntent();
        String dingdan = intent.getStringExtra("dingdan");
        dingdans= dingdan;

        if(dingdans!=null){
            biaoti.setText("我的订单");
        }

        JSONObject job = new JSONObject();

        try {
            job.put("userid", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(job.toString(), ApiContact.SHOPCAR_LIST);


        shopcar_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
        shopCaroneRecyclerViewAdpater = new ShopcaroneRecyclerViewAdpater(valueslist, this) {

            @Override
            public void OnClickListeners(baseview holder, int position) {


                holder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.checkBox.isChecked()) {
                            valueslist.get(position).setFlag(true);
                            String shop_num = valueslist.get(position).getShop_num();
                            String shop_price = valueslist.get(position).getShop_price();
                            if (!shop_num.equals("")) {
                                sum += Float.parseFloat(shop_num) * Float.parseFloat(shop_price);
                            }
                            shopcar_zongji.setText("总计:￥" + sum);
                            int counts = counts();
                            shopcar_count.setText("("+counts+")");
                        } else {
                            valueslist.get(position).setFlag(false);
                            String shop_num = valueslist.get(position).getShop_num();
                            String shop_price = valueslist.get(position).getShop_price();
                            if (!shop_num.equals("")) {
                                sum -= Float.parseFloat(shop_num) * Float.parseFloat(shop_price);
                            }
                            shopcar_zongji.setText("总计:￥" + sum);
                            int counts = counts();
                            shopcar_count.setText("("+counts+")");
                        }
                        shopCaroneRecyclerViewAdpater.notifyDataSetChanged();

                    }
                });
                //购物----
                holder.jia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int num = Integer.parseInt(valueslist.get(position).getShop_num());
                        num++;
                        valueslist.get(position).setShop_num(num + "");
                        String shop_price = valueslist.get(position).getShop_price();
                        sum += num * (Float.parseFloat(shop_price) * 1);


                        shopCaroneRecyclerViewAdpater.notifyDataSetChanged();
                    }
                });
                //购物++++
                holder.jian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int num = Integer.parseInt(valueslist.get(position).getShop_num());

                        String shop_price = valueslist.get(position).getShop_price();
                        sum -= num * (Float.parseFloat(shop_price) * 1);

                        if (num <= 0) {
                            Toast.makeText(ShopCarActivity.this, "不能减了,靓仔", Toast.LENGTH_SHORT).show();
                        } else {
                            num--;
                            valueslist.get(position).setShop_num(num + "");
                        }
                        shopCaroneRecyclerViewAdpater.notifyDataSetChanged();
                    }
                });


                for (int i = 0; i < valueslist.size(); i++) {
                    if (!valueslist.get(i).isFlag()) {
                        shopcar_quanxuan.setChecked(false);
                        return;
                    }
                }
                shopcar_quanxuan.setChecked(true);

            }


        };
        shopcar_recyclerviewone.setLayoutManager(new LinearLayoutManager(this));
        shopcar_recyclerviewone.setAdapter(shopCaroneRecyclerViewAdpater);

        shopCarRecyclerViewAdpater = new ShopCarRecyclerViewAdpater(list, this);
        shopcar_recyclerviewtwo.setLayoutManager(new GridLayoutManager(this, 2));
        shopcar_recyclerviewtwo.setAdapter(shopCarRecyclerViewAdpater);


        //全选按钮
        shopcar_quanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (shopcar_quanxuan.isChecked()) {
                    sum = 0;
                    for (int i = 0; i < valueslist.size(); i++) {
                        valueslist.get(i).setFlag(true);
                        float jisuan = jisuan(valueslist.get(i).getShop_num(), valueslist.get(i).getShop_price());
                        sum = jisuan;
                    }
                    int counts = counts();
                    shopcar_count.setText("("+counts+")");
                    shopcar_zongji.setText("总计:￥" + sum);
                } else {
                    int counts = counts();
                    for (int i = 0; i < valueslist.size(); i++) {
                        valueslist.get(i).setFlag(false);
                    }
                    sum = 0;
                    shopcar_zongji.setText("总计:￥" + sum);
                    shopcar_count.setText("("+0+")");
                }


                shopCaroneRecyclerViewAdpater.notifyDataSetChanged();
            }
        });


        //编辑按钮
        shopcar_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count % 2 == 0) {
                    shopcar_bianji.setText("编辑");
                    for (int i = 0; i < valueslist.size(); i++) {
                        valueslist.get(i).setCountflag(false);
                    }
                    shopcarone_yidong.setVisibility(View.GONE);
                } else if ((count % 2 == 1)) {
                    shopcar_bianji.setText("完成");
                    for (int i = 0; i < valueslist.size(); i++) {
                        valueslist.get(i).setCountflag(true);
                    }
                    shopcarone_yidong.setVisibility(View.VISIBLE);
                }
                shopCaroneRecyclerViewAdpater.notifyDataSetChanged();
            }
        });

        shopcarone_yidong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (int i = 0; i <valueslist.size() ; i++) {
                    if(valueslist.get(i).isFlag()==true){
                        shouCangBeans.add(new ShouCangBean(valueslist.get(i).getShop_name(),valueslist.get(i).getCar_path(),valueslist.get(i).getShop_price()));
                    }
                }

                Toast.makeText(ShopCarActivity.this, "成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {




        shopcar_count = (TextView) findViewById(R.id.shopcar_count);
        biaoti = (TextView) findViewById(R.id.biaoti);
        shopcar_count.setOnClickListener(this);
        shopcarone_yidong = (Button) findViewById(R.id.shopcarone_yidong);
        shopcarone_yidong.setOnClickListener(this);
        shopcar_bianji = (TextView) findViewById(R.id.shopcar_bianji);
        shopcar_bianji.setOnClickListener(this);
        shopcar_zongji = (TextView) findViewById(R.id.shopcar_zongji);
        shopcar_zongji.setOnClickListener(this);
        shopcar_fanhui = findViewById(R.id.shopcar_fanhui);
        shopcar_recyclerviewone = findViewById(R.id.shopcar_recyclerviewone);
        shopcar_recyclerviewtwo = findViewById(R.id.shopcar_recyclerviewtwo);

        tops = (RelativeLayout) findViewById(R.id.tops);
        tops.setOnClickListener(this);
        shopcar_quanxuan = (CheckBox) findViewById(R.id.shopcar_quanxuan);
        shopcar_quanxuan.setOnClickListener(this);
        shopcar_jiesuan = (Button) findViewById(R.id.shopcar_jiesuan);
        shopcar_jiesuan.setOnClickListener(this);
        bottomt = (RelativeLayout) findViewById(R.id.bottomt);
        bottomt.setOnClickListener(this);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }

    public float jisuan(String a, String b) {
        float aa = 0;
        float bb = 0;
        if (!a.equals("")) {
            float aFloat = Float.parseFloat(a.trim());
            float aFloat1 = Float.parseFloat(b.trim());
            aa = aFloat;
            bb = aFloat1;

        }
        return sum += aa * bb;
    }
    public int counts(){
        countes=0;
        for (int i = 0; i < valueslist.size(); i++) {
            if(valueslist.get(i).isFlag()){
                countes++;
            }
        }
        return countes;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopcar_jiesuan:
                payV2();
                break;
            case R.id.shopcarone_yidong:
                break;
        }
    }

    public void payV2() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
//            showAlert(this, getString(R.string.error_missing_appid_rsa_private));
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ShopCarActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
