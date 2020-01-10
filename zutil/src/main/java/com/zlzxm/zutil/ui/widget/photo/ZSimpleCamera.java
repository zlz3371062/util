package com.zlzxm.zutil.ui.widget.photo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.yalantis.ucrop.UCrop;
import com.zlzxm.zutil.R;
import com.zlzxm.zutil.zfile.FileHelp;

import java.io.File;

import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.AppCompatButton;

import static android.app.Activity.RESULT_OK;


/**
 * Created by zlz on 2018/7/4.
 *
 *
 * 由 bootmsheet 的dialog 开始研究 dialog 的全屏设置
 *
 *
 *
 *   发现： 1.想要dialog 全屏 需要给window 设置 背景色  然后 设置 window 布局为 MATCH_PARENT
 *         2.同时 发现 想要给window 设置的属性 生效  window 的 修改必须在 setcontent 方法之后
 *
 *  结论 ： dialog 默认的window 有边距   window 的背景 设置 也有边距  只要 去除了两层边距  视图就可以全屏
 */

public class ZSimpleCamera extends AppCompatDialog implements View.OnClickListener {


    public  interface  ImageUriCallBack{

        void photoUri(Uri uri);

    }

    private  View mView = null;

    private AppCompatButton mBtnTakPhoto = null;

    private AppCompatButton  mBtnPickPhoto = null;

    private AppCompatButton  mBtnCancle = null;

    private  Uri uri = null;

    private  ImageUriCallBack callBack = null;

    private  int mCropWidth = 400;

    private  int mCropHeight = 400;

    private String authority =null;


    public ZSimpleCamera setCallBack(ImageUriCallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public ZSimpleCamera( Context context,String authority) {

        super(context);
        this.authority = authority;
        setOwnerActivity((Activity) context);

    }

    public  ZSimpleCamera setHeightWidth(int width,int height){

        mCropWidth = width;

        mCropHeight = height;

        return this;

    }


    public ZSimpleCamera( Context context, int themeResId) {
        super(context, themeResId);
        setOwnerActivity((Activity) context);
    }

    protected ZSimpleCamera( Context context, boolean cancelable,  OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setOwnerActivity((Activity) context);
    }

    @Override
    public void setContentView(View view) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.window_camera);

        Window window = getWindow();

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);

        window.setBackgroundDrawable(new ColorDrawable(0));

        window.setGravity(Gravity.BOTTOM);

        mBtnTakPhoto = findViewById(R.id.btn_takephoto);

        mBtnPickPhoto = findViewById(R.id.btn_pickphoto);

        mBtnCancle  = findViewById(R.id.btn_cancel);

        mBtnTakPhoto.setOnClickListener(this);
        mBtnPickPhoto.setOnClickListener(this);
        mBtnCancle.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_takephoto){
            dismiss();
            if(authority!=null) {
                takePhoto(authority);
            }else {

                throw  new RuntimeException("no authority");
            }
        }else  if(v.getId() == R.id.btn_pickphoto){
            dismiss();
            pickPhoto();
        }else  if(v.getId() == R.id.btn_cancel){

            dismiss();

        }

    }

    private void pickPhoto() {

        ZGetPhoto.pickPhoto(getOwnerActivity(), ZRequestCode.PICK_PHOTO, "请选择图片来源");

    }


    private String getPhotoName() {

        return FileHelp.getFileNameByTime("IMG", "jpg");
    }

    private void takePhoto(String authority) {

        final String currentPhotoName = getPhotoName();

        final File tempFile = new File(FileHelp.CAMERA_PHOTO_DIR, currentPhotoName);

        uri =   ZGetPhoto.takePhoto(getOwnerActivity(),tempFile,ZRequestCode.TAKE_PHOTO,authority);

    }

    public void doActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode == RESULT_OK){

            switch (requestCode){

                case ZRequestCode.TAKE_PHOTO:

                    UCrop.of(uri,uri).withMaxResultSize(mCropWidth,mCropHeight).start(getOwnerActivity());

                    break;

                case  ZRequestCode.PICK_PHOTO:

                    if(data !=null){

                        final  Uri pickPath = data.getData();

                        final  String  pickResult = Uri.parse
                                (FileHelp.createFile("Crop_photo",
                                        FileHelp.getFileNameByTime("IMG", "jpg")).getPath()).getPath();
                        if (pickPath != null) {

                            UCrop.of(pickPath,Uri.parse(pickResult)).withMaxResultSize(mCropWidth,mCropHeight).start(getOwnerActivity());
                        }
                    }


                    break;

                case  ZRequestCode.CROP_PHOTO:

                    final  Uri cropUri = UCrop.getOutput(data);

                    if(callBack != null){

                        callBack.photoUri(cropUri);
                    }

                    break;



                default:
                    break;

            }
        }else if(resultCode == ZRequestCode.CROP_ERROR){
            final Throwable cropError = UCrop.getError(data);
            Toast.makeText(getContext(),"剪裁出错",Toast.LENGTH_SHORT).show();

        }

    }


}