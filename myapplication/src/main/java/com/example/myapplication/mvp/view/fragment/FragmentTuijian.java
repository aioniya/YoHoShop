package com.example.myapplication.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreCompoent;
import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.GifiEntity;
import com.example.myapplication.mvp.model.entity.GuanZhuBean;
import com.example.myapplication.mvp.model.entity.UfoBean;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.view.adpater.UfoRecyclerViewAdpater;
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

public class FragmentTuijian extends BaseFragment<LrePresenter> implements LreContact.LreView {
    private ImageView xiaoxi;
    private TabLayout shequ_tablayout;
    private XRecyclerView shequ_recyclerview;
    private RecyclerView guanzhu_recyclerview;
    private ViewPager shequ_viewpager;
    private Banner banner;


    ArrayList<String> bannerlist = new ArrayList<>();
    ArrayList<UfoBean> shequlist = new ArrayList<>();
    ArrayList<GuanZhuBean> guanzhulist = new ArrayList<>();
    ArrayList<Fragment> fragments = new ArrayList<>();


    UfoRecyclerViewAdpater ufoRecyclerViewAdpater;

    @Override
    public void success(BaseEntity baseEntity, int type) {

        if (type == ApiContact.Gifi_LIST) {

            GifiEntity gifiEntity = (GifiEntity) baseEntity;
            List<GifiEntity.BannerBean> bannerlistbean = gifiEntity.getBanner();
            for (int i = 0; i < bannerlistbean.size(); i++) {
                bannerlist.add(Api.APP_DOMAIN+bannerlistbean.get(i).getRecommend_url());
            }
            banner.setImages(bannerlist);
            banner.start();



            List<GifiEntity.ValuesBean> values = gifiEntity.getValues();
            for (int i = 0; i <values.size() ; i++) {
                GifiEntity.ValuesBean valuesBean = values.get(i);
                String title = valuesBean.getTitle();
                List<GifiEntity.ValuesBean.ImgsBean> imgs = valuesBean.getImgs();
                for (int j = 0; j < imgs.size(); j++) {
                    GifiEntity.ValuesBean.ImgsBean imgsBean = imgs.get(j);
                    String img_path = imgsBean.getImg_path();
                    shequlist.add(new UfoBean(Api.APP_DOMAIN +img_path+".jpg",title));
                    guanzhulist.add(new GuanZhuBean(Api.APP_DOMAIN +img_path+".jpg","梵高","关注"));
                }
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
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_shequ_tuijian, null);

        shequ_recyclerview = inflate.findViewById(R.id.shequ_recyclerview);

        View inflate1 = LayoutInflater.from(getActivity()).inflate(R.layout.layout_heard, null);
        banner = inflate1.findViewById(R.id.heard_banner);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });

        shequ_recyclerview.addHeaderView(inflate1);

        ufoRecyclerViewAdpater = new UfoRecyclerViewAdpater(shequlist,getActivity());
        shequ_recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        shequ_recyclerview.setAdapter(ufoRecyclerViewAdpater);
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
        mPresenter.lreAll(params, ApiContact.Gifi_LIST);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
