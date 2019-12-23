package com.example.myapplication.mvp.contact;

import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;

public interface HomeContact {
    interface HomeIView extends IView{
        //页面访问成功
        void onSuccess();
        //接口请求返回
        void resultSuccess(BaseEntity baseEntity,int type);
        //下拉刷新
        void pullrefresh(BaseEntity baseEntity);
        //上拉加载
        void loadmore(BaseEntity baseEntity);



        void success(MenuEntity menuEntity);
    }

    interface HomeIModel extends IModel{
        //请求全部
        Observable<BaseEntity> requestAll(String category,String goodlist);
        //下拉刷新
        Observable<GoodsListEntity> refresh(String pramas);
        //上垃加载
        Observable<GoodsListEntity> loadmore(String pramas);


        Observable<MenuEntity> requestMenu();

    }


}

