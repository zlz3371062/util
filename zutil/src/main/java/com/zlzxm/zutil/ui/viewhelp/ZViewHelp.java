package com.zlzxm.zutil.ui.viewhelp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

import com.zlzxm.zutil.common.StringHelp;

/**
 * Created by zlz on 2018/7/18.
 * view 相关快捷帮助
 */

public class ZViewHelp {


    public static   <T extends View> T findById(Activity activity ,@IdRes int id){


        return activity.findViewById(id);
    }




    public static <T extends View> T setOnClickListener(Activity activity ,@IdRes int id, View.OnClickListener listener){

        T t =  findById(activity,id);

        t.setOnClickListener(listener);

        return t;
    }

    public static   <T extends  View> T findById(View view ,@IdRes int id){


        return view.findViewById(id);
    }


    public static <T extends View> T setOnClickListener(View view , @IdRes int id, View.OnClickListener listener){

        T t =  findById(view,id);

        t.setOnClickListener(listener);

        return t;
    }

    public static <T extends EditText> T addTextChangedListener(View view , @IdRes int id, TextWatcher watcher){

        T t =  findById(view,id);

        t.addTextChangedListener(watcher);

        return t;
    }

    public static <T extends EditText> T addTextChangedListener(Activity activity ,@IdRes int id, TextWatcher watcher){

        T t =  findById(activity,id);

        t.addTextChangedListener(watcher);

        return t;
    }

    public static int getStateBarHeight(Context context){
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);

            return result;

        }else {

            return  -1;
        }
    }

    public static void changeViewSize(int width,int height,View view){

        ViewGroup.LayoutParams  lp = view.getLayoutParams();

        lp.width = width;

        lp.height = height;

        view.setLayoutParams(lp);


    }

    public static  View inflate(Context context, ViewGroup parent, @LayoutRes int resource, boolean attachToRoot){

        return LayoutInflater.from(context).inflate(resource,parent,attachToRoot);
    }


    public static int dpTopx(Context context,float dp){

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }


    public static int getScreenHeight(Context context){

        Activity activity = null;

        if(context instanceof Activity){

            activity = (Activity) context;
            WindowManager manager = activity.getWindowManager();
            DisplayMetrics outMetrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(outMetrics);
            int width = outMetrics.widthPixels;
            int height = outMetrics.heightPixels;

            return  height;
        }

        return  0;
    }

    public static int getScreenWidth(Context context){

        Activity activity = null;

        if(context instanceof Activity){

            activity = (Activity) context;
            WindowManager manager = activity.getWindowManager();
            DisplayMetrics outMetrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(outMetrics);
            int width = outMetrics.widthPixels;
            int height = outMetrics.heightPixels;

            return  width;
        }

        return  0;
    }


    public static boolean checkDeviceHasNavigationBar(Context activity) {

        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(activity)
                .hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap
                .deviceHasKey(KeyEvent.KEYCODE_BACK);

        if (!hasMenuKey && !hasBackKey) {
            // 做任何你需要做的,这个设备有一个导航栏
            return true;
        }
        return false;
    }

    public static int getNavigationBarHeight(Context activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    //<editor-fold defaultState="collapsed" desc="TextVIew">
    public static String getText(TextView textView){

        return textView.getText().toString().trim();
    }

    /**
     *  根据内容确定是否展示textview
     * @param textView view
     * @param content 内容
     */
    public static void setText(TextView textView, String content){

        StringHelp.isEmpty(content,()-> textView.setVisibility(View.GONE), it->{
            textView.setVisibility(View.VISIBLE);
            textView.setText(it);
        });

    }


    public static void setText(TextView textView, String content,String defaultStr){

        textView.setVisibility(View.VISIBLE);
        textView.setText(StringHelp.initWithDefault(content,defaultStr));
    }
// </editor-fold>

//<editor-fold defaultState="collapsed" desc="ImageView">

    public static void setImageRes(ImageView imageView,@DrawableRes int res){

        if(res == 0){

            imageView.setVisibility(View.GONE);
        }else {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(res);
        }
    }

// </editor-fold>
}
