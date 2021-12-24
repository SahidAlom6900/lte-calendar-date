package com.technoelevate.ess_lte.exception;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException {
	public CustomException(String message){
		super(message);
	}
}
