package com.zlzxm.zutil.ui.adapter.selectadapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by zlz
 * on  2019-11-11
 *
 * 不太友好:在用户越过setSelectPosition 方法 手动修改状态值时 会出现bug
 */
public abstract class SelectStatusAdapter<T extends SelectStatusAdapter.SelectEntity> extends BaseQuickAdapter<T, BaseViewHolder> {

    private int selectPostion = -1;

    public SelectStatusAdapter(int layoutResId, @Nullable List<T> data) {

        super(layoutResId, data);

        for (int i = 0; i < data.size(); i++) {

            if(data.get(i).isSelected){

                selectPostion = i;
            }
        }
    }

    public T getSelectEntity(){


        for (T entity:mData) {

            if(entity.isSelected){

                return entity;
            }

        }

        return null;
    }


    public T setSelectPosition(int position){

        if(position > mData.size()||position<-1){


            return null;
        }


        if(selectPostion == -1){

            if(position==-1){

                return  null;
            }

            mData.get(position).setSelected(true);

            selectPostion = position;

            notifyDataSetChanged();

            return  mData.get(selectPostion);

        }else {

            if(position == -1){
                selectPostion = -1;
                mData.get(selectPostion).setSelected(false);
                notifyDataSetChanged();
                return null;
            }else {

                T entity = mData.get(selectPostion);

                if (entity != null) {

                    entity.setSelected(false);

                }
                if (mData.get(position) != null) {
                    selectPostion = position;
                    mData.get(selectPostion).setSelected(true);
                    notifyDataSetChanged();
                    return mData.get(selectPostion);
                }
                notifyDataSetChanged();
            }

        }

        return  null;
    }


    public static class SelectEntity {

        protected boolean isSelected;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

}
