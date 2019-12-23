package com.example.myapplication.mvp.model;

import com.example.myapplication.mvp.contact.HomeContact;
import com.example.myapplication.mvp.contact.MenuContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.BannerEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.example.myapplication.mvp.model.entity.RecommendEntity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.jess.arms.mvp.IModel;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class HomeModel extends BaseModel implements HomeContact.HomeIModel, MenuContact.MenuModel {
    @Inject
    public HomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public Observable<BaseEntity> requestAll(String category, String goodlist) {

        Observable<BannerEntity> bannerList = mRepositoryManager.obtainRetrofitService(Api.class).getBannerList();
        Observable<RecommendEntity> recommend = mRepositoryManager.obtainRetrofitService(Api.class).postRecommendList(category);
        Observable<GoodsListEntity> goodeslist = mRepositoryManager.obtainRetrofitService(Api.class).postGoodList(goodlist);

        //合并
        Observable<BaseEntity> all = Observable.merge(bannerList,recommend,goodeslist);
        return all;
    }

    @Override
    public Observable<GoodsListEntity> refresh(String pramas) {

        return mRepositoryManager.obtainRetrofitService(Api.class).postGoodList(pramas);
    }

    @Override
    public Observable<GoodsListEntity> loadmore(String pramas) {

        return mRepositoryManager.obtainRetrofitService(Api.class).postGoodList(pramas);
    }

    @Override
    public Observable<MenuEntity> requestMenu() {

        return mRepositoryManager.obtainRetrofitService(Api.class).getmenulist();
    }


//    public Observable<BaseEntity> requestAll(String category,String goodlist){
//        //需要合并rxjava
//
//        Observable<BannerEntity> bannerList = mRepositoryManager.obtainRetrofitService(Api.class).getBannerList();
//        Observable<RecommendEntity> recommend = mRepositoryManager.obtainRetrofitService(Api.class).postRecommendList(category);
//        Observable<GoodsListEntity> goodeslist = mRepositoryManager.obtainRetrofitService(Api.class).postGoodList(goodlist);
//
//        //合并
//        Observable<BaseEntity> all = Observable.merge(bannerList,recommend,goodeslist);
//        return all;
//    }
//
//    public Observable<GoodsListEntity> pullrefresh(String pramas){
//
//        return mRepositoryManager.obtainRetrofitService(Api.class).postGoodList(pramas);
//    }
//    public Observable<GoodsListEntity> loadmore(String pramas){
//
//        return mRepositoryManager.obtainRetrofitService(Api.class).postGoodList(pramas);
//    }
}
