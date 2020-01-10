package com.zlzxm.zutil.ui.widget.listmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zlz
 * on  2019/7/12
 */
public class MenuRecyclerView extends RecyclerView implements BaseQuickAdapter.OnItemClickListener {

    public interface  MenuClickListener{

        void onMenuClick(MenuEntity entity);

    }

    private final List<MenuEntity> mMenus = new ArrayList<>();

    private final MenuAdapter mMenuAdapter = new MenuAdapter(mMenus);

    private MenuClickListener mMenuClickListener = null;

    public MenuClickListener getmMenuClickListener() {
        return mMenuClickListener;
    }

    public void setmMenuClickListener(MenuClickListener mMenuClickListener) {
        this.mMenuClickListener = mMenuClickListener;
    }

    public MenuRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public MenuRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MenuRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setLayoutManager(new LinearLayoutManager(context));

        setAdapter(mMenuAdapter);

        mMenuAdapter.setOnItemClickListener(this);

    }

    public MenuRecyclerView addMenu(MenuEntity menuEntity){

        mMenus.add(menuEntity);

        mMenuAdapter.notifyDataSetChanged();

        return this;

    }

    public MenuRecyclerView addallMenu(List<MenuEntity> menuEntitys){

        mMenus.addAll(menuEntitys);

        mMenuAdapter.notifyDataSetChanged();

        return this;

    }


    public MenuRecyclerView remove(MenuEntity menuEntitys){

        mMenus.remove(menuEntitys);

        mMenuAdapter.notifyDataSetChanged();

        return this;
    }

    public MenuRecyclerView clear(){

        mMenus.clear();

        mMenuAdapter.notifyDataSetChanged();

        return this;
    }

    public MenuRecyclerView setMenuBgRec(@DrawableRes int rec){

        if(mMenuAdapter != null)
            mMenuAdapter.setMenuBacRec(rec);

        return this;
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        if(mMenuClickListener != null){

            mMenuClickListener.onMenuClick(mMenus.get(position));

        }

    }
}
