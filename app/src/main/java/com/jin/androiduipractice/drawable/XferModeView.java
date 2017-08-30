package com.jin.androiduipractice.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by wanny-n1 on 2017/8/30.
 */

public class XferModeView extends AppCompatImageView {
    private Paint mPaint=new Paint();

    public XferModeView(Context context) {
        super(context);
    }

    public XferModeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XferModeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        canvas.drawRect(new RectF(0,0,getWidth(),getHeight()), mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mPaint.setColor(Color.TRANSPARENT);
        int min=Math.min(getWidth()/2,getHeight()/2);
        canvas.drawCircle(getWidth()/2,getHeight()/2, min,mPaint);
        mPaint.setXfermode(null);
    }
}
