package com.zlzxm.zutil.net.retrofitabout;


import com.zlzxm.zutil.appmanage.Config;
import com.zlzxm.zutil.appmanage.ConfigType;
import com.zlzxm.zutil.net.retrofitabout.RetrofitCreator;

import retrofit2.Retrofit;

public class RetrofitClient {

    public static String BASE_URL = Config.getConfig(ConfigType.SERVICE_ADDRESS);

    private static Retrofit RETROFIT = null;


    private RetrofitClient(){

    }

    public static Retrofit get(){

        if(RETROFIT == null){

            RETROFIT = RetrofitCreator.creatorWithGsonSupportRx(BASE_URL);

        }

        return RETROFIT;
    }

    public  static  <T>T  create(Class<T> service){

        if(RETROFIT == null) {

            get();
        }

        return RETROFIT.create(service);
    }


}
