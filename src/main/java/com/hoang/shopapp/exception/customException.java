package com.hoang.shopapp.exception;


public class customException extends RuntimeException{
    private final String field;

    public customException(String field, String message) {
        super(message); // message sẽ lấy bằng e.getMessage()
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
