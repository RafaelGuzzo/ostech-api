package br.com.ostech.exception;

public class RuleException extends RuntimeException{

    private static final long serialVersion = 1L;
    private String field;

    public RuleException(String message){
        super(message);
    }

    public RuleException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
