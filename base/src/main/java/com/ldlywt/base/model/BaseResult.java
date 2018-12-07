package com.ldlywt.base.model;


public class BaseResult<T> {

    public static final int RESULT_SUCCESS = 0;

    private int resultCode;

    private String errorMsg;

    private T data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess(){
        return RESULT_SUCCESS == resultCode;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "resultCode=" + resultCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
