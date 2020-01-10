package com.zlzxm.zutil.ui.widget.listmenu;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by zlz
 * on  2019/7/12
 */
public class MenuEntity<T> implements MultiItemEntity {

    private int id;

    private int leftIconId = 0;

    private String key;

    private int itemType = MenuItemType.ITEM_IMG_TEXT_IMG;

    private int rightIconId = 0;

    private boolean isShowLine;

    private boolean isShowRightTip;

    private String rightTip;

    private T ex;

    public int getId() {
        return id;
    }

    public MenuEntity setId(int id) {
        this.id = id;
        return this;
    }

    public T getEx() {
        return ex;
    }

    public void setEx(T ex) {
        this.ex = ex;
    }

    public String getRightTip() {
        return rightTip;
    }

    public MenuEntity setRightTip(String rightTip) {
        this.rightTip = rightTip;

        return this;
    }

    public boolean isShowRightTip() {
        return isShowRightTip;
    }

    public MenuEntity setShowRightTip(boolean showTightTip) {
        isShowRightTip = showTightTip;
        return this;
    }



    public int getIconImg() {
        return leftIconId;
    }

    public MenuEntity setIconImg(int iconImg) {
        this.leftIconId = iconImg;

        return this;
    }

    public String getKey() {
        return key;
    }

    public MenuEntity setKey(String key) {
        this.key = key;

        return this;
    }

    public MenuEntity setItemType(int itemType) {
        this.itemType = itemType;

        return this;
    }

    public int getRightIconId() {
        return rightIconId;
    }

    public MenuEntity setRightIconId(int rightIconId) {
        this.rightIconId = rightIconId;

        return this;
    }

    public boolean isShowLine() {
        return isShowLine;
    }

    public MenuEntity setShowLine(boolean showLine) {
        isShowLine = showLine;

        return this;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
