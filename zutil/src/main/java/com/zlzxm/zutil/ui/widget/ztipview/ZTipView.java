package com.zlzxm.zutil.ui.widget.ztipview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.zlzxm.zutil.R;
import com.zlzxm.zutil.common.ObjectHelp;

/**
 * Created by zlz
 * on  2019/6/25
 */
public class ZTipView extends RelativeLayout {

    private static final String TAG ="ZTipView";

    private TextView mTxtTip ;

    private SpinKitView mSkLoading;

    private String mLoadingTip = "正在加载，请稍后";

    private String mEmptyTip = "暂时没有数据啊";

    public ZTipView(Context context) {
        this(context,null);
    }

    public ZTipView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZTipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_simple_tip, this, true);

        mTxtTip = view.findViewById(R.id.txtTip);

        mSkLoading = view.findViewById(R.id.skLoading);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        ObjectHelp.Empty empty = () -> setBackgroundColor(Color.WHITE);

        ObjectHelp.isEmpty(getParent(),empty,parent->{
            ViewGroup parentView = (ViewGroup) parent;

            ObjectHelp.isEmpty(parentView.getBackground(),empty, this::setBackground);

        });
    }

    public void setTextTip(String tip){

        mTxtTip.setText(tip);
    }

    public void showLoading(boolean isShow){

        if (isShow){

            mSkLoading.setVisibility(VISIBLE);
        }else {

            mSkLoading.setVisibility(GONE);
        }
    }

    public ZTipView setLoadingTip(String tip){

        mLoadingTip = tip;

        return this;
    }

    public ZTipView tipModel(String tip){
        show();
        showLoading(false);
        setTextTip(tip);
        return this;
    }

    public ZTipView emptyModel(String tip){
        show();
        mEmptyTip = tip;
        showLoading(false);
        setTextTip(mEmptyTip);

        return this;
    }

    public ZTipView emptyModel(){
        show();
        showLoading(false);
        setTextTip(mEmptyTip);

        return this;
    }
    public ZTipView loadingModel(String tip){
        show();
        mLoadingTip = tip;

        loadingModel();
        return this;

    }

    public ZTipView loadingModel(){
        show();
        showLoading(true);
        setTextTip(mLoadingTip);

        return this;

    }


    public void disMiss(){


        setVisibility(GONE);

    }

    public void show(){

        setVisibility(VISIBLE);

    }

}
