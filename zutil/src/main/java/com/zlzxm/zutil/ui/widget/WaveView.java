package com.zlzxm.zutil.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorRes;


/**
 * Created by zlz
 * on  2019-09-06
 */
public class WaveView extends View  {


    public static final String  TAG = "WaveView";

    private int mWidth;

    private int mHeight;

    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;

    private Path path2 = new Path();

    Bitmap bitmap = null;

    float start = 0;

    private int mWaveColor =Color.GRAY;

    private boolean isAnimation = true;

    public void setWaveColor(@ColorRes int waveColor) {

        this.mWaveColor = getResources().getColor(waveColor);
        if(mPaint2!=null){

            mPaint2.setColor(mWaveColor);
        }

    }



    public void stop(){

        isAnimation  = false;
    }

    public void start(){

        isAnimation  = true;

        postInvalidate();
    }


    public WaveView(Context context) {
        this(context,null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setColor(mWaveColor);
        mPaint1.setStyle(Paint.Style.FILL);


        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setColor(mWaveColor);
        mPaint2.setStyle(Paint.Style.FILL);

        mPaint3 = new Paint();
        mPaint3.setAntiAlias(true);
        mPaint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint3.setColor(Color.BLACK);
        mPaint3.setStyle(Paint.Style.FILL);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        bitmap =makeDest();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float zq = (float) (Math.PI * 2 / mWidth);
        path2.reset();
        path2.moveTo(0, mHeight);
        path2.lineTo(0, mHeight/2);
        mPaint2.setXfermode(null);
        for (int i = 0; i <= mWidth; i+=1) {

            float y  = (float) ( Math.sin(zq * i + Math.PI   +start))*20;

            path2.lineTo(i,mHeight/2-y);
        }

        path2.lineTo(mWidth, mHeight);
        path2.close();
        canvas.drawPath(path2, mPaint2);


        int layerId = canvas.saveLayer(0, 0, mWidth, mWidth, mPaint3, Canvas.ALL_SAVE_FLAG);
        canvas.drawCircle(mWidth/2,mWidth/2,mWidth/2,mPaint1);
        canvas.restoreToCount(layerId);

        start += (float) (  Math.PI  / 10);

        if(start >  (float) (  Math.PI * 2)){

            start = 0;
        }

        if(isAnimation) {
            postInvalidateDelayed(130);
        }

    }




    private Bitmap makeDest(){

        Bitmap bitmap = Bitmap.createBitmap(mWidth,mHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(mWidth/ 2,mWidth/2,mWidth/2,paint);

        return  bitmap;
    }



}
