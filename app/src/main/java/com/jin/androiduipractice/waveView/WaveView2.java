package com.jin.androiduipractice.waveView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wanny-n1 on 2018/2/23.
 */

public class WaveView2 extends View {
    private Path mPath1, mPath2;
    private Paint mPaint1, mPaint2;
    private float φ = 0f;
    private int mWaveCount;

    private OnWaveAnimationListener mWaveAnimationListener;

    public WaveView2(Context context) {
        this(context, null);
    }

    public WaveView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public WaveView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPath1 = new Path();
        mPath2 = new Path();

        mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint1.setAntiAlias(true);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setColor(Color.WHITE);

        mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint1.setAntiAlias(true);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setColor(Color.WHITE);
        mPaint1.setAlpha(80);
    }

    // 画白色的波浪边
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 抗锯齿 等同于mPaint1.setAntiAlias(true);
        //canvas.setDrawFilter( new PaintFlagsDrawFilter(0,Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
        int left = getLeft();
        int right = getRight();
        int bottom = getLeft();
        int width = getWidth();

        mPath1.moveTo(left, bottom);
        mPath2.moveTo(left, bottom);

        φ -= 0.1f;
        // 不断改变 初相 φ 使 Path 不断变动
        double ω = 2 * Math.PI / width;
        float y1, y2;
        for (int x = 0; x <= width; x += 20) {
            /**
             *  y=Asin(ωx+φ)+k
             *  A—振幅越大，波形在y轴上最大与最小值的差值越大
             *  ω—角速度， 控制正弦周期(单位角度内震动的次数)
             *  φ—初相，反映在坐标系上则为图像的左右移动。这里通过不断改变φ,达到波浪移动效果
             *  k—偏距，反映在坐标系上则为图像的上移或下移。
             */

            y1 = (float) (8 * Math.cos(ω * x + φ) + 8);
            y2 = (float) (8 * Math.sin(ω * x + φ));
            mPath1.lineTo(x, y1);
            mPath2.lineTo(x, y2);

            //回调 把y坐标的值传出去(在activity里面接收让小机器人随波浪一起摇摆)
            if (mWaveAnimationListener != null) {
                mWaveAnimationListener.OnWaveAnimation(y1);
            }
        }


        //TODO 使用贝塞尔曲线画


        mPath1.lineTo(right, bottom);
        mPath2.lineTo(right, bottom);

        canvas.drawPath(mPath1, mPaint1);
        canvas.drawPath(mPath2, mPaint2);
        // 20 秒重画一次
        postInvalidateDelayed(20);
    }

    public void setOnWaveAnimationListener(OnWaveAnimationListener l) {
        this.mWaveAnimationListener = l;
    }

    public interface OnWaveAnimationListener {
        void OnWaveAnimation(float y);
    }
}
