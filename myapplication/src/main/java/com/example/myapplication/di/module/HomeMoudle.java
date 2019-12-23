package com.example.myapplication.di.module;

import com.example.myapplication.mvp.contact.HomeContact;
import com.example.myapplication.mvp.model.HomeModel;
import com.jess.arms.di.scope.ActivityScope;
import javax.inject.Inject;
import dagger.Module;
import dagger.Provides;

@Module
public class HomeMoudle {
    private HomeContact.HomeIView view;
    @Inject
    public HomeMoudle(HomeContact.HomeIView view) {
        this.view = view;
    }
    @ActivityScope
    @Provides
    HomeContact.HomeIView providerHomeView(){return view;}

    @ActivityScope
    @Provides
    HomeContact.HomeIModel providerHomeModel(HomeModel homeModel){

        return homeModel;
    }

}
