package com.jin.androiduipractice.move;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by wanny-n1 on 2016/12/12.
 */

public class DragView extends View {
    private int mLastX;
    private int mLastY;
    private Scroller mScroller;

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = rawX;
                mLastY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                View viewGroup = (View) getParent();
                mScroller.startScroll(viewGroup.getScrollX(), viewGroup.getScrollY(),
                        -viewGroup.getScrollX(), -viewGroup.getScrollY());
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = rawX - mLastX;
                int offsetY = rawY - mLastY;

                //移动方式1  layout   移动相对于父布局位置
                //getLeft()  相当于margin
//                layout(getLeft() + offsetX, getTop() + offsetY,
//                        getRight() + offsetX, getBottom() + offsetY);


//               //移动方式 2 offsetLeftAndRight offsetTopAndBottom
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                //移动方式3 layoutParams
                // 注意为ViewGroup.MarginLayoutParams
//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(layoutParams);

                //移动方式4
                ((View) getParent()).scrollBy(-offsetX, -offsetY);
//
                mLastX = rawX;
                mLastY = rawY;
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
//        //判断Scroller 是否执行完毕
//        if (mScroller.computeScrollOffset()) {
//            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
//            invalidate();
//        }
    }
}
