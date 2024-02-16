package br.com.ostech.exception;

public class ClientNotFoundException extends NotFoundException {

    public ClientNotFoundException() {
        super("Client not found");
    }
}
