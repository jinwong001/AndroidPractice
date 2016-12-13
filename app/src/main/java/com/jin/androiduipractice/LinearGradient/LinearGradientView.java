package com.jin.androiduipractice.LinearGradient;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.jin.androiduipractice.R;

/**
 * Created by wanny-n1 on 2016/12/9.
 */

public class LinearGradientView extends View {
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private int mStartColor;
    private int mMiddleColor;
    private int mEndColor;
    private LinearGradient.TileMode mTileMode;

    private static final int DEFAULT_SIZE = 100;

    public LinearGradientView(Context context) {
        this(context, null);
    }

    public LinearGradientView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LinearGradientView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LinearGradientView);
        mStartColor = ta.getColor(R.styleable.LinearGradientView_startColor, 0);
        mMiddleColor = ta.getColor(R.styleable.LinearGradientView_middleColor, 0);
        mEndColor = ta.getColor(R.styleable.LinearGradientView_endColor, 0);
        int index = ta.getInt(R.styleable.LinearGradientView_tileMode, 0);
        setTileMode(index);
        ta.recycle();
    }

    private void setTileMode(int index) {
        switch (index) {
            case 0:
                mTileMode = Shader.TileMode.CLAMP;
                break;
            case 1:
                mTileMode = Shader.TileMode.REPEAT;
                break;
            case 2:
                mTileMode = Shader.TileMode.MIRROR;
                break;
            default:
                mTileMode = Shader.TileMode.CLAMP;
                break;
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(DEFAULT_SIZE, widthMeasureSpec),
                getDefaultSize(DEFAULT_SIZE, heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LinearGradient linearGradient = new LinearGradient(0, 0, getMeasuredWidth() / 2, getMeasuredHeight() / 2,
                new int[]{mStartColor, mMiddleColor, mEndColor},
                null,
                mTileMode);
        mPaint.setShader(linearGradient);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

    }

    public static int getDefaultSize(int size, int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = Math.min(size, specSize);
        }
        return result;
    }
}
