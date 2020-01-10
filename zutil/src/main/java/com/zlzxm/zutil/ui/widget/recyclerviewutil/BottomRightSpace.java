package com.zlzxm.zutil.ui.widget.recyclerviewutil;

import android.graphics.Rect;
import android.view.View;

import com.zlzxm.zutil.ui.viewhelp.ZViewHelp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * Created by zlz
 * on  2019/6/26
 */
public class BottomRightSpace extends RecyclerView.ItemDecoration {

    private final int bottomSpace ;
    private final int rightSpace ;

    public BottomRightSpace(int bottomSpace,int rightSpace) {

        this.bottomSpace = bottomSpace;
        this.rightSpace = rightSpace;

    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if(parent.getAdapter() != null) {

            outRect.bottom = ZViewHelp.dpTopx(parent.getContext(), bottomSpace);

            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();

            if (layoutParams.getSpanIndex() == 0) {

                outRect.right = ZViewHelp.dpTopx(parent.getContext(),10);

            } else {

                outRect.right = 0;
            }
        }
    }


}
