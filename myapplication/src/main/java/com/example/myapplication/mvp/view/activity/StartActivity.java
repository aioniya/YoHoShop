package com.example.myapplication.mvp.view.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.gyf.barlibrary.ImmersionBar;
import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {
    @SuppressLint("HandlerLeak")
    Handler handlers = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1002) {
                count++;
                if (count == 1) {
                    AnimatorSet animatorSet = new AnimatorSet();
                    ObjectAnimator alpha = ObjectAnimator.ofFloat(start_image, "alpha", 0f, 1f);
                    animatorSet.setDuration(3000);
                    animatorSet.play(alpha);
                    animatorSet.start();
                }
                if (count == 3) {
                    AnimatorSet animatorSet = new AnimatorSet();
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(start_image, "scaleX", 1.1f, 1f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(start_image, "scaleY", 1.1f, 1f);
                    animatorSet.setDuration(4000);
                    animatorSet.play(scaleX).with(scaleY);
                    animatorSet.start();
                    animatorSet.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) { }
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            start_button.setVisibility(View.GONE);
                        }
                        @Override
                        public void onAnimationCancel(Animator animation) { }
                        @Override
                        public void onAnimationRepeat(Animator animation) { }
                    });

                }
                if(count == 5){
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.startin,R.anim.startout);
                    finish();
                    timer.cancel();
                }
            }
        }
    };
    Timer timer;
    int count = 0;
    private ImageView start_image;
    private Button start_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ImmersionBar.with(this).init();
        initView();
        start_button.setFocusable(true);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handlers.sendEmptyMessage(1002);
            }
        }, 0, 1500);

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(start_image, "scaleX", 1f, 1.1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(start_image, "scaleY", 1f, 1.1f);
        animatorSet.setDuration(10);
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                timer.cancel();
            }
        });
    }

    private void initView() {
        start_image = (ImageView) findViewById(R.id.start_image);
        start_button = (Button) findViewById(R.id.start_button);
    }

}
