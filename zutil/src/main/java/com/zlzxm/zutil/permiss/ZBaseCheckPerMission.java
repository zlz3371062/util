package com.zlzxm.zutil.permiss;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class ZBaseCheckPerMission {

    public static final  String TAG = "ZBaseCheckPerMission";

    public  static String[] check(Context context,String... params){

        final List<String> permissList = new ArrayList<>();

        for (String param : params){

            if(!EasyPermissions.hasPermissions(context,param)){

                Log.e(TAG,param + "no permission");
                permissList.add(param);

            }

        }

        String[] permission =  permissList.toArray(new String[permissList.size()]);

        return  permission;
    }
}