package com.zlzxm.zutil.common;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;

/**
 * Created by zlz
 * on  2019-09-26
 */
public class CameraHelp {


    public static boolean openLight(Context context,boolean isOpen){

        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){

            try {
                //获取CameraManager
                CameraManager mCameraManager = (CameraManager)context.getSystemService(Context.CAMERA_SERVICE);
                //获取当前手机所有摄像头设备ID
                String[] ids  = mCameraManager.getCameraIdList();
                for (String id : ids) {
                    CameraCharacteristics c = mCameraManager.getCameraCharacteristics(id);
                    //查询该摄像头组件是否包含闪光灯
                    Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    /*
                     * 获取相机面对的方向
                     * CameraCharacteristics.LENS_FACING_FRONT 前置摄像头
                     * CameraCharacteristics.LENS_FACING_BACK 后只摄像头
                     * CameraCharacteristics.LENS_FACING_EXTERNAL 外部的摄像头
                     */
                    Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
                    if (flashAvailable != null && flashAvailable
                            && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                        //打开或关闭手电筒
                        mCameraManager.setTorchMode(id, isOpen);
                        return isOpen;
                    }
                }

            } catch (CameraAccessException e) {
                e.printStackTrace();
                return !isOpen;
            }

        }else {

            Camera camera = Camera.open();
            Camera.Parameters  parameters = camera.getParameters();
            if (isOpen) {

                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);// 开启
                camera.setParameters(parameters);

            } else {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);// 关闭
                camera.setParameters(parameters);
                camera.release();
            }

            return  isOpen;

        }


        return false;
    }
}
