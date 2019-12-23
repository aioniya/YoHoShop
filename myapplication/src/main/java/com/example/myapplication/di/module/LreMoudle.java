package com.example.myapplication.di.module;

import com.example.myapplication.mvp.contact.HomeContact;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.LreModel;
import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;


@Module
public class LreMoudle {
    private LreContact.LreView lreView;
    @Inject
    public LreMoudle(LreContact.LreView lreView) {
        this.lreView = lreView;
    }

    @ActivityScope
    @Provides
    public LreContact.LreView providerView(){
        return lreView;
    }
    @ActivityScope
    @Provides
    public LreContact.LreModel providerModel(LreModel lreModel){
        return lreModel;
    }

}
