package com.zlzxm.zutil.ui.widget.keyvaluelistview;

import java.util.PrimitiveIterator;

/**
 * Created by zlz
 * on  2019-09-02
 */
public class KeyValueEntity {

    private int id;

    private String key;

    private String value;


    public int getId() {
        return id;
    }

    public KeyValueEntity setId(int id) {
        this.id = id;

        return this;
    }

    public String getKey() {
        return key;
    }

    public KeyValueEntity setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public KeyValueEntity setValue(String value) {
        this.value = value;
        return this;
    }
}
