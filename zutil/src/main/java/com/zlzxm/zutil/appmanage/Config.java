package com.zlzxm.zutil.appmanage;

import android.content.Context;

/**
 * Created by zlz on 2018/3/3.
 */

public class Config {


    public  static Configurator init(Context context){


            getConfiguration().getCoreConfigs().put(ConfigType.APPLICATION_CONTEXT,context);

        return Configurator.getInstance();

    }

    //获取配置对象
    public  static Configurator  getConfiguration(){


        return  Configurator.getInstance();
    }

    //获取配置属性
    public  static  <T>T getConfig(Object key){


        return getConfiguration().getConfiguration(key);
    }

    //apliication 多次 直接提供方法

    public  static Context getApplicationContext(){


        return getConfig(ConfigType.APPLICATION_CONTEXT);
    }

}