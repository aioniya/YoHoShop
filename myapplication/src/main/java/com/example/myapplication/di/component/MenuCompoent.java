package com.example.myapplication.di.component;

import com.example.myapplication.di.module.MenuMoudle;
import com.example.myapplication.mvp.view.activity.MainActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import dagger.Component;


@ActivityScope
@Component(modules = MenuMoudle.class,dependencies = AppComponent.class)
public interface MenuCompoent {

}
