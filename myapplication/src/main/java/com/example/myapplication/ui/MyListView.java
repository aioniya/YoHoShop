package com.example.myapplication.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

public class MyListView extends ListView {
    private ScrollView mParent;
    private float mDownY;
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                setParentScrollAble(false);
//                mDownY = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                //
//                if (isListViewReachTop() && ev.getY() - mDownY > 0) {
//                    setParentScrollAble(true);
//                } else if (isListViewReachBottom() && ev.getY() - mDownY < 0) {
//                    setParentScrollAble(true);
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//
//            case MotionEvent.ACTION_CANCEL:
//                setParentScrollAble(true);
//                break;
//            default:
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }
//
//
//    /**
//     * @param flag
//     */
//    private void setParentScrollAble(boolean flag) {
//        mParent.requestDisallowInterceptTouchEvent(!flag);
//    }
//
//
//    public boolean isListViewReachTop() {
//        boolean result=false;
//        if(getFirstVisiblePosition()==0){
//            View topChildView = getChildAt(0);
//            if (topChildView != null) {
//                result=topChildView.getTop()==0;
//            }
//        }
//        return result ;
//    }
//
//    public boolean isListViewReachBottom() {
//        boolean result=false;
//        if (getLastVisiblePosition() == (getCount() - 1)) {
//            View bottomChildView = getChildAt(getLastVisiblePosition() - getFirstVisiblePosition());
//            if (bottomChildView != null) {
//                result= (getHeight() >= bottomChildView.getBottom());
//            }
//        }
//        return  result;
//    }


}
