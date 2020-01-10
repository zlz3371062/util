package com.zlzxm.zutil.mvp;

public class ZBaseMvpPresenter<T extends ZBaseMvpView> {

    protected T mView;

    public ZBaseMvpPresenter(T mView) {
        this.mView = mView;
    }
}
