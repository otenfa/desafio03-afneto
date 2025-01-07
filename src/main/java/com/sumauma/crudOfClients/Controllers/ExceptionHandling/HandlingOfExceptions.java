package com.sumauma.crudOfClients.Controllers.ExceptionHandling;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sumauma.crudOfClients.DTO.validationErrors.BodyError;
import com.sumauma.crudOfClients.DTO.validationErrors.ComplementsOfBodyError;
import com.sumauma.crudOfClients.services.customExceptions.NoSuchElementException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HandlingOfExceptions {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<BodyError> custonName(NoSuchElementException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		BodyError err = new BodyError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BodyError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ComplementsOfBodyError err = new ComplementsOfBodyError(Instant.now(), status.value(), "Campos inválidos", request.getRequestURI());
		for(FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addErrors(f.getField(),f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(err);
	}

}
