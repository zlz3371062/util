package com.zlzxm.zutil.appmanage;


import com.blankj.utilcode.util.SPUtils;
import com.jiuling.baselibrary.Constants;
import com.jiuling.baselibrary.Tool;
import com.jiuling.baselibrary.UserInfoModel;
import com.zlzxm.zutil.appmanage.Config;
import com.zlzxm.zutil.appmanage.ConfigType;
import com.zlzxm.zutil.common.StringHelp;
import com.zlzxm.zutil.storage.ZBaseSharePreferences;

/**
 * Created by zlz
 * on  2019/7/14
 */
public class AppManager{

    public final static boolean isRelease = false;

    private final static String TAG_TOKEN = "TOKEN";

//    private final static String TAG_TOKEN_TIME = "TOKEN_TIME";

    private final static String TAG_TOKEN_ID = "TOKEN_ID";

    public final static String JIGUANG = "JIGUANG";

//    public final static String TAG_SESSION = "SESSION";


    private static  final ZBaseSharePreferences zBaseSharePreferences =
            new ZBaseSharePreferences(Config.getConfig(ConfigType.APPLICATION_CONTEXT));

    private  static String TOKEN = null;

    private  static String ID ;


    public static void login(String token,String id) {

        zBaseSharePreferences.setString(TAG_TOKEN,token);
//        zBaseSharePreferences.setLong(TAG_TOKEN_TIME,time);
        zBaseSharePreferences.setString(TAG_TOKEN_ID,id);
        Constants.accessToken = token;
        SPUtils.getInstance(Constants.spLoginFileName).put(Constants.spLoginToken, token);
        SPUtils.getInstance(Constants.spLoginFileName).put(Constants.spLoginId, id+"");
//        Tool.saveUserInfo(dataBean);
    }

    public static void saveUserInfo(UserInfoModel.DataBean.MemberBean data){
        Tool.saveUserInfo(data);
    }

    public static void exit() {
        TOKEN = null;
        ID=null;
        Tool.saveUserInfo(null);
        Tool.saveUserStatus(null);
        zBaseSharePreferences.setString(TAG_TOKEN,null);
        zBaseSharePreferences.setString(TAG_TOKEN_ID,null);
    }

    public static boolean isLogin() {




        return  getToken()!=null && getId() != null;
    }


    public static String getToken(){


        if(TOKEN !=null){


            return  TOKEN;
        }


        TOKEN =  zBaseSharePreferences.getString(TAG_TOKEN);

        return  TOKEN;

    }

    public static String getId(){

        if(!StringHelp.isEmpty(ID)){


            return  ID;
        }

        ID =  zBaseSharePreferences.getString(TAG_TOKEN_ID);


        return  ID;
    }


    public  static String getDeviceId() {

        return  zBaseSharePreferences.getString(JIGUANG);
    }

}

