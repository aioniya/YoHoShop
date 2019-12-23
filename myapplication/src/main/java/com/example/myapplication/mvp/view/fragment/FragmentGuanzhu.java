package com.example.myapplication.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.GuanZhuBean;
import com.example.myapplication.mvp.model.entity.LoginAfterBean;
import com.example.myapplication.mvp.view.activity.MainActivity;
import com.example.myapplication.mvp.view.activity.WoDeActivity;
import com.example.myapplication.mvp.view.adpater.GuanZhuRecyclerViewAdpater;
import com.example.myapplication.mvp.view.adpater.LoginAfterRecyclerViewAdpater;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

public class FragmentGuanzhu extends Fragment {


    public static Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if(msg.what== 9000){
                boolean loginflag = MainActivity.sp.getBoolean("loginflag", false);
                if(loginflag==true){


                    Log.e("login","denglu");
                    guanzhu_xrecyclerview.setVisibility(View.VISIBLE);
                    guanzhu_centers.setVisibility(View.GONE);
                }else{
                    Log.e("login","tuichu");
                    guanzhu_xrecyclerview.setVisibility(View.GONE);
                    guanzhu_centers.setVisibility(View.VISIBLE);
                }
            }
        }
    };


    public static RecyclerView guanzhu_xrecyclerview;
    public static RecyclerView recyclerview;
    public static Button denglu;


    ArrayList<GuanZhuBean> list = new ArrayList<>();
    ArrayList<LoginAfterBean> loginafterlist = new ArrayList<>();
    public static RelativeLayout guanzhu_centers;

    LoginAfterRecyclerViewAdpater loginAfterRecyclerViewAdpater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_shequ_guanzhu, null);
        guanzhu_xrecyclerview = inflate.findViewById(R.id.guanzhu_xrecyclerview);
        recyclerview = inflate.findViewById(R.id.recyclerview);
        denglu = inflate.findViewById(R.id.denglu);
        guanzhu_centers = inflate.findViewById(R.id.guanzhu_centers);


        for (int i = 0; i < 10; i++) {
            list.add(new GuanZhuBean("https://pic.qqtn.com/up/2017-12/15138348382346479.jpg", "隔壁小黄", "关注"));
        }


        for (int i = 0; i < 5; i++) {
            loginafterlist.add(new LoginAfterBean(R.mipmap.yifu, "TU着黑指甲，窜这大衣"));
            loginafterlist.add(new LoginAfterBean(R.mipmap.tu1,"你看这个碗"));
            loginafterlist.add(new LoginAfterBean(R.mipmap.tu2,"我想飞尚庭玩"));
            loginafterlist.add(new LoginAfterBean(R.mipmap.tu3,"你说会会会会失去"));
            loginafterlist.add(new LoginAfterBean(R.mipmap.tu4,"没有什么问题"));
            loginafterlist.add(new LoginAfterBean(R.mipmap.tu5,"我们会申请拥抱"));
        }


        GuanZhuRecyclerViewAdpater guanZhuRecyclerViewAdpater = new GuanZhuRecyclerViewAdpater(list, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(guanZhuRecyclerViewAdpater);


        loginAfterRecyclerViewAdpater = new LoginAfterRecyclerViewAdpater(loginafterlist, getActivity());
        guanzhu_xrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        guanzhu_xrecyclerview.setAdapter(loginAfterRecyclerViewAdpater);


        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), WoDeActivity.class);
                startActivity(intent);


            }
        });


        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume","onResume");
        boolean loginflag = MainActivity.sp.getBoolean("loginflag", false);
        if(loginflag==true){



            guanzhu_xrecyclerview.setVisibility(View.VISIBLE);
            guanzhu_centers.setVisibility(View.GONE);
        }else{


            guanzhu_xrecyclerview.setVisibility(View.GONE);
            guanzhu_centers.setVisibility(View.VISIBLE);
        }
    }
}
