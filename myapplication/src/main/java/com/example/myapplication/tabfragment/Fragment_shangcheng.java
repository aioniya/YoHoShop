package com.example.myapplication.tabfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.ShouCangBean;
import com.example.myapplication.mvp.model.entity.StaggBean;
import com.example.myapplication.mvp.model.entity.UfoBean;
import com.example.myapplication.mvp.view.activity.AddressActivity;
import com.example.myapplication.mvp.view.activity.FootActivity;
import com.example.myapplication.mvp.view.activity.ShopCarActivity;
import com.example.myapplication.mvp.view.activity.ShopShouCangActivity;
import com.example.myapplication.mvp.view.activity.YouHuiActivity;
import com.example.myapplication.mvp.view.adpater.YouXuanRecyclerViewAdpater;

import java.util.ArrayList;

public class Fragment_shangcheng extends Fragment {
    private RecyclerView shangcheng_recyclerview;
    private RelativeLayout shouhuodizhi;
    private RelativeLayout dingdan;
    private LinearLayout shangcheng_shoucang;
    private LinearLayout zuji;
    private LinearLayout youhuiquan;
    private TextView shoucang_count;

    ArrayList<StaggBean> list = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_tab_shangcheng, null);
        shangcheng_recyclerview = inflate.findViewById(R.id.shangcheng_recyclerview);
        shouhuodizhi = inflate.findViewById(R.id.shouhuodizhi);
        dingdan = inflate.findViewById(R.id.dingdan);
        shoucang_count = inflate.findViewById(R.id.shoucang_count);
        zuji = inflate.findViewById(R.id.zuji);
        youhuiquan = inflate.findViewById(R.id.youhuiquan);
        youhuiquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), YouHuiActivity.class);
                startActivity(intent);
            }
        });
        zuji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FootActivity.class);
                startActivity(intent);
            }
        });
        shangcheng_shoucang = inflate.findViewById(R.id.shangcheng_shoucang);
        shangcheng_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ShopCarActivity.shouCangBeans!=null){
                    Intent intent = new Intent(getActivity(), ShopShouCangActivity.class);
                    intent.putParcelableArrayListExtra("list",ShopCarActivity.shouCangBeans);
                    startActivity(intent);
                }

            }
        });
        dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShopCarActivity.class);
                intent.putExtra("dingdan","1");
                startActivity(intent);
            }
        });
        shouhuodizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AddressActivity.class);
                startActivity(intent);
            }
        });


        list.clear();
        list.add(new StaggBean(R.mipmap.micai,"[1110]迷彩连帽up夹克"));
        list.add(new StaggBean(R.mipmap.tianwenqun,"MO%天文裙子不规则"));
        list.add(new StaggBean(R.mipmap.shoutibao,"迷彩ASJF手提包"));
        list.add(new StaggBean(R.mipmap.maoyi,"mao贸易"));
        list.add(new StaggBean(R.mipmap.zhizhuxia,"手办蜘蛛侠"));
        list.add(new StaggBean(R.mipmap.yuyi,"粉色的羽翼 氧气"));
        list.add(new StaggBean(R.mipmap.huiseyifu,"灰色的衣服"));
        list.add(new StaggBean(R.mipmap.lining,"李宁的鞋子"));

        for (int i = 0; i < 20; i++) {
            list.add(new StaggBean(R.mipmap.xiuxianxie,"USDFH休闲鞋子"));
        }


        YouXuanRecyclerViewAdpater youXuanRecyclerViewAdpater = new YouXuanRecyclerViewAdpater(list,getActivity());
        shangcheng_recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        shangcheng_recyclerview.setAdapter(youXuanRecyclerViewAdpater);
        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(ShopCarActivity.shouCangBeans!=null){
            shoucang_count.setText(ShopCarActivity.shouCangBeans.size()+"");
        }
    }
}
