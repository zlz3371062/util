package com.zlzxm.zutil.ui.widget.photo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.io.File;

import androidx.core.content.FileProvider;

/**
 * Created by zlz on 2018/7/4.
 *
 *  获取图片的方式
 */

public class ZGetPhoto {


    public static Uri takePhoto(Activity activity, File file, int request,String authority){

        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri uri = null;

        //兼容7.0及以上的写法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            //为了解耦  没有使用FileProvider的方式 把file类型uri 转换成 content 类型的uri
             uri =  FileProvider.getUriForFile(activity.getApplication(), authority, file);

        } else {

            uri = Uri.fromFile(file);


        }

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(intent, request);

        return  Uri.fromFile(file);

    }



    public static  void pickPhoto(Activity activity,int request,String title) {
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        activity.startActivityForResult
                (Intent.createChooser(intent, title),request);
    }

}