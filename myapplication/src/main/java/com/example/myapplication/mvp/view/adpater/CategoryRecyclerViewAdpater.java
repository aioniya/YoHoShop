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
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.RecommendEntity;

import java.util.ArrayList;

public abstract class CategoryRecyclerViewAdpater extends RecyclerView.Adapter<CategoryRecyclerViewAdpater.baseview> {

    ArrayList<GoodsListEntity.ValuesBean> list;
    Context context;

    public CategoryRecyclerViewAdpater(ArrayList<GoodsListEntity.ValuesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item_category, parent, false);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {
        Glide.with(context).load(Api.APP_DOMAIN +list.get(position).getGoods_img_path()).into(holder.imageView);
        holder.textView.setText(list.get(position).getGoods_name());
        OnClickListener(holder,position);
    }
    public abstract  void OnClickListener(baseview holder, int position);

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class baseview extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;


        public baseview(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.category_item_image);
            this.textView = itemView.findViewById(R.id.category_item_text);

        }
    }
}
