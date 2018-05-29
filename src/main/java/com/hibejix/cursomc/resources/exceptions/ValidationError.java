/**
 * 
 */
package com.hibejix.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author msalvador
 *
 */
public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String mensagem, Long timeStamp) {
		super(status, mensagem, timeStamp);
	}

	/**
	 * @return the fields
	 */
	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
}
