package com.example.myapplication.mvp.view.fragment;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerHomeCompoent;
import com.example.myapplication.di.component.DaggerLreCompoent;
import com.example.myapplication.di.module.HomeMoudle;
import com.example.myapplication.di.module.LreMoudle;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.api.ApiContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.ShouCangBean;
import com.example.myapplication.mvp.model.entity.UserEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.view.activity.ErWeiMaActivity;
import com.example.myapplication.mvp.view.activity.GeRenActivity;
import com.example.myapplication.mvp.view.activity.MainActivity;
import com.example.myapplication.mvp.view.activity.SheZhiActivity;
import com.example.myapplication.mvp.view.activity.XiaoXiActivity;
import com.example.myapplication.mvp.view.adpater.RegisterTabFragmentAdpater;
import com.example.myapplication.tabfragment.Fragment_shangcheng;
import com.example.myapplication.tabfragment.Fragment_shequ;
import com.example.myapplication.ui.HomeHeadViewPager;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class FragmentMine extends BaseFragment<LrePresenter> implements LreContact.LreView {
    public static RelativeLayout relativeLayou;
    private ImageView mine_shezhi;
    private ImageView mine_erweima;
    private ImageView mine_xiaoxi;
    private ImageView mine_fenxiang;
    private ImageView mine_touxiang;
    private RelativeLayout tops;
    private RelativeLayout topss;
    private TextView chaoliujiushiwodetaidu;
    public  TextView mine_name;
    private RelativeLayout all;
    private RelativeLayout fabu;
    private TabLayout mine_tablayout;
    private HomeHeadViewPager mine_viewpager;
    private NestedScrollView mine_nestedscrollview;

    Fragment_shequ fragment_shequ = new Fragment_shequ();
    Fragment_shangcheng fragment_shangcheng = new Fragment_shangcheng();
    ArrayList<Fragment> fragmentlist = new ArrayList<>();



    String url = null;

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("youhuo");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，确保SDcard下面存在此张图片
        oks.setImagePath("/sdcard/test.jpg");
        // url在微信、Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(getActivity());
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void show(String s){
        Log.e("0055",s);
        if(s!=null){
            mine_name.setText(s+"");

            MainActivity.spedit.putString("loginname",s).commit();
        }

    }


    @Override
    public void onResume() {
        super.onResume();

//        String loginname = MainActivity.sp.getString("loginname", null);
//
//        if(loginname!=null){
//            mine_name.setText(loginname);
//        }



        if(url !=null){
            Glide.with(getActivity()).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mine_touxiang);

        }
        JSONObject job = new JSONObject();

        try {
            job.put("userid", "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(job.toString(), ApiContact.USER_LIST);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void success(BaseEntity baseEntity, int type) {

        if(type == ApiContact.USER_LIST){
            UserEntity userEntity = (UserEntity) baseEntity;
            List<UserEntity.ValuesBean> values = userEntity.getValues();
            for (int i = 0; i <values.size() ; i++) {
                String user_head = values.get(i).getUser_head();
                url= Api.APP_DOMAIN+user_head;
                Glide.with(getActivity()).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mine_touxiang);


                String nick_name = values.get(i).getNick_name();
                mine_name.setText(nick_name);
            }

        }

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
        DaggerLreCompoent.builder().appComponent(appComponent).lreMoudle(new LreMoudle(this)).build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment_mine, null);
//        if(EventBus.getDefault().isRegistered(this)){
//            EventBus.getDefault().register(this);
//        }



        fragmentlist.clear();
        fragmentlist.add(fragment_shequ);
        fragmentlist.add(fragment_shangcheng);


        relativeLayou = inflate.findViewById(R.id.all);
        mine_shezhi = inflate.findViewById(R.id.mine_shezhi);
        mine_erweima = inflate.findViewById(R.id.mine_erweima);
        mine_xiaoxi = inflate.findViewById(R.id.mine_xiaoxi);
        mine_fenxiang = inflate.findViewById(R.id.mine_fenxiang);
        tops = inflate.findViewById(R.id.tops);
        topss = inflate.findViewById(R.id.topss);
        chaoliujiushiwodetaidu = inflate.findViewById(R.id.chaoliujiushiwodetaidu);
        mine_tablayout = inflate.findViewById(R.id.mine_tablayout);
        mine_viewpager = inflate.findViewById(R.id.mine_viewpager);
        mine_name = inflate.findViewById(R.id.mine_name);
        mine_nestedscrollview = inflate.findViewById(R.id.mine_nestedscrollview);
        fabu = inflate.findViewById(R.id.fabu);
        all = inflate.findViewById(R.id.all);
        mine_touxiang = inflate.findViewById(R.id.mine_touxiang);

        ObjectAnimator translationX = ObjectAnimator.ofFloat(tops, "alpha", 0f, 0f);
        translationX.setDuration(0);
        translationX.start();
        mine_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GeRenActivity.class);
                startActivity(intent);
            }
        });
        mine_nestedscrollview.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("Range")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int scrollY = mine_nestedscrollview.getScrollY();
                Log.e("scrollY",scrollY+"");
//                float alpha = tops.getAlpha();
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        if(scrollY==0){
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(tops, "alpha", 0f, 0f);
                            translationX.setDuration(0);
                            translationX.start();
                        }
                        if(scrollY>=100){
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(tops, "alpha", 0f, 0.2f);
                            translationX.setDuration(0);
                            translationX.start();
                        }
                        if(scrollY>=200){
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(tops, "alpha", 0f, 0.4f);
                            translationX.setDuration(0);
                            translationX.start();
                        }
                        if(scrollY>=300){
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(tops, "alpha", 0f, 0.6f);
                            translationX.setDuration(0);
                            translationX.start();
                        }
                        if(scrollY>=400){
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(tops, "alpha", 0f, 0.8f);
                            translationX.setDuration(0);
                            translationX.start();
                        }
                        if(scrollY>=500){
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(tops, "alpha", 0f, 1f);
                            translationX.setDuration(0);
                            translationX.start();
                        }


//                        if(scrollY>=100){
//

//                        }
                        ObjectAnimator translationX = ObjectAnimator.ofFloat(fabu, "translationX", 0f, 100f);
                        translationX.setDuration(0);
                        translationX.start();

                        break;

                    case MotionEvent.ACTION_UP:




                        ObjectAnimator translationXX = ObjectAnimator.ofFloat(fabu, "translationX", 100f, 0f);
                        translationXX.setDuration(0);
                        translationXX.start();


                        break;
                }


                return false;
            }
        });



        RegisterTabFragmentAdpater registerTabFragmentAdpater = new RegisterTabFragmentAdpater(getChildFragmentManager(),fragmentlist);
        mine_viewpager.setAdapter(registerTabFragmentAdpater);
        mine_viewpager.setFocusable(true);
        mine_tablayout.setupWithViewPager(mine_viewpager);


        mine_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SheZhiActivity.class);
                startActivity(intent);
            }
        });
        mine_erweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ErWeiMaActivity.class);
                startActivity(intent);

            }
        });
        mine_xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), XiaoXiActivity.class);
                startActivity(intent);


            }
        });
        mine_fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });





        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        JSONObject job = new JSONObject();

        try {
            job.put("userid", "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(job.toString(), ApiContact.USER_LIST);


    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
