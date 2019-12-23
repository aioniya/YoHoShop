package com.example.myapplication.mvp.view.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerHomeCompoent;
import com.example.myapplication.di.module.HomeMoudle;
import com.example.myapplication.mvp.contact.HomeContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.BannerEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.example.myapplication.mvp.model.entity.RecommendEntity;
import com.example.myapplication.mvp.model.entity.StaggBean;
import com.example.myapplication.mvp.presenter.HomePresenter;
import com.example.myapplication.mvp.view.adpater.CategoryRecyclerViewAdpater;
import com.example.myapplication.mvp.view.adpater.RecyclerViewAdpater;
import com.example.myapplication.ui.AppBarStateChangListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PagerFragmentWoman extends BaseFragment<HomePresenter> implements HomeContact.HomeIView {
    public static ArrayList<String> bannerlist = new ArrayList<>();
    public static ArrayList<Integer> ajbanner = new ArrayList<>();
    public static ArrayList<StaggBean> stagglist = new ArrayList<>();
    public static ArrayList<GoodsListEntity.ValuesBean> Categorylist = new ArrayList<>();

    List<RecommendEntity.CategoryBean> category;



    public static Banner man_banner;
//    public static Banner man_ajbanner;
    public static NestedScrollView man_scrollview;
//    public static RecyclerView man_staggview;
    public static XRecyclerView man_recyclerview;
    public static TabLayout man_tablayout;

    RecyclerViewAdpater recyclerViewAdpater;
    CategoryRecyclerViewAdpater categoryRecyclerViewAdpater;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    AppBarLayout man_appbarlayout;
    int count = 1;
    String category_positionid=null;
    @Override
    public void onDestroy() {
        super.onDestroy();
        bannerlist.clear();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void resultSuccess(BaseEntity baseEntity, int type) {



        if (type == 1) { //banner

            BannerEntity bannerEntity = (BannerEntity) baseEntity;
            List<BannerEntity.ValuesBean> values = bannerEntity.getValues();
            for (int i = 0; i < values.size(); i++) {
                bannerlist.add(Api.APP_DOMAIN+values.get(i).getRecommend_url());
            }
            man_banner.setImages(bannerlist);
            man_banner.start();

        }
        if (type == 2) { // recom
            man_tablayout.removeAllTabs();
            RecommendEntity recommendEntity = (RecommendEntity) baseEntity;
            category = recommendEntity.getCategory();

            if(category!=null){
                for (int i = 0; i < category.size(); i++) {
                    String category_name = this.category.get(i).getCategory_name();
                    Log.e("category_name",category_name);
                    man_tablayout.addTab(man_tablayout.newTab().setText(category_name));
                }

            }

            List<RecommendEntity.RecommendBean> recommend = recommendEntity.getRecommend();
            for (int i = 0; i <recommend.size() ; i++) {
                String recommend_name = recommend.get(i).getRecommend_name();

            }


        }
        if (type == 3) { //goods
            Categorylist.clear();

            GoodsListEntity goodsListEntity = (GoodsListEntity) baseEntity;
            List<GoodsListEntity.ValuesBean> values = goodsListEntity.getValues();
            if(values!=null){
                Categorylist.addAll(values);
            }

            categoryRecyclerViewAdpater.notifyDataSetChanged();
        }
        if(type == 4){

            GoodsListEntity goodsListEntity = (GoodsListEntity) baseEntity;
            List<GoodsListEntity.ValuesBean> values = goodsListEntity.getValues();
            if(values!=null){
                Categorylist.addAll(values);
            }

            categoryRecyclerViewAdpater.notifyDataSetChanged();

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

    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeCompoent.builder().appComponent(appComponent).homeMoudle(new HomeMoudle(this)).build().inject(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_pager_fragment_woman, null);
        man_banner=inflate.findViewById(R.id.man_banner);
//        man_staggview=inflate.findViewById(R.id.man_staggview);
//        man_ajbanner=inflate.findViewById(R.id.man_ajbanner);
//        man_scrollview = inflate.findViewById(R.id.man_scrollview);
        man_tablayout = inflate.findViewById(R.id.man_tablayout);
        man_recyclerview = inflate.findViewById(R.id.man_recyclerview);
        man_appbarlayout = inflate.findViewById(R.id.man_appbarlayout);
        man_appbarlayout.addOnOffsetChangedListener(new AppBarStateChangListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int i) {
                switch (state){
                    case IDLE:
//                        Log.e("state",i+"");
                        FragmentHome.heard_tablayout.setVisibility(View.VISIBLE);
                        break;
                    case COLLAPSED:
//                        Log.e("state",i+"");
                        FragmentHome.heard_tablayout.setVisibility(View.GONE);

                        break;
                }
            }
        });

        stagglist.clear();
        ajbanner.clear();

        ajbanner.add(R.mipmap.ajone);
        ajbanner.add(R.mipmap.ajtwo);
        ajbanner.add(R.mipmap.ajthree);

        stagglist.add(new StaggBean(R.mipmap.six,"球鞋鉴定"));
        stagglist.add(new StaggBean(R.mipmap.one,"球鞋日历"));
        stagglist.add(new StaggBean(R.mipmap.two,"有货推手"));
        stagglist.add(new StaggBean(R.mipmap.three,"0元抽奖"));
        stagglist.add(new StaggBean(R.mipmap.four,"潮物奥莱"));
        stagglist.add(new StaggBean(R.mipmap.five,"潮流趋势"));

//
//        man_ajbanner.setImages(ajbanner);
//        man_ajbanner.setImageLoader(new ImageLoader() {
//            @Override
//            public void displayImage(Context context, Object path, ImageView imageView) {
//                Glide.with(context).load(path).into(imageView);
//            }
//        });
//        man_ajbanner.start();


        recyclerViewAdpater = new RecyclerViewAdpater(stagglist,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        man_staggview.setLayoutManager(linearLayoutManager);
//        man_staggview.setAdapter(recyclerViewAdpater);


        bannerlist.clear();
        man_banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });




        man_tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                String tabText = tab.getText().toString();
                for (int i = 0; i <category.size() ; i++) {
                    if(category.get(i).getCategory_name().equals(tabText)){
                        String category_id = category.get(i).getCategory_id();
                        category_positionid=category_id;
                        count=1;
                        mPresenter.getdataCate(category_id,count+"");
                    }
                }
                categoryRecyclerViewAdpater.notifyDataSetChanged();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        categoryRecyclerViewAdpater = new CategoryRecyclerViewAdpater(Categorylist, getActivity()) {
            @Override
            public void OnClickListener(baseview holder, int position) {

            }
        };
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        man_recyclerview.setLayoutManager(staggeredGridLayoutManager);
        man_recyclerview.setAdapter(categoryRecyclerViewAdpater);
//        man_recyclerview.setHasFixedSize(false);
//        man_recyclerview.setNestedScrollingEnabled(false);
        man_recyclerview.setPullRefreshEnabled(false);
        man_recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                onRefreshs();
                man_recyclerview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                onLoadMores();
                man_recyclerview.loadMoreComplete();
            }
        });

//        man_scrollview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                Log.e("mmm",scrollY+"");
//
////                if(scrollY>=5310){
////////                    ObjectAnimator alpha = ObjectAnimator.ofFloat(FragmentHome.heard_tablayout, "alpha", 1f, 0f);
////////                    alpha.setDuration(500);
////////                    alpha.start();
//////
////                    FragmentHome.heard_tablayout.setVisibility(View.GONE);
////                }else{
////                    FragmentHome.heard_tablayout.setVisibility(View.VISIBLE);
//////                    ObjectAnimator alpha = ObjectAnimator.ofFloat(FragmentHome.heard_tablayout, "alpha", 0f, 1f);
//////                    alpha.setDuration(500);
//////                    alpha.start();
//////
////                }
//                if(scrollY==5332){
//
//                    man_recyclerview.setHasFixedSize(true);
//                    man_recyclerview.setNestedScrollingEnabled(true);
//
//
//
//                }else{
//
//
//                    man_recyclerview.setHasFixedSize(false);
//                    man_recyclerview.setNestedScrollingEnabled(false);
//
//
//
//
////                    man_scrollview.setFocusable(true);
////                    man_scrollview.setFocusableInTouchMode(true);
//                }
//
//                Log.e("qqq",FragmentHome.heard_tablayout.getY()+"");
//
//            }
//        });

        return inflate;
    }
    public void onLoadMores(){

        count++;
        if(category_positionid!=null){
            mPresenter.loadMoreCate(category_positionid,count+"");
        }
        categoryRecyclerViewAdpater.notifyDataSetChanged();
    }
    public void onRefreshs(){
        Categorylist.clear();
        count=1;
        if(category_positionid!=null){
            mPresenter.getdataCate(category_positionid,count+"");
        }
        categoryRecyclerViewAdpater.notifyDataSetChanged();
    }
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.getdata();
        mPresenter.getmenudata();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
