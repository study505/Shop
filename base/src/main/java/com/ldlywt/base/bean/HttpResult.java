package com.ldlywt.base.bean;

/**
 * author : wutao
 * e-mail : wutao@himango.cn
 * time   : 2019/05/17
 * desc   :
 * version: 1.0
 */
public class HttpResult {

    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE = -1;


    /**
     * data : ...
     * errorCode : 0
     * errorMsg :
     */

    private String data;
    private int errorCode;
    private String errorMsg;

    public HttpResult( int errorCode,String data) {
        this.data = data;
        this.errorCode = errorCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
