package com.example.myapplication.mvp.view.fragment;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;


import com.example.myapplication.di.component.DaggerHomeCompoent;
import com.example.myapplication.di.module.HomeMoudle;
import com.example.myapplication.mvp.contact.HomeContact;
import com.example.myapplication.mvp.model.entity.BannerEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.example.myapplication.mvp.model.entity.RecommendEntity;
import com.example.myapplication.mvp.presenter.HomePresenter;
import com.example.myapplication.mvp.view.activity.SouSuoActivity;
import com.example.myapplication.mvp.view.adpater.TabFragmentAdpater;
import com.example.myapplication.mvp.view.adpater.XRecyclerViewAdpater;
import com.google.android.material.tabs.TabLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;


import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends BaseFragment<HomePresenter> implements HomeContact.HomeIView {
    public static PagerFragmentMan pagerFragmentMan = new PagerFragmentMan();
    public static PagerFragmentWoman pagerFragmentWoman = new PagerFragmentWoman();
    public static PagerFragmentShoe pagerFragmentShoe = new PagerFragmentShoe();
    public static PagerFragmentLife pagerFragmentLife = new PagerFragmentLife();

    private View inflate;

    public static TabLayout heard_tablayout;
    public static ViewPager heard_viewpager;
    SmartRefreshLayout man_smartrefresh;

    LinearLayoutManager linearLayoutManager;

    public static XRecyclerView fragment_home_xrecyclerview;
    public static XRecyclerViewAdpater  xRecyclerViewAdpater;


    public static ArrayList<String> tablist = new ArrayList<>();
    public static ArrayList<String> list = new ArrayList<>();
    public static ArrayList<Fragment> fragmentlist = new ArrayList<>();

    public static TabFragmentAdpater tabFragmentAdpater;
    public static NestedScrollView home_scrollview;
    public static Toolbar fragment_home_toolbar;
    public static RelativeLayout home_sou;




    List<MenuEntity.ValuesBean> values;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==1001){
                list.clear();
                list.add(".");
                for (int i = 0; i < values.size(); i++) {
                    MenuEntity.ValuesBean valuesBean = values.get(i);
                    tablist.add(valuesBean.getMenu_name());

                }
                fragmentlist.add(pagerFragmentMan);
                fragmentlist.add(pagerFragmentWoman);
                fragmentlist.add(pagerFragmentShoe);
                fragmentlist.add(pagerFragmentLife);

                tabFragmentAdpater = new TabFragmentAdpater(getChildFragmentManager(), fragmentlist, tablist);
                heard_viewpager.setAdapter(tabFragmentAdpater);
                heard_tablayout.setupWithViewPager(heard_viewpager);
//                heard_tablayouts.setupWithViewPager(heard_viewpager);

//                xRecyclerViewAdpater.notifyDataSetChanged();
            }else if(msg.what==5001){
                fragment_home_toolbar.setVisibility(View.GONE);



            }else if(msg.what==5002){
                fragment_home_toolbar.setVisibility(View.VISIBLE);

                ObjectAnimator translationY = ObjectAnimator.ofFloat(fragment_home_toolbar, "alpha", 0f, 1f);
                translationY.setDuration(1000);
                translationY.start();
            }


        }
    };



    @Override
    public void onSuccess() {

    }

    @Override
    public void resultSuccess(BaseEntity baseEntity, int type) {

        if (type == 1) { //banner


//            BannerEntity bannerEntity = (BannerEntity) baseEntity;
//            List<BannerEntity.ValuesBean> values = bannerEntity.getValues();
//            for (int i = 0; i < values.size(); i++) {
//                Message obtain = Message.obtain();
//                obtain.what=2000;
//                obtain.obj=values.get(i).getRecommend_url();
//                PagerFragmentMan.handler.sendMessage(obtain);
//            }


        }
        if (type == 2) { // recom

            RecommendEntity recommendEntity = (RecommendEntity) baseEntity;
            List<RecommendEntity.RecommendBean> recommend = recommendEntity.getRecommend();
            Log.e("recommend", recommend.toString());


        }
        if (type == 3) { //goods

            GoodsListEntity goodsListEntity = (GoodsListEntity) baseEntity;
            List<GoodsListEntity.ValuesBean> values = goodsListEntity.getValues();
            Log.e("values", values.toString());
        }
    }

    @Override
    public void pullrefresh(BaseEntity baseEntity) {

    }

    @Override
    public void loadmore(BaseEntity baseEntity) {

    }

    @Override
    public void success(MenuEntity menuEntity) {
        tablist.clear();
        fragmentlist.clear();


        values = menuEntity.getValues();
        handler.sendEmptyMessage(1001);



    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeCompoent.builder().appComponent(appComponent).homeMoudle(new HomeMoudle(this)).build().inject(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.layout_fragment_home, null);
        heard_tablayout= inflate.findViewById(R.id.heard_tablayout);
        heard_viewpager= inflate.findViewById(R.id.heard_viewpager);
//        man_smartrefresh = inflate.findViewById(R.id.man_smartrefresh);
//        home_scrollview = inflate.findViewById(R.id.home_scrollview);
        fragment_home_toolbar = inflate.findViewById(R.id.fragment_home_toolbar);
        home_sou = inflate.findViewById(R.id.home_sou);

        init();

        home_sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SouSuoActivity.class);
                startActivity(intent);
            }
        });


        return this.inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        mPresenter.getdata();
        mPresenter.getmenudata();
    }

    private void init() {


//        fragment_home_xrecyclerview = inflate.findViewById(R.id.fragment_home_xrecyclerview);


    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        if (message != null) {
            Log.e("message", message);
        }

    }
}
