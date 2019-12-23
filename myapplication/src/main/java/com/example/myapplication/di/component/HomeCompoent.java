package com.example.myapplication.di.component;

import com.example.myapplication.di.module.HomeMoudle;
import com.example.myapplication.mvp.view.fragment.FragmentHome;
import com.example.myapplication.mvp.view.fragment.PagerFragmentLife;
import com.example.myapplication.mvp.view.fragment.PagerFragmentMan;
import com.example.myapplication.mvp.view.fragment.PagerFragmentShoe;
import com.example.myapplication.mvp.view.fragment.PagerFragmentWoman;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import dagger.Component;
@ActivityScope
@Component(modules = HomeMoudle.class,dependencies = AppComponent.class)
public interface HomeCompoent {
    void inject(FragmentHome fragmentHome);
    void inject(PagerFragmentMan pagerFragmentMan);
    void inject(PagerFragmentWoman pagerFragmentWoman);
    void inject(PagerFragmentLife pagerFragmentLife);
    void inject(PagerFragmentShoe pagerFragmentShoe);
}
