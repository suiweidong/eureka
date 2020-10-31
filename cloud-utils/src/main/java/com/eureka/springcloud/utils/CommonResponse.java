package com.eureka.springcloud.utils;

import com.eureka.springcloud.enums.CommonCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 公共返回类
 */
@Data
public class CommonResponse<T> implements Serializable {

    /**
     * 0000成功 1111失败
     */
    private String resCode;

    /**
     * 返回消息
     */
    private String resMsg;

    private T record;

    public CommonResponse(){}

    public CommonResponse(String resCode, String resMsg, T record) {
        this.resCode = resCode;
        this.resMsg = resMsg;
        this.record = record;
    }

    public CommonResponse(String resCode, String resMsg) {
        this.resCode = resCode;
        this.resMsg = resMsg;
    }

    public static CommonResponse success(){
        return new CommonResponse(CommonCodeEnum.SUCCESS.getCode(), CommonCodeEnum.SUCCESS.getName());
    }

    public static CommonResponse success(String resMsg){
        return new CommonResponse(CommonCodeEnum.SUCCESS.getCode(), resMsg);
    }

    public static CommonResponse fail(String resMsg){
        return new CommonResponse(CommonCodeEnum.FAIL.getCode(), resMsg);
    }
}
