package com.example.myapplication.mvp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.LoginAfterBean;
import com.example.myapplication.mvp.view.adpater.LoginAfterRecyclerViewAdpater;

import java.util.ArrayList;

public class FragmentZuixin extends Fragment {


    LoginAfterRecyclerViewAdpater loginAfterRecyclerViewAdpater;
    private RecyclerView zuixin_recyclerview;
    ArrayList<LoginAfterBean> loginafterlist = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_shequ_zuixin, null);
        zuixin_recyclerview= inflate.findViewById(R.id.zuixin_recyclerview);

        loginafterlist.clear();
        for (int i = 0; i < 2; i++) {
            loginafterlist.add(new LoginAfterBean(R.mipmap.tu1,"你看这个碗"));
            loginafterlist.add(new LoginAfterBean(R.mipmap.tu2,"我想飞尚庭玩"));
            loginafterlist.add(new LoginAfterBean(R.mipmap.tu3,"你说会会会会失去"));
            loginafterlist.add(new LoginAfterBean(R.mipmap.tu4,"没有什么问题"));
            loginafterlist.add(new LoginAfterBean(R.mipmap.tu5,"我们会申请拥抱"));
            loginafterlist.add(new LoginAfterBean(R.mipmap.tu6,"我们会一直到老"));
            loginafterlist.add(new LoginAfterBean(R.mipmap.tu7,"只要能够爱着你就好"));
            loginafterlist.add(new LoginAfterBean(R.mipmap.tu8,"如果这就是爱情"));
            loginafterlist.add(new LoginAfterBean(R.mipmap.tu9,"我都知道"));
        }




        loginAfterRecyclerViewAdpater = new LoginAfterRecyclerViewAdpater(loginafterlist, getActivity());
        zuixin_recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        zuixin_recyclerview.setAdapter(loginAfterRecyclerViewAdpater);
        return inflate;
    }
}
