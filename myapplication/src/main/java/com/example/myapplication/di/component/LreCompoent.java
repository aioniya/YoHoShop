package com.example.myapplication.di.component;

import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.view.activity.AddAddressActivity;
import com.example.myapplication.mvp.view.activity.AddressActivity;
import com.example.myapplication.mvp.view.activity.FootActivity;
import com.example.myapplication.mvp.view.activity.GeRenActivity;
import com.example.myapplication.mvp.view.activity.GoodsActivity;
import com.example.myapplication.mvp.view.activity.MainActivity;
import com.example.myapplication.mvp.view.activity.OrderActivity;
import com.example.myapplication.mvp.view.activity.RegisterActivity;
import com.example.myapplication.mvp.view.activity.ShopCarActivity;
import com.example.myapplication.mvp.view.activity.WoDeActivity;
import com.example.myapplication.mvp.view.activity.XiuGaiNameActivity;
import com.example.myapplication.mvp.view.activity.YouHuiActivity;
import com.example.myapplication.mvp.view.fragment.FragmentGift;
import com.example.myapplication.mvp.view.fragment.FragmentMine;
import com.example.myapplication.mvp.view.fragment.FragmentTuijian;
import com.example.myapplication.mvp.view.fragment.FragmentType;
import com.example.myapplication.mvp.view.fragment.FragmentUfo;
import com.example.myapplication.mvp.view.fragment.TypeFragment_PinLei;
import com.example.myapplication.mvp.view.fragment.TypeFragment_PinPai;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;


@ActivityScope
@Component(modules = LreMoudle.class,dependencies = AppComponent.class)
public interface LreCompoent {
    void inject(TypeFragment_PinLei typeFragment_pinLei);
    void inject(TypeFragment_PinPai typeFragment_pinPai);
    void inject(FragmentUfo fragmentUfo);
    void inject(FragmentGift fragmentGift);
    void inject(FragmentTuijian tuijian);
    void inject(WoDeActivity woDeActivity);
    void inject(RegisterActivity registerActivity);
    void inject(GoodsActivity goodsActivity);
    void inject(ShopCarActivity shopCarActivity);
    void inject(MainActivity mainActivity);
    void inject(GeRenActivity geRenActivity);
    void inject(FragmentMine fragmentMine);
    void inject(AddAddressActivity addAddressActivity);
    void inject(AddressActivity addressActivity);
    void inject(XiuGaiNameActivity xiuGaiNameActivity);
    void inject(FootActivity footActivity);
    void inject(YouHuiActivity youHuiActivity);
}
