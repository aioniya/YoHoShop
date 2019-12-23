package com.example.myapplication.mvp.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.jwkj.libzxing.QRCodeManager;

public class ErWeiMaActivity extends AppCompatActivity {

    private ImageView erweima_image;
    private TextView erweima_dianjishuaxin;
    int count = 10086;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_wei_ma);
        initView();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.touxiangs);
        Bitmap logo_qRcode = QRCodeManager.getInstance().createQRCode(count+"", 500, 500, bitmap);
        erweima_image.setImageBitmap(logo_qRcode);

        erweima_dianjishuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count+=10;
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.touxiangs);
                Bitmap logo_qRcode = QRCodeManager.getInstance().createQRCode(count+"", 500, 500, bitmap);
                erweima_image.setImageBitmap(logo_qRcode);

            }
        });
    }

    private void initView() {
        erweima_image = (ImageView) findViewById(R.id.erweima_image);
        erweima_dianjishuaxin = (TextView) findViewById(R.id.erweima_dianjishuaxin);

    }
}
