package com.ldlywt.base.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({Status.LOADING,Status.SUCCESS,Status.FAILURE,Status.ERROR})
@Retention(RetentionPolicy.SOURCE)
public @interface Status {
    int LOADING = 0;
    int SUCCESS = 1;
    int FAILURE = 2;
    int ERROR = 3;
}
