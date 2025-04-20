package com.hexaware.px.exception;

public class PayrollNotFoundException extends Exception {
    
    private static final long serialVersionUID = 1L;

    // Constructor with custom message
    public PayrollNotFoundException(String message) {
        super(message);
    }

    // Constructor with custom message and cause (for exception chaining)
    public PayrollNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
