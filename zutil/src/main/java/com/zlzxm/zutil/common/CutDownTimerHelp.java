package com.zlzxm.zutil.common;

import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zlz
 * on  2019/7/14
 */
public class CutDownTimerHelp {


    public interface  DoneListener{

        void done();

    }

    private final static String TAG = "CutDownTimerHelp";

    private int mTotalCount = 60;

    private Timer mTimer;

    private TimerTask mTimerTask;

    private  TextView mTxtCode;

    private int mCurrentCount = 60;

    private String  tip = "重新发送验证码";

    private String  downTip = "%dS";

    private DoneListener doneListener;

    public void setDoneListener(DoneListener doneListener) {
        this.doneListener = doneListener;
    }

    public CutDownTimerHelp(TextView mTxtCode) {
        this.mTxtCode = mTxtCode;
    }


    public CutDownTimerHelp setTotalCount(int mTotalCount) {
        this.mTotalCount = mTotalCount;

        this.mCurrentCount = mTotalCount;
        return this;
    }
    public CutDownTimerHelp setDoneTip(String tip) {
        this.tip = tip;
        return this;
    }


    public void start(){

        cancel();

        mTimer = new Timer();

        mTimerTask = new MyTimerTask();

        mTimer.schedule(mTimerTask,0,1000);

    }

    public void cancel(){


        if(mTimer != null){

            mTimer.cancel();
            mTimer = null;
        }

        if(mTimerTask != null){

            mTimerTask.cancel();
            mTimerTask = null;
        }

    }

    public void recycle(){

        cancel();

        mTxtCode = null;

    }


    class  MyTimerTask extends TimerTask{

        @Override
        public void run() {

            if(mCurrentCount < 1){

                ObjectHelp.notEmpty(mTxtCode, it -> it.post(() -> {

                    mTxtCode.setEnabled(true);
                    mTxtCode.setText(tip);
                    mCurrentCount = mTotalCount;
                    if(doneListener !=null){

                        doneListener.done();
                    }
                    cancel();
                }));


            }else {

                ObjectHelp.notEmpty(mTxtCode, it -> it.post(() -> {

                    mTxtCode.setEnabled(false);
                    mTxtCode.setText(String.format("%sS",mCurrentCount));
                    mCurrentCount -- ;
                }));



            }

        }
    }


}
