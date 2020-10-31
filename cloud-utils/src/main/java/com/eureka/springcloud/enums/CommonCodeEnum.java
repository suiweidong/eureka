package com.eureka.springcloud.enums;

/**
 * 通用返回码枚举类
 */
public enum CommonCodeEnum {

    SUCCESS("0000", "成功"),
    FAIL("1111", "失败");

    private CommonCodeEnum(String code, String name) {
        this.name = name;
        this.code = code;
    }

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
