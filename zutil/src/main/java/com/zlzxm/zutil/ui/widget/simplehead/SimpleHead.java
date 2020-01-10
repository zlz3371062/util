package com.zlzxm.zutil.ui.widget.simplehead;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zlzxm.zutil.R;
import com.zlzxm.zutil.ui.viewhelp.ZViewHelp;

import androidx.annotation.DrawableRes;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

public class SimpleHead extends RelativeLayout implements View.OnClickListener {

    private ImageView mIvLeft = null;

    private ImageView mIvRight = null;

    private TextView mTxtTitle = null;

    private TextView mTxtLeft = null;

    private TextView mTxtRight = null;

    private LinearLayout mLlLeft = null;

    OnLeftclicklistener mItemOnclicklistener = null;

    private String mTitle  = "";

    int mLeftIcon = 0;

    int mRightIcon = 0;

    int mTitleColor = 0;

    int mLeftTipColor = 0;

    float mTitleSize = 0f;

    float mleftTipSize = 0f;

    private  String mLeftTip = "";

    private  String mRightTip = "";

    float mRightTipSize = 0f;

    int mRightTipColor = 0;

    boolean mTitleIsBold = false;


    private ImageView mIvLeftTag;

    public SimpleHead(Context context) {
        this(context,null);
    }

    public SimpleHead(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SimpleHead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_simple_head,this,true);

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.SimpleHead);

        mLeftIcon = ta.getResourceId(R.styleable.SimpleHead_leftIcon,0);

        mRightIcon = ta.getResourceId(R.styleable.SimpleHead_rightIcon,0);

        mTitleColor = ta.getColor(R.styleable.SimpleHead_titleColor,0);

        mTitle = ta.getString(R.styleable.SimpleHead_titleText);

        mTitleSize = ta.getDimensionPixelSize(R.styleable.SimpleHead_titleSize,24);

        mLeftTip = ta.getString(R.styleable.SimpleHead_leftTip);

        mLeftTipColor = ta.getColor(R.styleable.SimpleHead_leftTipColor,0);

        mleftTipSize = ta.getDimensionPixelSize(R.styleable.SimpleHead_leftTipSize,24);


        mRightTip = ta.getString(R.styleable.SimpleHead_rightTip);

        mRightTipColor = ta.getColor(R.styleable.SimpleHead_rightTipColor,0);

        mRightTipSize = ta.getDimensionPixelSize(R.styleable.SimpleHead_rightTipSize,24);

        mTitleIsBold = ta.getBoolean(R.styleable.SimpleHead_isBold,false);

        ta.recycle();

        mIvLeft = view.findViewById(R.id.ivLeft);
        mIvRight = view.findViewById(R.id.ivRight);
        mTxtTitle = view.findViewById(R.id.txtTitle);
        mTxtLeft = view.findViewById(R.id.txtLeft);
        mTxtRight = view.findViewById(R.id.txtRight);
        mLlLeft = view.findViewById(R.id.llLeft);

        mIvLeftTag = view.findViewById(R.id.ivLeftTag);

        mIvRight.setOnClickListener(this);
        mTxtTitle.setOnClickListener(this);

        if (mTitle != null && !mTitle.equals("")) {
            mTxtTitle.setText(mTitle);
            mTxtTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleSize);
            mTxtTitle.setVisibility(VISIBLE);
            if(mTitleIsBold){

                mTxtTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

            }
        }else {
            mTxtTitle.setVisibility(GONE);

        }



        if(mLeftTip != null){
            if(mLeftTipColor!= 0) {
                mTxtLeft.setTextColor(mLeftTipColor);
            }
            mTxtLeft.setVisibility(VISIBLE);
            mTxtLeft.setText(mLeftTip);
            mTxtLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX,mleftTipSize);
            mLlLeft.setOnClickListener(this);
        }

        if(mRightTip != null){
            if(mRightTipColor!= 0) {
                mTxtRight.setTextColor(mRightTipColor);
            }
            mTxtRight.setVisibility(VISIBLE);
            mTxtRight.setText(mRightTip);
            mTxtRight.setTextSize(TypedValue.COMPLEX_UNIT_PX,mRightTipSize);
            mTxtRight.setOnClickListener(this);
        }



        if(mLeftIcon != 0){
            mLlLeft.setOnClickListener(this);
            mIvLeft.setVisibility(View.VISIBLE);
            mIvLeft.setImageResource(mLeftIcon);
        }

        if(mRightIcon!=0){
            mIvRight.setImageResource(mRightIcon);
            mIvRight.setVisibility(View.VISIBLE);
        }

        if(mTitleColor != 0){

            mTxtTitle.setTextColor(mTitleColor);
        }


    }

    public void setOnItemclicklistener(OnLeftclicklistener mItemOnclicklistener) {
        this.mItemOnclicklistener = mItemOnclicklistener;
    }

    public void setRightIconWidthHeight(int width,int height){

        ((View)mIvRight.getParent()).setVisibility(View.VISIBLE);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mIvRight.getLayoutParams();
        layoutParams.width= ZViewHelp.dpTopx(getContext(),width);
        layoutParams.height= ZViewHelp.dpTopx(getContext(),height);

        mIvRight.setLayoutParams(layoutParams);



    }

    public void setTitle(String title){

        mTitle = title;

        mTxtTitle.setText(mTitle);
    }

    public String getTitle(){

        return  mTitle;
    }

    public void setLeftImgRec(int id){

        if(mIvLeft != null){

            mIvLeft.setImageResource(id);
        }


    }

    @Override
    public void onClick(View v) {

        if(mItemOnclicklistener!= null){

            if(v.equals(mLlLeft)){

                mItemOnclicklistener.onLeftItemClick();

            }else if(v.equals(mIvRight)){

                if(mItemOnclicklistener instanceof OnItemclicklistener)

                    ((OnItemclicklistener)mItemOnclicklistener).onRightItemClick();

            }else if(v.equals(mTxtTitle)){

                if(mItemOnclicklistener instanceof OnItemclicklistener)

                    ((OnItemclicklistener)mItemOnclicklistener).onTitleClick();

            }else if(v.equals(mTxtRight)){

                if(mItemOnclicklistener instanceof OnItemclicklistener)

                    ((OnItemclicklistener)mItemOnclicklistener).onRightTxtClick();
            }
        }




    }


    public void setLeftTagBg(@DrawableRes int id){

        mIvLeftTag.setImageResource(id);

    }

    public void showLeftTag(boolean isShow){

        if(isShow){

            mIvLeftTag.setVisibility(View.VISIBLE);

        }else {
            mIvLeftTag.setVisibility(View.GONE);

        }

    }

    private int dpTopx(int dp){

        return (int) TypedValue.applyDimension(COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    }

}
