/**
 * 
 */
package com.hibejix.cursomc.services.exceptions;

/**
 * @author marcelo
 *
 */
public class PedidoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public PedidoNotFoundException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
