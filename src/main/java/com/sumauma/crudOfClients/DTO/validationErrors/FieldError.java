package com.sumauma.crudOfClients.DTO.validationErrors;

public class FieldError {

	private String fieldError;
	private String messageError;
	
	public FieldError(String fieldError, String messageError) {
		super();
		this.fieldError = fieldError;
		this.messageError = messageError;
	}

	public String getFieldError() {
		return fieldError;
	}

	public String getMessageError() {
		return messageError;
	}
			
}
