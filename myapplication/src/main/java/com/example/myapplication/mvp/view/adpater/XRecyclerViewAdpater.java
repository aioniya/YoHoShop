package com.example.myapplication.mvp.view.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.mvp.view.fragment.FragmentHome;

import java.util.ArrayList;

public class XRecyclerViewAdpater extends RecyclerView.Adapter<XRecyclerViewAdpater.baseview> {

    ArrayList<String> list;
    Context context;

    public XRecyclerViewAdpater(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layoutlayoutlayoutlayoutlayout, parent, false);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {

            holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class baseview extends RecyclerView.ViewHolder{

        TextView textView;

        public baseview(@NonNull View itemView) {
            super(itemView);
            this.textView= itemView.findViewById(R.id.aaa);
        }
    }
}
