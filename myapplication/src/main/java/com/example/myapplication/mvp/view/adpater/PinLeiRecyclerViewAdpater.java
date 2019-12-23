package com.example.myapplication.mvp.view.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.StaggBean;
import com.example.myapplication.mvp.model.entity.UiBean;

import java.util.ArrayList;

public abstract class PinLeiRecyclerViewAdpater extends RecyclerView.Adapter<PinLeiRecyclerViewAdpater.baseview> {

    ArrayList<UiBean> list;
    Context context;


    public PinLeiRecyclerViewAdpater(ArrayList<UiBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_pinlei, parent, false);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {
        holder.textView.setText(list.get(position).getTitle());
        holder.datext.setText(list.get(position).getTitle());
        OnClicken(holder,position);





    }
    public abstract  void OnClicken(baseview holder, int position);

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class baseview extends RecyclerView.ViewHolder{

        public TextView textView;
       public LinearLayout linearLayout;
       public TextView datext;

        public baseview(@NonNull View itemView) {
            super(itemView);
            this.textView= itemView.findViewById(R.id.text);
            this.linearLayout= itemView.findViewById(R.id.tiaomu);
            this.datext= itemView.findViewById(R.id.datext);
        }
    }
}
