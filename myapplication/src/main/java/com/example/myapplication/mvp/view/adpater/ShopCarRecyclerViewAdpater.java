package com.example.myapplication.mvp.view.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.StaggBean;
import com.example.myapplication.mvp.model.entity.UfoBean;

import java.util.ArrayList;

public class ShopCarRecyclerViewAdpater extends RecyclerView.Adapter<ShopCarRecyclerViewAdpater.baseview> {

    ArrayList<UfoBean> list;
    Context context;

    public ShopCarRecyclerViewAdpater(ArrayList<UfoBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_shopcartwo, parent, false);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {
        holder.textView.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getPic()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class baseview extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        ImageView car;

        public baseview(@NonNull View itemView) {
            super(itemView);
            this.imageView= itemView.findViewById(R.id.item_image);
            this.textView= itemView.findViewById(R.id.item_text);
            this.car= itemView.findViewById(R.id.item_jiagou);
        }
    }
}
