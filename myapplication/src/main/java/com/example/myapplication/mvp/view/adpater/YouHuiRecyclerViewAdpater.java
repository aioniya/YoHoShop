package com.example.myapplication.mvp.view.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.StaggBean;
import com.example.myapplication.mvp.model.entity.YouHuiEntity;

import java.util.ArrayList;

public abstract class YouHuiRecyclerViewAdpater extends RecyclerView.Adapter<YouHuiRecyclerViewAdpater.baseview> {

    ArrayList<YouHuiEntity.ValuesBean> list;
    Context context;

    public YouHuiRecyclerViewAdpater(ArrayList<YouHuiEntity.ValuesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_youhui_item, parent, false);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {

        holder.youhui_money.setText(list.get(position).getCoupon_condition());
        holder.youhui_title.setText(list.get(position).getCoupon_title());
        holder.telishangpin.setText(list.get(position).getCoupon_explain());
        OnClick(holder,position);
    }


    abstract  public void OnClick(baseview holder, int position);

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class baseview extends RecyclerView.ViewHolder{
        public TextView youhui_money;
        public CheckBox youhui_shuoming;
        public TextView youhui_title;

        public TextView telishangpin;
        public LinearLayout linearLayout;



        public baseview(@NonNull View itemView) {
            super(itemView);
            this.youhui_money= itemView.findViewById(R.id.youhui_money);
            this.youhui_title= itemView.findViewById(R.id.youhui_title);
            this.telishangpin= itemView.findViewById(R.id.telishangpin);
            this.linearLayout= itemView.findViewById(R.id.shuoming);

            this.youhui_shuoming= itemView.findViewById(R.id.youhui_shuoming);
        }
    }
}
