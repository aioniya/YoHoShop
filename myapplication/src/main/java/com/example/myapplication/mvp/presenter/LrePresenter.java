package com.example.myapplication.mvp.presenter;



import android.util.Log;

import com.example.myapplication.mvp.contact.LreContact;
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
import com.jess.arms.mvp.BasePresenter;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import retrofit2.Retrofit;

@ActivityScope
public class LrePresenter extends BasePresenter<LreContact.LreModel,LreContact.LreView> {
    @Inject
    public LrePresenter(LreContact.LreModel model, LreContact.LreView rootView) {
        super(model, rootView);
    }

    public void lreAll(String params,int type){

        mModel.lreRequest(params,type)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<BaseEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseEntity baseEntity) {

                if(baseEntity instanceof CategoryGoodsEntity){
                    mRootView.success(baseEntity, ApiContact.CATEGORY_GOODS);
                }else if(baseEntity instanceof BrandListEntity){
                    mRootView.success(baseEntity, ApiContact.BRAND_LIST);
                }else if(baseEntity instanceof ShoesEntity){
                    mRootView.success(baseEntity, ApiContact.SHOES_LIST);
                }else if(baseEntity instanceof GifiEntity){
                    mRootView.success(baseEntity, ApiContact.Gifi_LIST);
                }else if(baseEntity instanceof LoginEntity){
                    mRootView.success(baseEntity, ApiContact.LOGINUSER);
                }else if(baseEntity instanceof RegisterEntity){
                    mRootView.success(baseEntity, ApiContact.REGISTER);
                }else if(baseEntity instanceof GoodsEntity){
                    mRootView.success(baseEntity, ApiContact.CAR_LIST);
                }else if(baseEntity instanceof ShopCarEntity){
                    mRootView.success(baseEntity, ApiContact.SHOPCAR_LIST);
                }else if(baseEntity instanceof UserEntity){
                    mRootView.success(baseEntity, ApiContact.USER_LIST);
                }else if(baseEntity instanceof AddressEntity){
                    mRootView.success(baseEntity, ApiContact.ADDRESSMANAGER);
                }else if(baseEntity instanceof AddAdressEntity){
                    mRootView.success(baseEntity, ApiContact.ADD_ADDRESS);
                }else if(baseEntity instanceof DeteteEntity){
                    mRootView.success(baseEntity, ApiContact.DELETE_ADDRESS);
                }else if(baseEntity instanceof UpdateUserEntity){
                    mRootView.success(baseEntity, ApiContact.UPDATE_USER);
                }else if(baseEntity instanceof FootEntity){
                    mRootView.success(baseEntity, ApiContact.FOOT_LIST);
                }else if(baseEntity instanceof YouHuiEntity){
                    mRootView.success(baseEntity, ApiContact.YOUHUI_LIST);
                }


            }

            @Override
            public void onError(Throwable e) {

                mRootView.error(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void LoadTu(List<MultipartBody.Part> list,int type){

        mModel.lreLoadTu(list,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity baseEntity) {
                        Log.d("TouXiangEntity","TouXiangEntity");
                        if(baseEntity instanceof TouXiangEntity){
                            mRootView.success(baseEntity, ApiContact.TOUXIANG_LIST);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        mRootView.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
