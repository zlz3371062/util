package com.zlzxm.zutil.ui.widget.tabview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zlzxm.zutil.R;
import com.zlzxm.zutil.ui.viewhelp.ZViewHelp;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by zlz
 * on  2019/6/29
 */
public class ZTabView extends LinearLayout implements ViewPager.OnPageChangeListener, View.OnClickListener {


    private final static int TAB_TAG = 1;

    interface OnPageChangeListener{

        void onPageScrolled(int i, float v, int i1);

        void onPageSelected(int i);

        void onPageScrollStateChanged(int i);

    }

    public interface OnTabClickLintener{

        boolean click(int i);

    }

    private int mCurrentPage = 0;

    private ViewPager mViewPager = null;

    private  boolean isBindToViewPager = false;

    private int mTabViewWidth = 0;

    private int mTabViewHeight = 0;

    private Paint mPaint = null;

    private float mLineStartX;

    private float mLineStartY;

    private float mHeight = 0;

    private String mTabTitle[]  ;

    private boolean isFirstMeasure = true;

    /*
        开放属性

     */

    private int mLineColor = Color.RED;

    private int mTitleColor = Color.BLACK;

    private  int mTitlebg = 0;

    private float mTitleSize = 12.0f;//dp


    private float mLineTag = 0.6f;

    private float mLineWidth = 0;

    private float mLineHeight = 0;

    private int mVisibleTabCount = 2;

    private int mSelectTextColor = 0;

    private boolean isSupportSelectBold = true;

    private OnPageChangeListener mOnPageChangeListener =null;

    private OnTabClickLintener onTabClickLintener = null;

    public void setOnTabClickLintener(OnTabClickLintener onTabClickLintener) {
        this.onTabClickLintener = onTabClickLintener;
    }

    public void setLineColor(@ColorInt int color){

        mLineColor = color;
    }


    public void setTitleColor(@ColorInt int color){

        mTitleColor = color;

    }

    public void setTitlebg(@DrawableRes int id ){

        mTitlebg = id;
    }


    public void setSelectTextColor(@ColorInt int color){


        mSelectTextColor =color;
    }
    /*
      dp
     */
    public void setTitleSize(int dp){

        this.mTitleSize = dp;

    }
    public void setLineTag(float tag){


        this.mLineTag = tag;

    }

    /*
        dp
     */
    public void setLineWidth(float lineWidth ){

        this.mLineWidth = (float) ZViewHelp.dpTopx(getContext(),lineWidth);

    }
    public void setLineHeight(float lineHeight){

        this.mLineHeight = (float) ZViewHelp.dpTopx(getContext(),lineHeight);

    }

    public void setVisibleTabCount(int count){

        this.mVisibleTabCount = count;

        requestLayout();

    }



    public  void  toolgetherViewPager(ViewPager viewPager, OnPageChangeListener onPageChangeListener){

        this.mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);

        isBindToViewPager = true;

        this. mOnPageChangeListener =onPageChangeListener;
    }

    public  void  toolgetherViewPager(ViewPager viewPager){


        toolgetherViewPager(viewPager,null);
    }

    public void unBindToViewpager(){

        isBindToViewPager = false;
        mViewPager.addOnPageChangeListener(null);
        mViewPager = null;

    }


    public ZTabView(Context context) {
        this(context,null);

    }

    public ZTabView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        setWillNotDraw(false);

    }




    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        canvas.drawLine(mLineStartX,mLineStartY,mLineStartX+mLineWidth ,mLineStartY ,mPaint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mTabViewWidth = w/ mVisibleTabCount;

        mTabViewHeight =  h;

        mHeight = mTabViewHeight;

        init();

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        removeAllViews();

        setTabView(mTabTitle);

        for (int i = 0 ; i<getChildCount();i++){

            LinearLayout.LayoutParams layoutParams = (LayoutParams) getChildAt(i).getLayoutParams();

            layoutParams.height = LayoutParams.MATCH_PARENT;

            layoutParams.width = MeasureSpec.getSize(widthMeasureSpec) / mVisibleTabCount;

        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }



    private void init() {
        mPaint = new Paint();

        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(mLineColor);

        if(mLineHeight == 0 ){

            mLineHeight = ZViewHelp.dpTopx(getContext(),2);
        }

        mPaint.setStrokeWidth(mLineHeight);

        mPaint.setStrokeCap(Paint.Cap.ROUND);

        if(mLineWidth == 0 ){

            mLineWidth = (int) (mTabViewWidth*mLineTag);
        }

        mLineStartX = mTabViewWidth / 2 - mLineWidth / 2;

        mLineStartY = mHeight - mLineHeight / 2;

    }


    public void setTabTitle(String[] tabTitle){

        this.mTabTitle = tabTitle;
    }

    public void updateTitle(String[] tabTitle){

        this.mTabTitle = tabTitle;

        int count = getChildCount();

        if(count == 0){


            return;
        }

        for (int i = 0; i <count ; i++) {

            View targetView = getChildAt(i);

            if(targetView instanceof  TextView){

                ((TextView) targetView).setText(tabTitle[i]);
            }

        }
    }

    public void seletIsBold(boolean isSelectBold){

        this.isSupportSelectBold = isSelectBold;
    }

    public void setTabView(String[] tabTitle){

        removeAllViews();

        mTabTitle = tabTitle;


        if(mTabTitle == null){

            return;
        }

        for (int i = 0 ; i < mTabTitle.length;i++){

            TextView textView  = new TextView(this.getContext());

            textView.setGravity(Gravity.CENTER);
            textView.setText(tabTitle[i]);
            textView.setOnClickListener(this);
            textView.setTextColor(mTitleColor);
            textView.setTag(R.id.zbv_tab,i);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,
                    mTitleSize);

            if(i == 0 && mSelectTextColor!=0){

                textView.setTextColor(mSelectTextColor);

                if(isSupportSelectBold){


                    TextPaint textPaint = textView.getPaint();

                    textPaint.setFakeBoldText(true);
                }

            }

            this.addView(textView);
        }

    }



    @Override
    public void onClick(View v) {

        int index = (Integer) v.getTag(R.id.zbv_tab);

        if(onTabClickLintener!=null&& !onTabClickLintener.click(index)) {

            if (isBindToViewPager) {

                mViewPager.setCurrentItem(index);

            }else {

                onPageSelected((Integer) v.getTag(R.id.zbv_tab));

                float startStartX = mLineStartX;

                float endStartX  = mTabViewWidth / 2 - mLineWidth / 2 + index * mTabViewWidth;

                ValueAnimator valueAnimator = ValueAnimator.ofFloat(startStartX,endStartX);

                valueAnimator.setDuration(200);

                valueAnimator.start();

                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {

                        mLineStartX = (float) valueAnimator.getAnimatedValue();
                        invalidate();
                    }
                });

            }
        }
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

        //position 当前所在页面
        //positionOffset 当前所在页面偏移百分比
        //positionOffsetPixels 当前所在页面偏移量


        if(isBindToViewPager){

            mLineStartX =   mTabViewWidth / 2 - mLineWidth / 2 + i * mTabViewWidth + v * mTabViewWidth;

            invalidate();

            if(this.mOnPageChangeListener!= null){

                mOnPageChangeListener.onPageScrolled(i,v,i1);

            }

        }

    }

    @Override
    public void onPageSelected(int i) {


        if(mSelectTextColor != 0){

            int childCount = getChildCount();

            for (int j = 0; j < childCount; j++) {

                TextView  textView = (TextView) getChildAt(j);


                if(textView != null){

                    if(j != i){

                        textView.setTextColor(mTitleColor);
                        if(isSupportSelectBold) {
                            TextPaint textPaint = textView.getPaint();
                            textPaint.setFakeBoldText(false);
                        }

                    }else {

                        textView.setTextColor(mSelectTextColor);
                        if(isSupportSelectBold) {
                            TextPaint textPaint = textView.getPaint();
                            textPaint.setFakeBoldText(true);
                        }

                    }

                }


            }



        }




        if(isBindToViewPager){

//            mLineStartX  =   mLineStartX + i * mTabViewWidth ;

//            invalidate();


            if(mCurrentPage < i && (getScrollX()+mVisibleTabCount * mTabViewWidth) < mTabTitle.length * mTabViewWidth){

                this.scrollBy(mTabViewWidth,0);

            }

            if(mCurrentPage>i && getScrollX()>0){

                scrollBy(-mTabViewWidth,0 );
            }
            mCurrentPage= i;

            if(this.mOnPageChangeListener!= null){

                mOnPageChangeListener.onPageSelected(i);

            }

        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

        if(isBindToViewPager){

            if(this.mOnPageChangeListener!= null){

                mOnPageChangeListener.onPageScrollStateChanged(i);

            }

        }
    }
}
