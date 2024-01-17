package br.com.ostech.utils;

import br.com.ostech.enuns.OrderStatus;

public class Validation {

    public static  boolean isNullOrBlank(String value){
        return value == null || value.isBlank();
    }

    public static boolean idNull(Long value){
        return value == null;
    }

    public static boolean statusNull(OrderStatus value){
        return value == null;
    }
}
