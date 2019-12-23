package com.example.myapplication.mvp.contact;


import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

public interface LreContact {

    interface LreView extends IView{
        void success(BaseEntity baseEntity,int type);
        void error(String error);

        void refreshSuccess(BaseEntity entity);
        void loadSuccess(BaseEntity entity);
    }

    interface  LreModel extends IModel{
        //请求数据
        Observable<BaseEntity> lreRequest(String params,int type);
        Observable<BaseEntity> lreLoadTu(List<MultipartBody.Part> list,int type);

        //下拉刷新
        Observable<BaseEntity> lrerefresh(String params,int type);

        //上拉加载
        Observable<BaseEntity> lreload(String params,int type);


    }

}

