/**
 * 
 */
package com.hibejix.cursomc.services.exceptions;

/**
 * @author marcelo
 *
 */
public class CategoriaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CategoriaNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public CategoriaNotFoundException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
