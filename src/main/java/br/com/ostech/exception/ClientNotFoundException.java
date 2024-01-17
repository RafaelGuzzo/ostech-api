package br.com.ostech.exception;

public class ClientNotFoundException extends RuntimeException {
    private static String NOT_FOUND = "client not found";

    public ClientNotFoundException() {
        super(NOT_FOUND);
    }
}
