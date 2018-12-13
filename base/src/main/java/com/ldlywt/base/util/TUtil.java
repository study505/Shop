package com.ldlywt.base.util;

import android.support.annotation.NonNull;

import java.lang.reflect.ParameterizedType;


public class TUtil {

    public static <T> T getNewInstance(Object object, int i) {
        if(object!=null){
            try {
                return ((Class<T>) ((ParameterizedType) (object.getClass()
                        .getGenericSuperclass())).getActualTypeArguments()[i])
                        .newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }

        }
        return null;

    }

    /**
     * 获得超类的泛型参数的实际类型
     */
    public static <T> T getInstance(Object object, int i) {
        if (object != null) {
            return (T) ((ParameterizedType) object.getClass()
                    .getGenericSuperclass())
                    .getActualTypeArguments()[i];
        }
        return null;

    }

    public static @NonNull <T> T checkNotNull(final T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
