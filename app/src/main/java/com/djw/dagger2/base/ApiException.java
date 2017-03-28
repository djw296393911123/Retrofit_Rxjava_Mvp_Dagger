package com.djw.dagger2.base;

/**
 * Created by JasonDong on 2017/3/23.
 *
 */
public class ApiException extends Exception{
    public ApiException(String msg)
    {
        super(msg);
    }
}