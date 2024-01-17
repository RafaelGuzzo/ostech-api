package br.com.ostech.exception;

public class OrderNotFoundException extends RuntimeException{

    private static String NOT_FOUND = "order not found";

    public OrderNotFoundException() {
        super(NOT_FOUND);
    }
}
