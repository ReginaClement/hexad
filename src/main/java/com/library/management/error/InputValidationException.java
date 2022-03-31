package com.library.management.error;

public class InputValidationException  extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InputValidationException(String message) {
        super(message);
    }
}
