package br.com.ostech.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

public class Utils {

    public static boolean isNullOrBlank(String value) {
        return value == null || value.isBlank();
    }

    public static String addLike(String value) {
        return "%".concat(value).concat("%");
    }

    public static String convertToBase64(MultipartFile file) {
        try {
            return Base64.getEncoder().encodeToString(file.getBytes());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
