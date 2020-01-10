package com.zlzxm.zutil.mvp;

import android.widget.Toast;

import com.trello.rxlifecycle3.components.support.RxFragment;

/**
 * Created by zlz
 * on  2019/6/21
 */
public class ZBaseMvpFragment<T> extends RxFragment implements ZBaseMvpView {

   protected Toast mToast = null;

    protected  T mPresenter = null;


    @Override
    public void showMessage(String msg) {


        if(mToast != null){


            mToast.setText(msg);

        }else {

            mToast = Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT);

        }

        mToast.show();

    }


}
