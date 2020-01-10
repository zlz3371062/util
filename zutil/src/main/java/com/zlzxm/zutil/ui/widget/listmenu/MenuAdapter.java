package com.zlzxm.zutil.ui.widget.listmenu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zlzxm.zutil.R;

import java.util.List;

/**
 * Created by zlz
 * on  2019/7/12
 */
public class MenuAdapter extends BaseMultiItemQuickAdapter<MenuEntity, BaseViewHolder> {


    private int mMenuBacRec = 0;

    public MenuAdapter(List<MenuEntity> data) {
        super(data);

        addItemType(MenuItemType.ITEM_IMG_TEXT_IMG, R.layout.layout_menu);
    }

    public void setMenuBacRec(int mMenuBacRec) {

        this.mMenuBacRec = mMenuBacRec;
    }


    @Override
    protected BaseViewHolder createBaseViewHolder(View view) {


            if(mMenuBacRec != 0){

                view.setBackgroundResource(mMenuBacRec);
            }

        return super.createBaseViewHolder(view);

    }

    @Override
    protected void convert(BaseViewHolder helper, MenuEntity item) {

        switch (item.getItemType()){

            case  MenuItemType.ITEM_IMG_TEXT_IMG:

                convertImgTextImg(helper,item);

                break;
        }

    }

    private void convertImgTextImg(BaseViewHolder helper, MenuEntity item) {


        helper.setImageResource(R.id.ivMenuIcon,item.getIconImg());
        helper.setText(R.id.txtMenuKey,item.getKey());
        helper.setImageResource(R.id.ivMenuRight,item.getRightIconId());
        helper.setVisible(R.id.menuLine,item.isShowLine());

        if(item.isShowRightTip()){

            helper.setVisible(R.id.tvRightTip,true);
            helper.setText(R.id.tvRightTip,item.getRightTip());
        }else {
            helper.setVisible(R.id.tvRightTip,false);

        }
    }





}
