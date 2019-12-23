package com.example.myapplication.mvp.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreCompoent;
import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.ShoesEntity;
import com.example.myapplication.mvp.model.entity.UfoBean;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.view.adpater.UfoRecyclerViewAdpater;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentUfo extends BaseFragment<LrePresenter> implements LreContact.LreView {

    private TabLayout ufo_tablayout;
    private XBanner ufo_banner;
    private RecyclerView ufo_recyclerview;



    ArrayList<String> bannerlist = new ArrayList<>();
    ArrayList<UfoBean> ufolist = new ArrayList<>();


    UfoRecyclerViewAdpater ufoRecyclerViewAdpater;

    private ImageView ufo_centerimage;
    private ImageView ufo_hhhimage;
    List<ShoesEntity.ValuesBean> values;

    @Override
    public void success(BaseEntity baseEntity, int type) {
        if (type == ApiContact.SHOES_LIST) {
            bannerlist.clear();
            ShoesEntity shoesEntity = (ShoesEntity) baseEntity;
            //banner
            List<ShoesEntity.BannerBean> banner = shoesEntity.getBanner();

            for (int i = 0; i < banner.size(); i++) {


                bannerlist.add(Api.APP_DOMAIN + banner.get(i).getRecommend_url());
            }
            ufo_banner.setData(bannerlist,null);




            List<ShoesEntity.BrandBean> brand = shoesEntity.getBrand();
            Log.e("shoes1", brand.toString());

            //values
            values = shoesEntity.getValues();
            Log.e("shoes2", values.toString());
            for (int i = 0; i < values.size(); i++) {
                Log.e("values1", values.get(i).getDiscount_detail());
                Log.e("values2", values.get(i).getDetail_flag());
                Log.e("values3", values.get(i).getFollow());
                Log.e("values4", values.get(i).getGoods_original_price());
                Log.e("values5", values.get(i).getGoods_popularity());



                ShoesEntity.ValuesBean valuesBean = values.get(i);
                String goods_name = valuesBean.getGoods_name();
                String goods_img_path = valuesBean.getGoods_img_path();

                ufolist.add(new UfoBean(Api.APP_DOMAIN + goods_img_path, goods_name));
            }


            ufoRecyclerViewAdpater.notifyDataSetChanged();
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
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLreCompoent.builder().appComponent(appComponent).lreMoudle(new LreMoudle(this)).build().inject(this);

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment_ufo, null);
        ufo_banner = inflate.findViewById(R.id.ufo_banner);
        ufo_tablayout = inflate.findViewById(R.id.ufo_tablayout);
        ufo_recyclerview = inflate.findViewById(R.id.ufo_recyclerview);
        ufo_centerimage = inflate.findViewById(R.id.ufo_centerimage);
        ufo_hhhimage = inflate.findViewById(R.id.ufo_hhhimage);


        ufo_tablayout.removeAllTabs();

        ufo_tablayout.addTab(ufo_tablayout.newTab().setText("推荐"));
        ufo_tablayout.addTab(ufo_tablayout.newTab().setText("新品"));
        ufo_tablayout.addTab(ufo_tablayout.newTab().setText("人气"));
        ufo_tablayout.addTab(ufo_tablayout.newTab().setText("潮搭"));
        ufo_tablayout.addTab(ufo_tablayout.newTab().setText("配饰"));
        ufo_tablayout.addTab(ufo_tablayout.newTab().setText("实战"));
        ufo_tablayout.addTab(ufo_tablayout.newTab().setText("女神"));

        ufo_banner.setPageTransformer(Transformer.Default);
        ufo_banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(bannerlist.get(position)).into((ImageView) view);
            }
        });



        ufo_tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                TextView textView = new TextView(getActivity());
                float selectedSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 14, getResources().getDisplayMetrics());
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,selectedSize);
                textView.setTextColor(Color.BLACK);
                textView.setText(tab.getText());
                tab.setCustomView(textView);


                String tabtext = tab.getText().toString();
                if(tabtext.equals("推荐")){
                    ufo_hhhimage.setVisibility(View.GONE);
                    ufo_banner.setVisibility(View.VISIBLE);
                    ufo_centerimage.setVisibility(View.VISIBLE);
                }
                if(tabtext.equals("新品")){
                    ufo_banner.setVisibility(View.GONE);
                    ufo_centerimage.setVisibility(View.GONE);

                }
                if(tabtext.equals("人气")){
                    ufo_hhhimage.setVisibility(View.VISIBLE);
                    ufo_banner.setVisibility(View.GONE);
                    ufo_centerimage.setVisibility(View.GONE);

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.hhhrenqi);
                    ufo_hhhimage.setImageBitmap(bitmap);

                }
                if(tabtext.equals("潮搭")){

                    ufo_hhhimage.setVisibility(View.VISIBLE);
                    ufo_banner.setVisibility(View.GONE);
                    ufo_centerimage.setVisibility(View.GONE);

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.hhhchaoda);
                    ufo_hhhimage.setImageBitmap(bitmap);
                }
                if(tabtext.equals("配饰")){
                    ufo_hhhimage.setVisibility(View.VISIBLE);
                    ufo_banner.setVisibility(View.GONE);
                    ufo_centerimage.setVisibility(View.GONE);

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.hhhpeishi);
                    ufo_hhhimage.setImageBitmap(bitmap);
                }
                if(tabtext.equals("实战")){
                    ufo_hhhimage.setVisibility(View.GONE);
                    ufo_banner.setVisibility(View.GONE);
                    ufo_centerimage.setVisibility(View.GONE);
                }
                if(tabtext.equals("女神")){
                    ufo_hhhimage.setVisibility(View.GONE);
                    ufo_banner.setVisibility(View.GONE);
                    ufo_centerimage.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });











        ufoRecyclerViewAdpater = new UfoRecyclerViewAdpater(ufolist, getActivity());
        ufo_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ufo_recyclerview.setAdapter(ufoRecyclerViewAdpater);
        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        JSONObject jsonObject = new JSONObject();
        try {
            //{"page":"1"}
            jsonObject.put("page", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String params = jsonObject.toString();
        mPresenter.lreAll(params, ApiContact.SHOES_LIST);


    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    //

}
