package com.zlzxm.zutil.ui.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import java.io.Serializable;

public class ZStartHelp {

    /**
     *  普通转场
     * @param activity  启动acitivity
     * @param cls       目标activity
     */
    public static void startActivity(Activity activity, Class<?> cls){

        startActivityWithAnimation(activity,cls,0);
    }


    /**
     * 1秒动画的 转场动画
     * @param activity  启动activity
     * @param cls      目标activity
     * @param model    动画模式
     */
    public static  void startActivityWithAnimation(Activity activity, Class<?> cls,int model){

        startActivity( activity, cls, model,0);
    }

    /**
     * 自定义动画时间的转场动画
     * @param activity  启动activity
     * @param cls    目标activity
     * @param model  动画模式
     * @param millis   动画时间
     */
    public static void startActivity(Activity activity, Class<?> cls,int model,int millis){


        Intent intent = new Intent(activity, cls);

        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP && model != 0) {

            intent.putExtra(ZStartModel.START_MODEL, model);

            if(millis > 0){

                intent.putExtra(ZStartModel.START_TIME, millis);

            }else {

                intent.putExtra(ZStartModel.START_TIME, 1000);
            }

            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());

        }else {


            activity.startActivity(intent);

        }

    }



    /**
     * 携带对象的转场
     * @param activity    启动activity
     * @param cls         目标activity
     * @param t              携带的数据
     * @param <T>       携带的数据类型
     *  没有设计好暂时不用
     */
    private static   <T extends Serializable> void  startActivityWithObject(Activity activity, Class<?> cls, T t){

        Intent intent = new Intent(activity,cls);

        intent.putExtra(cls.getSimpleName(),t);

        activity.startActivity(intent);

    }




    public   static  void startActivity(Context context, Intent intent, Bundle bundle){

        if(bundle != null){

            intent.putExtras(bundle);
        }

        context.startActivity(intent);

    }

    public   static  void startActivity(Context context,Class<?> to, Bundle bundle){

        Intent intent = new Intent(context,to);

        startActivity(context,intent,bundle);
    }

    public   static  void startActivity(Context context,Intent intent ){

        startActivity(context,intent,null);

    }

    public   static  void startActivity(Context context,Class<?> to ){

        Intent intent = new Intent(context,to);

        startActivity(context,to,null);

    }

}
