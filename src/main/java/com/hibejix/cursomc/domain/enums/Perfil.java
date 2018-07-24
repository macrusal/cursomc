/**
 * 
 */
package com.hibejix.cursomc.domain.enums;

/**
 * @author marcelo
 *
 */
public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");

	private int codigo;
	private String descricao;

	/**
	 * @param codigo
	 * @param descricao
	 */
	private Perfil(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
	
		for (Perfil x : Perfil.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
	
}
