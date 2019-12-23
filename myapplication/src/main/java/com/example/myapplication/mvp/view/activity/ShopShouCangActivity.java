package com.example.myapplication.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.ShouCangBean;
import com.example.myapplication.mvp.view.adpater.ShopShouCangRecyclerViewAdpater;
import com.example.myapplication.mvp.view.adpater.ShopcaroneRecyclerViewAdpater;

import java.util.ArrayList;
import java.util.Iterator;

public class ShopShouCangActivity extends AppCompatActivity {

    private TextView shopshoucang_fanhui;
    private TextView shopshoucang_bianji;
    private RecyclerView shopshoucang_recyclerview;

    ShopShouCangRecyclerViewAdpater shopcaroneRecyclerViewAdpater;

    ArrayList<ShouCangBean> listbean = new ArrayList();


    String name = null;
    String pic = null;
    String price = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_shou_cang);
        initView();

        Intent intent = getIntent();
        ArrayList<ShouCangBean> list = intent.getParcelableArrayListExtra("list");
        listbean=list;
        Log.e("listbean",listbean.size()+"");
//        for (int i = 0; i < listbean.size(); i++) {
//             name = listbean.get(i).getName();
//             pic = listbean.get(i).getPic();
//             price = listbean.get(i).getPrice();
//
//            MainActivity.spedit.putString(name,name).commit();
//            MainActivity.spedit.putString(pic,pic).commit();
//            MainActivity.spedit.putString(price,price).commit();
//
//        }
        if(list!=null){
            shopcaroneRecyclerViewAdpater = new ShopShouCangRecyclerViewAdpater(listbean, this) {
                @Override
                public void show(baseview holder, int position) {
                    holder.ce_shanchu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ShopCarActivity.shouCangBeans.remove(position);
//                            Iterator<ShouCangBean> iterator = ShopCarActivity.shouCangBeans.iterator();
//                            while (iterator.hasNext()){
//                                ShouCangBean next = iterator.next();
//                                if(next.getName().equals(listbean.get(position).getName())){
//                                    ShopCarActivity.shouCangBeans.remove(next);
//                                }
//                            }
                            listbean.remove(position);


                            shopcaroneRecyclerViewAdpater.notifyDataSetChanged();
                        }
                    });
                }
            };
            shopshoucang_recyclerview.setLayoutManager(new LinearLayoutManager(this));
            shopshoucang_recyclerview.setAdapter(shopcaroneRecyclerViewAdpater);
        }

        shopshoucang_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {
        shopshoucang_fanhui = (TextView) findViewById(R.id.shopshoucang_fanhui);
        shopshoucang_bianji = (TextView) findViewById(R.id.shopshoucang_bianji);
        shopshoucang_recyclerview = (RecyclerView) findViewById(R.id.shopshoucang_recyclerview);
    }
    public void showw(){
        listbean.clear();
        String nameq = MainActivity.sp.getString(name, null);
        String picq = MainActivity.sp.getString(pic, null);
        String priceq = MainActivity.sp.getString(price, null);
        listbean.add(new ShouCangBean(nameq,picq,priceq));
    }
    @Override
    protected void onResume() {
        super.onResume();
//        listbean.clear();
//        String nameq = MainActivity.sp.getString(name, null);
//        String picq = MainActivity.sp.getString(pic, null);
//        String priceq = MainActivity.sp.getString(price, null);
//        listbean.add(new ShouCangBean(nameq,picq,priceq));

    }
}
