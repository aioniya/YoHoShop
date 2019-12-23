package com.example.myapplication.mvp.view.adpater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.ShopCarEntity;
import com.example.myapplication.mvp.model.entity.ShouCangBean;

import java.util.ArrayList;

public abstract class ShopShouCangRecyclerViewAdpater extends RecyclerView.Adapter<ShopShouCangRecyclerViewAdpater.baseview> {

    ArrayList<ShouCangBean> list;
    Context context;

    public ShopShouCangRecyclerViewAdpater(ArrayList<ShouCangBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_shoucang, parent, false);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {
        if(list.get(position).getName()!=null){
            holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
            holder.name.setText(list.get(position).getName());
            holder.price.setText("￥"+list.get(position).getPrice());
            Glide.with(context).load(Api.APP_DOMAIN +list.get(position).getPic()).into(holder.pic);
        }



        show(holder,position);

    }
    abstract public void show(baseview holder, int position);


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class baseview extends RecyclerView.ViewHolder{
        public  ImageView shanchu;
        public  ImageView pic;
        public TextView name;
        public TextView price;
        public ImageView xiangsi;
        public Button ce_shanchu;
        public SwipeLayout swipeLayout;


        public baseview(@NonNull View itemView) {
            super(itemView);
            this.shanchu= itemView.findViewById(R.id.shoucang_shanchu);
            this.pic= itemView.findViewById(R.id.shoucang_image);
            this.price= itemView.findViewById(R.id.shoucang_price);
            this.xiangsi= itemView.findViewById(R.id.shoucang_zhaoxiangsi);
            this.swipeLayout= itemView.findViewById(R.id.shoucang_swipelayout);
            this.ce_shanchu= itemView.findViewById(R.id.shoucang_ce_shanchu);
            this.name= itemView.findViewById(R.id.shoucang_name);


        }
    }
}
