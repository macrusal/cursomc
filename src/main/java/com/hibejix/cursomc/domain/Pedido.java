/**
 * 
 */
package com.hibejix.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author marcelo
 *
 */
@Entity
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Date instante;

	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name="endereco_entrega_id")
	private Endereco enderecoEntrega;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	@OneToMany(mappedBy="id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();
	
	/**
	 * 
	 */
	public Pedido() {
	}

	/**
	 * @param id
	 * @param instante
	 * @param enderecoEntrega
	 * @param cliente
	 */
	public Pedido(Integer id, Date instante, Endereco enderecoEntrega, Cliente cliente) {
		super();
		this.id = id;
		this.instante = instante;
		this.enderecoEntrega = enderecoEntrega;
		this.cliente = cliente;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the instante
	 */
	public Date getInstante() {
		return instante;
	}

	/**
	 * @param instante the instante to set
	 */
	public void setInstante(Date instante) {
		this.instante = instante;
	}
	
	/**
	 * @return the pagamento
	 */
	public Pagamento getPagamento() {
		return pagamento;
	}

	/**
	 * @param pagamento the pagamento to set
	 */
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	/**
	 * @return the enderecoEntrega
	 */
	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	/**
	 * @param enderecoEntrega the enderecoEntrega to set
	 */
	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the itens
	 */
	public Set<ItemPedido> getItens() {
		return itens;
	}

	/**
	 * @param itens the itens to set
	 */
	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
