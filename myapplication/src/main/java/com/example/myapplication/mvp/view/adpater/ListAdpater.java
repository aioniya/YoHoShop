package com.example.myapplication.mvp.view.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.ABCDBean;

import java.util.ArrayList;

public class ListAdpater extends RecyclerView.Adapter<ListAdpater.Baseview> {

    ArrayList<ABCDBean> list;
    Context context;

    public ListAdpater(ArrayList<ABCDBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
        return new Baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Baseview holder, int position) {
        holder.textView1.setText(list.get(position).getLetter());
        holder.textView2.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Baseview extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;

        public Baseview(@NonNull View itemView) {
         super(itemView);
         this.textView1 = itemView.findViewById(R.id.list_letter);
         this.textView2 = itemView.findViewById(R.id.list_name);
     }
 }
}
