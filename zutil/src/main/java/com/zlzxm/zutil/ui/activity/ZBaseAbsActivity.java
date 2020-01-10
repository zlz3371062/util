package com.zlzxm.zutil.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.zlzxm.zutil.appmanage.ActivityManager;
import com.zlzxm.zutil.appmanage.LanguagerManager;
import com.zlzxm.zutil.ui.widget.simplehead.OnLeftclicklistener;
import com.zlzxm.zutil.ui.widget.simplehead.SimpleHead;

/**
 *
 *  提供基础功能并且 向下级要求方法
 *
 */

public abstract  class ZBaseAbsActivity extends RxAppCompatActivity {


    private static  boolean isSupportManager = false;

    private final static long EXIT_TIME = 2000;

    private long mFirstBackPressedTime = 0;

    private boolean mIsConsumeBack = false;

    protected abstract Object initContentView();

    protected abstract void initLayout(Bundle savedInstanceState);

    /**
     * 设置初始值 或者  加载本地数据
     */
    protected abstract void initData();

    protected abstract void beforeLoadContentView(Bundle savedInstanceState);

    protected Toast mToast = null;


    public static void supportManager(){

        isSupportManager = true;
    }


    @Override
    protected void attachBaseContext(Context context) {

        super.attachBaseContext(isSupportManager?LanguagerManager.setLocal(context):context);
    }


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(isSupportManager){

            ActivityManager.getInstance().addActivity(this);

        }
        int startModel =   getIntent().getIntExtra(ZStartModel.START_MODEL,0);

        int time =  getIntent().getIntExtra(ZStartModel.START_TIME,0);

        if(startModel != 0 && Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){

            switch (startModel) {

                case  ZStartModel.EXPLODE:

                    getWindow().setEnterTransition(new Explode().setDuration(time));
                    getWindow().setExitTransition(new Explode().setDuration(time));

                    break;

                case  ZStartModel.SLIDE:

                    getWindow().setEnterTransition(new Slide().setDuration(time));
                    getWindow().setExitTransition(new Slide().setDuration(time));

                    break;

                case  ZStartModel.FADE:

                    getWindow().setEnterTransition(new Fade().setDuration(time));
                    getWindow().setExitTransition(new Fade().setDuration(time));

                    break;
                default:
                    break;
            }


        }

        beforeLoadContentView(savedInstanceState);

        if(initContentView() instanceof View){

            View root = (View) initContentView();

            setContentView(root);

        }else if(initContentView() instanceof  Integer){

            int view = (int) initContentView();

            setContentView(view);

        }else {

            throw new ClassCastException("type of initContentView() must be int or View!");
        }

        initLayout(savedInstanceState);

        initData();

    }


    public void  setStatusColor(int color){

        setStatusColor(color,false);
    }

    /**
     *  设置 状态栏颜色
     * @param color  颜色
     */
    public void setStatusColor(int color,boolean  isLightBar){

        if(color == Color.WHITE){


            isLightBar = true;
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().getDecorView().setSystemUiVisibility(0);

            if(color == Color.TRANSPARENT)
            {

                if(isLightBar) {

                    window.getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }else {

                    window.getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE );

                }

            }else {

                if (isLightBar) {

                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

                }
            }

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(color);

        }else {


        }

    }


    /**
     * 设置透明状态栏
     */
    public void setStatusBarTransparent(boolean isLightBar){

        setStatusColor(Color.TRANSPARENT,isLightBar);

    }

    public void setStatusBarTransparent(){

        setStatusColor(Color.TRANSPARENT,false);

    }



    protected void showToast(String content){

        if(mToast ==null){

            mToast = Toast.makeText(this,content,Toast.LENGTH_SHORT);
        }
        mToast.setText(content);
        mToast.show();
    }

    @Override
    protected void onDestroy() {
        if(isSupportManager) {
            ActivityManager.getInstance().removeActivity(this);
        }
        super.onDestroy();
    }


    protected void hideKeyBoard(){

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (getCurrentFocus() != null)
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),  0);
    }

    protected void openKeyBoard(View v){

        InputMethodManager imm = ( InputMethodManager ) v.getContext().getSystemService( Context.INPUT_METHOD_SERVICE );
        if (!imm.isActive(v)){
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    protected void consumeBack(boolean isConsumeBack){

        mIsConsumeBack = isConsumeBack;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(mIsConsumeBack){

            if(keyCode == KeyEvent.KEYCODE_BACK){

                long time = System.currentTimeMillis();

                if((time - mFirstBackPressedTime) > EXIT_TIME){

                    mFirstBackPressedTime = time;

                    showToast("再按一次退出");

                }else {

                    finish();

                }

                return  true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }


    protected SimpleHead toolgetherSimpleHead(int id){

        SimpleHead  simpleHead  = findViewById(id);
        simpleHead.setOnItemclicklistener(new OnLeftclicklistener() {
            @Override
            public void onLeftItemClick() {

                finish();
            }
        });

        return  simpleHead;
    }



}
