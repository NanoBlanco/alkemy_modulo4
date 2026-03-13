package com.reinaldo.api.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3107648543460052661L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
