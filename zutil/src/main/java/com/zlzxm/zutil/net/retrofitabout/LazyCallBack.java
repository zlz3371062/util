package com.zlzxm.zutil.net.retrofitabout;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zlz
 * on  2019/6/21
 */
public abstract class LazyCallBack<T> implements Callback<T>{

    public abstract void onLazy();


    public LazyCallBack(){

    }



    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        onLazy();

    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        onLazy();
    }

}
