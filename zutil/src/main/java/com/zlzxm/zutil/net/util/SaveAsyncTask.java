package com.zlzxm.zutil.net.util;

import android.os.AsyncTask;

import com.zlzxm.zutil.zfile.FileHelp;

import java.io.File;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by zlz
 * on  2019/7/14
 */
public class SaveAsyncTask extends AsyncTask<ResponseBody,Integer, File> {

    public interface SaveListener{

        void onPreExecute();

        void onProgressUpdate(int progress);

        void onEndExecute(File file);

    }


    private SaveListener mSaveListener = null;

    private final String mPath;

    private final String mFileName;

    public SaveAsyncTask(String mPath, String mFileName) {
        this.mPath = mPath;
        this.mFileName = mFileName;
    }

    public SaveAsyncTask setSaveListener(SaveListener listener){

        mSaveListener = listener;

        return  this;

    }


    @Override
    protected File doInBackground(ResponseBody... params) {

        if(mPath == null || mFileName == null){

            return  null;
        }

        ResponseBody rp = params[0];

        File file = null;

        if(rp!=null) {

            try {

                 file = FileHelp.save(mPath,mFileName,rp.bytes());

            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        return file;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(mSaveListener != null){

            mSaveListener.onPreExecute();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if(mSaveListener != null){

            mSaveListener.onProgressUpdate(values[0]);
        }

    }

    @Override
    protected void onCancelled(File file) {
        super.onCancelled(file);


    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);

        if(mSaveListener != null){

            mSaveListener.onEndExecute(file);
        }
    }
}
