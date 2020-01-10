package com.zlzxm.zutil.ui.widget.refreshlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zlzxm.zutil.R;

/**
 * Created by zlz
 * on  2019-09-02
 */
public class SwipeRefreshRecyclerView extends SwipeRefreshLayout {

    private RecyclerView mRv;

    public SwipeRefreshRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public SwipeRefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mRv = new RecyclerView(context);
        mRv.setLayoutManager(new LinearLayoutManager(context));
        ViewGroup.LayoutParams layoutParams  =  new ViewGroup.LayoutParams(-1,-1);
        addView(mRv,layoutParams);

    }


    public void bindAdapter(RecyclerView.Adapter adapter){

        mRv.setAdapter(adapter);

    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration){

        mRv.addItemDecoration(itemDecoration);

    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){

        mRv.setLayoutManager(layoutManager);

    }

}
