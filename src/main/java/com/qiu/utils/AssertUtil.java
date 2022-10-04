package com.qiu.utils;

import com.qiu.exceptions.ParamsException;

public class AssertUtil {


    public  static void isTrue(Boolean flag,String msg){
        if(flag){
            throw  new ParamsException(msg);
        }
    }

    public static void stringIsBlank(String data,String msg){
        if (data == null || data == ""){
            throw new ParamsException(msg);
        }
    }

}
