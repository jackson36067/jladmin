package com.jackson.enumeration;

import lombok.Getter;

@Getter
public enum SysLogType {
    ADD("新增"),
    DELETE("删除"),
    UPDATE("修改"),
    LOGIN("登录"),
    BLOCK("拉黑"),
    PAUSE("暂停"),
    RESUME("恢复");
    private final String value;

    SysLogType(String value) {
        this.value = value;
    }
}
