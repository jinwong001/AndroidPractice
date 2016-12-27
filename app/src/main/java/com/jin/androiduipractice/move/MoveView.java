package com.jin.androiduipractice.move;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by wanny-n1 on 2016/12/12.
 */

public class MoveView extends View {
    private int mLastX;
    private int mLastY;
    private int mStartX;
    private int mStartY;
    private Scroller mScroller;

    public MoveView(Context context) {
        this(context, null);
    }

    public MoveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoveView(Context context, AttributeSet attrs, int defStyleAttr) {
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
                mStartX = rawX;
                mStartY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                int distenceX = rawX - mStartX;
                int distenceY = rawY - mStartY;

                View viewGroup = (View) getParent();
                mScroller.startScroll(viewGroup.getScrollX(), viewGroup.getScrollY(),
                        distenceX, distenceY);
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
                // scrollBy 移动的是内容，textview 中是文字，viewGroup 中是子view，
                //本质上移动是相框距离，可以参考相框，
                ((View) getParent()).scrollBy(-offsetX, -offsetY);

                mLastX = rawX;
                mLastY = rawY;
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断Scroller 是否执行完毕
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
