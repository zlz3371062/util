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
public class BottomSpace extends RecyclerView.ItemDecoration {

    private final int bottomSpace ;

    public BottomSpace(int bottomSpace) {

        this.bottomSpace = bottomSpace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if(parent.getAdapter() != null) {

                outRect.bottom = ZViewHelp.dpTopx(parent.getContext(), bottomSpace);
        }
    }


}
