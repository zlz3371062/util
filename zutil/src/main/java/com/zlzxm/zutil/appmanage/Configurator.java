package com.zlzxm.zutil.appmanage;

import java.util.HashMap;

/**
 * Created by zlz on 2018/3/3.
 */

public class Configurator {


    private static final HashMap<Object,Object> CONFIG = new HashMap<>();

    private Configurator(){

        CONFIG.put(ConfigType.CONFIG_REAY,false);

    }


    private static class  Holder{

        private static  final  Configurator CONFIGURATOR = new Configurator();


    }

      static Configurator getInstance(){


        return  Holder.CONFIGURATOR;
    }



    final HashMap<Object,Object> getCoreConfigs(){

        return CONFIG;
    }


    public <T> Configurator config(ConfigType configType,T obj){

        CONFIG.put(configType,obj);

        return this;
    }


    public  void config(){

        CONFIG.put(ConfigType.CONFIG_REAY,true);

    }

    final <T>T getConfiguration(Object key){

        checkReady();

        final Object value = CONFIG.get(key);

        if(value == null){


            throw  new RuntimeException(key.toString() + "config is null");
        }


        return (T) CONFIG.get(key);

    }

    //核对配置有没有完成
    private  void checkReady(){

        boolean isReady = (boolean) CONFIG.get(ConfigType.CONFIG_REAY);

        if(!isReady){

            throw  new RuntimeException("config is not init");

        }

    }
}