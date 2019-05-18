package com.ldlywt.base.bean;

import com.zhouyou.http.model.ApiResult;

/**
 * author : wutao
 * e-mail : wutao@himango.cn
 * time   : 2019/05/17
 * desc   :
 * version: 1.0
 */
public class BaseResult<T> extends ApiResult<T> {

    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE = -1;


    private String errorMsg;
    private int errorCode;

    @Override
    public int getCode() {
        return errorCode;
    }

    @Override
    public void setCode(int code) {
        errorCode = code;
    }

    @Override
    public String getMsg() {
        return errorMsg;
    }

    @Override
    public void setMsg(String msg) {
        errorMsg = msg;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "errorMsg='" + errorMsg + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
