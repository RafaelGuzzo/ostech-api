package br.com.ostech.utils;

public class Validation {

    public static boolean isNullOrBlank(String value){
        return value == null || value.isBlank();
    }
}
