package com.study.utils;


import com.study.param.BaseResult;

/**
 * 响应工具类
 */
public class BaseResultUtils {

    /**
     *响应成功
     */
    public static <T> BaseResult<T> generateSuccess(T t){
        BaseResult<T> baseResult = new BaseResult<>();
        //成功响应码为200
        baseResult.setCode(200);
        baseResult.setSuccess(true);
        baseResult.setMsg("操作成功");
        baseResult.setData(t);

        return baseResult;
    }

    /**
     *响应失败
     */
    public static  BaseResult generateFail(Integer code, String msg){
        BaseResult baseResult = new BaseResult<>();
        //成功响应码为200
        baseResult.setCode(code);
        baseResult.setSuccess(false);
        baseResult.setMsg(msg);
        baseResult.setData(null);

        return baseResult;
    }

    /**
     *响应失败
     */
    public static  BaseResult generateFail(String msg){
        BaseResult baseResult = new BaseResult<>();
        //成功响应码为200
        baseResult.setCode(-1);
        baseResult.setSuccess(false);
        baseResult.setMsg(msg);
        baseResult.setData(null);

        return baseResult;
    }


}