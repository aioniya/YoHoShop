package com.example.myapplication.mvp.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreCompoent;
import com.example.myapplication.di.module.HomeMoudle;
import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.CategoryGoodsEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.view.activity.SouSuoActivity;
import com.example.myapplication.mvp.view.adpater.TypeTabFragmentAdpater;
import com.example.myapplication.ui.MyViewPager;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.TabView;


public class FragmentType extends BaseFragment<LrePresenter> implements LreContact.LreView {

    ArrayList<Fragment> list = new ArrayList<>();
    TypeFragment_PinLei typeFragment_pinLei = new TypeFragment_PinLei();
    TypeFragment_PinPai typeFragment_pinPai = new TypeFragment_PinPai();


    @Override
    public void success(BaseEntity baseEntity, int type) {


    }

    @Override
    public void error(String error) {


    }

    @Override
    public void refreshSuccess(BaseEntity entity) {

    }

    @Override
    public void loadSuccess(BaseEntity entity) {

    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
//        DaggerLreCompoent.builder().appComponent(appComponent).lreMoudle(new LreMoudle(this)).build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_fragment_type, null);
        ViewPager type_viewpager = inflate.findViewById(R.id.type_viewpager);
        TabLayout type_tablayout = inflate.findViewById(R.id.type_tablayout);
        ImageView type_sou = inflate.findViewById(R.id.type_sou);
        list.clear();
        list.add(typeFragment_pinLei);
        list.add(typeFragment_pinPai);

        TypeTabFragmentAdpater typeTabFragmentAdpater = new TypeTabFragmentAdpater(getChildFragmentManager(),list);
        type_viewpager.setAdapter(typeTabFragmentAdpater);
        type_tablayout.setupWithViewPager(type_viewpager);

        type_sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SouSuoActivity.class);
                startActivity(intent);
            }
        });



        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
