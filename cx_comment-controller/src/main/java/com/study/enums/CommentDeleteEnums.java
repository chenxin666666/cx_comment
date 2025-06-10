package com.study.enums;

/**
 * 评论的删除状态
 */
public enum CommentDeleteEnums {
    NORMAL(0,"正常"),
    DELETED(1,"已删除")
    ;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    int code;
    String msg;

    CommentDeleteEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
