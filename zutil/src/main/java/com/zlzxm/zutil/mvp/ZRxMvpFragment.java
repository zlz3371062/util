package com.zlzxm.zutil.mvp;

import androidx.fragment.app.FragmentManager;

import com.trello.rxlifecycle3.LifecycleTransformer;

/**
 * Created by zlz
 * on  2019-09-12
 */
public abstract class ZRxMvpFragment<T> extends ZBaseMvpFragment<T> implements ZRxMvpView{


    @Override
    public <T> LifecycleTransformer<T> rxBindLife() {
        return bindToLifecycle();
    }

    @Override
    public FragmentManager getViewFragmentManager() {
        return getChildFragmentManager();
    }
}
