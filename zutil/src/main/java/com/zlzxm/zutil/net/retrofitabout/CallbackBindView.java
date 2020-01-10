package com.zlzxm.zutil.net.retrofitabout;

import com.zlzxm.zutil.mvp.ZBaseMvpView;

import retrofit2.Call;

/**
 * Created by zlz
 * on  2019/6/21
 */
public abstract class CallbackBindView<T> extends LazyCallBack<T> {

    private final ZBaseMvpView mZBaseMvpView;

    public CallbackBindView(ZBaseMvpView mZBaseMvpView) {
        this.mZBaseMvpView = mZBaseMvpView;
    }

    @Override
    public void onLazy() {

    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        super.onFailure(call, throwable);
        mZBaseMvpView.showMessage("程序出错了");
    }
}
