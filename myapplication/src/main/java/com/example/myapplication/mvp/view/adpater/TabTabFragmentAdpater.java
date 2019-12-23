package com.example.myapplication.mvp.view.adpater;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class TabTabFragmentAdpater extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentlist;
    String arr[]=new String[]{"关注","推荐","最新"};
    public TabTabFragmentAdpater(@NonNull FragmentManager fm, ArrayList<Fragment> fragmentlist) {
        super(fm);
        this.fragmentlist = fragmentlist;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return fragmentlist.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arr[position];
    }
}
