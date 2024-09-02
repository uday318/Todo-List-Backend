package com.todo.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.todo.Entity.GenericResponseEntity;


@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(DataValidationException.class)
	public ResponseEntity<GenericResponseEntity> handleDataValidationException(DataValidationException exception,
			WebRequest request)  {
		
		//ErrorMessage message = new ErrorMessage(412, exception.getMessage());
		
		GenericResponseEntity message = new GenericResponseEntity(412, exception.getMessage());
		
		return new  ResponseEntity<GenericResponseEntity>(message,HttpStatus.PRECONDITION_FAILED);
	}
	
}
