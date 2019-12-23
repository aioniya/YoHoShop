package com.example.myapplication.tabfragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class Fragment_shequ extends Fragment {
    private RadioButton shequ_neirong_bt;
    private RadioButton shequ_shoucang_bt;
    private RadioGroup shequ_group;
    private Button shequ_button;
    private ImageView shequ_images;
    private TextView shequ_texts;
    private ImageView shequ_imageone;
    private ImageView shequ_imagetwo;
    private ImageView shequ_imagethree;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_tab_shequ, null);
        shequ_neirong_bt = inflate.findViewById(R.id.shequ_neirong_bt);
        shequ_shoucang_bt = inflate.findViewById(R.id.shequ_shoucang_bt);
        shequ_group = inflate.findViewById(R.id.shequ_group);
        shequ_button = inflate.findViewById(R.id.shequ_button);
        shequ_images = inflate.findViewById(R.id.shequ_images);
        shequ_texts = inflate.findViewById(R.id.shequ_texts);
        shequ_imageone = inflate.findViewById(R.id.shequ_imageone);
        shequ_imagetwo = inflate.findViewById(R.id.shequ_imagetwo);
        shequ_imagethree = inflate.findViewById(R.id.shequ_imagethree);


        caozuo();











        shequ_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "132", Toast.LENGTH_SHORT).show();
            }
        });
        shequ_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (shequ_neirong_bt.isChecked()) {
                    //图片
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bijiben);
                    shequ_images.setImageBitmap(bitmap);
                    //文字
                    shequ_texts.setText("发布你的第一篇内容,可立赚5元");
                    //button
                    shequ_button.setText("去发布");
                   //image
                    Bitmap bitmapone = BitmapFactory.decodeResource(getResources(), R.mipmap.youjiang);
                    shequ_imageone.setImageBitmap(bitmapone);
                    Bitmap bitmaptwo = BitmapFactory.decodeResource(getResources(), R.mipmap.yotop);
                    shequ_imagetwo.setImageBitmap(bitmaptwo);
                    Bitmap bitmapthree = BitmapFactory.decodeResource(getResources(), R.mipmap.yoyuansu);
                    shequ_imagethree.setImageBitmap(bitmapthree);



                }
                if (shequ_shoucang_bt.isChecked()) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.xingxing);
                    shequ_images.setImageBitmap(bitmap);
                    //文字
                    shequ_texts.setText("快去社区发现潮流好内容吧");
                    //button
                    shequ_button.setText("去社区逛逛");
                    //image
//                    Bitmap bitmapone = BitmapFactory.decodeResource(getResources(), R.mipmap.youjiangtwo);
//                    shequ_imageone.setImageBitmap(bitmapone);
//                    Bitmap bitmaptwo = BitmapFactory.decodeResource(getResources(), R.mipmap.yotoptwo);
//                    shequ_imagetwo.setImageBitmap(bitmaptwo);
//                    Bitmap bitmapthree = BitmapFactory.decodeResource(getResources(), R.mipmap.yoyuansutwo);
//                    shequ_imagethree.setImageBitmap(bitmapthree);
                }
            }
        });

        return inflate;
    }

    private void caozuo() {


    }
}
