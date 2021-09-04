package com.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

  @ExceptionHandler({InvalidPersonException.class})
  public ResponseEntity<String> resourceNotFoundException(InvalidPersonException ex) {
	  
	    
	    return new ResponseEntity<String>("Invalid Person, validation failed", HttpStatus.BAD_REQUEST);
	  }
}
