package com.zlzxm.zutil.ui.viewhelp;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zlz
 * on  2019/6/19
 */
public  class AdjustBySatusBarRunable implements  Runnable{

    protected final View  mView;


    public AdjustBySatusBarRunable(View view) {
        this.mView = view;
    }

    @Override
    public void run() {

        ViewGroup.LayoutParams layoutParams =  mView.getLayoutParams();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){


            int height =  ZViewHelp.getStateBarHeight(mView.getContext());

            if(height != -1){


                layoutParams.height = layoutParams.height + height;

                mView.setPadding(mView.getPaddingLeft(),mView.getPaddingTop()+ height,mView.getPaddingRight(),mView.getPaddingBottom());


                mView.setLayoutParams(layoutParams);


            }else {

                return;
            }


        }



    }


//
//    protected int barHeight(){
//
//       return   ZViewHelp.getStateBarHeight(mView.getContext());
//    }
}
