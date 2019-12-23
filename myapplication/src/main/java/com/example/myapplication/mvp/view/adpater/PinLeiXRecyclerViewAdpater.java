package com.example.myapplication.mvp.view.adpater;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.GridviewBean;
import com.example.myapplication.mvp.model.entity.PinLeiBean;
import com.example.myapplication.ui.MyGridview;

import java.util.ArrayList;


public class PinLeiXRecyclerViewAdpater extends RecyclerView.Adapter<PinLeiXRecyclerViewAdpater.baseview> {

    ArrayList<PinLeiBean> pinLeiBeans;
    Context context;


    GridViewBaseAdpater gridViewBaseAdpater;


    public PinLeiXRecyclerViewAdpater(ArrayList<PinLeiBean> pinLeiBeans, Context context) {
        this.pinLeiBeans = pinLeiBeans;
        this.context = context;


    }

    @Override
    public int getItemViewType(int position) {
        return pinLeiBeans.get(position).getType();
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(viewType==0){
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_pinlei_title, parent, false);
        }else if(viewType==1){
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_pinlei_gridview, parent, false);
        }
       return new baseview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {
        int itemViewType = getItemViewType(position);

        if(itemViewType==0){
            String s = pinLeiBeans.get(position).toString();
            Log.e("sssf",s);

                if(pinLeiBeans.get(position).getTitle()!=null){
                    holder.pinlei_title.setText(pinLeiBeans.get(position).getTitle());
                }



        }else{

            String s = pinLeiBeans.get(position).toString();
            Log.e("sssf",s);
                                gridViewBaseAdpater = new GridViewBaseAdpater(pinLeiBeans,context);
                    holder.recyclerView.setLayoutManager(new GridLayoutManager(context,3));
                    holder.recyclerView.setAdapter(gridViewBaseAdpater);

//                if(pinLeiBeans.get(position).getPictitle()==null){
//                    pinLeiBeans.remove(position);

//                }else{
//                    gridViewBaseAdpater = new GridViewBaseAdpater(pinLeiBeans,context);
//                    holder.recyclerView.setLayoutManager(new GridLayoutManager(context,3));
//                    holder.recyclerView.setAdapter(gridViewBaseAdpater);
//                }



        }



    }

    @Override
    public int getItemCount() {
        return pinLeiBeans.size();
    }

    class baseview extends RecyclerView.ViewHolder{

        TextView pinlei_title;
        RecyclerView recyclerView;

        public baseview(@NonNull View itemView) {
            super(itemView);
            this.pinlei_title = itemView.findViewById(R.id.pinlei_title);
            this.recyclerView = itemView.findViewById(R.id.pinlei_gridview);

        }
    }
}
