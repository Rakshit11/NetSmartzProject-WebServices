package com.webservices.restapi.exception;

public class FoodProductNotFound extends RuntimeException {

	private static final long serialVersionUID = -6777060032827320750L;
	private String message;

	public FoodProductNotFound(String message) {
		super(message);
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
