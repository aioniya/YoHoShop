package com.example.myapplication.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import com.example.myapplication.di.component.DaggerLreCompoent;
import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.CategoryGoodsEntity;
import com.example.myapplication.mvp.model.entity.PinLeiBean;
import com.example.myapplication.mvp.model.entity.UiBean;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.view.activity.SouSuoActivity;
import com.example.myapplication.mvp.view.adpater.PinLeiRecyclerViewAdpater;
import com.example.myapplication.mvp.view.adpater.PinLeiXRecyclerViewAdpater;
import com.google.android.material.tabs.TabLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class TypeFragment_PinLei extends BaseFragment<LrePresenter> implements LreContact.LreView {
    Unbinder bind;

    @BindView(R.id.pinpei_XRecyclerView)
    public XRecyclerView pinpei_XRecyclerView;

    @BindView(R.id.pinpei_RecyclerView)
    public RecyclerView pinpei_RecyclerView;
//    @BindView(R.id.tops_banner)
//    public Banner tops_banner;


    ArrayList<PinLeiBean> pinLeiBeans = new ArrayList<>();
    ArrayList<UiBean> Stringlist = new ArrayList<>();
    ArrayList<Integer> bannlist = new ArrayList<>();
    PinLeiXRecyclerViewAdpater pinLeiXRecyclerViewAdpater;
    PinLeiRecyclerViewAdpater pinLeiRecyclerViewAdpater;


    List<CategoryGoodsEntity.ValuesBean> values;

    @Override
    public void success(BaseEntity baseEntity, int type) {

            if(type == ApiContact.CATEGORY_GOODS){
                pinLeiBeans.clear();

                CategoryGoodsEntity categoryGoods = (CategoryGoodsEntity) baseEntity;
                Log.e("categoryGoods",categoryGoods.getValues().size()+"");
                values = categoryGoods.getValues();


                for (int i = 1; i <=3; i++) {

                    pinLeiBeans.add(new PinLeiBean("热门品牌男装"+i,Api.APP_DOMAIN+values.get(i).getImage_path(),values.get(i).getName(),0));

                    pinLeiBeans.add(new PinLeiBean(null,Api.APP_DOMAIN+values.get(i).getImage_path(),values.get(i).getName(),1));

                 }


                pinLeiXRecyclerViewAdpater.notifyDataSetChanged();


            }




    }

    @Override
    public void error(String error) {
        if(error!=null){
            Log.e("error",error);
        }

    }

    @Override
    public void refreshSuccess(BaseEntity entity) {

    }

    @Override
    public void loadSuccess(BaseEntity entity) {

    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLreCompoent.builder().appComponent(appComponent).lreMoudle(new LreMoudle(this)).build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_type_pinlei, null);
        bind = ButterKnife.bind(this, inflate);

        pinLeiXRecyclerViewAdpater = new PinLeiXRecyclerViewAdpater(pinLeiBeans,getActivity());
        pinpei_XRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        pinpei_XRecyclerView.setAdapter(pinLeiXRecyclerViewAdpater);
        bannlist.clear();

        bannlist.add(R.mipmap.bannerone);
        bannlist.add(R.mipmap.bannertwo);
        bannlist.add(R.mipmap.bannerthree);


        View inflate1 = LayoutInflater.from(getActivity()).inflate(R.layout.layout_top_banner, null);
        pinpei_XRecyclerView.addHeaderView(inflate1);


        Banner banner = inflate1.findViewById(R.id.top_banner);
        banner.setImages(bannlist);

        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.start();






        Stringlist.clear();
        Stringlist.add(new UiBean("男装",true));
        Stringlist.add(new UiBean("女装",false));
        Stringlist.add(new UiBean("球鞋",false));
        Stringlist.add(new UiBean("运动",false));

        pinLeiRecyclerViewAdpater = new PinLeiRecyclerViewAdpater(Stringlist, getActivity()) {
            @Override
            public void OnClicken(PinLeiRecyclerViewAdpater.baseview holder, int position) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(mContext,Stringlist.get(position).getTitle() , Toast.LENGTH_SHORT).show();
                        pinLeiBeans.clear();
                        for (int i = 1; i <=3; i++) {
                            pinLeiBeans.add(new PinLeiBean("热门品牌"+Stringlist.get(position).getTitle()+i,Api.APP_DOMAIN+values.get(i).getImage_path(),values.get(i).getName(),0));
                            pinLeiBeans.add(new PinLeiBean(null,Api.APP_DOMAIN+values.get(i).getImage_path(),values.get(i).getName(),1));
                        }


                        for (int i = 0; i < Stringlist.size(); i++) {
                            Stringlist.get(i).setFlag(false);
                        }
                        Stringlist.get(position).setFlag(true);



                        pinLeiRecyclerViewAdpater.notifyDataSetChanged();
                        pinLeiXRecyclerViewAdpater.notifyDataSetChanged();
                    }
                });
                if(Stringlist.get(position).isFlag()==true){
                    holder.linearLayout.setVisibility(View.VISIBLE);
                    holder.datext.setVisibility(View.VISIBLE);
                }else{
                    holder.linearLayout.setVisibility(View.GONE);
                    holder.datext.setVisibility(View.GONE);
                }
            }
        };
        pinpei_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        pinpei_RecyclerView.setAdapter(pinLeiRecyclerViewAdpater);


        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("categoryid","1");
        } catch (JSONException e) {
        e.printStackTrace();
    }
    String params = jsonObject.toString();

        mPresenter.lreAll(params,ApiContact.CATEGORY_GOODS);
//        mPresenter.lreAll("1", ApiContact.CATEGORY_GOODS);

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
}
