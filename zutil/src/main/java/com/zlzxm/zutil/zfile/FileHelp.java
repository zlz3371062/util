package com.zlzxm.zutil.zfile;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zlz
 * on  2019/7/6
 */
public class FileHelp  {

    public static final String CAMERA_PHOTO_DIR =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/Camera/";


    public static final String  PATH_PICTURES =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()+"/zlz/";

    public static final String SDCARD_DIR =
            Environment.getExternalStorageDirectory().getPath();
    private static final String TIME_FORMAT = "_yyyyMMdd_HHmmss";
    public static File save(String strPath,String filename, byte[] content){

        try {

            File path = new File(strPath);

            if(!path.exists()){

                path.mkdir();
            }

            File file = new File(strPath+"/"+filename);

            if(!file.exists()){

                file.createNewFile();
            }

            OutputStream outputStream = new FileOutputStream(file);

            outputStream.write(content);
            outputStream.flush();
            outputStream.close();

            return  file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return  null;
        } catch (IOException e) {
            e.printStackTrace();

            return  null;
        }

    }


    public static  String getFileContent(String filepath){

        String result = null;

        File file = new File(filepath);

        if(file.exists()){

            try {

                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

                String line = "";

                StringBuilder sb= new StringBuilder();

                while ((line = br.readLine())!=null){

                    sb.append(line);
                }

                br.close();

                return sb.toString();

            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return  result;
    }

    public static String getFileNameByTime(String timeFormatHeader, String extension) {
        return getTimeFormatName(timeFormatHeader) + "." + extension;
    }
    private static String getTimeFormatName(String timeFormatHeader) {
        final Date date = new Date(System.currentTimeMillis());
        //必须要加上单引号
        final SimpleDateFormat dateFormat = new SimpleDateFormat("'" + timeFormatHeader + "'" + TIME_FORMAT, Locale.getDefault());
        return dateFormat.format(date);
    }
    public static File createFile(String sdcardDirName, String fileName) {
        return new File(createDir(sdcardDirName), fileName);
    }

    private static File createDir(String sdcardDirName) {
        //拼接成SD卡中完整的dir
        final String dir = SDCARD_DIR + "/" + sdcardDirName + "/";
        final File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return fileDir;
    }

}
