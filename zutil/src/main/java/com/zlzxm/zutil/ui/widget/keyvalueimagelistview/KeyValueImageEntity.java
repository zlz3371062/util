package com.zlzxm.zutil.ui.widget.keyvalueimagelistview;

/**
 * Created by zlz
 * on  2019-09-02
 */
public class KeyValueImageEntity {

    private long id = -1;

    private String key;

    private String value;

    private int leftIcon;

    private int RightIcon;

    private boolean showline;


    public boolean isShowline() {
        return showline;
    }

    public KeyValueImageEntity setShowline(boolean showline) {
        this.showline = showline;
        return this;
    }

    public int getLeftIcon() {
        return leftIcon;
    }

    public KeyValueImageEntity setLeftIcon(int leftIcon) {
        this.leftIcon = leftIcon;

        return this;
    }

    public int getRightIcon() {
        return RightIcon;
    }

    public KeyValueImageEntity setRightIcon(int rightIcon) {
        RightIcon = rightIcon;
        return this;
    }

    public long getId() {
        return id;
    }

    public KeyValueImageEntity setId(long id) {
        this.id = id;

        return this;
    }

    public String getKey() {
        return key;
    }

    public KeyValueImageEntity setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public KeyValueImageEntity setValue(String value) {
        this.value = value;
        return this;
    }
}
