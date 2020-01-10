package com.zlzxm.zutil.ui.widget.keyvalueimagelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zlzxm.zutil.R;
import com.zlzxm.zutil.ui.viewhelp.ZViewHelp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlz
 * on  2019-09-02
 */
public class KeyValueImageRecyclerView extends RecyclerView implements KeyValueImageAdapter.OnItemClickListener {

    public static final int ALIGN_PARENT_BOTTOM = 12;
    public static final int ALIGN_PARENT_LEFT = 9;
    public static final int ALIGN_PARENT_RIGHT = 11;

    @Override
    public void onItemClick(KeyValueImageEntity entity) {

        if(onItemClickListener != null){

            onItemClickListener.onItemClick(entity);
        }

    }


    public interface OnItemClickListener{

        void onItemClick(KeyValueImageEntity entity);

    }
    public String defaultStr = "未知";
    private List<KeyValueImageEntity> list = new ArrayList<>();

    private KeyValueImageAdapter keyValueImageAdapter = new KeyValueImageAdapter();

    private RelativeLayout.LayoutParams lineParams;

    private LinearLayout.LayoutParams rightIconParams;
    private LinearLayout.LayoutParams leftIconParams;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        keyValueImageAdapter.setOnItemClickListener(this);
    }

    public KeyValueImageRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public KeyValueImageRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }
    public void setDefaultStr(String defaultStr) {
        this.defaultStr = defaultStr;

        keyValueImageAdapter.setEmptyTip(defaultStr);
    }

    @Override
    public void scheduleLayoutAnimation() {
        super.scheduleLayoutAnimation();

    }

    public KeyValueImageRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setLayoutManager(new LinearLayoutManager(context));
        keyValueImageAdapter.setKeyValueEntities(list);
        setAdapter(keyValueImageAdapter);

    }

    public KeyValueImageRecyclerView setEmptyTip(String tip){

        keyValueImageAdapter.setEmptyTip(tip);
        return this;
    }

    public KeyValueImageRecyclerView add(KeyValueImageEntity menuEntity){

        list.add(menuEntity);

        keyValueImageAdapter.notifyDataSetChanged();

        return this;

    }

    public KeyValueImageRecyclerView addall(List<KeyValueImageEntity> menuEntitys){

        list.addAll(menuEntitys);

        keyValueImageAdapter.notifyDataSetChanged();

        return this;

    }


    public KeyValueImageRecyclerView remove(KeyValueImageEntity menuEntitys){

        list.remove(menuEntitys);

        keyValueImageAdapter.notifyDataSetChanged();

        return this;
    }

    public KeyValueImageRecyclerView clear(){

        list.clear();

        keyValueImageAdapter.notifyDataSetChanged();

        return this;
    }

    public KeyValueImageRecyclerView setItemHeight(int dp){
        keyValueImageAdapter.setItemHeight(dp);


        return this;

    }

    public KeyValueImageRecyclerView setKeyTextColor(@ColorRes int color){
        keyValueImageAdapter.setKeyColor(color);
        return this;
    }

    public KeyValueImageRecyclerView setValueTextColor(@ColorRes int color){
        keyValueImageAdapter.setValueColor(color);
        return this;
    }
    public KeyValueImageRecyclerView setKeyTextSize(int dp){
        keyValueImageAdapter.setKeyTextSize(dp);

        return this;

    }

    public KeyValueImageRecyclerView setItemPadding(int left, int top, int right, int bottom){
        keyValueImageAdapter.setLeftPadding(left);
        keyValueImageAdapter.setTopPadding(top);
        keyValueImageAdapter.setRightPadding(right);
        keyValueImageAdapter.setBottomPading(bottom);

        return this;
    }
    public KeyValueImageRecyclerView setItemLeftPadding(int left){
        keyValueImageAdapter.setLeftPadding(left);

        return this;
    }
    public KeyValueImageRecyclerView setItemTopPadding(int top){
        keyValueImageAdapter.setTopPadding(top);

        return this;
    }

    public KeyValueImageRecyclerView setItemRightPadding(int right){

        keyValueImageAdapter.setRightPadding(right);

        return this;
    }

    public KeyValueImageRecyclerView setItemBottomPadding(int bottom){
        keyValueImageAdapter.setBottomPading(bottom);

        return this;
    }

    public KeyValueImageRecyclerView setRightIconParams(LinearLayout.LayoutParams layoutParams){

        keyValueImageAdapter.setRightIvParams(layoutParams);

        return this;
    }

    public KeyValueImageRecyclerView setLeftIconParams(LinearLayout.LayoutParams layoutParams){

        keyValueImageAdapter.setLeftIvParams(layoutParams);

        return this;
    }


    public KeyValueImageRecyclerView setLineColor(@ColorRes int color){

        keyValueImageAdapter.setLineColor(color);

        return this;
    }

    public KeyValueImageRecyclerView setLineParams(RelativeLayout.LayoutParams params){

        keyValueImageAdapter.setLineParams(params);

        return this;
    }


    public KeyValueImageRecyclerView setRightIconWithHeight(int width,int height){

        if(rightIconParams == null){

            rightIconParams = new LinearLayout.LayoutParams(ZViewHelp.dpTopx(getContext(),width),ZViewHelp.dpTopx(getContext(),height));
        }


        return setRightIconParams(rightIconParams);
    }
    public KeyValueImageRecyclerView setRightIconMargin(int left,int Right){

        if(rightIconParams == null){

            rightIconParams = new LinearLayout.LayoutParams(ZViewHelp.dpTopx(getContext(),12),ZViewHelp.dpTopx(getContext(),12));
        }

        rightIconParams.leftMargin = ZViewHelp.dpTopx(getContext(),left);
        rightIconParams.rightMargin =  ZViewHelp.dpTopx(getContext(),Right);


        return setRightIconParams(rightIconParams);
    }


    public KeyValueImageRecyclerView setLeftIconMargin(int left,int Right){

        if(leftIconParams == null){

            leftIconParams = new LinearLayout.LayoutParams(ZViewHelp.dpTopx(getContext(),12),ZViewHelp.dpTopx(getContext(),12));
        }

        leftIconParams.leftMargin = ZViewHelp.dpTopx(getContext(),left);
        leftIconParams.rightMargin =  ZViewHelp.dpTopx(getContext(),Right);


        return setLeftIconParams(leftIconParams);
    }


    public KeyValueImageRecyclerView setLineHeight(float height){

        if(lineParams == null){

            lineParams = new RelativeLayout.LayoutParams(-1,ZViewHelp.dpTopx(getContext(),height));
        }


        return setLineParams(lineParams);
    }

    public KeyValueImageRecyclerView setLineAlign(int align){

        if(lineParams == null){

            lineParams = new RelativeLayout.LayoutParams(-1,ZViewHelp.dpTopx(getContext(),1));
        }
        lineParams.addRule(align);

        return setLineParams(lineParams);
    }


    public KeyValueImageRecyclerView setLineMargin(int left,int Right){

        if(lineParams == null){

            lineParams = new RelativeLayout.LayoutParams(-1,ZViewHelp.dpTopx(getContext(),1));
        }

        lineParams.leftMargin = ZViewHelp.dpTopx(getContext(),left);
        lineParams.rightMargin =  ZViewHelp.dpTopx(getContext(),Right);


        return setLineParams(lineParams);
    }

    public void commit(){

        keyValueImageAdapter.notifyDataSetChanged();
    }

    public KeyValueImageRecyclerView setLeftIconWidthHeight(int width,int height ){

        if(leftIconParams == null){

            leftIconParams = new LinearLayout.LayoutParams(ZViewHelp.dpTopx(getContext(),width),ZViewHelp.dpTopx(getContext(),height));
        }


        return setLeftIconParams(leftIconParams);

    }

}
