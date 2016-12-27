package com.jin.androiduipractice.moneyScale;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.jin.androiduipractice.R;

/**
 * Created by wanny-n1 on 2016/12/19.
 */

public class MoneyScaleView extends View {
    private Paint mPaint;
    private int mMaxValue;
    private int mMinValue;
    private int mScale;
    private int mCurrentValue;
    private GestureDetector mGestureDetector;
    private int mMoveDistance = 0;
    private Scroller mScroller;

    public MoneyScaleView(Context context) {
        this(context, null);
    }

    public MoneyScaleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoneyScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initPaint();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MoneyScaleView);
        mMaxValue = ta.getInt(R.styleable.MoneyScaleView_maxValue, 200000);
        mMinValue = ta.getInt(R.styleable.MoneyScaleView_minValue, 0);
        mScale = ta.getInt(R.styleable.MoneyScaleView_moneyScale, 1000);
        mCurrentValue = ta.getInt(R.styleable.MoneyScaleView_money, 3000);
        ta.recycle();

        mMoveDistance = (mCurrentValue - mMinValue) * 130 / mScale;
        mScroller = new Scroller(context);

        initGesture(context);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(0xffff0000);
    }

    private void initGesture(final Context context) {
        mGestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                mMoveDistance += distanceX;
                invalidate();
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // ViewConfigration.get(getContext()).getScaledTouchSlop()


                //FixMe  fling 设置不成功，值读数不准确
                if (velocityX < ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                    return false;
                }
                mScroller.fling(getScrollX(), getScrollY(), (int) velocityX, (int) velocityY, 0, 1000, 0, 1000);
                return true;
            }
        });
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            // mScroller.getCurrX();
            mMoveDistance += mScroller.getCurrX();
            invalidate();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int defaultWidth = Integer.MAX_VALUE;
        int defaultHeight = 150;
        int widthSize;
        int heightSize;

        if (wMode == MeasureSpec.EXACTLY) {
            widthSize = wSize;
        } else {
            widthSize = Math.min(defaultWidth, wSize);
        }

        if (hMode == MeasureSpec.EXACTLY) {
            heightSize = hSize;
        } else {
            heightSize = Math.min(defaultHeight, hSize);
        }
        setMeasuredDimension(widthSize, heightSize);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        canvas.drawLine(0, height, width, height, mPaint);
        canvas.drawLine(width / 2, 0, width / 2, height, mPaint);
        canvas.save();
        if (listener != null) {
            mCurrentValue = mMoveDistance * mScale / 130 - mMinValue;
            if (mCurrentValue * 10 % mScale == 0) {
                listener.getValue(mCurrentValue);
            }
        }
        canvas.translate(width / 2 - mMoveDistance, height);

        int size = (mMaxValue - mMinValue) * 10 / mScale;
        for (int i = 0; i <= size; i++) {
            if (i % 10 == 0) {
                canvas.drawLine(i * 13, 0, i * 13, -30, mPaint);
                mPaint.setTextAlign(Paint.Align.CENTER);
                mPaint.setTextSize(sp2px(10));
                canvas.drawText(String.valueOf(mMinValue + i * mScale / 10), i * 13, -40, mPaint);
            } else {
                canvas.drawLine(i * 13, 0, i * 13, -20, mPaint);
            }
        }

        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }


    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    public interface onValueChangeListener {
        void getValue(int value);
    }

    private onValueChangeListener listener;

    public void setValueChangeListener(onValueChangeListener listener) {
        this.listener = listener;
    }

}
