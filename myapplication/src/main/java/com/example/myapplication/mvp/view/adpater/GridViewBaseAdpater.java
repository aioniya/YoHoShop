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
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.PinLeiBean;

import java.util.ArrayList;

public class GridViewBaseAdpater extends RecyclerView.Adapter<GridViewBaseAdpater.baseview> {
    ArrayList<PinLeiBean> list;
    Context context;
    ArrayList<PinLeiBean> gridviewBeans = new ArrayList<>();
    public GridViewBaseAdpater(ArrayList<PinLeiBean> list, Context context) {
        this.list = list;
        this.context = context;

//        for (int i = 0; i < list.size(); i++) {
//            if(list.get(i).getType()==1){
//                gridviewBeans.add(new PinLeiBean(list.get(i).getTitle(),list.get(i).getPic(),list.get(i).getPictitle(),list.get(i).getType()));
//            }
//        }
//        for (int i = 0; i < gridviewBeans.size(); i++) {
//            Log.e("ppp",gridviewBeans.get(i).getType()+"");
//        }

    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item_gridview_item, parent, false);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {


//        for (int i = 0; i < gridviewBeans.size(); i++) {
////            Log.e("size",gridviewBeans.get(i).toString());
//            holder.textView.setText(gridviewBeans.get(i).getPictitle());
//            Glide.with(context).load(gridviewBeans.get(i).getPic());
//        }
//        if(list.get(position).getType()==1){
            holder.textView.setText(list.get(position).getPictitle());
            Glide.with(context).load(list.get(position).getPic()).into(holder.imageView);
//        }



    }

    @Override
    public int getItemCount() {
        Log.e("size",list.size()+"");
        return list.size();

    }


    class baseview extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        public baseview(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.gridview_item_image);
            this.textView = itemView.findViewById(R.id.gridview_item_text);
        }
    }

}
