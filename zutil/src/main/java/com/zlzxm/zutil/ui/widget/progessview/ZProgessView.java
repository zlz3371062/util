package com.zlzxm.zutil.ui.widget.progessview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.ColorRes;

import com.zlzxm.zutil.R;

/**
 * Created by zlz
 * on  2019-09-04
 */
public class ZProgessView extends View {

    private static final int TIME_RUN= 5;

    private int mWidth;

    private int mHeight;

    private Paint mPaint;

    private float mProgess = 0;

    private Rect mRect = new Rect();

    private float mAnmationProgess = 0;

    private boolean isAnimation = false;

    private int mPaintColor = 0;

    public void setAnimation(boolean animation) {
        isAnimation = animation;
    }

    public void setProgess(float mProgess) {
        mAnmationProgess = 0;
        this.mProgess = mProgess;
        invalidate();
    }

    public void setPaintColor(@ColorRes int mPaintColor) {
        this.mPaintColor = mPaintColor;
        if(mPaint == null){
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
        }

        mPaint.setColor(getContext().getResources().getColor(mPaintColor));
    }

    public ZProgessView(Context context) {
        this(context,null);
    }

    public ZProgessView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZProgessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ZProgessView);

        int color  = ta.getResourceId(R.styleable.ZProgessView_progessColor,0);


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        if(color == 0) {

            mPaint.setColor(Color.BLACK);

        }else {

            mPaint.setColor(context.getResources().getColor(color));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("zlz","onMeasure");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        mRect.top = 0;
        mRect.left = 0;
        mRect.right = mWidth;
        mRect.bottom = mHeight;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("zlz","onDraw");
        if(mProgess>0) {

            if (isAnimation) {

                canvas.drawRect(mRect, mPaint);

                if(mAnmationProgess<1)
                    postDelayed(runnable,TIME_RUN);

            } else {

                mRect.top = (int)(mHeight - mHeight*mProgess);

                canvas.drawRect(mRect, mPaint);

            }
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            mRect.top = (int)(mHeight - mAnmationProgess*mHeight*mProgess);


            mAnmationProgess += 0.01;

            if(mAnmationProgess <= 1 ){

                invalidate();
            }

        }
    };


}
