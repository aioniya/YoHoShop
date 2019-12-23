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

public class YouXuanRecyclerViewAdpater extends RecyclerView.Adapter<YouXuanRecyclerViewAdpater.baseview> {

    ArrayList<StaggBean> list;
    Context context;
    private ArrayList<Integer> mHeight;

    public YouXuanRecyclerViewAdpater(ArrayList<StaggBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_ufo, parent, false);
        mHeight=new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            mHeight.add((int) (150+Math.random()*300));
        }
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
        layoutParams.height = mHeight.get(position);
        holder.imageView.setLayoutParams(layoutParams);

        Glide.with(context).load(list.get(position).getPic()).into(holder.imageView);
        holder.textView.setText(list.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class baseview extends RecyclerView.ViewHolder{

        ImageView imageView;
        ImageView jinggao;
        TextView textView;

        public baseview(@NonNull View itemView) {
            super(itemView);
            this.imageView= itemView.findViewById(R.id.ufo_image);
            this.textView= itemView.findViewById(R.id.ufo_text);
            this.jinggao= itemView.findViewById(R.id.jinggao);
        }
    }
}
