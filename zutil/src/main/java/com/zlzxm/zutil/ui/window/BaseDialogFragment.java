package com.zlzxm.zutil.ui.window;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.zlzxm.zutil.R;
import com.zlzxm.zutil.common.StringHelp;

/**
 * Created by zlz
 * on  2019-11-02
 */
public class BaseDialogFragment extends DialogFragment {

    private static final String TAG ="BaseDialogFragment";

    private Toast mToast;

    /**
     * 在此之前会创建 dialog 的构造方法构造dialog对象  构造dialog时会创建phoneWindow
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG,"onCreateView");


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG,"onViewCreated");
    }

    /**
     * 在fragment 中的start 中调用dialog.show 使dialog展示 dialog
     * show 中如果 window没有添加 调用wm.addview 和 activity 中onresume 做的一样
     */
    @Override
    public void onStart() {
        Log.e(TAG,"onStart");
        Window window =getDialog().getWindow();
        window.setLayout(-1,-2);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.getAttributes().windowAnimations = R.style.BaseDialogEnter;
        super.onStart();
    }

    /**
     * show 会将fragment添加到dialog中 这时会触发fragment 的 生命周期
     * @param manager  2
     * @param tag  2
     */
    @Override
    public void show(FragmentManager manager, String tag) {
        Log.e(TAG,"show");
        super.show(manager, tag);

    }

    /**
     * onViewCreated
     * 这时 dialog 会 将fragmnet 中的view 添加到 dialog中 调用例如activity 中的 setContentView
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e(TAG,"onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }


    public void showToast(String content){

        if(!StringHelp.isEmpty(content)&&getContext()!=null){
            if(mToast==null){

                mToast = Toast.makeText(getContext(),content,Toast.LENGTH_SHORT);
            }
            mToast.setText(content);
            mToast.show();
        }
    }
}
