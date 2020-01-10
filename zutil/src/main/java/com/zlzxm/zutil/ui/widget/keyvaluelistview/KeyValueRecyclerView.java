package com.zlzxm.zutil.ui.widget.keyvaluelistview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlz
 * on  2019-09-02
 */
public class KeyValueRecyclerView extends RecyclerView {

    private List<KeyValueEntity> list = new ArrayList<>();

    private KeyValueAdapter   keyValueAdapter = new KeyValueAdapter();

    public KeyValueRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public KeyValueRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public KeyValueRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setLayoutManager(new LinearLayoutManager(context));

        setAdapter(keyValueAdapter);

    }


    public KeyValueRecyclerView add(KeyValueEntity menuEntity){

        list.add(menuEntity);

        keyValueAdapter.notifyDataSetChanged();

        return this;

    }

    public KeyValueRecyclerView addall(List<KeyValueEntity> menuEntitys){

        list.addAll(menuEntitys);

        keyValueAdapter.notifyDataSetChanged();

        return this;

    }


    public KeyValueRecyclerView remove(KeyValueEntity menuEntitys){

        list.remove(menuEntitys);

        keyValueAdapter.notifyDataSetChanged();

        return this;
    }

    public KeyValueRecyclerView clear(){

        list.clear();

        keyValueAdapter.notifyDataSetChanged();

        return this;
    }

    public KeyValueRecyclerView setItemHeight(int dp){
        keyValueAdapter.setItemHeight(dp);


        return this;

    }

    public KeyValueRecyclerView setKeyTextColor(@ColorRes int color){
        keyValueAdapter.setKeyColor(color);
        return this;
    }

    public KeyValueRecyclerView setValueTextColor(@ColorRes int color){
        keyValueAdapter.setValueColor(color);
        return this;
    }
    public KeyValueRecyclerView setKeyTextSize( int dp){
        keyValueAdapter.setKeyTextSize(dp);

        return this;

    }

    public KeyValueRecyclerView setItemPadding(int left,int top,int right,int bottom){
        keyValueAdapter.setLeftPadding(left);
        keyValueAdapter.setTopPadding(top);
        keyValueAdapter.setRightPadding(right);
        keyValueAdapter.setBottomPading(bottom);

        return this;
    }
    public KeyValueRecyclerView setItemLeftPadding(int left){
        keyValueAdapter.setLeftPadding(left);

        return this;
    }
    public KeyValueRecyclerView setItemTopPadding( int top){
        keyValueAdapter.setTopPadding(top);

        return this;
    }

    public KeyValueRecyclerView setItemRightPadding( int right){

        keyValueAdapter.setRightPadding(right);

        return this;
    }

    public KeyValueRecyclerView setItemBottomPadding( int bottom){
        keyValueAdapter.setBottomPading(bottom);

        return this;
    }

    public void commit(){

        keyValueAdapter.notifyDataSetChanged();
    }
}
