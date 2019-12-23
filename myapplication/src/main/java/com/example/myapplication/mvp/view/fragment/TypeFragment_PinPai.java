package com.example.myapplication.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreCompoent;
import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.api.IOnTouchEvent;
import com.example.myapplication.mvp.model.entity.ABCDBean;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.view.adpater.ListAdpater;
import com.example.myapplication.mvp.view.adpater.PinPaiRecyclerViewAdpater;
import com.example.myapplication.ui.MyListView;
import com.example.myapplication.ui.MyScorllRight;
import com.google.android.material.appbar.AppBarLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

public class TypeFragment_PinPai extends BaseFragment<LrePresenter> implements LreContact.LreView {
    Unbinder bind;


//    @BindView(R.id.pinpai_banner)
//    public Banner pinpai_banner;
//
//    @BindView(R.id.pinpai_group)
//    public RadioGroup pinpai_group;
//
//
//    @BindView(R.id.pinpai_chaotong)
//    public RadioButton pinpai_chaotong;
//
//    @BindView(R.id.pinpai_gaojie)
//    public RadioButton pinpai_gaojie;
//
//
//    @BindView(R.id.pinpai_nanzhuang)
//    public RadioButton pinpai_nanzhuang;
//
//    @BindView(R.id.pinpai_nvzhuang)
//    public RadioButton pinpai_nvzhuang;
//
//    @BindView(R.id.pinpai_shenghuofangshi)
//    public RadioButton pinpai_shenghuofangshi;
//
//    @BindView(R.id.pinpai_recyclerview)
//    public RecyclerView pinpai_recyclerview;
//
//    @BindView(R.id.pinpai_quanbu)
//    public RadioButton pinpai_quanbu;
//
//    @BindView(R.id.pinpai_remen)
//    public RadioButton pinpai_remen;
//
//    @BindView(R.id.pinpai_xinruzhu)
//    public RadioButton pinpai_xinruzhu;
//
//    @BindView(R.id.pinpai_grouptwo)
//    public RadioGroup pinpai_grouptwo;
//
//    @BindView(R.id.pinpai_recyclerviewtwo)
//    public RecyclerView pinpai_recyclerviewtwo;


    PinPaiRecyclerViewAdpater pinPaiRecyclerViewAdpater;
    ListAdpater listAdpater;


    public ArrayList<Integer> bannerlist = new ArrayList<>();
    public ArrayList<String> oldlist = new ArrayList<>();
    public ArrayList<String> newlist = new ArrayList<>();
    public ArrayList<ABCDBean> abcdlist = new ArrayList<>();


    private RadioButton pinpai_nanzhuang;
    private RadioButton pinpai_nvzhuang;
    private RadioButton pinpai_shenghuofangshi;
    private RadioButton pinpai_chaotong;
    private RadioButton pinpai_gaojie;
    private RadioGroup pinpai_group;
//    private RecyclerView pinpai_recyclerview;
    private RadioButton pinpai_quanbu;
    private RadioButton pinpai_xinruzhu;
    private RadioButton pinpai_remen;
    private RadioGroup pinpai_grouptwo;
    private RecyclerView pinpai_recyclerviewtwo;
    private Banner pinpai_banner;
    private MyScorllRight pinpai_myscrollright;
    private RecyclerView pinpai_listview;
    private AppBarLayout pinpai_appbar;



    public List<BrandListEntity.ValuesBean> values;

    @Override
    public void success(BaseEntity baseEntity, int type) {
        if (type == ApiContact.BRAND_LIST) {

            BrandListEntity brandListEntity = (BrandListEntity) baseEntity;
            values = brandListEntity.getValues();


            abcdlist.clear();
            if(values!=null){
                pinpai_listview.setVisibility(View.VISIBLE);
                pinpai_recyclerviewtwo.setVisibility(View.GONE);
                for (int i = 0; i < values.size(); i++) {

                    String brand_letter = values.get(i).getBrand_letter();
                    String brand_name = values.get(i).getBrand_name();
                    abcdlist.add(new ABCDBean(brand_letter,brand_name));
                }

                listAdpater.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void error(String error) {
        if (error != null) {
            Log.e("error", error);
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
        View inflate = inflater.inflate(R.layout.layout_type_pinpai, null);

        pinpai_nanzhuang=inflate.findViewById(R.id.pinpai_nanzhuang);
        pinpai_nvzhuang=inflate.findViewById(R.id.pinpai_nvzhuang);
        pinpai_chaotong=inflate.findViewById(R.id.pinpai_chaotong);
        pinpai_shenghuofangshi=inflate.findViewById(R.id.pinpai_shenghuofangshi);
        pinpai_gaojie=inflate.findViewById(R.id.pinpai_gaojie);

        pinpai_group=inflate.findViewById(R.id.pinpai_group);
//        pinpai_recyclerview=inflate.findViewById(R.id.pinpai_recyclerview);
        pinpai_quanbu=inflate.findViewById(R.id.pinpai_quanbu);
        pinpai_xinruzhu=inflate.findViewById(R.id.pinpai_xinruzhu);
        pinpai_remen=inflate.findViewById(R.id.pinpai_remen);
        pinpai_grouptwo=inflate.findViewById(R.id.pinpai_grouptwo);
        pinpai_recyclerviewtwo=inflate.findViewById(R.id.pinpai_recyclerviewtwo);
        pinpai_banner=inflate.findViewById(R.id.pinpai_banner);
        pinpai_myscrollright=inflate.findViewById(R.id.pinpai_myscrollright);
        pinpai_listview=inflate.findViewById(R.id.pinpai_listview);
        pinpai_appbar=inflate.findViewById(R.id.pinpai_appbar);


        pinpai_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(pinpai_nanzhuang.isChecked()){
                    bannerlist.clear();
                    bannerlist.add(R.mipmap.pinpaibannerone);
                    bannerlist.add(R.mipmap.pinpaibannertwo);
                    bannerlist.add(R.mipmap.pinpaibannerthree);
                    pinpai_banner.setImages(bannerlist);
                    pinpai_banner.start();
                }
                if(pinpai_nvzhuang.isChecked()){
                    bannerlist.clear();
                    bannerlist.add(R.mipmap.nvzhuangone);
                    bannerlist.add(R.mipmap.nvzhuangtwo);
                    bannerlist.add(R.mipmap.nvzhuangthree);
                    pinpai_banner.setImages(bannerlist);
                    pinpai_banner.start();
                }
                if(pinpai_shenghuofangshi.isChecked()){
                    bannerlist.clear();
                    bannerlist.add(R.mipmap.pinpaibannerone);
                    bannerlist.add(R.mipmap.pinpaibannertwo);
                    bannerlist.add(R.mipmap.pinpaibannerthree);
                    pinpai_banner.setImages(bannerlist);
                    pinpai_banner.start();
                }
                if(pinpai_chaotong.isChecked()){
                    bannerlist.clear();
                    bannerlist.add(R.mipmap.tongzhuangone);
                    bannerlist.add(R.mipmap.tongzhuangtwo);
                    bannerlist.add(R.mipmap.tongzhuangthree);
                    pinpai_banner.setImages(bannerlist);
                    pinpai_banner.start();
                }
                if(pinpai_gaojie.isChecked()){
                    bannerlist.clear();
                    bannerlist.add(R.mipmap.pinpaibannerone);
                    bannerlist.add(R.mipmap.pinpaibannertwo);
                    bannerlist.add(R.mipmap.pinpaibannerthree);
                    pinpai_banner.setImages(bannerlist);
                    pinpai_banner.start();
                }
            }
        });
//        pinpai_listview.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_UP){
//                    pinpai_group.requestDisallowInterceptTouchEvent(false);
//                }else{
//                    pinpai_group.requestDisallowInterceptTouchEvent(true);//屏蔽父控件的拦截事件
//                }
//                return false;
//            }
//        });

        pinpai_myscrollright.setTouchEvent(new IOnTouchEvent() {
            @Override
            public void onTouchEvent(int index) {

                pinpai_appbar.setExpanded(false);

                ((LinearLayoutManager) pinpai_listview.getLayoutManager()).scrollToPositionWithOffset(index,0);

            }
        });



//        bind = ButterKnife.bind(this, inflate);


        pinpai_grouptwo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (pinpai_quanbu.isChecked()) {
//                    pinpai_recyclerviewtwo.setVisibility(View.GONE);
                    abcdlist.clear();
                    if(values!=null){
                        pinpai_myscrollright.setVisibility(View.VISIBLE);
                        pinpai_listview.setVisibility(View.VISIBLE);
                        pinpai_recyclerviewtwo.setVisibility(View.GONE);
                        for (int i = 0; i < values.size(); i++) {

                            String brand_letter = values.get(i).getBrand_letter();
                            String brand_name = values.get(i).getBrand_name();
                            abcdlist.add(new ABCDBean(brand_letter,brand_name));
                        }

                        listAdpater.notifyDataSetChanged();
                    }


                }
                if (pinpai_xinruzhu.isChecked()) {
                    pinpai_myscrollright.setVisibility(View.GONE);
                    pinpai_listview.setVisibility(View.GONE);
                    pinpai_recyclerviewtwo.setVisibility(View.VISIBLE);
                    oldlist.clear();
                    if(values!=null){
                        for (int i = 0; i < values.size(); i++) {

                            oldlist.add(Api.APP_DOMAIN +values.get(i).getBrand_icon());
                        }
                    }


                    pinPaiRecyclerViewAdpater.notifyDataSetChanged();

                }
                if (pinpai_remen.isChecked()) {
                    pinpai_myscrollright.setVisibility(View.GONE);
                    pinpai_listview.setVisibility(View.GONE);
                    pinpai_recyclerviewtwo.setVisibility(View.VISIBLE);
                    oldlist.clear();
                    if(values!=null){
                        for (int i = 0; i < values.size(); i++) {

                            oldlist.add(Api.APP_DOMAIN +values.get(i).getBrand_bg());

                        }
                    }

                    pinPaiRecyclerViewAdpater.notifyDataSetChanged();
                }
            }
        });


        bannerlist.clear();
        bannerlist.add(R.mipmap.pinpaibannerone);
        bannerlist.add(R.mipmap.pinpaibannertwo);
        bannerlist.add(R.mipmap.pinpaibannerthree);

        pinpai_banner.setImages(bannerlist);
        pinpai_banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {

                Glide.with(context).load(path).into(imageView);
            }
        });
        pinpai_banner.start();


        pinPaiRecyclerViewAdpater = new PinPaiRecyclerViewAdpater(oldlist, getActivity());
        pinpai_recyclerviewtwo.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        pinpai_recyclerviewtwo.setAdapter(pinPaiRecyclerViewAdpater);


        listAdpater = new ListAdpater(abcdlist,getActivity());
        pinpai_listview.setLayoutManager(new LinearLayoutManager(getActivity()));
        pinpai_listview.setAdapter(listAdpater);

        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("menuid", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String params = jsonObject.toString();
        mPresenter.lreAll(params, ApiContact.BRAND_LIST);

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
