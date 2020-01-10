package com.zlzxm.zutil.ui.activity;

import android.os.Bundle;
import android.util.Log;

/**
 * 提供
 */

public abstract  class ZBaseAbsSimpleActivity extends ZBaseAbsActivity {


    @Override
    protected void initLayout(Bundle savedInstanceState) {
        Log.d(this.getClass().getSimpleName(),"initLayout");
    }

    @Override
    protected void initData() {

        Log.d(this.getClass().getSimpleName(),"initData");
    }

    @Override
    protected void beforeLoadContentView(Bundle savedInstanceState) {
        Log.d(this.getClass().getSimpleName(),"beforeLoadContent");
    }


}
