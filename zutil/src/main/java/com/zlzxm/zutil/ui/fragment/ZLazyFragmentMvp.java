package com.zlzxm.zutil.ui.fragment;

import android.os.Bundle;

import com.zlzxm.zutil.mvp.ZBaseMvpFragment;

import androidx.annotation.Nullable;

/**
 *
 *
 */
public abstract  class ZLazyFragmentMvp<T>  extends ZBaseMvpFragment<T> {

    private  boolean isDataLoad = false;

    private boolean isShow = false;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(!isDataLoad && isShow){
            lazyLoad();
            isDataLoad = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isShow = isVisibleToUser;
        if(isVisibleToUser && isVisible() && !isDataLoad ){
            lazyLoad();
            isDataLoad = true;
        }

    }

    protected  abstract void lazyLoad();


}