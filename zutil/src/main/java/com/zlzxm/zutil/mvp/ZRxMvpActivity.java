package com.zlzxm.zutil.mvp;

import androidx.fragment.app.FragmentManager;

import com.trello.rxlifecycle3.LifecycleTransformer;
import com.zlzxm.zutil.ui.activity.ZBaseAbsActivity;

/**
 * Created by zlz
 * on  2019-09-11
 */
public abstract class ZRxMvpActivity<T> extends ZBaseMvpActivity<T> implements ZRxMvpView{


    @Override
    public <T> LifecycleTransformer<T> rxBindLife() {
       return bindToLifecycle();
    }


    @Override
    public FragmentManager getViewFragmentManager() {
        return super.getSupportFragmentManager();
    }
}
