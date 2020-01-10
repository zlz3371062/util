package com.zlzxm.zutil.mvp;

import androidx.fragment.app.FragmentManager;

import com.trello.rxlifecycle3.LifecycleTransformer;

/**
 * Created by zlz
 * on  2019-09-11
 */
public interface ZRxMvpView extends ZBaseMvpView{

    <T> LifecycleTransformer<T> rxBindLife();


    FragmentManager getViewFragmentManager();
}
