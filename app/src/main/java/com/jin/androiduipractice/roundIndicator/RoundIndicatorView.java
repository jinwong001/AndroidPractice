package com.jin.androiduipractice.roundIndicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.jin.androiduipractice.R;

import static android.view.View.MeasureSpec.EXACTLY;

/**
 * Created by wanny-n1 on 2016/12/13.
 */

public class RoundIndicatorView extends View {
    private static final int DEFLAULT_SIZE = 100;
    private Paint mPaint;
    private Paint mPaint2;
    private int mRadius;
    private int mStartAngel;
    private int mSweepAndgel;
    private int mSweepInWidth;
    private int mSweepOutWidth;
    private int mMaxNum;
    private int mValue;
    private String[] mText = new String[]{"较差", "中等", "良好", "优秀", "极好"};


    public RoundIndicatorView(Context context) {
        this(context, null);
    }

    public RoundIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        setBackgroundColor(0xff00bbd3);

        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setColor(Color.WHITE);


        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundIndicatorView);


        mStartAngel = ta.getInt(R.styleable.RoundIndicatorView_startAngel, 160);
        mSweepAndgel = ta.getInt(R.styleable.RoundIndicatorView_sweepAngel, 220);
        mMaxNum = ta.getInt(R.styleable.RoundIndicatorView_maxNum, 500);
        mValue = ta.getInt(R.styleable.RoundIndicatorView_value, 500);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getDimension(widthMeasureSpec), getDimension(heightMeasureSpec));
    }

    private int getDimension(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == EXACTLY) {
            return specSize;
        } else {
            return Math.min(specSize, DEFLAULT_SIZE);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        mRadius = Math.min(width, height) / 4;
        canvas.save();
        canvas.translate(width / 2, height / 2);
        drawCicle(canvas);
        drawScale(canvas);
        drawCenterText(canvas);
        drawIndicator(canvas); //画当前进度值


    }

    private void drawIndicator(Canvas canvas) {
        canvas.save();
        int outRadius = mRadius + dp2px(10);
        int sweepAngel = mSweepAndgel * mValue / mMaxNum;
        mPaint2.setStrokeWidth(dp2px(3));
        mPaint2.setShader(new SweepGradient(0, 0, new int[]{0xffffffff, 0x00ffffff, 0x99ffffff, 0xffffffff}, null));
        canvas.drawArc(new RectF(-outRadius, -outRadius, outRadius, outRadius), mStartAngel, sweepAngel, false, mPaint2);
        mPaint.setStyle(Paint.Style.FILL);
        //边缘加上模糊效果
        mPaint.setMaskFilter(new BlurMaskFilter(dp2px(3), BlurMaskFilter.Blur.SOLID));
        canvas.drawCircle((float) (outRadius * Math.cos(Math.toRadians(mStartAngel + sweepAngel))), (float) (outRadius * Math.sin(Math.toRadians(mStartAngel + sweepAngel))), dp2px(3), mPaint);
        canvas.restore();
    }

    private void drawCenterText(Canvas canvas) {
        canvas.save();
        mPaint.setAlpha(0xaa);
        mPaint.setTextSize(dp2px(30));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(String.valueOf(mValue), 0, 0, mPaint);
        mPaint.setTextSize(dp2px(20));
        String creditRating;
        if (mValue <= 0) {
            creditRating = mText[0];
        } else if (mValue >= mMaxNum) {
            creditRating = mText[4];
        } else {
            creditRating = mText[mValue * 5 / mMaxNum];
        }
        canvas.drawText("信用" + creditRating, 0, dp2px(30), mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.restore();

    }

    private void drawScale(Canvas canvas) {
        canvas.save();
        //这里角度一定要为float 类型，否则会有偏移量
        float angel = (float) mSweepAndgel / 30;
        canvas.rotate(mStartAngel - 270);
        mPaint.setAlpha(0xaa);
        mPaint.setStrokeWidth(dp2px(2));
        for (int i = 0; i <= 30; i++) {
            if (i % 6 == 0) {
                mPaint.setAlpha(0xaa);
                canvas.drawLine(0, -mRadius - dp2px(4), 0, -mRadius + dp2px(4), mPaint);
                drawText(String.valueOf(mMaxNum * i / 30), canvas);
            } else {
                mPaint.setAlpha(0x40);
                canvas.drawLine(0, -mRadius - dp2px(4), 0, -mRadius + dp2px(4), mPaint);
            }

            if (i == 3 || i == 9 || i == 15 || i == 21 || i == 27) {
                drawText(mText[(i - 3) / 6], canvas);
            }

            canvas.rotate(angel);
        }

        canvas.restore();
    }

    private void drawText(String text, Canvas canvas) {
        mPaint.setAlpha(0xaa);
        mPaint.setTextSize(dp2px(8));
        mPaint.setStyle(Paint.Style.FILL);
        float textWidth = mPaint.measureText(text);
        canvas.drawText(text, -textWidth / 2, -mRadius + dp2px(15), mPaint);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    private void drawCicle(Canvas canvas) {
        canvas.save();
        mPaint.setStrokeWidth(dp2px(8));
        mPaint.setAlpha(0x40);
        canvas.drawArc(new RectF(-mRadius, -mRadius, mRadius, mRadius), mStartAngel, mSweepAndgel, false, mPaint);
        mPaint.setStrokeWidth(dp2px(3));
        int outRadius = mRadius + dp2px(10);
        canvas.drawArc(new RectF(-outRadius, -outRadius, outRadius, outRadius), mStartAngel, mSweepAndgel, false, mPaint);
        canvas.restore();
    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }


}
