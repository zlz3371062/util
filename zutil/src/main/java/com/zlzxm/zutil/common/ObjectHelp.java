package com.zlzxm.zutil.common;

/**
 * Created by zlz
 * on  2019-09-12
 */
public class ObjectHelp<T> {

    public interface Empty{


        void  isEmpty();

    }


    public interface  NotEmpty<T>{



        void  notEmpty(T t);

    }


    public static <T> T isEmpty(T t,Empty isEmpty){

        if(t == null){

            if(isEmpty!=null){
                isEmpty.isEmpty();
            }
        }

        return null;

    }

    public static <T> T notEmpty(T t,NotEmpty<T> notEmpty){

        if(t != null){

            if(notEmpty!=null){

                notEmpty.notEmpty(t);
            }
        }

        return t;

    }

    public static <T> void isEmpty(T t,Empty empty,NotEmpty<T> notEmpty){

        if(t == null){

            if(empty != null){

                empty.isEmpty();

            }

        }else {

            if(notEmpty != null){

                notEmpty.notEmpty(t);

            }

        }


    }


}
