package com.example.myapplication.mvp.view.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.ShopCarEntity;
import com.example.myapplication.mvp.model.entity.StaggBean;

import java.util.ArrayList;

public abstract class ShopcaroneRecyclerViewAdpater extends RecyclerView.Adapter<ShopcaroneRecyclerViewAdpater.baseview> {

    ArrayList<ShopCarEntity.ValuesBean> list;
    Context context;

    public ShopcaroneRecyclerViewAdpater(ArrayList<ShopCarEntity.ValuesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_shopcarone, parent, false);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {
        holder.color.setText("颜色:"+list.get(position).getShop_color());
        holder.name.setText(list.get(position).getShop_name());
        holder.momery.setText("￥"+list.get(position).getShop_price());
        holder.shopcarone_num.setText("x"+list.get(position).getShop_num());
        holder.shuliang.setText(list.get(position).getShop_num());

        Glide.with(context).load(Api.APP_DOMAIN +list.get(position).getCar_path()).into(holder.imageView);

        if(list.get(position).isFlag()==true){
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }

        if(list.get(position).isCountflag()==true){
           holder.name.setVisibility(View.GONE);
           holder.color.setVisibility(View.GONE);
           holder.shopcarone_all.setVisibility(View.VISIBLE);

        }else{
            holder.name.setVisibility(View.VISIBLE);
            holder.color.setVisibility(View.VISIBLE);
            holder.shopcarone_all.setVisibility(View.GONE);
        }




        OnClickListeners(holder,position);
    }
     abstract public void OnClickListeners(baseview holder, int position);

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class baseview extends RecyclerView.ViewHolder{
        public CheckBox checkBox;
        public ImageView imageView;
        public TextView name;
        public TextView color;
        public TextView momery;
        public TextView shopcarone_num;
        public LinearLayout shopcarone_all;
        public RelativeLayout jia;
        public RelativeLayout jian;
        public TextView shuliang;


        public baseview(@NonNull View itemView) {
            super(itemView);
            this.checkBox= itemView.findViewById(R.id.shopcarone_check);
            this.shuliang= itemView.findViewById(R.id.shopcarone_shuliang);
            this.imageView= itemView.findViewById(R.id.shopcarone_image);
            this.name= itemView.findViewById(R.id.shopcarone_text);
            this.color= itemView.findViewById(R.id.shopcarone_color);
            this.momery= itemView.findViewById(R.id.shopcarone_momery);
            this.shopcarone_num= itemView.findViewById(R.id.shopcarone_num);
            this.shopcarone_all= itemView.findViewById(R.id.shopcarone_all);
            this.jia= itemView.findViewById(R.id.shopcarone_jia);
            this.jian= itemView.findViewById(R.id.shopcarone_jian);

        }
    }
}
