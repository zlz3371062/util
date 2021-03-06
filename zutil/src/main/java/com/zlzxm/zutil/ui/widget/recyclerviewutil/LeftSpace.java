package com.zlzxm.zutil.ui.widget.recyclerviewutil;

import android.graphics.Rect;
import android.view.View;

import com.zlzxm.zutil.ui.viewhelp.ZViewHelp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zlz
 * on  2019/6/26
 */
public class LeftSpace extends RecyclerView.ItemDecoration {

    private final int leftSpace ;

    public LeftSpace(int leftSpace) {

        this.leftSpace = leftSpace;

    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if(parent.getAdapter() != null) {

            outRect.left = ZViewHelp.dpTopx(view.getContext(),leftSpace);
        }
    }


}
