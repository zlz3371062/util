package com.zlzxm.zutil.ui.activity;

import android.content.Intent;

import com.zlzxm.zutil.mvp.ZRxMvpActivity;

import java.util.List;

import androidx.annotation.NonNull;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public abstract class ZBaseAbsPermissionActivity<T> extends ZRxMvpActivity<T>
        implements  EasyPermissions.RationaleCallbacks,
        EasyPermissions.PermissionCallbacks{

    private static final String Tag = "PermissionActivity";

    private String[] mArrPermission;

    private int mRequestCode;

    public   abstract  void doWithPermission(int requestCode);

    public  abstract  void  doWithoutPermission(int requestCode);

    protected  void  requestPermission( String[] permission,int requestCode){

        mArrPermission = permission;

        mRequestCode = requestCode;

        if (EasyPermissions.hasPermissions(this, mArrPermission)) {

            doWithPermission(mRequestCode);

        } else {

            EasyPermissions.
                    requestPermissions(this,"app需要权限，是否需要打开权限,取消将不能进入app",requestCode,permission);

        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {

            if (EasyPermissions.hasPermissions(this, mArrPermission)) {

                doWithPermission(mRequestCode);

            }else {

                doWithoutPermission(mRequestCode);

            }

        }
    }


    @Override
    public void onRationaleAccepted(int requestCode) {


    }

    @Override
    public void onRationaleDenied(int requestCode) {
    }


    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {


        if(EasyPermissions.somePermissionPermanentlyDenied(this, perms)){
            new AppSettingsDialog
                    .Builder(this)
                    .setTitle("权限请求")
                    .setRationale("您的权限已经设置为拒绝,请前往权限中心打开权限")
                    .setPositiveButton("好")
                    .setNegativeButton("取消")
                    .build()
                    .show();
        }
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.hasPermissions(this, mArrPermission)) {

            doWithPermission(mRequestCode);

        }else {

            doWithoutPermission(mRequestCode);

        }
    }
}
