package com.example.myapplication.mvp.model.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;



public class Contact {

    //加密rsa变量
    public static final String RSA = "RSA";// 非对称加密密钥算法
    public static final String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";//加密填充方式

    public static String joint(String name,String id){
        String str = "";
        try {
            JSONObject object=new JSONObject();
            object.put(name,id);
            str=object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void show(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
