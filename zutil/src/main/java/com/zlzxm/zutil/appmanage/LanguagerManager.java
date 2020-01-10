package com.zlzxm.zutil.appmanage;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.Log;

import com.zlzxm.zutil.storage.ZBaseSharePreferences;
import com.zlzxm.zutil.ui.activity.ZBaseAbsActivity;

import java.util.Locale;

/**
 * Created by zlz
 *
 * on  2019-09-05
 */
public class LanguagerManager {

    public static final String TAG = "LanguagerManager";

    public static final int  CHINESE = 1;
    public static final int  ENGLISH = 2;
    public static final int  JAPANESE = 3;


    private static Locale  SYSTEMLANGUAGER;

    private static final String key_LANGUAGER = "LANGUAGER_MANAGER";


    private LanguagerManager() {

    }

    public static  void managerLanguage(){

        ZBaseAbsActivity.supportManager();
    }

    public static  void setCurrentLanguager(Locale locale){

        SYSTEMLANGUAGER = locale;
    }

    private static void saveLanguage(Context context, int languager) {

        ZBaseSharePreferences zBaseSharePreferences = new ZBaseSharePreferences(context);

        zBaseSharePreferences.setInteger(key_LANGUAGER,languager);

    }


    public static Locale getCurrentLanguage(Context context){

        ZBaseSharePreferences zBaseSharePreferences = new ZBaseSharePreferences(context);

//        Log.e(TAG,zBaseSharePreferences.getInteger(key_LANGUAGER) + "language");
        switch (zBaseSharePreferences.getInteger(key_LANGUAGER)) {
            case -1:
                return SYSTEMLANGUAGER==null?getSystemLocale(context):SYSTEMLANGUAGER;
            case 1:
                return Locale.CHINA;
            case 2:
                return Locale.ENGLISH;
            case 3:
                return Locale.JAPAN;
            default:
                return Locale.CHINA;
        }
    }




    public static Locale getSystemLocale(Context context) {

        Locale locale;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            locale = LocaleList.getDefault().get(0);

        } else {

            locale = Locale.getDefault();
        }

        return locale;
    }

    public    static Context setLocal(Context context) {
        return updateResources(context, getCurrentLanguage(context));
    }

    private static Context updateResources(Context context, Locale locale) {
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }


    public static  void selectLanguager(Activity activity, int language){

        saveLanguage(activity,language);

        ActivityManager.getInstance().recreateAllOtherActivity(activity);
    }
}
