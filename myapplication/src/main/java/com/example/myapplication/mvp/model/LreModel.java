package com.example.myapplication.mvp.model;

import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.AddAdressEntity;
import com.example.myapplication.mvp.model.entity.AddressEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.CategoryGoodsEntity;
import com.example.myapplication.mvp.model.entity.DeteteEntity;
import com.example.myapplication.mvp.model.entity.FootEntity;
import com.example.myapplication.mvp.model.entity.GifiEntity;
import com.example.myapplication.mvp.model.entity.GoodsEntity;
import com.example.myapplication.mvp.model.entity.LoginEntity;
import com.example.myapplication.mvp.model.entity.RegisterEntity;
import com.example.myapplication.mvp.model.entity.ShoesEntity;
import com.example.myapplication.mvp.model.entity.ShopCarEntity;
import com.example.myapplication.mvp.model.entity.TouXiangEntity;
import com.example.myapplication.mvp.model.entity.UpdateUserEntity;
import com.example.myapplication.mvp.model.entity.UserEntity;
import com.example.myapplication.mvp.model.entity.YouHuiEntity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.Functions;
import okhttp3.MultipartBody;

@ActivityScope
public class LreModel extends BaseModel implements LreContact.LreModel {
    @Inject
    public LreModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity> lreRequest(String params, int type) {
        Observable<BaseEntity> ob = null;
        switch (type){
            case ApiContact.CATEGORY_GOODS:
                Observable<CategoryGoodsEntity> categoryGoodsEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryGoodsList(params);
                ob = Observable.fromArray(categoryGoodsEntityObservable).flatMap((Function) Functions.identity(),false,1);
                break;

            case ApiContact.BRAND_LIST:
                Observable<BrandListEntity> brandListEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postBrandList(params);
                ob = Observable.fromArray(brandListEntityObservable).flatMap((Function) Functions.identity(),false,1);

                break;

            case ApiContact.SHOES_LIST:
                Observable<ShoesEntity> shoesEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postShoesList(params);
                ob = Observable.fromArray(shoesEntityObservable).flatMap((Function) Functions.identity(),false,1);

                break;

            case ApiContact.Gifi_LIST:
                Observable<GifiEntity> gifiEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postGifiList(params);
                ob = Observable.fromArray(gifiEntityObservable).flatMap((Function) Functions.identity(),false,1);

                break;
            case ApiContact.LOGINUSER:
                Observable<LoginEntity> loginEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postLoginList(params);
                ob = Observable.fromArray(loginEntityObservable).flatMap((Function) Functions.identity(),false,1);

                break;

            case ApiContact.REGISTER:
                Observable<RegisterEntity> registerEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postRegisterList(params);
                ob = Observable.fromArray(registerEntityObservable).flatMap((Function) Functions.identity(),false,1);

                break;

            case ApiContact.CAR_LIST:
                Observable<GoodsEntity> goodsEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postCarList(params);
                ob = Observable.fromArray(goodsEntityObservable).flatMap((Function) Functions.identity(),false,1);

                break;

            case ApiContact.SHOPCAR_LIST:
                Observable<ShopCarEntity> shopCarEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postShopCarList(params);
                ob = Observable.fromArray(shopCarEntityObservable).flatMap((Function) Functions.identity(),false,1);

                break;

            case ApiContact.USER_LIST:
                Observable<UserEntity> userEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postUserList(params);
                ob = Observable.fromArray(userEntityObservable).flatMap((Function) Functions.identity(),false,1);

                break;




            case ApiContact.DELETE_ADDRESS:
                Observable<DeteteEntity>baseEntity=mRepositoryManager.obtainRetrofitService(Api.class).postDeleteCarData(params);
                ob = Observable.fromArray(baseEntity).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiContact.ADDRESSMANAGER:
                Observable<AddressEntity>addressEntity=mRepositoryManager.obtainRetrofitService(Api.class).postAddressData(params);
                ob = Observable.fromArray(addressEntity).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiContact.ADD_ADDRESS:
                Observable<AddAdressEntity>baseEntity2=mRepositoryManager.obtainRetrofitService(Api.class).postAddData(params);
                ob = Observable.fromArray(baseEntity2).flatMap((Function) Functions.identity(), false, 1);
                break;


            case ApiContact.UPDATE_USER:
                Observable<UpdateUserEntity> updateUserEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postUpdate(params);
                ob = Observable.fromArray(updateUserEntityObservable).flatMap((Function) Functions.identity(), false, 1);
                break;

            case ApiContact.FOOT_LIST:
                Observable<FootEntity> footEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postFootList(params);
                ob = Observable.fromArray(footEntityObservable).flatMap((Function) Functions.identity(), false, 1);
                break;

            case ApiContact.YOUHUI_LIST:
                Observable<YouHuiEntity> youHuiEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postYouHuiList(params);
                ob = Observable.fromArray(youHuiEntityObservable).flatMap((Function) Functions.identity(), false, 1);
                break;
        }


        return ob;
    }

    @Override
    public Observable<BaseEntity> lreLoadTu(List<MultipartBody.Part> list, int type) {
        Observable<BaseEntity> ob = null;
        if(type == ApiContact.TOUXIANG_LIST){
            Observable<TouXiangEntity> touXiangEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postHeadImg(list);
            ob = Observable.fromArray(touXiangEntityObservable).flatMap((Function) Functions.identity(),false,1);

        }

        return ob;
    }


    @Override
    public Observable<BaseEntity> lrerefresh(String params, int type) {
        Observable<BaseEntity> ob = null;
        switch (type){
            case ApiContact.CATEGORY_GOODS:
                Observable<CategoryGoodsEntity> categoryGoodsEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryGoodsList(params);
                ob = Observable.fromArray(categoryGoodsEntityObservable).flatMap((Function) Functions.identity(),false,1);
                break;

            case ApiContact.BRAND_LIST:
                Observable<BrandListEntity> brandListEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postBrandList(params);
                ob = Observable.fromArray(brandListEntityObservable).flatMap((Function) Functions.identity(),false,1);

                break;
        }


        return ob;
    }

    @Override
    public Observable<BaseEntity> lreload(String params, int type) {
        Observable<BaseEntity> ob = null;
        switch (type){
            case ApiContact.CATEGORY_GOODS:
                Observable<CategoryGoodsEntity> categoryGoodsEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryGoodsList(params);
                ob = Observable.fromArray(categoryGoodsEntityObservable).flatMap((Function) Functions.identity(),false,1);
                break;

            case ApiContact.BRAND_LIST:
                Observable<BrandListEntity> brandListEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postBrandList(params);
                ob = Observable.fromArray(brandListEntityObservable).flatMap((Function) Functions.identity(),false,1);

                break;
        }


        return ob;
    }
}
