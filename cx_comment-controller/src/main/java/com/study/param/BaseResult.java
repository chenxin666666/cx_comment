package com.study.param;


import lombok.Data;

@Data
public class BaseResult<T> {

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 是否成功
     * true:成功
     * false:失败
     */
    private Boolean success;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回具体数据
     */
    private T data;
}
