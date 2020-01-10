package com.zlzxm.zutil.common;

import android.util.Log;

/**
 * Created by zlz
 * on  2019-09-02
 */
public class StringHelp {

    private static final String TAG= "StringHelp";


    public interface Empty{


        void  isEmpty();

    }


    public interface  NotEmpty{


        void  notEmpty(String str);

    }

    public static boolean isEmpty(String str){

        if(str == null){

            return  true;
        }
        if(str.contains("null")){


            return true;
        }

        return  str.isEmpty();
    }


    public static String initWithDefault(String str,String defaultStr){

        return  isEmpty(str)?defaultStr:str;
    }

    public static String initWidthSpace(String str){
        return initWithDefault(str,"");
    }

    public static boolean isEmpty(String... str){

        for (String s:str){

            if(isEmpty(s)){

                return  true;
            }
        }

        return  false;
    }

    public static void isEmpty(String str,Empty empty){

        if(isEmpty(str) && empty != null){

            empty.isEmpty();
        }

    }

    public static void notEmpty(String str,NotEmpty not) {

        if (!isEmpty(str) && not != null) {

            not.notEmpty(str);

        }
    }

    public static void isEmpty(String str,Empty empty,NotEmpty notEmpty){

        if(isEmpty(str) ){

            if(empty!=null) {
                empty.isEmpty();
            }
        }else {

            if(notEmpty!=null){

                notEmpty.notEmpty(str);

            }

        }

    }

}
