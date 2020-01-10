package com.zlzxm.zutil.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by zlz
 * on  2019-09-24
 */
public class VersionHelp {


    public  static  String getVersionName(Context context){

        try {
            PackageInfo packageInfo  = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(),0);

           return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

            return null;
        }


    }

}
