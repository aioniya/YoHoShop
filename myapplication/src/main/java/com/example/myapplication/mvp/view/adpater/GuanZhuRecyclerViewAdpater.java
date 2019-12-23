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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.GuanZhuBean;
import com.example.myapplication.mvp.model.entity.StaggBean;

import java.util.ArrayList;

public class GuanZhuRecyclerViewAdpater extends RecyclerView.Adapter<GuanZhuRecyclerViewAdpater.baseview> {

    ArrayList<GuanZhuBean> list;
    Context context;

    public GuanZhuRecyclerViewAdpater(ArrayList<GuanZhuBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_h_recyclerview, parent, false);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.guanzhu.setText("   "+list.get(position).getGuanzhu()+"   ");
        String pic = list.get(position).getPic();
        Log.e("pic",pic);

        Glide.with(context).load(pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.imageView);


    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class baseview extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name;
        TextView guanzhu;

        public baseview(@NonNull View itemView) {
            super(itemView);
            this.imageView= itemView.findViewById(R.id.guanzhu_images);
            this.name= itemView.findViewById(R.id.texts);
            this.guanzhu= itemView.findViewById(R.id.guanzhu);
        }
    }
}
