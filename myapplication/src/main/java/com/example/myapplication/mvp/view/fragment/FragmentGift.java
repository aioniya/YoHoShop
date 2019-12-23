package com.example.myapplication.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.example.myapplication.mvp.view.activity.MainActivity;
import com.example.myapplication.mvp.view.adpater.GuanZhuRecyclerViewAdpater;
import com.example.myapplication.mvp.view.adpater.TabTabFragmentAdpater;
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

public class FragmentGift extends BaseFragment<LrePresenter> implements LreContact.LreView {

    private ImageView xiaoxi;
    private ImageView gift_touxiang;
    private TabLayout shequ_tablayout;
    private XRecyclerView shequ_recyclerview;
    private RecyclerView guanzhu_recyclerview;
    private ViewPager shequ_viewpager;


    ArrayList<String> bannerlist = new ArrayList<>();
    ArrayList<UfoBean> shequlist = new ArrayList<>();
    ArrayList<GuanZhuBean> guanzhulist = new ArrayList<>();
    ArrayList<Fragment> fragments = new ArrayList<>();


    UfoRecyclerViewAdpater ufoRecyclerViewAdpater;

    Banner banner;
    @Override
    public void success(BaseEntity baseEntity, int type) {}

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
//        DaggerLreCompoent.builder().appComponent(appComponent).lreMoudle(new LreMoudle(this)).build().inject(this);

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment_gift, null);
        shequ_tablayout= inflate.findViewById(R.id.shequ_tablayout);
//        shequ_recyclerview= inflate.findViewById(R.id.shequ_recyclerview);
//        guanzhu_recyclerview= inflate.findViewById(R.id.guanzhu_recyclerview);
        shequ_viewpager= inflate.findViewById(R.id.shequ_viewpager);
        gift_touxiang= inflate.findViewById(R.id.gift_touxiang);

        shequ_tablayout.removeAllTabs();
        shequ_tablayout.addTab(shequ_tablayout.newTab().setText("关注"));
        shequ_tablayout.addTab(shequ_tablayout.newTab().setText("推荐"));
        shequ_tablayout.addTab(shequ_tablayout.newTab().setText("最新"));


        gift_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentGuanzhu.handler.sendEmptyMessage(9000);
            }
        });
//
//        guanzhulist.clear();

//        GuanZhuRecyclerViewAdpater zhuRecyclerViewAdpater = new GuanZhuRecyclerViewAdpater(guanzhulist,getActivity());

        fragments.clear();
        fragments.add(new FragmentGuanzhu());
        fragments.add(new FragmentTuijian());
        fragments.add(new FragmentZuixin());


        TabTabFragmentAdpater tabTabFragmentAdpater = new TabTabFragmentAdpater(getChildFragmentManager(),fragments);
        shequ_viewpager.setAdapter(tabTabFragmentAdpater);
        shequ_tablayout.setupWithViewPager(shequ_viewpager);


        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
