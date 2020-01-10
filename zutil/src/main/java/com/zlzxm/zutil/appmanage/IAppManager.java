package com.zlzxm.zutil.appmanage;

/**
 * Created by zlz
 * on  2019/6/21
 */
public interface IAppManager {

    void login(long id,String token);

    void exit();

    boolean isLogin();

    String getAccesstoken();
}
