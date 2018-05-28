/**
 * 
 */
package com.hibejix.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibejix.cursomc.domain.Pedido;
import com.hibejix.cursomc.repositories.PedidoRepository;
import com.hibejix.cursomc.services.exceptions.PedidoNotFoundException;

/**
 * @author msalvador
 *
 */
@Service
public class PedidoService {

	@Autowired
	PedidoRepository repository;
	
	public Pedido buscar(final Integer id) {
		
		Optional<Pedido> pedido = repository.findById(id);
		return pedido.orElseThrow(() -> new PedidoNotFoundException(
				"Pedido não encontrada! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
