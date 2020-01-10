package com.zlzxm.zutil.mvp;

import com.zlzxm.zutil.ui.activity.ZBaseAbsSimpleActivity;

/**
 * Created by zlz
 * on  2019/6/21
 */
public abstract class ZBaseMvpActivity<T> extends ZBaseAbsSimpleActivity implements ZBaseMvpView {


    protected T mPresenter;

    @Override
    public void showMessage(String msg) {

        showToast(msg);
    }

}
