package com.todo.Error;

public class DataValidationException extends RuntimeException {
	
	  public DataValidationException(String message) {
	        super(message);
	    }

	    public DataValidationException(String message, Throwable cause) {
	        super(message, cause);
	    }

}


