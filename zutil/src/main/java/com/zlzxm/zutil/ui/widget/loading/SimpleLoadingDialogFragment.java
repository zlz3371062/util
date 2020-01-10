package com.zlzxm.zutil.ui.widget.loading;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.zlzxm.zutil.R;
import com.zlzxm.zutil.common.ObjectHelp;
import com.zlzxm.zutil.ui.window.BaseDialogFragment;

/**
 * Created by zlz
 * on  2019/6/21
 */
public class SimpleLoadingDialogFragment extends BaseDialogFragment {


    private View mRootView = null;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setCancelable(false);
        if(mRootView == null){

            mRootView = inflater.inflate(R.layout.layout_simple_loading,container,false);
        }

        return mRootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();

        ObjectHelp.notEmpty(window,it -> {

            WindowManager.LayoutParams layoutParams =  it.getAttributes();
            ObjectHelp.notEmpty(layoutParams,params->{
                params.dimAmount = 0;
                params.windowAnimations = 0;
                it.setAttributes(params);

            });
        });

    }

}
