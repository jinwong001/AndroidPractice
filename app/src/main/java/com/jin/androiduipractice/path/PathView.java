package com.jin.androiduipractice.path;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by wanny-n1 on 2016/12/20.
 */

public class PathView extends View {
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private Path mDst;
    private float mAnimatorValue=0;
    private float mLength;
    private Path mPath;
    private float[] mPos;
    private float[] mTan;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setLayerType(LAYER_TYPE_HARDWARE, null);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);


        //三角形
//        mPath = new Path();
//        mPath.moveTo(100, 100);
//        mPath.lineTo(500, 100);
//        mPath.lineTo(300, 400);

        //圆形
        mPath=new Path();
        mPath.addCircle(0,0,200, Path.Direction.CW);

        mDst = new Path();


        mPathMeasure = new PathMeasure();
        //将path与PathMeasure绑定，true 测量时闭合path
        mPathMeasure.setPath(mPath, true);
        //计算路径长度
        //getLength 获取是当前path长度，而不是所有path的长度和
        mLength = mPathMeasure.getLength();


        mPos = new float[2];
        mTan = new float[2];


        //当path 中包括多个path,需要nextCountour()获取他们
        //   mPathMeasure.nextContour();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) animation.getAnimatedValue();

                //DashPathEffect(float[] intervals, float phase)
                //intervals 为实线，虚线路径长度，可以通过PathMeasure.getLengh() 计算出
//                //phase 计算偏移量
//                DashPathEffect dashPathEffect = new DashPathEffect(new float[]{mLength, mLength}, mAnimatorValue * mLength);
//                mPaint.setPathEffect(dashPathEffect);

                invalidate();
            }
        });

        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getSize(100, widthMeasureSpec), getSize(100, heightMeasureSpec));
    }

    private int getSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPathMeasure.getPosTan(mAnimatorValue*mLength,mPos, mTan);
        float degree=(float)(Math.atan2(mTan[1],mTan[0])*180/Math.PI);

        canvas.save();
        canvas.translate(400,400);
        canvas.drawPath(mPath,mPaint);
        canvas.drawCircle(mPos[0],mPos[1],10,mPaint);
        canvas.rotate(degree);
        canvas.drawLine(0,-200,300,-200,mPaint);
        canvas.restore();





   //     canvas.drawPath(mPath, mPaint);
//        mDst.reset();
//        // 硬件加速的BUG
//        mDst.lineTo(0, 0);
//        float stop = mLength * mAnimatorValue;
//
//        //截取path1中0到stop 部分的path到mDst;
//        mPathMeasure.getSegment(0, stop, mDst, true);
//        canvas.drawPath(mDst, mPaint);


//        //三角形
//        Path path1 = new Path();
//        path1.moveTo(100, 100);
//        path1.lineTo(500, 100);
//        path1.lineTo(300, 400);
//        path1.close();
//        canvas.drawPath(path1, mPaint);
//
//        Path path2 = new Path();
//        path2.addRect(100, 100, 400, 400, Path.Direction.CW);
//        path2.setLastPoint(300, 400);
//
//      //  path2.addPath(path1);
//
//
//        path2.arcTo(new RectF(300, 200, 400, 300), 0, 90, true);
//
//        Path path = new Path();
//        path.addCircle(300,200,100, Path.Direction.CW);
//        path.addCircle(200,200,100, Path.Direction.CW);
//        path.setFillType(Path.FillType.EVEN_ODD);
//        path.toggleInverseFillType();
//        canvas.drawPath(path, mPaint);


//        typeface = Typeface.create("宋体", Typeface.NORMAL);
//        mPaint.setTypeface(typeface);
//        canvas.drawText("是一颗小小的石头", 100, 100, mPaint);


//        Typeface typeface=Typeface.create("宋体",Typeface.BOLD);
//        mPaint.setTypeface(typeface);
//        canvas.drawText("是一颗小小的石头",100,100,mPaint);
//
//
//        canvas.drawColor(Color.YELLOW);
//        canvas.clipRect(new RectF(100,100,200,200), Region.Op.REPLACE);
//        canvas.drawColor(Color.BLUE);

        //  canvas.drawPath(path, mPaint);


    }
}
