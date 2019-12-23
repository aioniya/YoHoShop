package com.example.myapplication.mvp.presenter;

import android.util.Log;

import com.example.myapplication.mvp.contact.HomeContact;
import com.example.myapplication.mvp.model.entity.BannerEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.example.myapplication.mvp.model.entity.RecommendEntity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class HomePresenter extends BasePresenter<HomeContact.HomeIModel,HomeContact.HomeIView> {

    @Inject
    public HomePresenter(HomeContact.HomeIModel model, HomeContact.HomeIView rootView) {
        super(model, rootView);
    }
    private int page=1;
    private String category="1";
    private String menu="1";
    //网络访问
    public void getmenudata(){
        mModel.requestMenu().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MenuEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MenuEntity menuEntity) {
                mRootView.success(menuEntity);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void getdataCate(String category,String page) {

        String categorySrc="{\"menu\":\""+menu+"\"}";
//        String categorySrc="{\"menu\":"+menu+"}";


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("category",category);
            jsonObject.put("page",page+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String goodslistSrc = jsonObject.toString();
        mModel.requestAll(categorySrc,goodslistSrc).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseEntity baseEntity) {
                if(baseEntity!=null){
                   if(baseEntity instanceof GoodsListEntity){
                        mRootView.resultSuccess(baseEntity,3);
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e("ltzE","错误");
            }

            @Override
            public void onComplete() {
                mRootView.showLoading();
            }
        });
    }





    public void loadMoreCate(String category,String page) {

        String categorySrc="{\"menu\":\""+menu+"\"}";
//        String categorySrc="{\"menu\":"+menu+"}";


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("category",category);
            jsonObject.put("page",page+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String goodslistSrc = jsonObject.toString();
        mModel.requestAll(categorySrc,goodslistSrc).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseEntity baseEntity) {
                if(baseEntity!=null){
                    if(baseEntity instanceof GoodsListEntity){
                        mRootView.resultSuccess(baseEntity,4);
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e("ltzE","错误");
            }

            @Override
            public void onComplete() {
                mRootView.showLoading();
            }
        });
    }












    public void getdata() {

        String categorySrc="{\"menu\":\""+menu+"\"}";
//        String categorySrc="{\"menu\":"+menu+"}";


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("category",category);
            jsonObject.put("page",page+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String goodslistSrc = jsonObject.toString();
        mModel.requestAll(categorySrc,goodslistSrc).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseEntity baseEntity) {
                if(baseEntity!=null){
                    if(baseEntity instanceof BannerEntity){
                            mRootView.resultSuccess(baseEntity,1);
                        Log.e("ltz","1");

                    }else if(baseEntity instanceof RecommendEntity){
                            mRootView.resultSuccess(baseEntity,2);
                        Log.e("ltz","2");

                    }else if(baseEntity instanceof GoodsListEntity){
                            mRootView.resultSuccess(baseEntity,3);
                        Log.e("ltz","3");

                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                    Log.e("ltzE","错误");
            }

            @Override
            public void onComplete() {
            mRootView.showLoading();
            }
        });
    }

        public void refresh(){
            page = 1;

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("category",category);
                jsonObject.put("page",page+"");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String goodslistSrc = jsonObject.toString();
            mModel.refresh(goodslistSrc).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<GoodsListEntity>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(GoodsListEntity goodsListEntity) {
                    if(goodsListEntity==null||goodsListEntity.getValues()==null){
                        mRootView.showMessage("没有数据");
                    }else{
                        mRootView.pullrefresh(goodsListEntity);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }


        public void loadmore(){
            page = page + 1;

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("categgory",category);
                jsonObject.put("page",page);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String goodslistSrc = jsonObject.toString();
            mModel.refresh(goodslistSrc).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<GoodsListEntity>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(GoodsListEntity goodsListEntity) {
                    if(goodsListEntity==null||goodsListEntity.getValues()==null){
                        mRootView.showMessage("没有数据");
                    }else{
                        mRootView.loadmore(goodsListEntity);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }

}
