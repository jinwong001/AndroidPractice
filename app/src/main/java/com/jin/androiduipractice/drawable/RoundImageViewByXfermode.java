package com.jin.androiduipractice.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

import com.jin.androiduipractice.R;

import java.lang.ref.WeakReference;

/**
 * Created by wanny-n1 on 2017/8/29.
 * <p>
 * link http://blog.csdn.net/lmj623565791/article/details/42094215
 */
public class RoundImageViewByXfermode extends AppCompatImageView {
    /**
     * 图片的类型，圆形or圆角
     */
    private int type;
    private int mBorderRadius;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;

    private Paint mPaint;
    private int mRadius;
    private RectF mRectF;
    private WeakReference<Bitmap> mWeakBitmap;
    private Bitmap mMaskBitmap;
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);


    public RoundImageViewByXfermode(Context context) {
        this(context, null);
    }

    public RoundImageViewByXfermode(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageViewByXfermode(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        mBorderRadius = array.getDimensionPixelSize(R.styleable.RoundImageViewByXfermode_borderRadius,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
        type = array.getInt(R.styleable.RoundImageViewByXfermode_type, TYPE_CIRCLE);
        Log.i("0000", "type:" + type);
        array.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (type == TYPE_CIRCLE) {
            int width = Math.min(getMeasuredWidth(), getMeasuredHeight());
            setMeasuredDimension(width, width);
        }
    }

    @Override
    public void invalidate() {
        mWeakBitmap = null;
        if (mMaskBitmap != null) {
            mMaskBitmap.recycle();
            mMaskBitmap = null;
        }
        super.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = mWeakBitmap == null ? null : mWeakBitmap.get();
        if (bitmap == null || bitmap.isRecycled()) {
            Drawable drawable = getDrawable();
            if (drawable == null) {
                // super.onDraw(canvas);
                return;
            }
            int dWidth = drawable.getIntrinsicWidth();
            int dHeight = drawable.getIntrinsicHeight();

            float scale = 1.0f;
            if (type == TYPE_CIRCLE) {
                scale = getWidth()*1.0f / Math.min(dWidth, dHeight);
            } else {
                scale = Math.max(getWidth() * 1.0f / dWidth, getHeight() * 1.0f / dHeight);
            }


            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas drawCanvas = new Canvas(bitmap);
            // 根据缩放比例，设置bounds,相当缩放图片了
            drawable.setBounds(0, 0, (int) (scale * dWidth), (int) (scale * dHeight));
            // 得到设置的图片
            drawable.draw(drawCanvas);

            mPaint.reset();
            mPaint.setFilterBitmap(false);
            mPaint.setXfermode(mXfermode);

            if (mMaskBitmap == null || mMaskBitmap.isRecycled()) {
                mMaskBitmap = getBitmap();
            }

            // 设置图片上
            //DST_IN  两层绘制交集，显示下层
            drawCanvas.drawBitmap(mMaskBitmap, 0, 0, mPaint);
            mPaint.setXfermode(null);

            canvas.drawBitmap(bitmap, 0, 0, null);
            mWeakBitmap = new WeakReference<Bitmap>(bitmap);
        } else {
            mPaint.setXfermode(null);
            canvas.drawBitmap(bitmap, 0, 0, mPaint);
        }
    }

    // 绘制圆形或者 圆角矩形的套子mask
    private Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        if (type == TYPE_CIRCLE) {
            canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, paint);
        } else {
            canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), mBorderRadius, mBorderRadius, paint);
        }

        return bitmap;
    }


}
