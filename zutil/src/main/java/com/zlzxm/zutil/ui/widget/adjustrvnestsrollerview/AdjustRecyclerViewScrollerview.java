package com.zlzxm.zutil.ui.widget.adjustrvnestsrollerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

/**
 * Created by zlz
 * on  2019/7/17
 */
public class AdjustRecyclerViewScrollerview  extends NestedScrollView {



    public AdjustRecyclerViewScrollerview(@NonNull Context context) {

        this(context,null);
    }

    public AdjustRecyclerViewScrollerview(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AdjustRecyclerViewScrollerview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }



    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(target, dx, dy, consumed, type);

//        if(dy > 0){
//
//            if(canScrollVertically(1)) {
//
//                scrollBy(0, dy);
//
//                consumed[1] = dy;
//            }
//
//        }

        if(dy > 0){

            if(canScrollVertically(1)) {

                if(consumed[1] == dy){

                    return;

                }else {

                    scrollBy(0, dy - consumed[1]);

                    consumed[1] = dy;

                }

            }

        }

    }


}
