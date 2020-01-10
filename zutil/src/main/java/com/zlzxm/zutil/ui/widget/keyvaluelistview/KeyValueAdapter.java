package com.zlzxm.zutil.ui.widget.keyvaluelistview;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zlzxm.zutil.R;
import com.zlzxm.zutil.ui.viewhelp.ZViewHelp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlz
 * on  2019-09-02
 */
public  class KeyValueAdapter extends RecyclerView.Adapter<KeyValueAdapter.KeyValueHold> {

    private static final String TAG= "KeyValueImageAdapter";

    private List<KeyValueEntity> keyValueEntities = new ArrayList<>();

    private float leftPadding = 0;

    private float topPadding = 0;

    private float rightPadding= 0;

    private float bottomPading = 0;

    private int itemHeight = 0;

    private int keyTextSize = 0;

    private int valueTextSize = 0;

    private int keyColor = 0;

    private int valueColor = 0;

    private RelativeLayout.LayoutParams keyParams = null;

    private RelativeLayout.LayoutParams valueParams = null;


    public void setKeyValueEntities(List<KeyValueEntity> keyValueEntities) {
        this.keyValueEntities = keyValueEntities;
    }

    public void setLeftPadding(float leftPadding) {
        this.leftPadding = leftPadding;
    }

    public void setTopPadding(float topPadding) {
        this.topPadding = topPadding;
    }

    public void setRightPadding(float rightPadding) {
        this.rightPadding = rightPadding;
    }

    public void setBottomPading(float bottomPading) {
        this.bottomPading = bottomPading;
    }

    public void setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
    }

    public void setKeyTextSize(int keyTextSize) {
        this.keyTextSize = keyTextSize;
    }

    public void setValueTextSize(int valueTextSize) {
        this.valueTextSize = valueTextSize;
    }


    public void setKeyColor(@ColorRes int keyColor) {
        this.keyColor = keyColor;
    }

    public void setValueColor(@ColorRes int valueColor) {
        this.valueColor = valueColor;
    }


    public void setKeyParams(RelativeLayout.LayoutParams keyParams) {
        this.keyParams = keyParams;
    }

    public void setValueParams(RelativeLayout.LayoutParams valueParams) {
        this.valueParams = valueParams;
    }

    @NonNull
    @Override
    public KeyValueHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.e(TAG,"onCreateViewHolder"  );

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_key_value,parent,false);

        KeyValueHold keyValueHold = new KeyValueHold(view);


        return keyValueHold;
    }

    @Override
    public void onBindViewHolder(@NonNull KeyValueHold holder, int position) {

        holder.mTvKey.setText(keyValueEntities.get(position).getKey());
        holder.mTvValue.setText(keyValueEntities.get(position).getValue());

        holder.mRlItem.setPadding(ZViewHelp.dpTopx(holder.itemView.getContext(),leftPadding),
                ZViewHelp.dpTopx(holder.itemView.getContext(),topPadding),
                ZViewHelp.dpTopx(holder.itemView.getContext(),rightPadding),
                ZViewHelp.dpTopx(holder.itemView.getContext(),bottomPading));

        if(itemHeight != 0){

            holder.updateItemLayoutparam();
        }

        if(keyParams != null){

            holder.updateKeyLayoutparam();
        }

        if(valueParams != null){

            holder.updateValueLayoutparam();
        }

        if(keyColor != 0){

            holder.mTvKey.setTextColor(holder.mRlItem.getContext().getResources().getColor(keyColor));

        }


        if(valueColor != 0){
            holder.mTvValue.setTextColor(holder.mRlItem.getContext().getResources().getColor(valueColor));

        }

        if(keyTextSize != 0){

            holder.mTvKey.setTextSize(TypedValue.COMPLEX_UNIT_DIP,keyTextSize);

        }


        if(valueTextSize != 0){

            holder.mTvValue.setTextSize(TypedValue.COMPLEX_UNIT_DIP,valueTextSize);

        }

    }

    @Override
    public int getItemCount() {
        return keyValueEntities.size();
    }

    public class KeyValueHold extends RecyclerView.ViewHolder {

        public static final String TAG = "KeyValueHold";

        private RelativeLayout mRlItem;

        private TextView mTvKey;

        private TextView mTvValue;

        public KeyValueHold(View view) {
            super(view);
            mRlItem =ZViewHelp.findById(view, R.id.rlItem);
            mTvKey = ZViewHelp.findById(view, R.id.tvKey);
            mTvValue = ZViewHelp.findById(view, R.id.tvValue);


        }


        public void updateItemLayoutparam(){

            ViewGroup.LayoutParams layoutParams = mRlItem.getLayoutParams();

            layoutParams.height = ZViewHelp.dpTopx(itemView.getContext(),itemHeight);

            mRlItem.setLayoutParams(layoutParams);

        }

        public void updateKeyLayoutparam(){


            mRlItem.setLayoutParams(keyParams);

        }

        public void updateValueLayoutparam(){


            mRlItem.setLayoutParams(valueParams);

        }

    }

}
