package com.example.myapplication.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MyXRecyclerview extends XRecyclerView {
    public MyXRecyclerview(Context context) {
        super(context);
    }

    public MyXRecyclerview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyXRecyclerview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
