package br.com.ostech.exception;

public class OrderNotFoundException extends NotFoundException{

    public OrderNotFoundException() {
        super("Order not found");
    }
}
