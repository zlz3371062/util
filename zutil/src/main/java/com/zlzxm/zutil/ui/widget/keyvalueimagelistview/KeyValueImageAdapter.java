package com.zlzxm.zutil.ui.widget.keyvalueimagelistview;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zlzxm.zutil.R;
import com.zlzxm.zutil.common.StringHelp;
import com.zlzxm.zutil.ui.viewhelp.ZViewHelp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlz
 * on  2019-09-02
 */
public  class KeyValueImageAdapter extends RecyclerView.Adapter<KeyValueImageAdapter.KeyValueHold> {

    private static final String TAG= "KeyValueImageAdapter";

    public interface OnItemClickListener{


        void  onItemClick(KeyValueImageEntity entity);

    }

    private List<KeyValueImageEntity> keyValueEntities = new ArrayList<>();


    private float leftPadding = 0;

    private float topPadding = 0;

    private float rightPadding= 0;

    private float bottomPading = 0;

    private int itemHeight = 0;

    private int keyTextSize = 0;

    private int valueTextSize = 0;

    private int keyColor = 0;

    private int valueColor = 0;

    private int lineColor=0;

    private LinearLayout.LayoutParams keyParams = null;

    private LinearLayout.LayoutParams valueParams = null;

    private LinearLayout.LayoutParams rightIvParams = null;

    private LinearLayout.LayoutParams leftIvParams = null;

    private RelativeLayout.LayoutParams lineParams= null;

    private OnItemClickListener onItemClickListener;

    public String defaultStr = "未知";



    public void setEmptyTip(String tip){

        defaultStr = tip;

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setKeyValueEntities(List<KeyValueImageEntity> keyValueEntities) {
        this.keyValueEntities = keyValueEntities;
    }


    public void setRightIvParams(LinearLayout.LayoutParams rightIvParams) {
        this.rightIvParams = rightIvParams;
    }

    public void setLeftIvParams(LinearLayout.LayoutParams leftIvParams) {
        this.leftIvParams = leftIvParams;
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


    public void setKeyParams(LinearLayout.LayoutParams keyParams) {
        this.keyParams = keyParams;
    }

    public void setValueParams(LinearLayout.LayoutParams valueParams) {
        this.valueParams = valueParams;
    }

    public void setLineParams(RelativeLayout.LayoutParams lineParams) {
        this.lineParams = lineParams;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    @NonNull
    @Override
    public KeyValueHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.e(TAG,"onCreateViewHolder"  );

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_key_value_icon,parent,false);


        return new KeyValueHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeyValueHold holder, int position) {

        if(onItemClickListener!=null){

            holder.mLlItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(onItemClickListener!=null){

                        onItemClickListener.onItemClick(keyValueEntities.get(position));
                    }

                }
            });
        }

        holder.mTvKey.setText(keyValueEntities.get(position).getKey());
        holder.mTvValue.setText(StringHelp.initWithDefault(keyValueEntities.get(position).getValue(),defaultStr));
        if(keyValueEntities.get(position).getLeftIcon()!=0){

            holder.mIvLeft.setImageResource(keyValueEntities.get(position).getLeftIcon());
            holder.mIvLeft.setVisibility(View.VISIBLE);
        }else {
            holder.mIvLeft.setVisibility(View.GONE);
        }

        if(keyValueEntities.get(position).getRightIcon()!=0){

            holder.mIvRight.setImageResource(keyValueEntities.get(position).getRightIcon());
            holder.mIvRight.setVisibility(View.VISIBLE);
        }else {
            holder.mIvRight.setVisibility(View.GONE);
        }


        holder.mLlItem.setPadding(ZViewHelp.dpTopx(holder.itemView.getContext(),leftPadding),
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


        if(rightIvParams != null){
            holder.updateRightImageLayoutparam();

        }

        if(leftIvParams != null){

            holder.updateLeftImageLayoutparam();
        }

        if(lineParams !=null){

            holder.updateLineLayoutparam();

        }

        if(keyColor != 0){

            holder.mTvKey.setTextColor(holder.mLlItem.getContext().getResources().getColor(keyColor));

        }

        if(lineColor != 0){

            if(keyValueEntities.get(position).isShowline()) {

                holder.line.setBackgroundColor(holder.mLlItem.getContext().getResources().getColor(lineColor));

                holder.line.setVisibility(View.VISIBLE);
            }else {

                holder.line.setVisibility(View.GONE);
            }
        }else {

            holder.line.setVisibility(View.GONE);
        }


        if(valueColor != 0){
            holder.mTvValue.setTextColor(holder.mLlItem.getContext().getResources().getColor(valueColor));

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

        private LinearLayout mLlItem;

        private TextView mTvKey;

        private TextView mTvValue;

        private ImageView mIvLeft ;

        private ImageView mIvRight ;

        private View line;

        public KeyValueHold(View view) {
            super(view);
            mLlItem =ZViewHelp.findById(view, R.id.llItem);
            mTvKey = ZViewHelp.findById(view, R.id.tvKey);
            mTvValue = ZViewHelp.findById(view, R.id.tvValue);
            mIvLeft = ZViewHelp.findById(view, R.id.ivLeft);
            mIvRight = ZViewHelp.findById(view, R.id.ivRight);
            line = ZViewHelp.findById(view, R.id.line);


        }


        public void updateItemLayoutparam(){

            ViewGroup.LayoutParams layoutParams = mLlItem.getLayoutParams();

            layoutParams.height = ZViewHelp.dpTopx(itemView.getContext(),itemHeight);

            mLlItem.setLayoutParams(layoutParams);

        }

        public void updateKeyLayoutparam(){


            mTvKey.setLayoutParams(keyParams);

        }

        public void updateValueLayoutparam(){


            mTvValue.setLayoutParams(valueParams);

        }


        public void updateLeftImageLayoutparam(){


            mIvLeft.setLayoutParams(leftIvParams);

        }

        public void updateRightImageLayoutparam(){


            mIvRight.setLayoutParams(rightIvParams);

        }


        public void updateLineLayoutparam(){

            line.setLayoutParams(lineParams);

        }

    }

}
