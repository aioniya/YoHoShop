package com.example.myapplication.mvp.view.adpater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.GuanZhuBean;
import com.example.myapplication.mvp.model.entity.LoginAfterBean;

import java.util.ArrayList;

public class LoginAfterRecyclerViewAdpater extends RecyclerView.Adapter<LoginAfterRecyclerViewAdpater.baseview> {

    ArrayList<LoginAfterBean> list;
    Context context;


    public LoginAfterRecyclerViewAdpater(ArrayList<LoginAfterBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_guanzhu, parent, false);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {
        holder.name.setText(list.get(position).getTitle());

        Glide.with(context).load( list.get(position).getImage()).into(holder.imageView);


    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class baseview extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name;


        public baseview(@NonNull View itemView) {
            super(itemView);
            this.imageView= itemView.findViewById(R.id.guanzhu_image);
            this.name= itemView.findViewById(R.id.guanzhu_name);

        }
    }
}
