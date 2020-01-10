package com.zlzxm.zutil.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * 由于文件的唯一性 所以是在同一个文件中操作
 */

public class ZBaseSharePreferences {

    private  final SharedPreferences mSharedPreferences;

    public ZBaseSharePreferences(Context context) {

        this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public  void setBoolean(String key ,boolean val) {
        mSharedPreferences
                .edit()
                .putBoolean(key, val)
                .apply();
    }

    public  boolean getBoolean(String key) {
        return  mSharedPreferences.getBoolean(key,false);
    }

    public   void setString(String key,String value){


        mSharedPreferences
                .edit()
                .putString(key, value)
                .apply();
    }

    public  String getString (String key){



        return mSharedPreferences.getString(key,null);
    }

    public   void setInteger(String key,int value){

        mSharedPreferences
                .edit()
                .putInt(key, value)
                .apply();
    }

    public int getInteger(String key){

        return  mSharedPreferences
                .getInt(key,-1);
    }

    public void setLong(String key,long value){

          mSharedPreferences.edit().putLong(key,value).apply();
    }

    public long getLong(String key){

        return mSharedPreferences
                .getLong(key, -1);
    }
}