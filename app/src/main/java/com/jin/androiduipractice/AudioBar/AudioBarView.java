package com.jin.androiduipractice.AudioBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wanny-n1 on 2016/12/12.
 */

public class AudioBarView extends View {
    private int mRectCount = 8;
    private int offerset = 100;
    private int mWidth;
    private int mRectHeight;
    private int mRectWidth;
    private Paint mPaint;
    private LinearGradient mLinearGradient;


    public AudioBarView(Context context) {
        this(context, null);
    }

    public AudioBarView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public AudioBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
      //  if (w != oldh && w != 0) {
            mWidth = getWidth();
            mRectHeight = getHeight();
            mRectWidth = (int) (mWidth * 0.6 / mRectCount);
            mLinearGradient = new LinearGradient(0, 0, mRectWidth, mRectHeight, Color.YELLOW, Color.BLUE, Shader.TileMode.CLAMP);
            mPaint = new Paint();
            mPaint.setShader(mLinearGradient);
     //   }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mRectCount; i++) {
            int currentHeight = (int) Math.random() * mRectHeight;
            canvas.drawRect((float) (mWidth * 0.4 / 2 + mRectWidth * i + offerset), currentHeight,
                    (float) (mWidth * 0.4 / 2 + mRectWidth * (i + 1)), mRectHeight, mPaint);
        }

    }
}
