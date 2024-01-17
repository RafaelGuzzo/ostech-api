package br.com.ostech.utils;

public class Utils {

    public static boolean isNullOrBlank(String value) {
        return value == null || value.isBlank();
    }

    public static String addLike(String value) {
        return "%".concat(value).concat("%");
    }
}
