package com.sumauma.crudOfClients.DTO.validationErrors;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ComplementsOfBodyError extends BodyError {

	private List<FieldError> listOfErrors = new ArrayList<>();
	
	public ComplementsOfBodyError(Instant timeStamp, Integer status, String msgError, String pathUri) {
		super(timeStamp, status, msgError, pathUri);
	}

	public List<FieldError> getListOfErrors() {
		return listOfErrors;
	}

	public void addErrors(String fieldError, String messageError) {
		listOfErrors.add(new FieldError(fieldError, messageError));
	}

	
}
