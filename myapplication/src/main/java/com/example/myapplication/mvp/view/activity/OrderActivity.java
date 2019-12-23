package com.example.myapplication.mvp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.myapplication.R;

import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.api.Contact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.OrderEntity;

import com.example.myapplication.mvp.presenter.LrePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;

public class OrderActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {

    @BindView(R.id.order_rlist)
    RecyclerView recyclerView;
    @BindView(R.id.order_back)
    ImageView back;

    CommonAdapter adapter;
    ArrayList<OrderEntity.ValuesBean> lists = new ArrayList<>();
    String id;

    @Override
    public void success(BaseEntity baseEntity, int type) {
//        if (type == Contact.ORDER){
//            OrderEntity orderEntity = (OrderEntity) baseEntity;
//            if(orderEntity != null) {
//                lists.addAll(orderEntity.getValues());
//                adapter.notifyDataSetChanged();
//            }
//        }
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
//        DaggerLreContact.builder().appComponent(appComponent).lreModule(new LreModule(this)).build().Inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_order;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        id = getIntent().getStringExtra("id");
        initRlist();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        mPresenter.lreAll(Contact.joint("userid","1"),Contact.ORDER);
    }

    private void initRlist() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new CommonAdapter(this,R.layout.order_item,lists) {
            @Override
            protected void convert(ViewHolder holder, Object o, int position) {
                TextView name = holder.getView(R.id.order_name_txt);
                TextView bianhao = holder.getView(R.id.order_bianhao_txt);
                TextView num = holder.getView(R.id.order_num_txt);
                TextView color = holder.getView(R.id.order_color_txt);
                TextView size = holder.getView(R.id.order_size_txt);
                TextView price = holder.getView(R.id.order_price_txt);
                ImageView img = holder.getView(R.id.order_img);
                OrderEntity.ValuesBean bean = lists.get(position);
                Glide.with(OrderActivity.this).load(Api.APP_DOMAIN+bean.getCar_path()).into(img);
                name.setText(bean.getShop_name());
                bianhao.setText("952451351561");
                num.setText("×"+bean.getShop_num());
                color.setText(bean.getShop_color());
                size.setText(bean.getShop_size());
                price.setText("￥"+bean.getShop_price());
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
