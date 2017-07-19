package com.jin.androiduipractice.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by wanny-n1 on 2017/7/17.
 */

public class CircleImageDrawable extends Drawable {
    private Paint mPaint;
    private int mRadius;
    private Bitmap mBitmap;


    public CircleImageDrawable(Bitmap bitmap) {
        mBitmap = bitmap;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        mRadius = Math.min(bitmap.getWidth()/2, bitmap.getHeight()/2);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
        mPaint.setAlpha(i);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicHeight() {
        return mRadius*2;
    }

    @Override
    public int getIntrinsicWidth() {
        return mRadius*2;
    }


}
