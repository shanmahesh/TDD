package com.person.exception;

public class InvalidPersonException  extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPersonException(String msg) {
		super(msg);
	}
}
