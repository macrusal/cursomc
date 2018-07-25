/**
 * 
 */
package com.hibejix.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import com.hibejix.cursomc.services.exceptions.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hibejix.cursomc.services.exceptions.CategoriaNotFoundException;
import com.hibejix.cursomc.services.exceptions.DataIntegrityException;


/**
 * @author marcelo
 *
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(CategoriaNotFoundException.class)
	public ResponseEntity<StandardError> categoriaNotFound(CategoriaNotFoundException e, HttpServletRequest request) {

		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), 
												e.getMessage(), 
												System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {

		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), 
												e.getMessage(), 
												System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), 
												"Erro de validação", 
												System.currentTimeMillis());
		
		for(FieldError erro: e.getBindingResult().getFieldErrors()) {
			error.addError(erro.getField(), erro.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {

		StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(),
				e.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
}
