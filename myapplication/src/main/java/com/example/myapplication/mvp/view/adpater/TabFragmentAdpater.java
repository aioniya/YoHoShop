package com.example.myapplication.mvp.view.adpater;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class TabFragmentAdpater extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentlist;
    ArrayList<String> titlelist;

    String[] strings;
    public TabFragmentAdpater(@NonNull FragmentManager fm, ArrayList<Fragment> fragmentlist, ArrayList<String> titlelist) {
        super(fm);
        this.fragmentlist = fragmentlist;
        this.titlelist = titlelist;

            String[] arr = new String[titlelist.size()];
            strings = titlelist.toArray(arr);
            Log.e("string",titlelist.toString()+"");

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
        return strings[position];
    }
}
